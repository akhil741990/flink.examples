package org.soul.table;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionDataGenerator;

public class TableExample {

		public static void main(String args[]) throws Exception{
			final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
			DataStream<Transaction> source =  env.addSource(new TransactionDataGenerator());

			//DataStream<String> sourceStr =  source.map(json -> json.toString());
			
			
			
			Table tab = tEnv.fromDataStream(source);
			
			tEnv.createTemporaryView("transaction", tab);
			
			Table result = tEnv.sqlQuery("select * from transaction");
			
			tEnv.toDataStream(result).print();
			env.execute("Table Example");
			
		}
}
