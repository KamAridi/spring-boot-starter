package com.aridi.springbootstarter.services;

import com.aridi.springbootstarter.entities.UserEntity;
import com.aridi.springbootstarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createOrUpdate(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

