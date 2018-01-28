import java.io.{File, FileInputStream}

import config.KafkaConfig
import org.slf4j.{Logger, LoggerFactory}
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

/**
  * Created by henneberger on 1/27/18.
  */
object Main {
  val logger: Logger = LoggerFactory.getLogger(Main.getClass)

  /**
    * @param args config location
    */
  def main(args: Array[String]): Unit = {
//    org.apache.log4j.BasicConfigurator.configure()
    val yaml = new Yaml(new Constructor(classOf[KafkaConfig]))
    val input = new FileInputStream(new File(args(0)))
    val config = yaml.load(input).asInstanceOf[KafkaConfig]

    logger.info("Starting kafka producer... topic: {}", config.topic)
    Producer.consume(config.properties, config.topic)
  }
}
