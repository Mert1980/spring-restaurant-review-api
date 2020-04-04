package com.awbd.restaurantreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.awbd.restaurantreview.models.SignIn;
import com.awbd.restaurantreview.models.SignUp;
import com.awbd.restaurantreview.services.IdentityService;
import com.awbd.restaurantreview.models.ChangePassword;
import com.awbd.restaurantreview.models.JsonWebToken;

@RestController
@RequestMapping("/auth")
public class IdentityController {

    private IdentityService identityService;

    @Autowired
    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUp signUpModel) {
        identityService.signUp(signUpModel.getFirstName(),signUpModel.getLastName(), signUpModel.getEmail(), signUpModel.getPassword());

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignIn signInModel) {
        JsonWebToken jsonWebToken = identityService.signIn(signInModel.getEmail(), signInModel.getPassword());

        return ResponseEntity.ok().body(jsonWebToken);
    }
}
