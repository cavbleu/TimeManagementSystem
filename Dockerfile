FROM openjdk:17
ADD data.sql /data/data.sql
ADD /target/TimeManagementSystem-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
