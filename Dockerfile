FROM gcr.io/corp-prod-gkeinfra/lacapitale-base-java:jdk8u171r3

RUN apt-get update
RUN apt-get install net-tools
RUN apt-get install vim -y

VOLUME /tmp

#-------------------------
# Debug pour output le contenu du context docker
#-------------------------
#RUN mkdir /tmp/build/
#COPY . /tmp/build
#RUN find /tmp/build/
#--------------------------------

ADD ./target/dsp-java-diagnosticToHealthCheck-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java"]

EXPOSE 8080
EXPOSE 8778

USER root

# We specify in the entrypoint the location of the application.properties file because it is provided by a ConfigMap in Kubernetes
# "-Dspring.config.location=file:/config/application.properties",
CMD ["-Djava.security.egd=file:/dev/./urandom","-Dspring.config.location=file:/config/diagnosticpage.json","-jar","/app.jar"]
