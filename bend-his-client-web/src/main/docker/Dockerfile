FROM java:8u144

VOLUME /tmp

ARG JAR_FILE
ADD $JAR_FILE /app.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+08", "/app.jar"]
