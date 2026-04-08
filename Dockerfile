FROM eclipse-temurin:21-jdk

WORKDIR /app

# Gradle wrapper ファイルも含めて全てコピー
COPY * .
#COPY gradlew .
#COPY build.gradle .
#COPY settings.gradle .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# アプリケーションを起動
CMD ["java", "-jar", "build/libs/EC-SITE-0.0.1-SNAPSHOT.jar"]
