FROM java:8
VOLUME /tmp
ADD eureka-server.jar app.jar
RUN bash -c 'touch /app.jar'

ENV SPRING-CLOUD-EUREKA-ZONE http://49.234.223.73:8761/eureka/
ENV SPRING-CLOUD-MYSQL-HOST 172.17.16.11:3306
ENV SPRING-CLOUD-DB-NAME team

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8761