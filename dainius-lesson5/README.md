# dainius-lesson5

1. Baseline is dainius-lesson4

2. Add docker packaging.

3. Add docker deployment.

# Build
```
./gradlew clean dockerBuild --console=plain
```

# Run
```
./gradlew dockerComposeUp
```

# Test
```
curl -X GET http://localhost:9090/api/v1/heartbeat

curl -X POST http://localhost:9090/api/v1/moving --header 'Content-Type: application/json' --data '{ "number": "2.0"}'
curl -X GET http://localhost:9090/api/v1/moving

```


