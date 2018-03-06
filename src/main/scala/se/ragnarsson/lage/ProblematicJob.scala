package se.ragnarsson.lage


import java.util.Properties

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

/**
  *
  */
object ProblematicJob {
  def main(args: Array[String]) {
    val maxOutOfOrderness = 10 * 1000
    val kafkaTopic = "my-topic"
    val kafkaProperties = new Properties()
    kafkaProperties.setProperty("group.id", "my-group")
    kafkaProperties.setProperty("bootstrap.servers", "kafka:9092")

    // Set up the execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    // Define Kafka source:
    val kafkaSource = new FlinkKafkaConsumer011[Option[(Long, String)]](
      kafkaTopic,
      new MyDeserializationSchema,
      kafkaProperties)

    // Assign timestamps and watermarks to Kafka source:
    kafkaSource.assignTimestampsAndWatermarks(
      new MyTimestampExtractor(maxOutOfOrderness))

    val inputStream: DataStream[Option[(Long, String)]] =
      env.addSource(kafkaSource)


    inputStream.filter(_.isDefined) // Do something with the stream

    env.execute()
  }
}
