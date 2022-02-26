FROM amazoncorretto:17
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} gateway-server.jar
ENTRYPOINT ["java","-jar","/gateway-server.jar"]