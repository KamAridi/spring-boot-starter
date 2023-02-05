package com.aridi.springbootstarter.repositories;

import com.aridi.springbootstarter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
