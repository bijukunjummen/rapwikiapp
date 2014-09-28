FROM bijukunjummen/gfxd-base:latest
COPY target/rapwikiapp-1.0.0-SNAPSHOT.war /code/
ENTRYPOINT java -jar /code/rapwikiapp-1.0.0-SNAPSHOT.war
EXPOSE 8080