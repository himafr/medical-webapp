FROM eclipse-temurin:17-jdk-alpine

COPY ./target/royal-hospital.jar ./target/app.jar

COPY ./src/main/webapp ./src/main/webapp
COPY ./src/main/resources ./src/main/resources

ENTRYPOINT ["java","-jar","./target/app.jar"]
EXPOSE 8080
