# Java Server [![Build Status](https://travis-ci.org/kyle-annen/java-server.svg?branch=master)](https://travis-ci.org/kyle-annen/java-server)

## Run tests

``` bash
mvn clean clover:setup test clover:aggregate clover:clover
```

## Run Server

- Clone repository.
- Compile the project.

``` bash
mvn package
```
- Run the server.
``` bash
cd target/classes/Main
java Main 3030
```

