package org.soul.udf;

import org.apache.flink.table.functions.ScalarFunction;

public class Mask extends ScalarFunction {

	boolean isStart;
	public Mask(boolean isStart){
		this.isStart = isStart;
	}
	
	public String eval(String input, int count){
		String output;
		if(count >= input.length()){
			output = getMaskString(input.length());
		}else{
			String mask =  getMaskString(count);
			if(isStart){
				String remaining = input.substring(count);
				output = mask + remaining;
			}else{
				String remaining = input.substring(0, input.length() - count); 
				output = remaining + mask;
			}
		}
		return output;
	}
	
	private String getMaskString(int count){
		StringBuilder s = new StringBuilder();
		for(int i =0 ; i < count ;i ++){
			s.append("X");
		}
		return s.toString();
	}
}
