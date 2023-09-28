FROM --platform=linux/amd64 openjdk:17

EXPOSE 8080

ARG JAR_FILE=build/libs/*jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]