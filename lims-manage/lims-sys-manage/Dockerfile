FROM openjdk:23-slim
RUN mkdir /app
WORKDIR /app
# 添加参数
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
#ADD target/lims-sys.jar lims-sys.jar
#EXPOSE  8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#CMD ["/opt/jdk1.8.0_301/bin/java","-jar","/app/lims-sys.jar"]