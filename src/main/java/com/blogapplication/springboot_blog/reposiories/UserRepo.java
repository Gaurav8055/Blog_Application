package com.blogapplication.springboot_blog.reposiories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.springboot_blog.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
