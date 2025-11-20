#!/bin/sh
export LANG="en_US.UTF-8"
delImage=`docker images | grep -E "boot-demo-8088" | awk '{print $3}'` 
echo $delImage

docker stop boot-demo-8088

sleep 2
docker rm boot-demo-8088

sleep 2
docker rmi $delImage
sleep 2
docker-compose up -d --build