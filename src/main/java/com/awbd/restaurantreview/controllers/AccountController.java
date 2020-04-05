package com.awbd.restaurantreview.controllers;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awbd.restaurantreview.dtos.request.UserRequestDto;
import com.awbd.restaurantreview.dtos.response.UserResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.models.ChangePasswordModel;
import com.awbd.restaurantreview.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@ModelAttribute @Valid UserRequestDto userDto) throws BaseException {
        accountService.create(userDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable("id") UUID id) throws BaseException {
        return ResponseEntity.ok(accountService.read(id));
    }

    @PutMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> update(@ModelAttribute @Valid UserRequestDto userDto) throws BaseException {
        accountService.update(userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws BaseException {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordModel changePasswordModel) throws BaseException{
        accountService.changePassword(changePasswordModel);
        return ResponseEntity.noContent().build();
    }

}
