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
10:26:29.787 [main] INFO  c.b.d.lesson11.producer.Application - starting container_name: local-kafka
...
10:36:02.379 [http-nio-9090-exec-1] INFO  o.s.web.servlet.DispatcherServlet - Completed initialization in 1 ms
10:36:02.408 [http-nio-9090-exec-1] INFO  c.b.d.l.p.rest.HeartbeatController - alive...
10:36:02.408 [http-nio-9090-exec-1] INFO  c.b.d.l.p.service.HeartbeatService - name=myProducerService
...

% curl -v -X GET http://localhost:9092/api/v1/heartbeat   
...
< HTTP/1.1 200 
...

% docker logs local-consumer --follow
...
10:26:29.738 [main] INFO  c.b.d.lesson11.consumer.Application - starting
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
% curl -v -X POST http://localhost:9091/api/v1/producer/message \
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
% curl -v -X GET http://localhost:9092/api/v1/consumer/message   
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

#### Test kafka consumer observability 

```
% curl -s -X GET http://localhost:9002/actuator/health | jq .
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 62671097856,
        "free": 53008785408,
        "threshold": 10485760,
        "path": "/app/.",
        "exists": true
      }
    },
    "livenessState": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    },
    "readinessState": {
      "status": "UP"
    }
  },
  "groups": [
    "liveness",
    "readiness"
  ]
}
```

```
% curl -s -X GET 'http://localhost:9002/actuator/prometheus?includedNames=kafka_consumer_fetch_manager_records_lag%2Ckafka_consumer_request_rate'
# HELP kafka_consumer_fetch_manager_records_lag The latest lag of the partition
# TYPE kafka_consumer_fetch_manager_records_lag gauge
kafka_consumer_fetch_manager_records_lag{client_id="dainius-lesson11-consumer-0",kafka_version="3.7.1",partition="0",spring_id="consumerFactory.dainius-lesson11-consumer-0",topic="my-messages"} 0.0
# HELP kafka_consumer_request_rate The number of requests sent per second
# TYPE kafka_consumer_request_rate gauge
kafka_consumer_request_rate{client_id="dainius-lesson11-consumer-0",kafka_version="3.7.1",spring_id="consumerFactory.dainius-lesson11-consumer-0"} 0.6845685010158113
```

## Run Monitoring

In order to start a full environment, run:
```shell
COMPONENTS=monitoring make up
```

To kill all the containers, execute:
```shell
COMPONENTS=monitoring make down
```

## Test Monitoring

### Test Prometheus 

```shell
curl -G 'http://localhost:9090/api/v1/metadata'
...
{"status":"success","data":{"application_ready_time_seconds":[{"type":"gauge","unit":"","help":"Time taken... }
```

### Test Grafana

```shell
curl -X GET 'http://admin:grafana@localhost:3000/api/datasources'
...
[{"id":1,"uid":"PBFA97CFB590B2093","orgId":1,"name":"Prometheus","type":"prometheus",...,"readOnly":false}] 
```

```shell
curl -X POST 'http://admin:grafana@localhost:3000/api/dashboards/db' \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  --data-binary '@docker/config/dashboards/demo-consumer.json'
...
curl http://localhost:3000 
...
username: admin
password: grafana
```

### Test Jaeger 

#### Grab traceId from producer logs

```
% curl -v -X POST http://localhost:9091/api/v1/producer/message \
--header 'Content-Type: application/json' \
--data '{ "messageId": "00000000-0000-0000-0000-000000000000", "message":"hello" }'   
...
< HTTP/1.1 200 
< Content-Length: 0
...

% docker logs local-producer --follow
...
10:48:57.111 [fb3b7415be34f3d48cc394236bcb4c31,5924168bffca07ff] [http-nio-9090-exec-3] INFO  c.b.d.l.p.rest.ProducerController - sendMessageRequest=class SendMessageRequest {
    messageId: 00000000-0000-0000-0000-000000000000
    message: hello
}
...
```

#### Find traceId in Jaeger UI (macOS)

```
open -a "Google Chrome" "http://localhost:16686/" 

// search for fb3b7415be34f3d48cc394236bcb4c31
```





