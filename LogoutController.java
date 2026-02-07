package com.example.shopping.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログアウト処理を担当するコントローラー
 */
@Controller
public class LogoutController {

    /**
     * セッションを無効化してログアウトする
     *
     * @param session HTTPセッション
     * @return ログインページへリダイレクト
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}