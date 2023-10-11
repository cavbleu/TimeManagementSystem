FROM openjdk:17
ADD /backend-springboot/target/backend-springboot-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
