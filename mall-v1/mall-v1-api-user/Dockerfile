FROM anapsix/alpine-java:8_server-jre_unlimited

ENV TZ=Asia/Shanghai

ENV JAVA_OPTS="-Xms128m -Xmx256m"

ENV SPRING_OPTS="--spring.profiles.active=dev"

ENV NACOS_HOST="127.0.0.1:8848"

ENV NACOS_USER="nacos"

ENV NACOS_PASS="nacos"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /project

WORKDIR /project

ADD ./target/mall-v1-api-user.jar ./run.jar

EXPOSE 80

CMD java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar run.jar --server.port=80 $SPRING_OPTS
