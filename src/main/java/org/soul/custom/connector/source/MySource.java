package org.soul.custom.connector.source;

import java.time.Instant;
import java.util.Random;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.table.data.GenericRowData;
import org.apache.flink.table.data.RowData;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionType;

//https://flink.apache.org/2021/09/07/connector-table-sql-api-part1.html
public class MySource extends RichSourceFunction<RowData> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3196362214383098455L;
	String [] cities = new String[]{"Pune","Mumbai","Chennai","Kolkata","Banglore","Kochi","Delhi"}; 
	private volatile boolean running = true;

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext<RowData> ctx)
			throws Exception {
		// TODO Auto-generated method stub
		Random city = new Random();
		
		Random amount = new Random();
		
		
		while(running){
			int cityIndex = city.nextInt(cities.length);
			Long timestamp =  Instant.now().toEpochMilli();
			Long amt = (long) amount.nextInt(50) * 1000;
			TransactionType type =  TransactionType.getRandom();
			Transaction tx = new Transaction(amt, type, cities[cityIndex], timestamp);
			//ctx.collectWithTimestamp(tx, timestamp);
			ctx.emitWatermark(new Watermark(timestamp)); //Watermark @Source
			ctx.collect(GenericRowData.of(tx));
			Thread.sleep(100);
		}
		
	}

}
