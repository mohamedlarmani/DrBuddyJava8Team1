FROM eclipse-temurin:17-jdk-jammy
add target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]