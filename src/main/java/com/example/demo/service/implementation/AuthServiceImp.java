package com.example.demo.service.implementation;

import com.example.demo.service.AuthService;
import com.example.demo.web.dto.auth.JwtRequest;
import com.example.demo.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
