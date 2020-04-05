package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.dtos.UserDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.InvalidInputException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.models.ChangePasswordModel;
import com.awbd.restaurantreview.repositories.UserRepository;
import com.awbd.restaurantreview.mappers.UserMapper;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper mapper;
    private final ResourceLoader resourceLoader;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper mapper, ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void create(UserDto userDto) throws BaseException {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new NotFoundException(userDto.getEmail());
        }

        User user = mapper.mapDtoToEntity(userDto);
        if(user.getProfilePicture() == null) {
            user.setProfilePicture(defaultProfilePicture());
        }
        user.setPasswordHash(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDto read(UUID id) throws BaseException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(id);
        }

        UserDto userDto = mapper.mapEntityToDto(optionalUser.get());
        return userDto;
    }

    @Override
    public void update(UserDto userDto) throws BaseException {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(userDto.getId());
        }

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getProfilePicture() != null) {
            try {
                user.setProfilePicture(userDto.getProfilePicture().getBytes());
            } catch(Exception e) {
                logger.info("Failed to update profile picture. Using default profile picture.");
                user.setProfilePicture(defaultProfilePicture());
            }
        } else {
            user.setProfilePicture(defaultProfilePicture());
        }

        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) throws BaseException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(ChangePasswordModel changePasswordModel) throws BaseException {
        Optional<User> optionalUser = userRepository.findById(changePasswordModel.getUserId());
        if (!optionalUser.isPresent()) {
            throw new NotFoundException(changePasswordModel.getUserId());
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(changePasswordModel.getCurrentPassword(), user.getPasswordHash())){
            throw new InvalidInputException("current password");
        }

        String newPasswordHash = bCryptPasswordEncoder.encode(changePasswordModel.getNewPassword());
        user.setPasswordHash(newPasswordHash);

        userRepository.save(user);
    }

    private byte[] defaultProfilePicture() {
        byte[] defaultProfilePicture = null;

        try {
            Resource resource = resourceLoader.getResource("classpath:profile_picture_default.png");
            defaultProfilePicture = resource.getInputStream().readAllBytes();
        } catch (Exception e) {
            logger.info(String.format("Failed to read default profile picture. Message: %s", e.getMessage()));
        }

        return defaultProfilePicture;
    }
}
