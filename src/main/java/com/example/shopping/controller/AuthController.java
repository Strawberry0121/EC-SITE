package com.example.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.shopping.repository.UserRepository;
import com.example.shopping.model.User;
import com.example.shopping.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.repository.UserRepository;

/**
 * 認証・登録を担当するコントローラー
 * ログインページとユーザー登録機能を提供する
 */
@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        try{
        userService.registerUser(user);
            
        } catch () {
            return "cart";
        }
        return "redirect:/login";
    }
}