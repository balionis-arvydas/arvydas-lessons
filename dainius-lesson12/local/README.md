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
% curl -v -X GET http://localhost:9091/api/v1/heartbeat
...
< HTTP/1.1 200 
...

% docker logs local-producer --follow
...
11:05:48.019 [,] [main] INFO  c.b.d.l.p.c.AppConfigurationProperties - appConfigurationProperties=AppConfigurationProperties(name=dainius-lesson11-producer)
...
11:09:32.988 [8d2805328169a515ac13bfe2aaae3807,934fad8fd1146d95] [http-nio-9090-exec-1] INFO  c.b.d.l.p.rest.HeartbeatController - alive...
11:09:32.988 [8d2805328169a515ac13bfe2aaae3807,934fad8fd1146d95] [http-nio-9090-exec-1] INFO  c.b.d.l.p.service.HeartbeatService - name=dainius-lesson11-producer

...
```
