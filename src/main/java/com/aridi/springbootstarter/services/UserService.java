package com.aridi.springbootstarter.services;

import com.aridi.springbootstarter.dto.UserCreateEditDto;
import com.aridi.springbootstarter.dto.UserReadDto;
import com.aridi.springbootstarter.mapper.UserCreateEditMapper;
import com.aridi.springbootstarter.mapper.UserReadMapper;
import com.aridi.springbootstarter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> userReadMapper.map(userEntity))
                .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userEntity -> userReadMapper.map(userEntity));
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(userDto -> userCreateEditMapper.map(userDto))
                .map(userEntity -> userRepository.saveAndFlush(userEntity))
                .map(userEntity -> userReadMapper.map(userEntity))
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(userEntity -> userCreateEditMapper.map(userCreateEditDto, userEntity))
                .map(userEntity -> userRepository.saveAndFlush(userEntity))
                .map(userEntity -> userReadMapper.map(userEntity));
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userRepository.delete(userEntity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}

