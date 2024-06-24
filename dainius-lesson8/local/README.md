# Local Environment

This module holds resources needed to create development environment
on localhost.

The directory contains a set of composable docker-compose files
that can be used to create a local functional develop environment.

## Run

In order to start a full environment, run:
```shell
make up
```

To kill all the containers, execute:
```shell
make down
```

## Test

```
% curl -v http://localhost:8081/api/v1/heartbeat   
...
< HTTP/1.1 200 
...
% curl -v http://localhost:8082/api/v1/heartbeat   
...
< HTTP/1.1 200 
...
```
