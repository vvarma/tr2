package com.tr1.instrument;

import java.util.ArrayList;
import java.util.List;

import com.tr1.util.Calculator;

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

	/*
	 * private Double calculateMACD() { // TODO Auto-generated method stub
	 * return calculateExponentialMean( smallPeriod) - calculateExponentialMean(
	 * bigPeriod); }
	 * 
	 * private Double calculateExponentialMean(int period) { // TODO
	 * Auto-generated method stub List<Price> priceList =
	 * this.getPriceList().subList( this.getPriceList().size() - period - 1,
	 * this.getPriceList().size() - 1); List<Price> priceList1 =
	 * this.getPriceList().subList(0, this.getPriceList().size() - period - 1);
	 * Instrument temp = new Instrument(priceList1); Double mean =
	 * calculateMean(temp, period); Double multiplier = (double) (2 / (period +
	 * 1)); for (Price p : priceList) { mean = (p.getPrice() - mean) *
	 * multiplier + mean; }
	 * 
	 * System.out.println(mean + "result"); return mean; }
	 */
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
