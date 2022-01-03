package org.soul;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionType;

public class TxAllWindow implements AllWindowFunction<Transaction, List<Transaction>, TimeWindow> {

	@Override
	public void apply(TimeWindow arg0, Iterable<Transaction> arg1, Collector<List<Transaction>> arg2) throws Exception {
		// TODO Auto-generated method stub
		
		
		Stream<Transaction> stream = StreamSupport.stream(arg1.spliterator(),false);
		
		List<Transaction> txs = stream
									//.filter(t -> t.getType() == TransactionType.DEBIT)
									.sorted(new Comparator<Transaction>() {

			@Override
			public int compare(Transaction o1, Transaction o2) {
				// TODO Auto-generated method stub
				return (int) (o2.getAmount() - o1.getAmount());
			}
		}).limit(10).collect(Collectors.toList());
		arg2.collect(txs);
	}

}
