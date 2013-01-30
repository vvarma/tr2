package com.tr2.instrument;

import java.util.Date;

public class Price {
	
	private Date timeStamp;
	private Double closePrice,openPrice,lowPrice,highPrice,lastClosePrice;
	private long totalTradedQuantity;

	public Price(double price, Date date) {
		timeStamp = date;
		this.closePrice = price;
	}

	
	public Price(Date timeStamp, Double closePrice, Double openPrice,
			Double lowPrice, Double highPrice, Double lastClosePrice,
			long totalTradedQuantity) {
		super();
		this.timeStamp = timeStamp;
		this.closePrice = closePrice;
		this.openPrice = openPrice;
		this.lowPrice = lowPrice;
		this.highPrice = highPrice;
		this.lastClosePrice = lastClosePrice;
		this.totalTradedQuantity = totalTradedQuantity;
	}


	public Double getOpenPrice() {
		return openPrice;
	}


	public Double getLowPrice() {
		return lowPrice;
	}


	public Double getHighPrice() {
		return highPrice;
	}


	public Double getLastClosePrice() {
		return lastClosePrice;
	}


	public long getTotalTradedQuantity() {
		return totalTradedQuantity;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}

	
	public Double getClosePrice() {
		return closePrice;
	}

	@Override
	public String toString() {
		return "Price [timeStamp=" + timeStamp + ", closePrice=" + closePrice + "]";
	}

}
