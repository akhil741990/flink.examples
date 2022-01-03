package org.soul;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionDataGenerator;

public class TransactionSinkConnector {
	
	public static void main(String args[]) throws Exception{
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		//env.getConfig().setAutoWatermarkInterval(1000L);
		
		DataStream<Transaction> source =  env.addSource(new TransactionDataGenerator());
//				.assignTimestampsAndWatermarks(new TranscationTimeAssigner()); // Watermark after non-source operation
//				.assignTimestampsAndWatermarks(
//				        WatermarkStrategy.
//				                forBoundedOutOfOrderness(Duration.ofSeconds(20)));
		DataStream<String> sourceStr =  source.map(json -> json.toString());
		
//		final FileSink<String> sink = FileSink
//			    .forRowFormat(new Path("/home/akhil/ML/flink-data/"), new SimpleStringEncoder<String>("UTF-8"))
//			    .withRollingPolicy(
//			        DefaultRollingPolicy.builder()
//			            .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
//			            .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
//			            .withMaxPartSize(1024 * 1024 * 5)
//			            .build())
//				.build();

		
		
		//sourceStr.print();
		sourceStr.writeAsText("/home/akhil/ML/flink-data/",org.apache.flink.core.fs.FileSystem.WriteMode.OVERWRITE).setParallelism(1);
		
//		source
//			.keyBy(tx -> tx.getCity())
//			.timeWindow(Time.seconds(30))
//			.apply(new TopNDebitTransactionPerCity())
//			.print();
		    
		
		source
			.timeWindowAll(Time.seconds(30))
			.apply(new TxAllWindow())
			.print();
		
		env.execute("Flink File Sink Example");
	}
}
