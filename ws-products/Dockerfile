FROM openjdk:8-jdk-alpine
EXPOSE 9876
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n", "-jar", "/var/ws-product.jar"]
ARG JAR_FILE
ADD ${JAR_FILE} "/var/ws-product.jar"