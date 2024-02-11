# dainius-lesson5

1. Baseline is dainius-lesson4

2. Add docker packaging.

3. Add docker deployment.

# Build
```
./gradlew clean dockerBuild --console=plain
```

# Run (Start under docker)
```
./gradlew dockerComposeUp --console=plain
```

# Test

## Tail Docker Logs

```
docker container ls
CONTAINER ID   IMAGE                    COMMAND                  CREATED          STATUS          PORTS                    NAMES
99a13e4e3ac0   dainius-lesson5:latest   "/bin/sh -c 'java $J…"   21 seconds ago   Up 20 seconds   0.0.0.0:8080->9090/tcp   dainius-lesson5-service1-1

arvydas@bamac01 ~ % docker logs -f 99a13e4e3ac0                                     
16:36:13.516 [main] INFO  c.b.dainius.lesson5.Application - starting
16:36:14.388 [main] WARN  o.s.b.l.logback.LogbackLoggingSystem - Ignoring 'logback.configurationFile' system property. Please use 'logging.config' instead.

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

16:36:14.557 [main] INFO  c.b.dainius.lesson5.Application - Starting Application using Java 17-ea with PID 1 (/app/app.jar started by root in /app)
16:36:14.558 [main] INFO  c.b.dainius.lesson5.Application - The following 1 profile is active: "docker"
16:36:16.591 [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer - Tomcat initialized with port 9090 (http)
...
16:37:28.966 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
16:37:28.997 [http-nio-9090-exec-1] INFO  c.b.d.l.rest.HeartbeatController - alive...
```

## Call service under docker
```
curl -X GET http://localhost:9090/api/v1/heartbeat
...
curl -X POST http://localhost:9090/api/v1/moving --header 'Content-Type: application/json' --data '{ "number": "2.0"}'
curl -X GET http://localhost:9090/api/v1/moving

// see docker container logs (see: 'Tail Docker Logs' above for how-to)
... 
arvydas@bamac01 ~ % docker logs -f 99a13e4e3ac0                                     
...
16:36:17.383 [main] INFO  c.b.dainius.lesson5.Application - finishing
16:37:28.965 [http-nio-9090-exec-1] INFO  o.a.c.c.C.[Tomcat].[localhost].[/] - Initializing Spring DispatcherServlet 'dispatcherServlet'
16:37:28.965 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Initializing Servlet 'dispatcherServlet'
16:37:28.966 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
16:37:28.997 [http-nio-9090-exec-1] INFO  c.b.d.l.rest.HeartbeatController - alive...
...
```


