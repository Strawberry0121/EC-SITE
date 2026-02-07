package com.example.shopping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.shopping.model.User;
import com.example.shopping.repository.UserRepository;

/**
 * アプリケーション起動時に初期データを投入するクラス
 * <p>
 * ユーザーが存在しない場合、サンプルユーザーを作成する
 * パスワードは BCrypt でハッシュ化して保存する
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * コンストラクタ注入
     *
     * @param userRepo ユーザーリポジトリ
     * @param passwordEncoder パスワードエンコーダー
     */
    public DataInitializer(UserRepository userRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * アプリケーション起動時に実行される処理
     * ユーザーが存在しなければ、サンプルユーザーを作成する
     *
     * @param args コマンドライン引数
     */
    @Override
    public void run(String... args) {

        if (userRepo.count() == 0) {

            User u = new User();
            u.setUsername("test");

            // パスワードをハッシュ化して保存
            u.setPassword(passwordEncoder.encode("1234"));

            userRepo.save(u);
        }
    }
}