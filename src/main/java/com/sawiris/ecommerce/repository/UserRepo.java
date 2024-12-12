package com.sawiris.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findUserByEmail(String email);
}
