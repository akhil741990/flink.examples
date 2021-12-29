package org.soul.serde;

import java.io.File;
import java.io.IOException;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.soul.data.generator.Transaction;
import org.soul.data.generator.TransactionType;

public class LiSerdeExample {

	public static void main(String [] args){
		String jsonStr = "";
		ObjectMapper om =  new ObjectMapper();
		try {
			LineItem li =  om.readValue(new File("C:/Users/akhil/ML/li.json"), LineItem.class);
			System.out.println("LI " +  li.toString());
			
			Transaction tx = new Transaction(1000L, TransactionType.DEBIT, "Pune", System.currentTimeMillis());
			System.out.println("tx =" + tx);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
