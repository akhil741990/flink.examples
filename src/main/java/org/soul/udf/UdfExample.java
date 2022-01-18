package org.soul.udf;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionDataGenerator;

public class UdfExample {

	public static void main(String args[]) throws Exception{
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
		
		DataStream<Transaction> source = env.addSource(new TransactionDataGenerator());
		
		Table t = tEnv.fromDataStream(source);
		
		tEnv.createTemporaryView("transaction", t);
		
		// register UDF
		tEnv.createTemporarySystemFunction("mask", new Mask(true));
		Table result = tEnv.sqlQuery("select amount, mask(city,2) from transaction");
		tEnv.toDataStream(result).print();
		env.execute("UDF example");
	}
}
