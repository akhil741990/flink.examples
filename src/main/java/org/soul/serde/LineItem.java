package org.soul.serde;

import java.util.Date;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;

public class LineItem {
	
	
	@Override
	public String toString() {
		return "LineItem [rowNumber=" + rowNumber + ", orderKey=" + orderKey + ", partKey=" + partKey + ", supplierKey="
				+ supplierKey + ", lineNumber=" + lineNumber + ", quantity=" + quantity + ", extendedPrice="
				+ extendedPrice + ", discount=" + discount + ", tax=" + tax + ", returnFlag=" + returnFlag + ", status="
				+ status + ", shipDate=" + shipDate + ", commitDate=" + commitDate + ", receiptDate=" + receiptDate
				+ ", shipInstructions=" + shipInstructions + ", shipMode=" + shipMode + ", comment=" + comment + "]";
	}

	private Long rowNumber;
	public Long getRowNumber() {
		return rowNumber;
	}



	public void setRowNumber(Long rowNumber) {
		this.rowNumber = rowNumber;
	}



	public Long getOrderKey() {
		return orderKey;
	}



	public void setOrderKey(Long orderKey) {
		this.orderKey = orderKey;
	}



	public Long getPartKey() {
		return partKey;
	}



	public void setPartKey(Long partKey) {
		this.partKey = partKey;
	}



	public Long getSupplierKey() {
		return supplierKey;
	}



	public void setSupplierKey(Long supplierKey) {
		this.supplierKey = supplierKey;
	}



	public Integer getLineNumber() {
		return lineNumber;
	}



	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}



	public Float getQuantity() {
		return quantity;
	}



	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}



	public Float getExtendedPrice() {
		return extendedPrice;
	}



	public void setExtendedPrice(Float extendedPrice) {
		this.extendedPrice = extendedPrice;
	}



	public Float getDiscount() {
		return discount;
	}



	public void setDiscount(Float discount) {
		this.discount = discount;
	}



	public Float getTax() {
		return tax;
	}



	public void setTax(Float tax) {
		this.tax = tax;
	}



	public Character getReturnFlag() {
		return returnFlag;
	}



	public void setReturnFlag(Character returnFlag) {
		this.returnFlag = returnFlag;
	}



	public Character getStatus() {
		return status;
	}



	public void setStatus(Character status) {
		this.status = status;
	}



	public Date getShipDate() {
		return shipDate;
	}



	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}



	public Date getCommitDate() {
		return commitDate;
	}



	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}



	public Date getReceiptDate() {
		return receiptDate;
	}



	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}



	public String getShipInstructions() {
		return shipInstructions;
	}



	public void setShipInstructions(String shipInstructions) {
		this.shipInstructions = shipInstructions;
	}



	public String getShipMode() {
		return shipMode;
	}



	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}

	private Long orderKey;
	private Long partKey;
	private Long supplierKey;
	private Integer lineNumber;
	private Float quantity;
	private Float extendedPrice;
	private Float discount;
	private Float tax;
	private Character returnFlag;
	private Character status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date shipDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date commitDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date receiptDate;
	
	private String shipInstructions;
	private String shipMode;
	private String comment;
	
	
	
	public LineItem(){
		
	}
	
	
	
	
}
