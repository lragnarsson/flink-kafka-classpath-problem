package se.ragnarsson.lage

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

class MyTimestampExtractor(maxOutOfOrderness: Long) extends AssignerWithPeriodicWatermarks[Option[(Long, String)]] {
  private var currentMaxTimestamp = 0L

  override def getCurrentWatermark: Watermark = {
    new Watermark(currentMaxTimestamp - maxOutOfOrderness)
  }

  override def extractTimestamp(element: Option[(Long, String)], previousElementTimestamp: Long): Long = {
    element match {
      case Some(msg) => msg._1
      case None => 0L
    }
  }
}
