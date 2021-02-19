`docker pull mysql`

`docker run -d --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql`

`docker exec -it mysqldb bash`

`mysql -u root -p`