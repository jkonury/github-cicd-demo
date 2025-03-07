FROM bellsoft/liberica-runtime-container:jdk-21-cds-slim-musl AS optimizer

WORKDIR /app

ARG JAR_FILE

COPY $JAR_FILE app.jar
RUN java -Djarmode=tools -jar ./app.jar extract --layers --launcher
COPY ./script/*.sh ./app
RUN chmod +x ./app/*.sh

FROM bellsoft/liberica-runtime-container:jre-21-stream-musl

WORKDIR /app

#ENV TZ=Asia/Seoul
ENV PROFILE=docker
ENV APP_NAME=github-cicd-demo
ENV SCOUTER_COLLECTOR_IP=127.0.0.1
ENV SCOUTER_ENABLE=true

#RUN apk --no-cache add tzdata &&  \
#    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY scouter/* /scouter/
COPY --from=optimizer /app/app/* ./

EXPOSE 8080
ENTRYPOINT ["sh", "run.sh"]
