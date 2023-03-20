package com.boilerplate.xotomicro_back_auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
    private Long expire;
    private String scope;
}
