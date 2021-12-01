package org.soul;

import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.soul.serde.LineItem;

public class LiCountWindowFunction implements WindowFunction<LineItem, LineItem, String, TimeWindow> {

	@Override
	public void apply(String shipMode, TimeWindow arg1, Iterable<LineItem> lis, Collector<LineItem> out)
			throws Exception {
		// TODO Auto-generated method stub
		float count = 0;
	    for(LineItem li :  lis){
	    	count =  count + li.getQuantity();
	    }
	    LineItem output = new LineItem();
	    output.setShipMode(shipMode);
	    output.setQuantity(count);
	    out.collect(output);
	}

}
