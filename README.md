
Installer version 3 de dockercompose
* https://docs.docker.com/compose/install/
* curl -L "https://github.com/docker/compose/releases/download/1.10.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
* Penser à regarder où se trouve docker-compose 

Il n'est peut être pas dans /usr/local/bin/docker-compose mais plutôt /usr/bin/docker-compose


## copier les fichiers depuis Windows -> linux

Sur Windows:
* C:\Users\Olivier\VirtualBox VMs\Ubuntu_Shared

Sur VirtualBox
* /media/sf_Ubuntu_Shared/docker-test/

Copier tous les fichiers :
* cp -rf /media/sf_Ubuntu_Shared/docker-test/* /home/oterrien/docker-test

## se loguer à dockerhub sous linux

* docker login
* credentials dockerhub

## pousser l'image de consumer dans dockerhub

* copier consumer.jar et Dockerfile dans le répertoire "/home/oterrien/docker-test/consumer" sur linux
    * cp -rf /media/sf_Ubuntu_Shared/docker-test/consumer/* /home/oterrien/docker-test/consumer
* docker build -t consumer /home/oterrien/docker-test/consumer
* docker tag consumer oterrien/consumer:2.0  
* docker push oterrien/consumer:2.0

## pousser l'image de publisher dans dockerhub

* copier publisher.jar et Dockerfile dans le répertoire "/home/oterrien/docker-test/publisher" sur linux
    * cp -rf /media/sf_Ubuntu_Shared/docker-test/publisher/* /home/oterrien/docker-test/publisher
* docker build -t publisher /home/oterrien/docker-test/publisher
* docker tag publisher oterrien/publisher:2.0  
* docker push oterrien/publisher:2.0

## lancer les services avec docker-compose

* copier docker-compose.yml sur linux
    * cp -rf /media/sf_Ubuntu_Shared/docker-test/docker-compose.yml /home/oterrien/docker-test
* docker-compose up -d

## plusieurs commandes

STOP ALL CONTAINERS  
 docker kill $(docker ps -q)
 
DELETE ALL STOPPED CONTAINERS  
 docker rm $(docker ps -a -q)
 
DELETE ALL IMAGES
 docker rmi -f $(docker images -q)