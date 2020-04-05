package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.dtos.UserDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.models.ChangePasswordModel;

public interface AccountService  {
    void create(UserDto userDto) throws BaseException;
    UserDto read(UUID id) throws BaseException;
    void update(UserDto userDto) throws BaseException;
    void delete(UUID id) throws BaseException;
    void changePassword(ChangePasswordModel changePasswordModel);
}
