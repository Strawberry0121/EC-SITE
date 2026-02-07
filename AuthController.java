package com.example.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.shopping.model.User;
import com.example.shopping.service.UserService;

/**
 * 認証・登録を担当するコントローラー
 * ログインページとユーザー登録機能を提供する
 */
@Controller
public class AuthController {

    private final UserService userService;

    /**
     * コンストラクタ注入
     *
     * @param userService ユーザーサービス
     */
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * ログインページを表示する
     *
     * @return login.html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * ユーザー登録フォームを表示する
     *
     * @param user ユーザーオブジェクト（フォームバインディング用）
     * @return register.html
     */
    @GetMapping("/register")
    public String showRegisterForm(User user) {
        return "register";
    }

    /**
     * ユーザーを登録する
     *
     * @param user 登録するユーザー
     * @return ログインページへリダイレクト
     */
    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}