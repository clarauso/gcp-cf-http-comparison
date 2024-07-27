#!/bin/bash

source $(dirname "$0")/utils-arg-check.sh "$@"

cd ../gcp-cf-micronaut && \
  ./mvnw clean package && \
  rm target/original-function.jar && \
  gcloud functions deploy test-http-micronaut \
    --gen2 \
    --runtime java21 \
    --memory 256Mi \
    --region=$region \
    --trigger-http \
    --allow-unauthenticated \
    --entry-point=io.micronaut.gcp.function.http.HttpFunction \
    --ingress-settings=internal-only \
    --source=target

