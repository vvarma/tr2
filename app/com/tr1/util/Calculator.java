package com.tr1.util;

import java.util.List;

import com.tr1.instrument.Instrument;
import com.tr1.instrument.Price;

public class Calculator {

	public static double calculateMACD(Instrument instrument, int shortPeriod,
			int longPeriod) {
		return calculateMean(instrument, shortPeriod)
				- calculateMean(instrument, longPeriod);

	}
	public static double calculateEMACD(Instrument instrument,int shortPeriod,int longPeriod){
		return calculateExponentialMean(instrument, shortPeriod)
				- calculateExponentialMean(instrument, longPeriod);
	}

	private static Double calculateMean(Instrument instrument, int period) {
		Double result = 0.0;
		/*List<Price> priceList = instrument.getPriceList().subList(
				instrument.getPriceList().size() - period - 1,
				instrument.getPriceList().size() - 1);*/
		
		for (Price p : instrument.getPriceList())
			result += p.getPrice();
		result /= instrument.getPriceList().size();
		System.out.println(result + "result");
		return result;
	}

	private static double calculateExponentialMean(Instrument instrument,
			int period) {
		// Double result = 0.0;
		List<Price> priceList = instrument.getPriceList().subList(
				instrument.getPriceList().size() - period - 1,
				instrument.getPriceList().size() - 1);
		List<Price> priceList1 = instrument.getPriceList().subList(0,
				instrument.getPriceList().size() - period - 1);
		Instrument temp = new Instrument(priceList1);
		Double mean = calculateMean(temp, period);
		Double multiplier = (double) (2 / (period + 1));
		for (Price p : priceList) {
			mean = (p.getPrice() - mean) * multiplier + mean;
		}

		System.out.println(mean + "result");
		return mean;
	}
}
