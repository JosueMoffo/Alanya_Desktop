package com.example.alanyadesktop.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor

public class AuthRequest {
    private String phoneNumber;
    private String password;
    private String userName; // Uniquement pour l'inscription



    // Constructeurs
    public AuthRequest() {}

    public AuthRequest(String phoneNumber, String password) { // pour le login
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public AuthRequest(String phoneNumber, String password, String userName) { //pour le sign up
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
    }


}