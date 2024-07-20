#!/bin/sh

./mvnw install

java -jar java-function-invoker-1.3.0.jar \
  --classpath target/gcp-cf-quarkus-1.0.0-SNAPSHOT-runner.jar \
  --target io.quarkus.gcp.functions.QuarkusHttpFunction