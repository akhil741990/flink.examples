package org.soul;

import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.soul.data.generator.Transaction;
import org.soul.serde.LineItem;

public class TranscationTimeAssigner extends BoundedOutOfOrdernessTimestampExtractor<Transaction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1010341864302723024L;

	public TranscationTimeAssigner() {
		super(Time.seconds(5));
		// TODO Auto-generated constructor stub
	}

	@Override
	public long extractTimestamp(Transaction arg0) {
		// TODO Auto-generated method stub
		
		return arg0.getTimestamp();
	}

}
