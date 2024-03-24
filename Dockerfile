FROM openjdk:11
WORKDIR /dockerapp
COPY ./target/dockerdemo.jar /dockerapp
EXPOSE 8010
CMD ["java","-Duser.timezone=UTC","-jar","dockerdemo.jar"]