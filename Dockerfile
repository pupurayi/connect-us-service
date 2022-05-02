FROM maven:3.8.4-openjdk-11 AS MAVEN_BUILD

MAINTAINER connectus.co.zw

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package


FROM adoptopenjdk:11-jre-hotspot

RUN adduser --system --group connectus
USER connectus:connectus

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=58190,server=y,suspend=n
ARG JAR_FILE=/build/target/*.jar

WORKDIR /app
COPY --from=MAVEN_BUILD ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]