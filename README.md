# Requirement
*Java 8.* If you are using Linux running environment and you have multiple Java versions installed, please use 
```
sudo update-alternatives --config java
sudo update-alternatives --config javac
```
to select Java 8 among multiple available Java versions.

# Notice
Sometimes failures would occur in the building process. Please use 
```
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```
to stop all running containers and remove them.

# Building and running using Eventuate Local
First, build the application:

```
./gradlew assemble -P eventuateDriver=local
```

Next, you can launch the application using [Docker Compose](https://docs.docker.com/compose/)

```
export DOCKER_HOST_IP=...
./gradlew mysqlbinlogComposeBuild
./gradlew mysqlbinlogComposeUp
```

Where `database-mode` is one of:

* `mysqlbinlog` - use MySQL with Binlog-based event publishing
* `postgreswal` - use Postgres with Postgres WAL-based event publishing
* `postgrespolling` - use Postgres with generic JDBC polling-based event publishing

Note: You need to set `DOCKER_HOST_IP` before running Docker Compose.
`DOCKER_HOST_IP` is the IP address of the machine running the Docker daemon.
It must be an IP address or resolvable hostname.
It cannot be `localhost`.
See this [guide to setting `DOCKER_HOST_IP`](http://eventuate.io/docs/usingdocker.html) for more information.

Finally, you can use the Swagger UI provided by the services to create customers and orders, and view the order history:

* `http://${DOCKER_HOST_IP?}:8081/swagger-ui.html` - Create a customer
* `http://${DOCKER_HOST_IP?}:8083/swagger-ui.html` - Create an order
* `http://${DOCKER_HOST_IP?}:8082/swagger-ui.html` - View the customer and the order

(Hint: best to open these URLs in separate tabs)

The script `./show-urls.sh` will display the URLs.

# Developing and extending project
Please refer [Recored Video](https://drive.google.com/file/d/1rptG_rqq0Y_0S-hxG-a3RTxbYDFFRzpa/view?usp=sharing) for further suggestions.
