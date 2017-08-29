# Java Server [![Build Status](https://travis-ci.org/kyle-annen/java-server.svg?branch=master)](https://travis-ci.org/kyle-annen/java-server)

## Requirements

- Java 1.8
- Maven 3.5.0


## Run Server

- Clone repository.
- Compile the project.

``` bash
mvn package
```
- Run the server, optionally specifying the port and root directory to serve.
- 

``` bash
java -jar target/java-server-0.1.jar -p 3030 -d /Root/directory/to/serve 
```

## Run tests

``` bash
mvn clean test
```

