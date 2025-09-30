# arvydas-lesson5

1. Baseline is arvydas-lesson5

2. Add postgres sql.

3. Add spring jpa.

4. Add liquibase into tests.

5. Have docker compose which starts postres, liquibase (commented), service 

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

## Tail Docker Logs

```
% docker container ls
CONTAINER ID   IMAGE                    COMMAND                  CREATED          STATUS          PORTS                    NAMES
2bb47c02965d   arvydas-lesson6:latest   "/bin/sh -c 'java $J…"   12 seconds ago   Up 11 seconds   0.0.0.0:8080->9090/tcp   local-service
c7c914949a35   postgres:latest          "docker-entrypoint.s…"   12 seconds ago   Up 11 seconds   0.0.0.0:5432->5432/tcp   local-postgres
arvydas@bamac01 local % docker logs -f 2bb47c02965d
% docker logs -f 2bb47c02965d
19:30:17.774 [main] INFO  c.b.arvydas.lesson6.Application - starting
19:30:18.611 [main] WARN  o.s.b.l.logback.LogbackLoggingSystem - Ignoring 'logback.configurationFile' system property. Please use 'logging.config' instead.

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

19:30:18.846 [main] INFO  c.b.arvydas.lesson6.Application - Starting Application using Java 17-ea with PID 1 (/app/app.jar started by root in /app)
19:30:18.847 [main] INFO  c.b.arvydas.lesson6.Application - The following 1 profile is active: "docker"
19:30:20.326 [main] INFO  o.s.d.r.c.RepositoryConfigurationDelegate - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
19:30:20.461 [main] INFO  o.s.d.r.c.RepositoryConfigurationDelegate - Finished Spring Data repository scanning in 115 ms. Found 1 JPA repository interface.
19:30:21.730 [main] INFO  o.s.b.w.e.j.JettyServletWebServerFactory - Server initialized with port: 9090
19:30:21.736 [main] INFO  org.eclipse.jetty.server.Server - jetty-12.0.5; built: 2023-12-18T14:06:32.502Z; git: 3aed62e4959bb8c01f5975fe81e078e3ff626126; jvm 17-ea+14
19:30:21.773 [main] INFO  o.e.j.s.h.ContextHandler.application - Initializing Spring embedded WebApplicationContext
19:30:21.774 [main] INFO  o.s.b.w.s.c.ServletWebServerApplicationContext - Root WebApplicationContext: initialization completed in 2717 ms
19:30:22.108 [main] INFO  o.e.j.s.DefaultSessionIdManager - Session workerName=node0
19:30:22.125 [main] INFO  o.e.j.server.handler.ContextHandler - Started osbwej.JettyEmbeddedWebAppContext@1946384{application,/,b=file:/tmp/jetty-docbase.9090.14803398072063920153/,a=AVAILABLE,h=oeje10s.SessionHandler@6ac4c3f7{STARTED}}
19:30:22.126 [main] INFO  o.e.j.e.s.ServletContextHandler - Started osbwej.JettyEmbeddedWebAppContext@1946384{application,/,b=file:/tmp/jetty-docbase.9090.14803398072063920153/,a=AVAILABLE,h=oeje10s.SessionHandler@6ac4c3f7{STARTED}}
19:30:22.142 [main] INFO  org.eclipse.jetty.server.Server - Started oejs.Server@2e463f4{STARTING}[12.0.5,sto=0] @5810ms
19:30:22.536 [main] INFO  o.h.jpa.internal.util.LogHelper - HHH000204: Processing PersistenceUnitInfo [name: default]
19:30:22.728 [main] INFO  org.hibernate.Version - HHH000412: Hibernate ORM core version 6.4.1.Final
19:30:22.813 [main] INFO  o.h.c.i.RegionFactoryInitiator - HHH000026: Second-level cache disabled
19:30:23.535 [main] INFO  o.s.o.j.p.SpringPersistenceUnitInfo - No LoadTimeWeaver setup: ignoring JPA class transformer
19:30:23.580 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
19:30:23.980 [main] INFO  com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@ce655b9
19:30:23.987 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
19:30:24.065 [main] WARN  org.hibernate.orm.deprecation - HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
19:30:25.321 [main] INFO  o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
19:30:25.324 [main] INFO  o.s.o.j.LocalContainerEntityManagerFactoryBean - Initialized JPA EntityManagerFactory for persistence unit 'default'
19:30:25.333 [main] INFO  c.b.d.l.c.AppConfigurationProperties - appConfigurationProperties=AppConfigurationProperties(name=myService)
19:30:26.322 [main] WARN  o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration - spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
19:30:27.695 [main] INFO  o.e.j.s.h.ContextHandler.application - Initializing Spring DispatcherServlet 'dispatcherServlet'
19:30:27.696 [main] INFO  o.s.web.servlet.DispatcherServlet - Initializing Servlet 'dispatcherServlet'
19:30:27.698 [main] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 2 ms
19:30:27.771 [main] INFO  o.e.jetty.server.AbstractConnector - Started ServerConnector@7995d760{HTTP/1.1, (http/1.1)}{0.0.0.0:9090}
19:30:27.780 [main] INFO  o.s.b.w.e.jetty.JettyWebServer - Jetty started on port 9090 (http/1.1) with context path '/'
19:30:27.813 [main] INFO  c.b.arvydas.lesson6.Application - Started Application in 9.798 seconds (process running for 11.482)
19:30:27.825 [main] INFO  c.b.arvydas.lesson6.Application - finishing
```

