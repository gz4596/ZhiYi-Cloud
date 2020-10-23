FROM anapsix/alpine-java:8_server-jre_unlimited

ENV TZ=Asia/Shanghai

ENV JAVA_OPTS="-Xms128m -Xmx256m"

ENV SPRING_OPTS="--spring.profiles.active=dev"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /project

WORKDIR /project

EXPOSE 6628

ADD ./target/zhiyi-authorization.jar ./run.jar

CMD java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar run.jar $SPRING_OPTS
