####
# This Dockerfile is used in order to build a container that runs the Spring Boot application
#
# Build the image with:
#
# docker build -f Dockerfile -t spring-boot-echo .
#
# Then run the container using:
#
# docker run -i --rm -p 8081:8081 spring-boot-echo
####
FROM docker.io/library/gradle:7.5-jdk18 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM docker.io/library/openjdk:18-jdk
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/echo-0.0.1-SNAPSHOT.jar

EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/app/echo-0.0.1-SNAPSHOT.jar", "--server.port=8081" ]
