#!/bin/bash
# Require 3 images mysql.tar, api.tar, surveyweb.tar

# Load images
echo -e "Loading 3 images to docker........"
docker load -i ./mysql.tar
if [ $? -eq 0 ]; then
  docker load -i ./api.tar
  if [ $? -eq 0 ]; then
    docker load -i ./surveyweb.tar
    if [ $? -eq 0 ]; then
        echo -e "Load 3 images docker done!!!"
    else
       echo -e "fail to load web!"
    fi
  else
    echo -e "fail to load api!"
  fi
else
  echo -e "fail to load mysql!"
fi

# Build containers
# IMPORTANT!!!  build mysql docker name MUST BE "mysql-docker-container"
echo -e "\nBuilding containers from images...."
docker run -d -p 3306:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=survey -e MYSQL_USER=app_user -e MYSQL_PASSWORD=test123 mysql:latest
if [ $? -eq 0 ]; then
    echo -e "build mysql container done!!!"
    docker run -d -t --name api-container --link mysql-docker-container:mysql -p 8080:8080 spring-jpa-app
    if [ $? -eq 0 ]; then
        echo -e "build api container done!!!"
    else
        echo -e "fail to build api container"
    fi
else
    echo -e "fail to build mysql container"
fi
sleep 1
docker run -d -p 80:80 --name web-container surveyweb
if [ $? -eq 0 ]; then
    echo -e "build web container done!!!"
else
    echo -e "fail to build web container"
fi

# Import DB
echo -e "Waiting 60s for mysql service startup then import db structure"
echo -e "............................................"
sleep 60
docker exec -i mysql-docker-container mysql -uroot -proot123 survey < survey.sql
echo -e "all done!!! Enter: \nhttp://localhost/#/survey"
