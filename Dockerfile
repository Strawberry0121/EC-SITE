FROM eclipse-temurin:21-jdk

WORKDIR /app

# 全部コピー
COPY . /app

# 実行権限
RUN chmod +x gradlew

# ビルド
RUN ./gradlew build -x test

# 起動
CMD ["sh", "-c", "java -jar build/libs/*.jar"]
