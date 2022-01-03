package org.soul.data.generator;

import java.time.Instant;
import java.util.Calendar;
import java.util.Random;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

public class TransactionDataGenerator extends RichParallelSourceFunction<Transaction>{

	/**
	 * 
	 */
	
	String [] cities = new String[]{"Pune","Mumbai","Chennai","Kolkata","Banglore","Kochi","Delhi"}; 
	private volatile boolean running = true;
	
	private static final long serialVersionUID = 3437279123313116451L;

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		running = false;
	}

	@Override
	public void run(org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext<Transaction> ctx)
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
			ctx.collectWithTimestamp(tx, timestamp);
			ctx.emitWatermark(new Watermark(timestamp)); //Watermark @Source
			//ctx.collect(tx);
			Thread.sleep(100);
		}
	}

}
