# Java com.github.kyleannen.javaserver.Server [![Build Status](https://travis-ci.org/kyle-annen/java-server.svg?branch=master)](https://travis-ci.org/kyle-annen/java-server)

## Requirements

- Java 1.8
- Maven 3.5.0


## Run com.github.kyleannen.javaserver.Server

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


## Adding routes

To add route, a method can be added in the com.github.kyleannen.javaserver.ConfigRoutes class.  

If the server is to be used as a dependency, the following pattern can be used to add routes.


``` java
import com.github.kyleannen.javaserver

public class CustomServer {
    public void run() {
        com.github.kyleannen.javaserver.Router router = new com.github.kyleannen.javaserver.Router();
        router.addRoute("GET", "/custom/route", customController);
        com.github.kyleannen.javaserver.Server server = com.github.kyleannen.javaserver.ConfigureServer().configure(args, router);
        server.start();
    }
}
```

