ARG JDK_VERSION="11"
ARG JDK_TYPE="amazoncorretto"
#ARG JDK_TYPE="openjdk"
FROM ${JDK_TYPE}:${JDK_VERSION}

VOLUME /tmp
ARG JAR_FILE=target/unitconversion-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]