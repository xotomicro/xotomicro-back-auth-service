package com.boilerplate.authservice.controller;

import com.boilerplate.authservice.dto.AuthDto;
import com.boilerplate.authservice.dto.TokenDto;
import com.boilerplate.authservice.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
