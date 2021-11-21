package org.soul;

import java.util.Properties;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class KafkaSourceToSink {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/*
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataStream<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 *
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * https://flink.apache.org/docs/latest/apis/streaming/index.html
		 *
		 */

		// execute program
		env.getConfig().getGlobalJobParameters();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("group.id", "test");
		ObjectMapper om = new ObjectMapper();

//		DataStreamSource<ObjectNode> stream = env
//			.addSource(new FlinkKafkaConsumer<>("tpch_",  new JSONKeyValueDeserializationSchema(false), properties));
		
		DataStream<String> stream = env
				.addSource(new FlinkKafkaConsumer<>("tpch_nation",  new SimpleStringSchema(), properties));
		
		//DataStream<LineItem> liStream = stream.map(json -> om.readValue(json, LineItem.class));
		
		
		//stream.print();

		
		stream.addSink(
				new FlinkKafkaProducer<String>(
					"flink_nation",
					new KeyedSerializationSchemaWrapper<>(new SimpleStringSchema()),
					properties,
					FlinkKafkaProducer.Semantic.EXACTLY_ONCE));
		


		
		
		
//		DataStream<String> s = stream
//			.timeWindowAll(Time.seconds(10))
//			.sum(0);
//		
//		System.out.println("Summ =====>");
//		
//		s.print();
		
		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}

}
