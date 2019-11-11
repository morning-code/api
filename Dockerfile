FROM openjdk:jdk-alpine
VOLUME /tmp
RUN mkdir /morning-code-api
WORKDIR /morning-code-api
ENV JAVA_OPTS=""
ENV MORNING_CODE_API_VERSION=0.0.1-SNAPSHOT
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/api-$MORNING_CODE_API_VERSION.jar" ] ]
