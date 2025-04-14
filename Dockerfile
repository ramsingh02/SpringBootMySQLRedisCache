#FROM eclipse-temurin:17

FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

LABEL mentainer="ram.singh01@nagarro.com"

WORKDIR /app

COPY target/springboot-mysql-redis-webservices.jar /app/springboot-mysql-redis-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-mysql-redis-webservices.jar"]