package com.boilerplate.xotomicro_back_auth_service.service.downstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boilerplate.xotomicro_back_auth_service.dto.UserDto;

@Component
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${downstream.xotomicro-back-user-service.search-user}")
    private String searchUserUrl;

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto searchUser(String username) {
        var url = String.format(searchUserUrl, username);
        try {
            var response = restTemplate.getForEntity(url, UserDto.class);
            return response.getBody();
        } catch (Exception ex) {
            logger.warn("Error: {}", ex.getMessage());
            return null;
        }
    }
}
