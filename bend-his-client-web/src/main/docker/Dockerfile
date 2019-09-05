FROM jdk1.8:144

VOLUME /tmp
EXPOSE $JAR_EXPOSE

ARG JAR_FILE
ADD $JAR_FILE /app.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+08", "/app.jar"]
#RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#ENTRYPOINT ["java", "-jar","/app.jar"]
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--Dspring.config.location=application.yml"]
