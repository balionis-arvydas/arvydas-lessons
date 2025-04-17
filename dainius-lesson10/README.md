# dainius-lesson10

This project is a clone of lesson9 except
- confluent is used for kafka engine  
- message is an avro rather than a json
- use of kafka registry tracking message format.

## Build

```
./gradlew clean build --console=plain
./gradlew dockerBuild --console=plain
```

## Deploy

See [local](local/README.md)
