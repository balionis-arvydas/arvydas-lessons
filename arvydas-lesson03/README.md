# arvydas-lesson03

1. Baseline is arvydas-lesson2

2. Implement spring core value and component injection.

3. Implement spring boot application.

# Build
```
./gradlew clean build --console=plain
```

# Run
```
APP_SERVICE_FUNCTION=sum java -Dlogback.configurationFile=./src/test/resources/logback-test.xml -jar build/libs/arvydas-lesson03-all.jar 2 3 5
```
