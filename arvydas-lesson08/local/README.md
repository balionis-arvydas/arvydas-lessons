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
% curl -v -X GET http://localhost:8081/api/v1/heartbeat
...
< HTTP/1.1 200 
...

% docker logs local-producer --follow
...
10:26:29.787 [main] INFO  c.b.d.lesson08.producer.Application - starting container_name: local-kafka
...
10:36:02.379 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
10:36:02.408 [http-nio-9090-exec-1] INFO  c.b.d.l.p.rest.HeartbeatController - alive...
10:36:02.408 [http-nio-9090-exec-1] INFO  c.b.d.l.p.service.HeartbeatService - name=myProducerService
...

% curl -v -X GET http://localhost:8082/api/v1/heartbeat   
...
< HTTP/1.1 200 
...

% docker logs local-consumer --follow
...
10:26:29.738 [main] INFO  c.b.d.lesson08.consumer.Application - starting
...
10:43:45.407 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
10:43:45.442 [http-nio-9090-exec-1] INFO  c.b.d.l.c.rest.HeartbeatController - alive...
10:43:45.443 [http-nio-9090-exec-1] INFO  c.b.d.l.c.service.HeartbeatService - name=myConsumerService
...

```
### Test kafka brokers

```
docker exec -it local-kafka bash 
$KAFKA_HOME/bin/kafka-topics.sh --list --zookeeper local-zookeeper:2181
my-messages
```

### Test kafka producer

```
% curl -v -X POST http://localhost:8081/api/v1/producer/message \
--header 'Content-Type: application/json' \
--data '{ "messageId": "00000000-0000-0000-0000-000000000000", "message":"hello" }'   
...
< HTTP/1.1 200 
< Content-Length: 0
...

% docker logs local-producer --follow
...
10:44:43.630 [kafka-producer-network-thread | producer-1] INFO  o.a.k.c.p.i.TransactionManager - [Producer clientId=producer-1] ProducerId set to 0 with epoch 0
10:44:43.733 [http-nio-9090-exec-3] INFO  c.b.d.l.p.stream.KafkaProducer - sent messageId=00000000-0000-0000-0000-000000000000
...

```

### Test kafka consumer

```
% curl -v -X GET http://localhost:8082/api/v1/consumer/message   
...
< HTTP/1.1 200 
{"messages":[]}
...

% docker logs local-consumer --follow
...
10:43:45.407 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
10:43:45.442 [http-nio-9090-exec-1] INFO  c.b.d.l.c.rest.HeartbeatController - alive...
10:43:45.443 [http-nio-9090-exec-1] INFO  c.b.d.l.c.service.HeartbeatService - name=myConsumerService
10:52:32.690 [http-nio-9090-exec-3] INFO  c.b.d.l.c.rest.ConsumerController - page=null
10:52:32.691 [http-nio-9090-exec-3] INFO  c.b.d.l.c.service.ConsumerService - pageSize=100, page=0
10:52:32.691 [http-nio-9090-exec-3] INFO  c.b.d.l.c.rest.ConsumerController - messages=[]
...

```
