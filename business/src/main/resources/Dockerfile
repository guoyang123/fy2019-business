from centos:7
RUN yum install -y java
COPY business.jar /app/
WORKDIR /app
EXPOSE 8888
ENTRYPOINT ["java","-jar","business.jar"]
