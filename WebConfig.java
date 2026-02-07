package com.example.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web アプリケーションの設定クラス
 * Interceptor の登録を行う
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Interceptor を登録する
     * LoginInterceptor を /cart/checkout に適用し、
     * /login と /css/** は除外する
     *
     * @param registry InterceptorRegistry オブジェクト
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/login")
                .excludePathPatterns( "/css/**");
    }
}