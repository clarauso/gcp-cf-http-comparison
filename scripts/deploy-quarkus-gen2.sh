#!/bin/bash

source $(dirname "$0")/utils-arg-check.sh "$@"

cd ../gcp-cf-quarkus && \
  ./mvnw clean package && \
  gcloud functions deploy test-http-quarkus \
    --gen2 \
    --runtime java21 \
    --memory 128Mi \
    --region=$region \
    --trigger-http \
    --allow-unauthenticated \
    --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
    --ingress-settings=internal-only \
    --source=target/deployment

