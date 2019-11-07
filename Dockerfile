FROM gcr.io/corp-prod-gkeinfra/lacapitale-base-java:jdk12

RUN apt-get update
RUN apt-get install net-tools
RUN apt-get install vim -y

VOLUME /tmp

ADD ./target/svc-dsp-diagnosticToHealthCheck.jar app.jar
ADD ./diagnosticpage.json diagnosticpage.json

ENTRYPOINT ["java"]

EXPOSE 9080
EXPOSE 8778

USER root

# We specify in the entrypoint the location of the application.properties file because it is provided by a ConfigMap in Kubernetes
# "-Dspring.config.location=file:/config/application.properties",
CMD ["-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"
