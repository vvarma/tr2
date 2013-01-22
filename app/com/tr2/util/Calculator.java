package com.tr2.util;

import java.util.List;

import com.tr2.instrument.Instrument;
import com.tr2.instrument.Price;

public class Calculator {

	public static double calculateEMACD(Instrument instrument, int shortPeriod,
			int longPeriod) {
		return calculateExponentialMean(instrument, shortPeriod)
				- calculateExponentialMean(instrument, longPeriod);
	}

	private static Double calculateMean(Instrument instrument) {
		Double result = 0.0;

		for (Price p : instrument.getPriceList())
			result += p.getPrice();
		result /= instrument.getPriceList().size();
		//System.out.println(result + "result");
		return result;
	}

	private static double calculateExponentialMean(Instrument instrument,
			int period) {
		// Double result = 0.0;
		List<Price> priceList = instrument.getPriceList().subList(
				instrument.getPriceList().size() - period,
				instrument.getPriceList().size());
		List<Price> priceList1 = instrument.getPriceList().subList(0,
				instrument.getPriceList().size() - period);
		Instrument temp = new Instrument(priceList1);
		Double mean = calculateMean(temp);
		Double multiplier = (double) (2 / (period + 1));
		for (Price p : priceList) {
			mean = (p.getPrice() - mean) * multiplier + mean;
		}

		//System.out.println(mean + "result");
		return mean;
	}
}
