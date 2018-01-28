package config

import java.util.Properties

import scala.beans.BeanProperty

/**
  * Created by henneberger on 1/27/18.
  */
class KafkaConfig {
  @BeanProperty var properties: Properties = _
  @BeanProperty var topic: String = _
}
