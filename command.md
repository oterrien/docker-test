docker kill $(docker ps -q)
docker rm $(docker ps -a -q)
cp -rf /media/sf_Ubuntu_Shared/docker-test/* /home/oterrien/docker-test
docker build -t consumer /home/oterrien/docker-test/consumer
docker tag consumer oterrien/consumer:3.0  
docker push oterrien/consumer:3.0
docker build -t publisher /home/oterrien/docker-test/publisher
docker tag publisher oterrien/publisher:3.0  
docker push oterrien/publisher:3.0
docker-compose up -d --build
docker-compose scale consumer=2
