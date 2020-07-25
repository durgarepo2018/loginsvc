FROM openjdk:12-alpine

COPY target/loginsvc-*.jar  /loginsvc.jar

CMD ["java", "-jar", "loginsvc.jar"]