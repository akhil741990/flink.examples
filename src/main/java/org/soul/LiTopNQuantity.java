package org.soul;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.soul.serde.LineItem;

public class LiTopNQuantity implements WindowFunction<LineItem, List<LineItem>, String, TimeWindow> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void apply(String arg0, TimeWindow arg1, Iterable<LineItem> arg2, Collector<List<LineItem>> arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	   
	   Stream<LineItem> stream = StreamSupport.stream(arg2.spliterator(), false);
	   
	   List<LineItem> lis =  stream.sorted(new Comparator<LineItem>() {

		@Override
		public int compare(LineItem o1, LineItem o2) {
			// TODO Auto-generated method stub
			return (int) (o2.getQuantity() - o1.getQuantity());
		}
	   }).limit(5).collect(Collectors.toList());
	   
	   arg3.collect(lis);
	}

}
