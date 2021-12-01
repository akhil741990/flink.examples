# flink.examples

Kafka: 


A) start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

B) start brober
bin/kafka-server-start.sh config/server.properties


C) List topic
bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list

D) console consumer
bin/kafka-console-consumer.sh --topic --from-beginning --bootstrap-server localhost:9092

E)Generate tpch data

./kafka-tpch load --brokers localhost:9092 --prefix tpch --tpch-type sf1

./kafka-tpch load --brokers localhost:9092 --prefix tpch --tpch-type sf1 --tables lineitem



Flink : 
Cluster setup :https://data-flair.training/blogs/install-run-flink-multi-node-cluster/

A) start
./bin/start-cluster.sh

B) stop
./bin/stop-cluster.sh

C)UI :  https://localhost:8081 
