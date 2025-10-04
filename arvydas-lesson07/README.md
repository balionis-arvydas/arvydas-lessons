# arvydas-lesson07

1. Baseline is arvydas-lesson06

2. Move from postgres to MySQL.

3. Add liquibase addColumn.

# Build
```
./gradlew clean dockerBuild --console=plain
```

# Run (Start under docker)
```
cd local
make up
```

# Test

## Call service heartbeat under docker
```
curl -X GET http://localhost:9091/api/v1/heartbeat

// see docker container logs (see: 'Tail Docker Logs' above for how-to)
... 
% docker logs -f local-service                                     
...
09:41:47.497 [main] INFO  o.e.jetty.server.AbstractConnector - Started ServerConnector@4e78f556{HTTP/1.1, (http/1.1)}{0.0.0.0:9090}
09:41:47.501 [main] INFO  o.s.b.w.e.jetty.JettyWebServer - Jetty started on port 9090 (http/1.1) with context path '/'
09:41:47.530 [main] INFO  c.b.arvydas.lesson07.Application - Started Application in 10.192 seconds (process running for 11.997)
09:41:47.547 [main] INFO  c.b.arvydas.lesson07.Application - finishing
09:43:49.611 [qtp737892411-44] INFO  c.b.d.l.rest.HeartbeatController - alive...
```

## Call pet store service under docker
```
curl -X POST http://localhost:9091/api/v1/pet --header 'Content-Type: application/json' --data '{ "pet": { "name": "myDog", "status": "AVAILABLE" } }'
{"id":"c6f9175a-66c0-4c15-97ce-e7b8eef00aeb"}
...
curl -X GET  http://localhost:9091/api/v1/pet/c6f9175a-66c0-4c15-97ce-e7b8eef00aeb
{"id":"864bbd39-4ce5-43ee-8da6-b7701ae16c5b","name":"myDog","status":"AVAILABLE"}
...
```