## Call service heartbeat under docker
```
curl -X GET http://localhost:8080/api/v1/heartbeat

// see docker container logs (see: 'Tail Docker Logs' above for how-to)
... 
arvydas@bamac01 ~ % docker logs -f 99a13e4e3ac0                                     
...
19:30:27.771 [main] INFO  o.e.jetty.server.AbstractConnector - Started ServerConnector@7995d760{HTTP/1.1, (http/1.1)}{0.0.0.0:9090}
19:30:27.780 [main] INFO  o.s.b.w.e.jetty.JettyWebServer - Jetty started on port 9090 (http/1.1) with context path '/'
19:30:27.813 [main] INFO  c.b.arvydas.lesson6.Application - Started Application in 9.798 seconds (process running for 11.482)
19:30:27.825 [main] INFO  c.b.arvydas.lesson6.Application - finishing
19:33:38.761 [qtp1147334625-44] INFO  c.b.d.l.rest.HeartbeatController - alive......
```

## Call pet store service under docker
```
curl -X POST http://localhost:8080/api/v1/pet --header 'Content-Type: application/json' --data '{ "pet": { "name": "myDog", "status": "AVAILABLE" } }'
{"id":"864bbd39-4ce5-43ee-8da6-b7701ae16c5b"}
...
curl -X GET  http://localhost:8080/api/v1/pet/864bbd39-4ce5-43ee-8da6-b7701ae16c5b
{"id":"864bbd39-4ce5-43ee-8da6-b7701ae16c5b","name":"myDog","status":"AVAILABLE"}
...
```

## Postgres troubleshooting 

```
arvydas@bamac01 local % docker container ls
CONTAINER ID   IMAGE                    COMMAND                  CREATED         STATUS         PORTS                    NAMES
8157dab0bad5   arvydas-lesson6:latest   "/bin/sh -c 'java $J…"   9 minutes ago   Up 9 minutes   0.0.0.0:8080->9090/tcp   local-service
80af3e73a2e8   postgres:latest          "docker-entrypoint.s…"   9 minutes ago   Up 9 minutes   0.0.0.0:5432->5432/tcp   local-postgres
...
arvydas@bamac01 local % docker exec -it 80af3e73a2e8 sh
...
# psql -U postgres
psql (16.2 (Debian 16.2-1.pgdg120+2))
Type "help" for help.

postgres=# \d
Did not find any relations.
postgres=# \l
List of databases
Name    |  Owner   | Encoding | Locale Provider |  Collate   |   Ctype    | ICU Locale | ICU Rules |   Access privileges   
------------+----------+----------+-----------------+------------+------------+------------+-----------+-----------------------
arvydas_db | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
postgres   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
template0  | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
|          |          |                 |            |            |            |           | postgres=CTc/postgres
template1  | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
|          |          |                 |            |            |            |           | postgres=CTc/postgres
(4 rows)
...
postgres=# \c arvydas_db;
You are now connected to database "arvydas_db" as user "postgres".
...
arvydas_db=# \d
List of relations
Schema | Name | Type  |  Owner   
--------+------+-------+----------
public | pets | table | postgres
(1 row)
...
arvydas_db=# \d pets
Table "public.pets"
Column   |            Type             | Collation | Nullable | Default
------------+-----------------------------+-----------+----------+---------
pet_id     | uuid                        |           | not null |
name       | character varying(100)      |           | not null |
status     | character(30)               |           | not null |
created_at | timestamp without time zone |           | not null |
updated_at | timestamp without time zone |           | not null |
Indexes:
"pets_pkey" PRIMARY KEY, btree (pet_id)
...
arvydas_db=# select * from pets;
pet_id                | name  |             status             |         created_at         |         updated_at         
--------------------------------------+-------+--------------------------------+----------------------------+----------------------------
864bbd39-4ce5-43ee-8da6-b7701ae16c5b | myDog | AVAILABLE                      | 2024-02-28 19:48:23.658357 | 2024-02-28 19:48:23.658357
(1 row)
...
arvydas_db=# \q
...
# exit
% ... 
```
