# Class loading for Flink Kafka Source Timestamp Extractor

Docker and docker-compose are needed to test this.

In order build a job jar, an IDE like IntelliJ IDEA with the Scala plugin is also needed

In order to reproduce the problem do the following:

    1. cd docker
    2. docker-compose build
    3. docker-compose up
    4. Navigate to localhost:8081 in your browser
    5. submit jar in GUI from target/scala-2.11/flink...jar

This leads to a failed job with the following exception: **java.lang.ClassNotFoundException: se.ragnarsson.lage.MyTimestampExtractor** which is a class in the user-submitted fat-jar. I have verified that building the timestamp extractor into its own jar and placing it in /opt/flink/lib works. 


To build a new fat-jar, just run sbt assembly in the project root. A prebuilt fat-jar is already included in the repo.
