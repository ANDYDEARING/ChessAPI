FROM java:8-jdk-alpine
COPY ./target/API-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 3333
ENTRYPOINT [ "java", "-jar", "API-0.0.1-SNAPSHOT.jar" ]