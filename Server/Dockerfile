FROM openjdk:16

RUN mkdir /app

WORKDIR /app

COPY src/Colors.java /app
COPY src/Server.java /app

RUN javac Server.java

CMD java Server
