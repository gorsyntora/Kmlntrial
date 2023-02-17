FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ARG JAR_FILE=target/Kamelntrial-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]