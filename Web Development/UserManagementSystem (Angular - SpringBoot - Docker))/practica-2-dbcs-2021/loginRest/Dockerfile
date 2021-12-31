FROM openjdk:17.0.1-jdk-oracle
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/users-0.0.1-SNAPSHOT.jar loginrest.jar
EXPOSE 8090
ENTRYPOINT exec java $JAVA_OPTS -jar loginrest.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar loginrest.jar
