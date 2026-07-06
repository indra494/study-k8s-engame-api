package com.usb.engame.api.service;

import com.usb.engame.api.domain.User;
import com.usb.engame.api.dto.AuthResponse;
import com.usb.engame.api.dto.LoginRequest;
import com.usb.engame.api.dto.RegisterRequest;
import com.usb.engame.api.repository.UserRepository;
import com.usb.engame.api.security.JwtService;
import com.usb.engame.api.security.UnauthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        User user = new User(request.username(), passwordEncoder.encode(request.password()), request.nickname());
        userRepository.save(user);
        String token = jwtService.generateToken(user.getId());
        return new AuthResponse(token, user.getId(), user.getNickname());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorizedException("아이디 또는 비밀번호가 올바르지 않습니다."));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new UnauthorizedException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        String token = jwtService.generateToken(user.getId());
        return new AuthResponse(token, user.getId(), user.getNickname());
    }
}
