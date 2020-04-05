package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.dtos.request.UserRequestDto;
import com.awbd.restaurantreview.dtos.response.UserResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.models.ChangePasswordModel;

public interface AccountService  {
    void create(UserRequestDto userDto) throws BaseException;
    UserResponseDto read(String email) throws BaseException;
    void update(UserRequestDto userDto) throws BaseException;
    void delete(UUID id) throws BaseException;
    void changePassword(ChangePasswordModel changePasswordModel) throws BaseException;
}
