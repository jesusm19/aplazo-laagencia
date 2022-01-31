FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD ./target/com.aplazo.interests-0.0.1-SNAPSHOT.jar interest.jar
ENTRYPOINT ["java","-jar","/interest.jar"]