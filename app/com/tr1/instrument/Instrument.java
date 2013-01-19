package com.tr1.instrument;

import java.util.ArrayList;
import java.util.List;

public class Instrument {

	/**
	 * @uml.property  name="priceList"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.tr1.instrument.Price"
	 */
	protected List<Price> priceList;
	protected String instrumentName;

	public Instrument(List<Price> priceList) {
		super();
		this.priceList = priceList;
	}

	public Instrument(String string) {
		instrumentName=string;
		priceList=new ArrayList<Price>();
	}

	public void addPrice(Price price){
		priceList.add(price);
	}

	@Override
	public String toString() {
		return "Instrument [priceList=" + priceList + ", instrumentName="
				+ instrumentName + "]";
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}
	
	
}
