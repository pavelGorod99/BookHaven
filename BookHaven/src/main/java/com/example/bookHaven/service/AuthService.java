package com.example.bookHaven.service;


import com.example.bookHaven.dto.*;

public interface AuthService {

    LogInResponseDTO login(LoginRequestDTO loginRequest) throws Exception;
//    LogInResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);
}
