package com.aridi.springbootstarter.services;

import com.aridi.springbootstarter.dto.UserCreateEditDto;
import com.aridi.springbootstarter.dto.UserReadDto;
import com.aridi.springbootstarter.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private static final Integer USER_ID = 1;
    private  UserCreateEditDto USER_EXAMPLE =
            new UserCreateEditDto("test", "test");

    @Test
    void shouldFindAllUsers() {
        List<UserReadDto> users = userService.findAll();
        Assertions.assertThat(users).hasSize(5);
    }

    @Test
    void shouldFindUserById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_ID);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(userReadDto ->
                assertEquals("aaa", userReadDto.getFirstname()));
    }

    @Test
    void shouldCreateUser() {
        UserReadDto actualResult = userService.create(USER_EXAMPLE);
        assertEquals(USER_EXAMPLE.getFirstname(), actualResult.getFirstname());
        assertEquals(USER_EXAMPLE.getLastname(), actualResult.getLastname());
    }

    @Test
    void shouldUpdateUser() {
        Optional<UserReadDto> actualResult = userService.update(USER_ID, USER_EXAMPLE);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(userReadDto -> {
            assertEquals(userReadDto.getFirstname(), USER_EXAMPLE.getFirstname());
            assertEquals(userReadDto.getLastname(), USER_EXAMPLE.getLastname());
        });
    }

    @Test
    void shouldDeleteUser() {
        boolean delete = userService.delete(USER_ID);
        assertTrue(delete);
    }
}























