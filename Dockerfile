FROM eclipse-temurin:17-jdk-alpine

COPY ./target/royal-hospital.jar ./target/app.jar

ENTRYPOINT ["java","-jar","./target/app.jar"]
EXPOSE 8080