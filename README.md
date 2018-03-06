# Class loading for Flink Kafka Source Timestamp Extractor

Fairly new versions of docker, docker-compose are needed to test this.
In order build a job jar, an IDE like IntelliJ IDEA with the SBT plugin is also needed

In order to reproduce the problem do the following:

    1. cd docker
    2. docker-compose build
    3. docker-compose up
    4. Navigate to localhost:8081 in your browser
    5. submit jar in GUI from target/scala-2.11/flink...jar

Expected result:

Failed job with the following exception: **java.lang.ClassNotFoundException: se.ragnarsson.lage.MyTimestampExtractor**