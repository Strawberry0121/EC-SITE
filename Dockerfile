FROM eclipse-temurin:21-jdk

WORKDIR /app

# 必要なファイルコピー
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew ./
COPY src src

# 実行権限
RUN chmod +x gradlew

# ビルド
RUN ./gradlew build -x test

# ポート
EXPOSE 8080

# 起動
CMD ["java", "-jar", "build/libs/*.jar"]
