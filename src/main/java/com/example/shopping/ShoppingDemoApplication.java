package com.example.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot アプリケーションのエントリーポイント
 * <p>
 * このクラスからアプリケーションが起動する
 */
@SpringBootApplication
public class ShoppingDemoApplication {

    /**
     * アプリケーションを起動する
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(ShoppingDemoApplication.class, args);
    }
}