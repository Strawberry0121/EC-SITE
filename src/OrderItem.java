package com.example.shopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 注文内の1商品を表すエンティティ
 */
@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private double price;
    private int quantity;

    public OrderItem() {}

    /**
     * OrderItem のコンストラクタ
     *
     * @param productId 商品ID
     * @param productName 商品名
     * @param price 価格
     * @param quantity 数量
     */
    public OrderItem(Long productId, String productName, double price, int quantity) {
        this.productId = productId; 
        this.productName = productName; 
        this.price = price; 
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}