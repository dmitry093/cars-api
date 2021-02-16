FROM gradle:jdk13 as compiler

USER root

WORKDIR /code
ENV GRADLE_USER_HOME=/Dependencies

ADD ./settings.gradle /code
ADD ./build.gradle /code
ADD ./src /code/src

RUN gradle -i --no-daemon --no-build-cache --stacktrace build
RUN mkdir -p /app && \
    cp build/libs/*.jar /app/api.jar && \
    rm -rf /code

FROM openjdk:13-jdk

WORKDIR /app

ENV JAVA_OPTS=""
ENV SPRING_PROFILE=default

CMD java \
    ${JAVA_OPTS} \
    -Dspring.profiles.active=${SPRING_PROFILE} \
    -jar api.jar

COPY --from=compiler /app /app