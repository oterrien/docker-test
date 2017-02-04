
Installer version 3 de dockercompose
* https://docs.docker.com/compose/install/
* curl -L "https://github.com/docker/compose/releases/download/1.10.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
* Penser à regarder où se trouve docker-compose 

Il n'est peut être pas dans /usr/local/bin/docker-compose mais plutôt /usr/bin/docker-compose

Pour démarrer les services:
# se loguer à dockerhub sous linux 
** docker login
** credentials dockerhub
# pousser l'image de consumer dans dockerhub
** copier consumer.jar et Dockerfile dans le répertoire "/home/oterrien/consumer" sur linux
*** cp -rf /media/sf_Ubuntu_Shared/pubsub/* /home/oterrien/pubsub-test
** docker build -t consumer /home/oterrien/consumer
** docker tag consumer oterrien/consumer:1.0  
** docker push oterrien/consumer:1.0
# pousser l'image de publisher dans dockerhub
** copier publisher.jar et Dockerfile dans le répertoire "/home/oterrien/consumer" sur linux
** docker build -t publisher /home/oterrien/publisher
** docker tag consumer oterrien/publisher:1.0  
** docker push oterrien/publisher:1.0
# lancer les services avec docker-compose
** docker-compose up -d