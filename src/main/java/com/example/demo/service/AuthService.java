package com.example.demo.service;

import com.example.demo.web.dto.auth.JwtRequest;
import com.example.demo.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
