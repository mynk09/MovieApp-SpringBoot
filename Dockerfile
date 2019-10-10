FROM openjdk:11
ADD ./target/MovieServer-0.0.1-SNAPSHOT.jar /usr/src/MovieServer-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "MovieServer-0.0.1-SNAPSHOT.jar"]