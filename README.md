# kafka-meetup-producer

Pushes the meetup event stream to a kafka queue to provide 


## Set up Kafka
 - Download kafka

Add JMX port 
 > export JMX_PORT=9999

Start Zookeeper
 > bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka 
 > bin/kafka-server-start.sh config/server.properties
 
Create a topic 
 > bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --config retention.bytes=100000000 --topic events

Verify
 > bin/kafka-topics.sh --list --zookeeper localhost:2181
 
## Run

Main config/local.yaml

## Verify

 > bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic events --from-beginning
 
## Metrics

 > bin/kafka-run-class.sh kafka.tools.JmxTool --object-name kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec 