package com.boilerplate.xotomicro_back_auth_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.boilerplate.xotomicro_back_auth_service.dto.AuthDto;
import com.boilerplate.xotomicro_back_auth_service.dto.UserDto;
import com.boilerplate.xotomicro_back_auth_service.exception.ServiceException;
import com.boilerplate.xotomicro_back_auth_service.service.downstream.UserService;

@ExtendWith(SpringExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(authService, "tokenExpireTime", 1L);
        ReflectionTestUtils.setField(authService, "secretKey", "1jcryR3Lsf");
        ReflectionTestUtils.setField(authService, "issuer", "issuer");
    }

    @DisplayName("Auth Exception")
    @Test
    void authLoginException() {
        List<UserDto> userList = new ArrayList();
        List<AuthDto> authDtoList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            UserDto user = new UserDto();
            user.setId((long) i);
            user.setFullName("My name " + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            user.setScope("Scope " + i);
            userList.add(user);

            AuthDto authDto = new AuthDto();
            authDto.setUsername("Username" + i);
            authDto.setPassword("Password" + i);
            authDtoList.add(authDto);
        }

        ServiceException usernamePasswordNotMatch = assertThrows(ServiceException.class, () -> { authService.authLogin(authDtoList.get(0)); });
        var errorMessage = "Username or password is not match.";
        assertEquals(errorMessage, usernamePasswordNotMatch.getMessage());

        ServiceException usernameNotProvided = assertThrows(ServiceException.class, () -> {
            authDtoList.get(0).setUsername(null);
            authService.authLogin(authDtoList.get(0));
        });

        ServiceException passwordNotProvided = assertThrows(ServiceException.class, () -> {
            authDtoList.get(0).setPassword(null);
            authService.authLogin(authDtoList.get(0));
        });
        var errorMessage2 = "Username and password must be provided.";
        assertEquals(errorMessage2, usernameNotProvided.getMessage());
        assertEquals(errorMessage2, passwordNotProvided.getMessage());
    }

    @DisplayName("Auth Exception")
    @Test
    void authLoginException2() {
        List<UserDto> userList = new ArrayList();
        List<AuthDto> authDtoList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            UserDto user = new UserDto();
            user.setId((long) i);
            user.setFullName("My name " + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            user.setScope("Scope " + i);
            userList.add(user);

            AuthDto authDto = new AuthDto();
            authDto.setUsername("Username" + i);
            authDto.setPassword("Password" + i);
            authDtoList.add(authDto);
        }
        var errorMessage = "Username or password is not match.";

        when(userService.searchUser(authDtoList.get(0).getUsername())).thenReturn(userList.get(1));

        ServiceException passwordNotMatch = assertThrows(ServiceException.class, () -> { authService.authLogin(authDtoList.get(0)); });
        assertEquals(errorMessage, passwordNotMatch.getMessage());
    }

    @DisplayName("User Not Found")
    @Test
    void userNotFound() {
        List<UserDto> userList = new ArrayList();
        List<AuthDto> authDtoList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            UserDto user = new UserDto();
            user.setId((long) i);
            user.setFullName("My name " + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            user.setScope("Scope " + i);
            userList.add(user);

            AuthDto authDto = new AuthDto();
            authDto.setUsername("Username" + i);
            authDto.setPassword("Password" + i);
            authDtoList.add(authDto);
        }
        var errorMessage = "Username or password is not match.";

        when(userService.searchUser(authDtoList.get(1).getUsername())).thenThrow(new ServiceException(errorMessage));

        ServiceException userNotFound = assertThrows(ServiceException.class, () -> { authService.authLogin(authDtoList.get(0)); });
        assertEquals(errorMessage, userNotFound.getMessage());
    }

    @DisplayName("Auth Login")
    @Test
    void authLogin() {
        List<UserDto> userList = new ArrayList();
        List<AuthDto> authDtoList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            UserDto user = new UserDto();
            user.setId((long) i);
            user.setFullName("My name " + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            user.setScope("Scope " + i);
            userList.add(user);

            AuthDto authDto = new AuthDto();
            authDto.setUsername("Username" + i);
            authDto.setPassword("Password" + i);
            authDtoList.add(authDto);
        }
        when(userService.searchUser(authDtoList.get(0).getUsername())).thenReturn(userList.get(0));

        authService.authLogin(authDtoList.get(0));
    }
}
