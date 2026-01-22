package com.vaxify.app.service;

import com.vaxify.app.dtos.AuthResponse;
import com.vaxify.app.dtos.LoginRequest;
import com.vaxify.app.dtos.SignupRequest;

public interface UserService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
