package com.example.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.model.Order;
import com.example.shopping.model.User;

/**
 * 注文(Order)に関するデータ操作を提供するリポジトリ
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * 指定したユーザーの注文一覧を取得する
     *
     * @param user ユーザー
     * @return 注文リスト
     */
    List<Order> findByUser(User user);
}