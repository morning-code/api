#!/bin/bash

echo "${CI_PULL_REQUEST}"

PR_NUMBER=$(echo "${CI_PULL_REQUEST}" | sed -e 's/.*\///g')

gradle sonarqube \
  -Dsonar.host.url="${SONARQUBE_URL}" \
  -Dsonar.login="${SONARQUBE_LOGIN}" \
  -Dsonar.github.pullRequest="${PR_NUMBER}" \
  -Dsonar.github.oauth="${GITHUB_OAUTH}" \
  -Dsonar.github.repository="${GITHUB_REPOS}"
