package se.ragnarsson.lage

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema

class MyDeserializationSchema extends AbstractDeserializationSchema[Option[(Long, String)]]{

  override def deserialize(message: Array[Byte]): Option[(Long, String)] = {
    val timeStamp = System.currentTimeMillis
    val messageString = new String(message)
    Some((timeStamp, messageString))
  }
}
