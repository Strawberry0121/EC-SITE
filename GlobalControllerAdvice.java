package com.example.shopping.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.shopping.dto.CartItem;

/**
 * 全体のコントローラーに共通する設定を提供する
 * カート内の合計数量を全ページで利用可能にする
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * カート内の合計数量を取得する
     *
     * @param session HTTPセッション
     * @return カート内の合計数量
     */
    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("CART");

        if (cart == null) return 0;

        return cart.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}