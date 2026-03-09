FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/product-service-0.0.1-SNAPSHOT.jar /app/product-service-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]