package org.soul.serde;

import java.io.File;
import java.io.IOException;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class LiSerdeExample {

	public static void main(String [] args){
		String jsonStr = "";
		ObjectMapper om =  new ObjectMapper();
		try {
			LineItem li =  om.readValue(new File("C:/Users/akhil/ML/li.json"), LineItem.class);
			System.out.println("LI " +  li.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
