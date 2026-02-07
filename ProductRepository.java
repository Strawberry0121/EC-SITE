package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.model.Product;

/**
 * 商品(Product)に関するデータ操作を提供するリポジトリ
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}