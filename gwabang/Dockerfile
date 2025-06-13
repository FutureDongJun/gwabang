FROM openjdk:17-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

# 베이스 이미지 설정
FROM amazoncorretto:17 AS builder

# Gradle 및 소스 코드 복사
WORKDIR /app
COPY . .

# Gradle 빌드 실행 (테스트 제외)
RUN ./gradlew clean build -x test

# 최종 이미지
FROM amazoncorretto:17

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
