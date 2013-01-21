package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;

import com.tr2.util.Calculator;

public class MACDInstrument extends Instrument {

	protected List<Double> macdList = new ArrayList<Double>();
	protected int smallPeriod = 12, bigPeriod = 26;

	// protected Double tempMean;
	public MACDInstrument(List<Price> priceList) {
		super(priceList);
		// TODO Auto-generated constructor stub
	}

	public MACDInstrument(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addPrice(Price price) {
		// TODO Auto-generated method stub
		super.addPrice(price);
		updateMACDList();
	}

	private void updateMACDList() {
		// TODO Auto-generated method stub
		if (priceList.size() < bigPeriod) {
			// calculateMean();
			macdList.add(0.0);
		} else if (priceList.size() == bigPeriod) {
			macdList.add(calculateMean());
		} else {
			macdList.add(Calculator
					.calculateEMACD(this, smallPeriod, bigPeriod));
			// macdList.add(calculateMACD());
		}
	}

	
	public Double calculateMean() {
		Double sum = 0.0;
		Double tempMean;
		int length = 0;
		for (Price price : priceList) {
			sum += price.getPrice();
			length++;
		}
		tempMean = sum / length;
		return tempMean;
	}

	@Override
	public String toString() {
		return "MACDInstrument [macdList=" + macdList + "]";
	}

}
