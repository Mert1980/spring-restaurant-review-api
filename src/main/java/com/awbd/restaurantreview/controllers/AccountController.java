package com.awbd.restaurantreview.controllers;

import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.awbd.restaurantreview.security.CookieHandler;
import com.awbd.restaurantreview.security.RefreshTokenHandler;
import com.awbd.restaurantreview.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final RefreshTokenHandler refreshTokenHandler;
    private final CookieHandler cookieHandler;

    @Autowired
    public AccountController(AccountService accountService, RefreshTokenHandler refreshTokenHandler, CookieHandler cookieHandler) {
        this.accountService = accountService;
        this.refreshTokenHandler = refreshTokenHandler;
        this.cookieHandler = cookieHandler;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@ModelAttribute @Valid UserRequestDto userDto) throws BaseException {
        accountService.create(userDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> update(@ModelAttribute @Valid UserRequestDto userDto) throws BaseException {
        accountService.update(userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws BaseException {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> read(Authentication authentication) throws BaseException {
        return ResponseEntity.ok(accountService.read(authentication.getName()));
    }

    @PostMapping(value = "/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordModel changePasswordModel) throws BaseException{
        accountService.changePassword(changePasswordModel);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/refresh")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<JsonWebTokenModel> refresh(@CookieValue(CookieHandler.REFRESH_TOKEN_COOKIE_NAME) String refreshToken,
                                                     HttpServletResponse response) throws BaseException {
        RefreshTokenModel model = refreshTokenHandler.createAccessToken(refreshToken);
        response.addCookie(cookieHandler.create(model.getRefreshToken()));

        return ResponseEntity.ok(new JsonWebTokenModel(model.getToken(), model.getExpires()));
    }

    @PostMapping(value = "/refresh-token/revoke")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> revoke(@CookieValue(CookieHandler.REFRESH_TOKEN_COOKIE_NAME) String refreshToken,
                                    Authentication authentication) throws BaseException {
        UserResponseDto userResponseDto = accountService.read(authentication.getName());
        refreshTokenHandler.revoke(userResponseDto.getId(), refreshToken);

        return ResponseEntity.noContent().build();
    }
}
