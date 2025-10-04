# arvydas-lesson10

This project shows how to write and read kafka messages (with default kafka config). It is a clone from lesson08.

The messages are in avro format. The kafka engine and registry are provided by confluent.io.  

## Build

```
./gradlew clean build --console=plain
./gradlew dockerBuild --console=plain
```

## Deploy

See [local](local/README.md)
