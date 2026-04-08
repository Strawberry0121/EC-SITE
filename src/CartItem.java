package com.example.shopping.dto;

import com.example.shopping.model.Product;

/**
 * カート内の1商品を表すDTO
 */
public class CartItem {
    private Long productId;
    private String name;
    private double price;
    private int quantity;

    public CartItem() {}

    /**
     * Product から CartItem を作成するコンストラクタ
     *
     * @param p Product オブジェクト
     * @param qty 数量
     */
    public CartItem(Product p, int qty) {
        this.productId = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.quantity = qty;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * 商品ごとの小計を計算する
     *
     * @return price * quantity
     */
    public double getSubtotal() { return price * quantity; }
}