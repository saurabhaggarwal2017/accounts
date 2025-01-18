FROM openjdk:20-jdk-slim
LABEL "org.opencontainers.image.authors"="eazybytes.com"
WORKDIR /app
COPY target/accounts-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]