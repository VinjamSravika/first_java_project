package com.example.authservices.model.user;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}