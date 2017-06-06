package com.example.axel.chess;

/**
 * Created by Axel on 2017-06-01.
 */

public class Player {
    public String getUsername() {
        return username;
    }

    private String username;

    public String getPassword() {
        return password;
    }

    private String password;
    public Player(String usernameInput, String passwordInput){
        username = usernameInput;
        password = passwordInput;
    }
}
