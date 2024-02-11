# dainius-lesson3

1. Baseline is dainius-lesson2

2. Implement spring core value and component injection.

3. Implement spring boot application.

# Build
```
./gradlew clean build --console=plain
```

# Run
```
APP_SERVICE_FUNCTION=sum java -Dlogback.configurationFile=./src/test/resources/logback-test.xml -jar build/libs/dainius-lesson3-all.jar 2 3 5
```
