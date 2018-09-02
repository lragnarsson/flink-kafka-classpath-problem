# Class loading for Flink Kafka Source Timestamp Extractor
This turned out to be a bug introduced in Flink 1.4.1 but fixed in 1.4.2
https://issues.apache.org/jira/browse/FLINK-8741

Original StackOverflow question: https://stackoverflow.com/questions/49112172/class-loading-for-flink-kafka-source-timestamp-extractor

Docker and docker-compose are needed to test this.

In order build a job jar, sbt 1.x is also needed.

To reproduce the problem do the following:

    1. cd docker
    2. docker-compose build
    3. docker-compose up
    4. Navigate to localhost:8081 in your browser
    5. submit jar in GUI from target/scala-2.11/flink...jar

This leads to a failed job with the following exception: **java.lang.ClassNotFoundException: se.ragnarsson.lage.MyTimestampExtractor** which is a class in the user-submitted fat-jar. I have verified that building the timestamp extractor into its own jar and placing it in /opt/flink/lib works. 


To build a new fat-jar, just run sbt assembly in the project root. A prebuilt fat-jar is already included in the repo.
