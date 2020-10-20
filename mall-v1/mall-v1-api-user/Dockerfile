FROM anapsix/alpine-java:8_server-jre_unlimited

ENV TZ=Asia/Shanghai

ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

ENV SPRING_OPTS="--spring.profiles.active=test"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /project

WORKDIR /project

EXPOSE 5513

ADD ./target/mall-v1-api-user.jar ./

CMD java $JAVA_OPTS -jar mall-v1-api-user.jar $SPRING_OPTS
