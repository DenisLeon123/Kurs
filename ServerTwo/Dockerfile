FROM openjdk:16

RUN mkdir /app

WORKDIR /app

COPY src/ServerTwo.java /app
COPY src/service.ServiceMemory.java /app

RUN javac ServerTwo.java

CMD java ServerTwo
