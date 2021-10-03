FROM maven:3.8.2-jdk-11-slim
WORKDIR /usr/src/app
COPY ./target/*.jar /usr/src/app
RUN chmod +x ./wait_for_it.sh
RUN mvn package -Dmaven.test.skip
CMD [ "sh", "-c", "mvn spring-boot:run" ]