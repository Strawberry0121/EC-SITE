FROM eclipse-temurin:21-jdk

WORKDIR /app

# 先にGradle設定だけコピー（キャッシュ制御）
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# ソースは後でコピー
COPY src src

RUN ./gradlew build -x test --no-daemon

CMD ["java", "-jar", "build/libs/EC-SITE-0.0.1-SNAPSHOT.jar"]
