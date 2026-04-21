package com.example.shopping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ユーザーを表すエンティティ
 */
@Entity
@Table(name = "users") 
public class User {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "ユーザー名は必須です")
    @Size(min = 3, max = 20, message = "3〜20文字で入力してください")
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 6, message = "6文字以上で入力してください")
    @Column(nullable = false)
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}