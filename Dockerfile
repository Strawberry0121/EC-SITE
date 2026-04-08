FROM eclipse-temurin:21-jdk

WORKDIR /app

# まず全部コピー
COPY . .

# gradlewがある前提（←ここ重要）
RUN chmod +x ./gradlew

# ビルド
RUN ./gradlew build -x test

# jar確認（デバッグ用）
RUN ls -l build/libs

# 起動
CMD ["java", "-jar", "build/libs/app.jar"]
