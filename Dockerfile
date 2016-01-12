FROM java:openjdk-8-jre

MAINTAINER k16wire

ENV APP_NAME circleline-gw
ENV VERSION 1.0

RUN mkdir /app
ADD ./target/scala-2.11/${APP_NAME}-assembly-${VERSION}.jar /app/
WORKDIR /app

CMD ["java","-jar","circleline-gw-assembly-1.0.jar"]