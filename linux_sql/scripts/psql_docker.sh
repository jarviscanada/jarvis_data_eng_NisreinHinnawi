#!/bin/bash

#option can be create, start, or stop
# if create is chosen, must provide db_username and db_password
option=$1
db_username=$2
db_password=$3

#start docker, if it is not started
sudo systemctl status docker || systemctl start docker

#get latest postgres image
docker pull postgres

#check container status
container_status=$?

#use switch case to handel the entered option
case $option in
create)
  #check if the container is already created
  if [ $container_status -eq 0 ]; then
      echo 'Container already exists'
    	exit 1
  fi

  #check if the username and the password are passed
  if [ $# -ne 3 ]; then
      echo 'Create requires username and password'
      exit 1
  fi

  #if all the conditions are met, then create volume
  docker volume create pgdata

  #create container with the given name and password
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password}
  -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine

  exit $?
  ;;

#if the option is start container or stop container
#make sure the container is exist
start|stop)
  if [ $container_status -ne 0 ]; then
      		echo "Error: Container does not exist"
      		exit 1
  fi
    #Start or stop the container
	docker container $option jrvs-psql
  exit $?
	;;

#if invalid  option is given
*)
  echo 'Illegal command'
  echo 'Commands: start|stop|create'
  exit 1
  ;;
esac