# Java 21
FROM eclipse-temurin:21-jdk

WORKDIR /app

# プロジェクト全体コピー（これが重要）
COPY . .

# Gradle実行権限
RUN chmod +x ./gradlew

# ビルド（testスキップ）
RUN ./gradlew build -x test

# RenderのPORTを使う
ENV PORT=8080
ENV SECRET_FILE_PATH=/etc/secrets/application.properties

# 起動
CMD ["sh", "-c", "java -jar build/libs/EC-SITE-0.0.1-SNAPSHOT.jar --server.port=$PORT"]
