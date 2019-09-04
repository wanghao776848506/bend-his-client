FROM jdk1.8:144
VOLUME /tmp
ADD bend-his-client-web-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
