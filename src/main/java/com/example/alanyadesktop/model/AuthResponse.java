package com.example.alanyadesktop.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {
    private String accessToken;
    private int userId;

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public int getUserId() { return userId; } // <-- Ce getter est nécessaire
    public void setUserId(int userId) { this.userId = userId; }

}