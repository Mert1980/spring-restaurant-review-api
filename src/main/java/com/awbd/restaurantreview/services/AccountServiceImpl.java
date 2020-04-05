package com.awbd.restaurantreview.services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.awbd.restaurantreview.dtos.UserDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.models.ChangePasswordModel;
import com.awbd.restaurantreview.repositories.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(UserDto userDto) throws BaseException {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDto read(UUID id) throws BaseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(UserDto userDto) throws BaseException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(UUID id) throws BaseException {
        // TODO Auto-generated method stub

    }

    @Override
    public void changePassword(ChangePasswordModel changePasswordModel) {
        // TODO Auto-generated method stub

    }
}
