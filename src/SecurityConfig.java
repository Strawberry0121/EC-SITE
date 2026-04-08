package com.example.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security の設定クラス
 * パスワードエンコーダーと HTTP セキュリティを設定する
 */
@Configuration
public class SecurityConfig {

	/**
	 * パスワードをハッシュ化する PasswordEncoder を生成する
	 * BCrypt を使用して安全にパスワードを保存できる
	 *
	 * @return BCryptPasswordEncoder のインスタンス
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Spring Security の SecurityFilterChain を設定する
	 * 設定内容:
	 * - CSRF を無効化
	 * - フレームオプションを sameOrigin に設定（H2 コンソール対応）
	 * - URL ごとの認可ルール:
	 *   - /cart/checkout はログイン必須
	 *   - /h2-console/** は全員アクセス可能
	 *   - その他の公開リソースは全員アクセス可能
	 * - ログインページは /login に設定
	 * - ログアウトは全員アクセス可能
	 *
	 * @param http HttpSecurity オブジェクト
	 * @return 設定済みの SecurityFilterChain
	 * @throws Exception セキュリティ設定のビルド中に発生する例外
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
				.authorizeHttpRequests(auth -> auth
					
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers(
								"/",
								"/products/**",
								"/product/**",
								"/cart/**",
								"/cart/checkout",
								"/css/**",
								"/js/**",
								"/images/**",
								"/login")
						.permitAll()
						.anyRequest().permitAll())
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll())
				.logout(logout -> logout.permitAll());

		return http.build();
	}
}