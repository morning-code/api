FROM openjdk:jdk-alpine
VOLUME /tmp
RUN mkdir /morning-code-api
WORKDIR /morning-code-api

ENV JAVA_OPTS=""
ENV MORNING_CODE_API_VERSION=0.0.1-SNAPSHOT
COPY ./build/libs/api-$MORNING_CODE_API_VERSION.jar /morning-code-api
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./api-$MORNING_CODE_API_VERSION.jar" ]
