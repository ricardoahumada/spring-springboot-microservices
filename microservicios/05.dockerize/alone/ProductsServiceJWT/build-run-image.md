# build image
docker build -t ricardoahumada/products-service-jwt:v1 -f Dockerfile ./

# run detached
docker run -it -d -p 9090:9090 ricardoahumada/products-service-jwt:v1

# exec commendas
docker exec -it <CONTAINER_NAME> sh

# exec sonarq analisys from inside 
## get the host ip
ip addr show eth0 # get the ip mask
route # get the gateway for the ip mask
## exec analysis
mvn clean verify sonar:sonar -Dsonar.projectKey=ProductsService -Dsonar.host.url=http://<IP>:9001 -Dsonar.login=sqp_73723efa1fe8625126c37ebda0ec5fef94a694cf -Dmaven.test.skip
