package com.example.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.model.User;

/**
 * ユーザー(User)に関するデータ操作を提供するリポジトリ
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * ユーザー名で検索する
     *
     * @param username ユーザー名
     * @return User の Optional
     */
    Optional<User> findByUsername(String username);
}