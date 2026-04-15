package com.example.shopping.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopping.model.User;
import com.example.shopping.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   public UserService(UserRepository userRepository,
                   PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

    public void registerUser(User user) {
        //パスワードを暗号化
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

      Exception ex = new Exception(encodedPassword);
 throw ex;
        userRepository.save(user);
    }
}
