FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./build/libs/arvydas-lesson5-all.jar ./app.jar

EXPOSE 9090

ENTRYPOINT java $JAVA_OPTS -jar app.jar