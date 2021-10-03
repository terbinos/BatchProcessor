FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
ARG appName=batch_process_app
ARG JAR_FILE=target/*.jar
CMD mkdir ${appName}
WORKDIR ${appName}
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]