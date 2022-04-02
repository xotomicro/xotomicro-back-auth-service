package com.boilerplate.authservice.service.downstream;

import com.boilerplate.authservice.dto.AuthDto;
import com.boilerplate.authservice.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(userService, "searchUserUrl", "http://userservice/users/search?username=%s");
    }

    @DisplayName("Search User")
    @Test
    void searchUser() {
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
        var url = String.format("http://userservice/users/search?username=%s", authDtoList.get(0).getUsername());

        when(restTemplate.getForEntity(url, UserDto.class)).thenReturn(ResponseEntity.ok(userList.get(0)));

        UserDto userDto = userService.searchUser(authDtoList.get(0).getUsername());
        assertNotNull(userDto);

        userService.searchUser(null);
    }

}
