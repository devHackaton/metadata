FROM openjdk:11-jdk
ADD target/metadata-1.0.jar metadata.jar
ENV TZ=Europe/Moscow
ENTRYPOINT exec java $JAVA_OPTS -jar /metadata.jar