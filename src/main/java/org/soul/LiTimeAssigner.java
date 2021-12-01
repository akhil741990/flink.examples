package org.soul;

import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.soul.serde.LineItem;

public class LiTimeAssigner extends BoundedOutOfOrdernessTimestampExtractor<LineItem> {

	public LiTimeAssigner() {
		super(Time.seconds(5));
		// TODO Auto-generated constructor stub
	}

	@Override
	public long extractTimestamp(LineItem arg0) {
		// TODO Auto-generated method stub
		
		return arg0.getCommitDate().getTime();
	}

}
