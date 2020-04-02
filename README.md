This is the Java version of the customers and orders example that I've used in numerous presentations
on developing microservices with event sourcing and CQRS.
The code is built using the Eventuate platform.
It illustrates how to implement an eventually consistent credit limit check using event sourcing.
For more information, see this [presentation from Gluecon 2016](http://www.slideshare.net/chris.e.richardson/a-pattern-language-for-microservices-gluecon-2016/24)

# About Eventuate&trade;

![](http://eventuate.io/i/logo.gif)

The application is built using [Eventuate](http://eventuate.io/), which is an application platform for writing transactional microservices.
It provides a simple yet powerful event-driven programming model that is based on event sourcing and Command Query Responsibility Segregation (CQRS).
Eventuate solves the distributed data management problems inherent in a microservice architecture.
It consists of a scalable, distributed event store and client libraries for various languages and frameworks including Java, Scala, and the Spring framework.

# Building and running the application.

This is a Java 8, Gradle project. However, you do not need to install Gradle since it will be downloaded automatically. You just need to have Java 8 installed.


## Building and running using Eventuate Local

First, build the application:

```
./gradlew assemble
```

Next, you can launch the application using [Docker Compose](https://docs.docker.com/compose/)

```
export DOCKER_HOST_IP=...
./gradlew <database-mode>ComposeBuild
./gradlew <database-mode>ComposeUp
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

## Deploying using Docker Stack/Swarm

You can also deploy the application using Docker Stack/Swarm mode.

```
./gradlew assemble
docker-compose -f docker-compose-eventuate-local.yml build
docker stack deploy -c docker-stack-eventuate-local-<db-type>.yml customers-and-orders
```
