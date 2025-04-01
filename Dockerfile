FROM eclipse-temurin:17

LABEL mentainer="javaguides.net@gmail.com"

WORKDIR /app

COPY target/SpringBootMySQLRedisCache-0.0.1-SNAPSHOT.jar /app/springboot-mysql-redis-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-mysql-redis-webservices.jar"]