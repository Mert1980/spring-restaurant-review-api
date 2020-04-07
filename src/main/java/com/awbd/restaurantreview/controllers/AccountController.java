package com.awbd.restaurantreview.controllers;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
import com.awbd.restaurantreview.models.JsonWebTokenModel;
import com.awbd.restaurantreview.models.RefreshTokenModel;
import com.awbd.restaurantreview.security.RefreshTokenHandler;
import com.awbd.restaurantreview.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;
    private RefreshTokenHandler refreshTokenHandler;

    @Autowired
    public AccountController(AccountService accountService, RefreshTokenHandler refreshTokenHandler) {
        this.accountService = accountService;
        this.refreshTokenHandler = refreshTokenHandler;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@ModelAttribute @Valid UserRequestDto userDto) throws BaseException {
        accountService.create(userDto);
        return ResponseEntity.noContent().build();
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

    @GetMapping(value = "/me")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<UserResponseDto> read(Authentication authentication) throws BaseException {
        return ResponseEntity.ok(accountService.read(authentication.getName()));
    }

    @PostMapping(value="/password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordModel changePasswordModel) throws BaseException{
        accountService.changePassword(changePasswordModel);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/{userId}/revoke/{refreshToken}")
    public ResponseEntity<?> revoke(@PathVariable("userId") UUID userId, @PathVariable("refreshToken") String refreshToken ) throws BaseException {
        refreshTokenHandler.revoke(userId, refreshToken);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/refresh/{refreshToken}")
    public ResponseEntity<JsonWebTokenModel> refresh(@PathVariable("refreshToken") String refreshToken, HttpServletResponse response) throws BaseException {
        RefreshTokenModel model = refreshTokenHandler.createAccessToken(refreshToken);
        Cookie cookie = new Cookie("refresh-token", model.getRefreshToken());
        response.addCookie(cookie);
        return ResponseEntity.ok(new JsonWebTokenModel(model.getToken(), model.getExpires()));
    }

}
