package com.example.shopping.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.repository.UserRepository;

/**
 * 旧式ログイン処理を扱うコントローラー
 * （Spring Security 未使用の例）
 */
@Controller
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ログインフォームを表示する
     *
     * @return login.html
     */
    public String loginForm() {
        return "login";
    }

    /**
     * ユーザーの認証処理を行う
     *
     * @param username ユーザー名
     * @param password パスワード
     * @param session HTTPセッション
     * @param model モデルオブジェクト
     * @return ログイン成功時はトップページ、失敗時は login.html
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            // session.setAttribute("USER", userOpt.get());
            return "redirect:/";
        }
        model.addAttribute("error", "ユーザー名またはパスワードが違います");
        return "login";
    }
}