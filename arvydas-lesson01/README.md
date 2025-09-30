# arvydas-lesson01

1. Monolith vs Dependencies

   What are java libraries?
   Why do I need them?
   How are they managed? 

2. Java CLI vs Gradle

   What are java build-in command line tools?
   Why do I hate them?
   What does Gradle solve?

3. Java module directory structure

   What is a package?  
   Why do I need it?
   What are package resources?
   What are unit tests?
   How do I keep main code clean from test code?

4. Java unit tests

   What are the unit tests?
   How does JUnit help me to run these tests?

# Build
```
./gradlew clean build --console=plain
```

# Run
```
java -Dlogback.configurationFile=./src/test/resources/logback-test.xml -jar build/libs/arvydas-lesson01-all.jar arg1 arg2 arg3
```