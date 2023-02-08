package com.aridi.springbootstarter.mapper;

import com.aridi.springbootstarter.dto.UserCreateEditDto;
import com.aridi.springbootstarter.entities.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, UserEntity> {
    @Override
    public UserEntity map(UserCreateEditDto object) {
        UserEntity userEntity = new UserEntity();
        copy(object, userEntity);

        return userEntity;
    }

    @Override
    public UserEntity map(UserCreateEditDto fromObject, UserEntity toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private static void copy(UserCreateEditDto object, UserEntity userEntity) {
        userEntity.setFirstname(object.getFirstname());
        userEntity.setLastname(object.getLastname());
    }
}
