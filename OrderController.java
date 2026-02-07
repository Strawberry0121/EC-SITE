package com.example.shopping.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shopping.model.User;
import com.example.shopping.service.OrderService;

/**
 * 注文履歴を表示するコントローラー
 */
@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * ログインユーザーの注文履歴を表示する
     *
     * @param session HTTPセッション
     * @param model モデルオブジェクト
     * @return orders.html またはログインページへリダイレクト
     */
    @GetMapping("/orders")
    public String myOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("USER");
        if (user == null) return "redirect:/login";

        var orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "orders";
    }
}