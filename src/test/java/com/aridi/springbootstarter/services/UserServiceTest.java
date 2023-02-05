package com.aridi.springbootstarter.services;

import com.aridi.springbootstarter.entities.UserEntity;
import com.aridi.springbootstarter.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private final UserEntity USER_STUB = UserEntity
            .builder()
            .firstname("bbb")
            .lastname("bbb")
            .build();

    @Test
    void shouldCreateUsersByEntity() {
        UserEntity returnedEntity = userService.createOrUpdate(USER_STUB);
        List<UserEntity> users = userService.findAll();
        Assertions.assertEquals(USER_STUB, returnedEntity);
        Assertions.assertTrue(users.contains(returnedEntity));
    }

    @Test
    void shouldFindAllUsersListAndNotEmpty() {
        List<UserEntity> users = userService.findAll();
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void shouldFindUserByUserId() {
        UserEntity user = userService.findById(1);
        Assertions.assertEquals(user.getId(), 1);
    }

    @Test
    void shouldDeleteUser(){
        userService.delete(3);
        Assertions.assertNull(userService.findById(3));
    }
}