# arvydas-lesson04

1. Baseline is arvydas-lesson03

2. Implement spring web.

3. Uses web client test.

# Build
```
./gradlew clean build --console=plain
```

# Run
```
APP_SERVICE_FUNCTION=sum java -Dlogback.configurationFile=./src/test/resources/logback-test.xml -jar build/libs/arvydas-lesson04-all.jar
```

# Test
```
curl -X GET http://localhost:9090/api/v1/heartbeat

curl -X POST http://localhost:9090/api/v1/moving --header 'Content-Type: application/json' --data '{ "number": "2.0"}'
curl -X GET http://localhost:9090/api/v1/moving

```


