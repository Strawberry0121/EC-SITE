package com.example.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopping.model.Order;
import com.example.shopping.model.User;
import com.example.shopping.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    // 注文を保存
    public Order save(Order order) {
        return repo.save(order);
    }

    // ユーザーごとの注文一覧を取得
    public List<Order> findByUser(User user) {
        return repo.findByUser(user);
    }
}