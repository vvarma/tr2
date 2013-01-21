package com.tr2.instrument;

import java.util.Date;

public class Price {
	/**
	 * @uml.property  name="timeStamp"
	 */
	private Date timeStamp;
	/**
	 * @uml.property  name="price"
	 */
	private Double price;

	public Price(double price, Date date) {
		timeStamp = date;
		this.price = price;
	}

	/**
	 * @return
	 * @uml.property  name="timeStamp"
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @return
	 * @uml.property  name="price"
	 */
	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Price [timeStamp=" + timeStamp + ", price=" + price + "]";
	}

}
