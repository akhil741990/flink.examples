package org.soul.data.generator;

import java.time.Instant;
import java.util.Random;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class TransactionDataGenerator implements SourceFunction<Transaction>{

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
			Long timestamp = System.currentTimeMillis();
			Long amt = (long) amount.nextInt(20) * 1000;
			TransactionType type =  TransactionType.getRandom();
			Transaction tx = new Transaction(amt, type, cities[cityIndex], timestamp);
			ctx.collectWithTimestamp(tx, Instant.now().toEpochMilli());
			Thread.sleep(500);
		}
	}

}
