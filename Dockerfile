FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-mongodb-0.0.1-SNAPSHOT.jar bookmanagementtool.jar
ENTRYPOINT ["java","-jar","/bookmanagementtool.jar"]