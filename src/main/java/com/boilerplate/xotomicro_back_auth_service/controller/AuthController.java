package com.boilerplate.xotomicro_back_auth_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boilerplate.xotomicro_back_auth_service.dto.AuthDto;
import com.boilerplate.xotomicro_back_auth_service.dto.TokenDto;
import com.boilerplate.xotomicro_back_auth_service.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseBody
    @PostMapping("/login")
    public TokenDto authLogin(@RequestBody AuthDto auth) {
        return authService.authLogin(auth);
    }

    // @ResponseBody
    // @PostMapping("/logout")
    // public void authLogout() {
    //     authService.authLogout();
    // }
}
