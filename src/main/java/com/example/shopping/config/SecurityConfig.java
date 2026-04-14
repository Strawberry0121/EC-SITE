package com.example.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // CSRF無効（開発用）
            .csrf(csrf -> csrf.disable())

            // 全許可（自作ログイン使うため）
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            )

            // Spring Securityのログイン機能を無効
            .formLogin(form -> form.disable())

            // ログアウトも無効
            .logout(logout -> logout.disable());

        return http.build();
    }
}