FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./build/libs/arvydas-lesson05-all.jar ./app.jar

EXPOSE 8080

ENTRYPOINT java $JAVA_OPTS -jar app.jar