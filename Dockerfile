FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . /app

# gradlewが無くても落ちないようにする
RUN chmod +x gradlew || true
RUN ls -la
RUN ./gradlew build -x test || echo "gradlew失敗"

EXPOSE 8080

CMD ["sh", "-c", "java -jar build/libs/*.jar"]
