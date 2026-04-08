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

            // H2コンソール用
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            // 全許可（自作ログイン使うため）
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().permitAll()
            )

            // ❌ Spring Securityのログイン機能を完全無効化
            .formLogin(form -> form.disable())

            // ログアウトも無効（必要なら後で実装）
            .logout(logout -> logout.disable());

        return http.build();
    }
}