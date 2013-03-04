package com.tr2.instrument;

import java.util.Date;

public class Investment {

	public Investment(String instrumentName, Price currentPrice2) {
		symbolName = instrumentName;
		buyPrice = currentPrice2.getClosePrice();
		currentPrice = currentPrice2.getClosePrice();
		quantity = 1;
		this.buyDate=currentPrice2.getTimeStamp();

	}

	private String symbolName;
	private Double buyPrice;
	private Double currentPrice;
	private int quantity;
	private Date buyDate,sellDate;
	
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	@Override
	public String toString() {
		return "Investment [symbolName=" + symbolName + ", buyPrice="
				+ buyPrice + ", currentPrice=" + currentPrice + ", quantity="
				+ quantity + "]";
	}
	public String getSymbolName() {
		// TODO Auto-generated method stub
		return symbolName;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	

	
}
