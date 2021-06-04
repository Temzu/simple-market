package com.temzu.market.controllers;

import com.temzu.market.configs.jwt.JwtProvider;
import com.temzu.market.model.dtos.AuthRequestDto;
import com.temzu.market.model.dtos.AuthResponseDto;
import com.temzu.market.model.dtos.SignUpRequestDto;
import com.temzu.market.model.entities.User;
import com.temzu.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(signUpRequest.getPassword());
        userService.saveUser(user);
        return new ResponseEntity<>("user registered", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponseDto(token);
    }

}
