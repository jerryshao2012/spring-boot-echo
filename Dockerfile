####
# This Dockerfile is used in order to build a container that runs the Spring Boot application
#
# Build the image with:
#
# docker build -f Dockerfile -t springboot/spring-boot-echo .
#
# Then run the container using:
#
# docker run -i --rm -p 8081:8081 springboot/spring-boot-echo
####
FROM gradle:7.5-jdk18-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:18-jdk
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/echo-0.0.1-SNAPSHOT.jar

EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/app/echo-0.0.1-SNAPSHOT.jar", "--server.port=8081" ]
