package org.soul.data.generator;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class Transaction {

	Long amount;
	TransactionType  type;
	String city;
	Long timestamp;
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object other){
		if(this == other){
			return true;
		}else if (!(other instanceof Transaction)){
			return false;
		}else {
			Transaction that = (Transaction) other;
			if(this.amount == that.amount && this.type == that.type && this.city.equals(that.city) &&
					this.timestamp == that.timestamp){
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	@Override
	public int hashCode(){
		return 41 *
				 ( 41 *
				   ( 41 * 
					 (41 + this.amount.hashCode()) + this.city.hashCode()
				    ) + this.type.hashCode() 
				  ) + this.timestamp.hashCode()
				 ;
	}
	@Override
	public String toString() {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{\"amount\"=\"" + amount + "\", \"type\"=\"" + type + "\", \"city\"=\"" + city + "\", \"timestamp\"=\"" + timestamp + "\"}";

		}
	}
	public Transaction(){
		
	}
	public Transaction(Long amount, TransactionType type, String city, Long timestamp) {
		super();
		this.amount = amount;
		this.type = type;
		this.city = city;
		this.timestamp = timestamp;
	}
	
	
}
