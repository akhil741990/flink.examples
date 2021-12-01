package org.soul.serde;

import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.nio.charset.StandardCharsets;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class LineItemSerializationSchema implements KafkaSerializationSchema<LineItem>{


	private String topic;
    private ObjectMapper mapper;


    public LineItemSerializationSchema(String topic) {
		// TODO Auto-generated constructor stub
    	super();
    	this.topic = topic;
	}

	@Override
	public ProducerRecord<byte[], byte[]> serialize(LineItem arg0, Long arg1) {
		// TODO Auto-generated method stub
		 return new ProducerRecord<byte[], byte[]>(topic, arg0.toString().getBytes(StandardCharsets.UTF_8));
	}

}
