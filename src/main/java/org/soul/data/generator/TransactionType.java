package org.soul.data.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TransactionType {

	CREDIT,DEBIT;
	private static final List<TransactionType> vals = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int size = vals.size();
	private static final Random random = new Random();
	
	public static TransactionType getRandom(){
		return vals.get(random.nextInt(vals.size()));
	}
}
