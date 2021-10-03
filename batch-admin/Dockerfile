FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
ARG JAR_FILE=target/*.jar
ARG appName=batch_admin_app
CMD mkdir ${appName}
WORKDIR ${appName}
COPY ${JAR_FILE} app.jar

ENV JWT_EXPIRE=6000000
ENV JWT_SECRET=FKckg7aj1rt9scui5ik5tr93d0

ENTRYPOINT ["java", "-jar", "app.jar"]