# syntax=docker/dockerfile:experimental
# Build
FROM gradle:8.0.2-jdk17-alpine AS build
WORKDIR /workspace/app
COPY . /workspace/app/
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build jar

# Execute
FROM gradle:8.0.2-jdk17-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs
COPY --from=build ${DEPENDENCY} /app
ENTRYPOINT ["java","-jar", "/app/airmendb-0.1.jar"]
