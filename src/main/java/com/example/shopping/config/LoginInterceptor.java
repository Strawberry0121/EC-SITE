package com.example.shopping.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ログイン状態をチェックするインターセプターです。
 * ユーザーがログインしていない場合は、ログインページ（/login）にリダイレクトします。
 * このインターセプターは、Spring MVC のハンドラーが呼び出される前に処理されます。
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * ハンドラーが実行される前に呼び出されます。
     * 
     * セッションに "USER" 属性が存在する場合はリクエストを継続し、
     * 存在しない場合は /login にリダイレクトします。
     *
     * @param request  HTTPリクエスト
     * @param response HTTPレスポンス
     * @param handler  ハンドラーオブジェクト
     * @return ユーザーがログインしている場合は {@code true}、それ以外の場合は {@code false}
     * @throws Exception リダイレクト処理中に発生する可能性のある例外
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("USER") != null){
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}