# TempService
spring-boot-neo4j-sample

Build app-service docker image:
  1.from project folder execute below command
  
    docker build --pull --rm -f "Dockerfile" -t tempservice:latest "."
    
  2.navigate to docker folder and execute the below command to up both db and app services
  
    docker-compose -f "docker/docker-compose.yml" up -d --build 
