#!/bin/sh

mvn dependency:copy \
  -Dartifact='com.google.cloud.functions.invoker:java-function-invoker:1.3.0' \
  -DoutputDirectory=.