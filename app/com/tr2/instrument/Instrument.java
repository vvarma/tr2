package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Instrument extends Observable {

	protected List<Price> priceList;
	protected String instrumentName;

	private Level level=Level.HOLD	;

	public Instrument(List<Price> priceList) {
		super();
		this.priceList = priceList;
	}

	public Instrument(String string) {
		instrumentName = string;
		priceList = new ArrayList<Price>();
	}

	public void generateTrigger() {
		if(level!=generateSignal()){
			level=generateSignal();
			setChanged();
		}
		if (hasChanged())
			notifyObservers();
	}

	public Level generateSignal() {
		// TODO Auto-generated method stub
		return Level.HOLD;
	}

	public void addPrice(Price price) {
		
		priceList.add(price);
		generateTrigger();
	}

	@Override
	public String toString() {
		return "Instrument [priceList=" + priceList.get(priceList.size() - 1)
				+ ", instrumentName=" + instrumentName + "]";
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

}
