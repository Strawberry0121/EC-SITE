FROM eclipse-temurin:21-jdk

WORKDIR /app

# サブフォルダごとコピー
COPY ecsitedemo /app

WORKDIR /app

RUN chmod +x gradlew
RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/*.jar"]
