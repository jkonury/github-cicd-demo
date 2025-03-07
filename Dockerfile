FROM bellsoft/liberica-runtime-container:jdk-21-cds-slim-musl AS optimizer

WORKDIR /app

# Gradle wrapper와 설정 파일만 먼저 복사
COPY gradlew build.gradle.kts ./
COPY gradle ./gradle

RUN ./gradlew dependencies # 의존성 다운로드 (캐싱 레이어)

COPY . /app

RUN ./gradlew build --no-daemon && \
    mv build/libs/github-cicd-demo-0.0.1-SNAPSHOT.jar ./app.jar && \
    java -Djarmode=tools -jar ./app.jar extract --layers --launcher
COPY ./script/*.sh ./app
RUN chmod +x ./app/*.sh

FROM bellsoft/liberica-runtime-container:jre-21-stream-musl

WORKDIR /app

#ENV TZ=Asia/Seoul
ENV PROFILE=docker
ENV APP_NAME=github-cicd-demo
ENV SCOUTER_COLLECTOR_IP=127.0.0.1
ENV SCOUTER_ENABLE=true

RUN apk --no-cache add tzdata &&  \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY scouter/* /scouter/
COPY --from=optimizer /app/app/* ./

EXPOSE 8080
ENTRYPOINT ["sh", "run.sh"]
