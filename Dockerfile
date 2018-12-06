FROM java:8

#Author of the Docker File
MAINTAINER Faisal Jarkass (fafj@kea.dk)

# Install maven
RUN apt-get update
RUN apt-get install -y maven

WORKDIR /SpringbootWepApp

VOLUME /tmp

# Prepare by downloading dependencies
ADD pom.xml /SpringbootWepApp/pom.xml
RUN ["mvn", "dependency:resolve"]

# Adding source, compile and package into a fat jar
ADD src /SpringbootWepApp/src

# Run maven commands
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]