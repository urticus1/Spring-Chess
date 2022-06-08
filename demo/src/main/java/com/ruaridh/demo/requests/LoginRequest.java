package com.ruaridh.demo.requests;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String _username;
    private String _password;

    public String getPassword() {
        return _password;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public void setUsername(String username) {
        _username = username;
    }
}
