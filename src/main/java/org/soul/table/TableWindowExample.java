package org.soul.table;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.Tumble;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionDataGenerator;

public class TableWindowExample {

		public static void main(String args[]) throws Exception{
			final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
			DataStream<Transaction> source =  env.addSource(new TransactionDataGenerator());

			DataStream<String> sourceStr =  source.map(json -> json.toString());
			
			
			
			Table table =
				    tEnv.fromDataStream(
				        source,
				        Schema.newBuilder()
				            .columnByMetadata("rowtime", "TIMESTAMP_LTZ(3)")
				            .watermark("rowtime", "SOURCE_WATERMARK()")
				            .build());
				table.printSchema();
				/*
				(
					  `amount` BIGINT,
					  `type` RAW('org.soul.data.generator.TransactionType', '...'),
					  `city` STRING,
					  `timestamp` BIGINT,
					  `rowtime` TIMESTAMP_LTZ(3) *ROWTIME* METADATA,
					  WATERMARK FOR `rowtime`: TIMESTAMP_LTZ(3) AS SOURCE_WATERMARK()
					)
				 */
		
//				tEnv.createTemporaryView("transaction", table);
//				
//				Table result = tEnv.sqlQuery("select * from transaction");
//				
//				tEnv.toChangelogStream(result).print();		
				
//			Table tab = tEnv.fromDataStream(source);
//			
			tEnv.createTemporaryView("transaction", table);
			
			
			
			
			Table result = tEnv.sqlQuery("SELECT SUM(amount), city, window_start, window_end  FROM TABLE(TUMBLE(TABLE transaction, DESCRIPTOR(rowtime), INTERVAL '30' SECONDS)) GROUP BY  city, window_start, window_end");
			
			tEnv.toChangelogStream(result).print();
			env.execute("Table Example");
			
		}
}
// Ref : https://nightlies.apache.org/flink/flink-docs-master/docs/dev/table/data_stream_api/


/*
Error :
Exception in thread "main" java.lang.UnsupportedOperationException: Currently Flink doesn't support individual window table-valued function TUMBLE(time_col=[rowtime], size=[1 min]).
 Please use window table-valued function with aggregate together using window_start and window_end as group keys.
        at org.apache.flink.table.planner.plan.nodes.exec.stream.StreamExecWindowTableFunction.translateToPlanInternal(StreamExecWindowTableFunction.java:61)

 */