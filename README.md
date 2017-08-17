# Java Server [![Build Status](https://travis-ci.org/kyle-annen/java-server.svg?branch=master)](https://travis-ci.org/kyle-annen/java-server)

## Run tests

``` bash
mvn clean test
```

## Run Server

- Clone repository.
- Compile the project.

``` bash
mvn package
```
- Run the server, optionally specifying the port and root directory to serve.
``` bash
cd target/classes/Main
java -jar target/java-server-0.1.jar 3030 /Root/directory/to/serve 

//>java Main <port> <directory>
```
