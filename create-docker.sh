#!/bin/bash
# Requires:
# brew install maven
# docker run -d -p 3306:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=survey -e MYSQL_USER=app_user -e MYSQL_PASSWORD=test123 mysql:latest

# Build images from source code

mvn clean install -DskipTests
docker build -f Dockerfile -t spring-jpa-app .

# Build container
#docker run -t --name spring-jpa-app-container --link mysql-docker-container:mysql -p 8080:8080 spring-jpa-app
