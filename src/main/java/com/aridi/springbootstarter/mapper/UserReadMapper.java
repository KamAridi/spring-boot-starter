package com.aridi.springbootstarter.mapper;

import com.aridi.springbootstarter.dto.UserReadDto;
import com.aridi.springbootstarter.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<UserEntity, UserReadDto> {
    @Override
    public UserReadDto map(UserEntity object) {
        return new UserReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname()
        );
    }
}
