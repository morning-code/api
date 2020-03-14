#!/bin/bash

ECRID=$1
ECRNAME=morning-code-api
REGION=ap-northeast-1
TAG=latest

# build an your docker app
docker build -t ${ECRNAME} .
docker tag ${ECRNAME}:${TAG} ${ECRID}.dkr.ecr.${REGION}.amazonaws.com/${ECRNAME}:${TAG}

# push
$(aws ecr get-login --no-include-email --region ap-northeast-1)
docker push ${ECRID}.dkr.ecr.${REGION}.amazonaws.com/${ECRNAME}:${TAG}
