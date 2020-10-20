FROM anapsix/alpine-java:8_server-jre_unlimited

ENV TZ=Asia/Shanghai

ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

ENV SPRING_OPTS="--spring.profiles.active=test"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /project

WORKDIR /project

EXPOSE 8008

ADD ./target/zhiyi-gateway.jar ./

CMD java $JAVA_OPTS -jar zhiyi-gateway.jar $SPRING_OPTS
