package com.meteo.security;

public class LoginRes {
    private String email;
    private String token;

    private String role;

    public LoginRes(String email, String token,String role) {
        this.email = email;
        this.token = token;
        this.role= role;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}