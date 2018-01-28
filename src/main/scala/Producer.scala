import java.net.URL
import java.util.Properties

import com.fasterxml.jackson.core.{JsonFactory, TreeNode}
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.slf4j.{Logger, LoggerFactory}

object Producer {
  val logger: Logger = LoggerFactory.getLogger(Producer.getClass)

  def consume(props: Properties, topic: String) {
    val url = new URL("http://stream.meetup.com/2/open_events")
    logger.debug("Reading events from {}", url.toString)

    val conn = url.openConnection()

    val jsonFactory = new JsonFactory(new ObjectMapper)
    val parser = jsonFactory.createParser(conn.getInputStream)

    val kafkaProducer = new KafkaProducer[String,String](props)
    while (parser.nextToken() != null)  {
      val record: TreeNode = parser.readValueAsTree()
      val producerRecord = new ProducerRecord[String, String](topic, record.toString)
      kafkaProducer.send(producerRecord)
    }

    kafkaProducer.close()
  }
}