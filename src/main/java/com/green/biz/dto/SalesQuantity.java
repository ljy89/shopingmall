package com.green.biz.dto;

public class SalesQuantity {
	private String pname;
	private int quantity;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "SalesQuantity [pname=" + pname + ", quantity=" + quantity + "]";
	}
	
	
}
