package com.awbd.restaurantreview.models;

import java.util.UUID;

public class SignIn {
    private String email;
    private String password;
    private UUID id;

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
