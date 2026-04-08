# ===== Build Stage =====
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Gradle Wrapperを先にコピー（重要）
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 権限付与
RUN chmod +x gradlew

# 依存ダウンロード（キャッシュ効率化）
RUN ./gradlew dependencies --no-daemon || true

# 残りのソースをコピー
COPY src src

# ビルド
RUN ./gradlew bootJar --no-daemon


# ===== Run Stage =====
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

# 起動
# CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
