#!/bin/bash

while getopts r: flag
do
    case "${flag}" in
        r) region=${OPTARG};;
        *) exit 22
    esac
done

if [ -z "$region" ]
then
      echo "Set the cloud region using -r option"
      exit 22
fi