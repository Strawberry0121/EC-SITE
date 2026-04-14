package com.example.shopping.service;

import org.springframework.stereotype.Service;

import com.example.shopping.model.User;
import com.example.shopping.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        //パスワードを暗号化
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}