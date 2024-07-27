#!/bin/bash

source $(dirname "$0")/utils-arg-check.sh "$@"

cd ../gcp-cf-spring && \
  mvn clean package && \
  gcloud functions deploy test-http-spring \
    --gen2 \
    --runtime java21 \
    --memory 256Mi \
    --region=$region \
    --trigger-http \
    --allow-unauthenticated \
    --entry-point=org.springframework.cloud.function.adapter.gcp.GcfJarLauncher \
    --ingress-settings=internal-only \
    --source=target/deploy
