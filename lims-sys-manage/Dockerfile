FROM zhengpanone/jdk8:latest
RUN mkdir /app
WORKDIR /app
ADD target/lims-sys.jar lims-sys.jar
EXPOSE  8081
CMD ["/opt/jdk1.8.0_301/bin/java","-jar","/app/lims-sys.jar"]