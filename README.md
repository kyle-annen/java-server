# Java Server 
[![Build Status](https://travis-ci.org/kyle-annen/java-server.svg?branch=master)](https://travis-ci.org/kyle-annen/java-server)
[![Coverage Status](https://coveralls.io/repos/github/kyle-annen/java-server/badge.svg?branch=add-coveralls)](https://coveralls.io/github/kyle-annen/java-server?branch=add-coveralls)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5b6cc4b4e1d5471992b778e9ee55cfa6)](https://www.codacy.com/app/kyle-annen/java-server?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kyle-annen/java-server&amp;utm_campaign=Badge_Grade)



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

``` bash
java -jar target/java-server-0.1.jar -p 3030 -d /Root/directory/to/serve 
```

## Run tests

``` bash
mvn clean test
```


## Adding routes

To add route, a method can be added in the ConfigRoutes class.

If the server is to be used as a dependency, the following pattern can be used to add routes, disable dynamic directory and file serving, and to run a custom configuration of the server.


``` java
import com.github.kyleannen.javaserver

public class CustomServer {
    public void run() {
        Router router = new Router();
        //optionally disable dynamic routing of directory structures
        router.disableDirectoryRouting();
        //optionally disable dynamic routing of files (file serving)
        router.disableFileRouting();
        //add individual routes
        router.addRoute("GET", "/custom/route", customController);
        //create a string array of the command line flags to pass to the server
        String[] args = new String[]{"-p", "3000"}
        Server server = ConfigureServer().configure(args, router);
        server.start();
    }
}
```

