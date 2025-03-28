#!/bin/bash

echo "Installing config-server..."
cd ./config-server
mvn clean package -Dmaven.test.skip
cd ..

echo "Installing naming-server..."
cd ./naming-server
mvn clean package -Dmaven.test.skip
cd ..

echo "Installing gateway-server..."
cd ./gateway-server
mvn clean package -Dmaven.test.skip
cd ..

echo "Installing products-service..."
cd ./products-service
mvn clean package -Dmaven.test.skip
cd ..

echo "Installing orders-service..."
cd ./orders-service
mvn clean package -Dmaven.test.skip
cd ..

echo "Installing users-management-service..."
cd ./users-management-service
mvn clean package -Dmaven.test.skip
cd ..

cp */target/*.jar jars/