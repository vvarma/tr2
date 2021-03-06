package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;

public class AddOnMACDInstrument extends AbstractDecoratorInstrument {

	protected List<Double> macdList = new ArrayList<Double>();
	protected List<Double> smallPeriodMean = new ArrayList<Double>();
	protected List<Double> largePeriodMean = new ArrayList<Double>();
	protected List<Double> emaMACDList = new ArrayList<>();
	protected int smallPeriod = 12, bigPeriod = 26, macdPeriod = 9;

	public List<Double> getEmaMACDList() {
		return emaMACDList;
	}

	public AddOnMACDInstrument(Instrument instrument) {
		super(instrument);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Level generateSignal() {
		// TODO Auto-generated method stub
		return super.generateSignal().addLevel(generateMACDSignal());
	}

	private Level generateMACDSignal() {
		updateMACDList();
		updateEMACDList();
		if (macdList.get(macdList.size() - 1) > emaMACDList
				.get(macdList.size() - 1))
			return Level.BUY;
		else if (macdList.get(macdList.size() - 1) == emaMACDList.get(macdList
				.size() - 1))
			return Level.HOLD;
		else
			return Level.SELL;
	}

	public List<Double> getMacdList() {
		return macdList;
	}

	private void updateEMACDList() {
		int index = priceList.size();
		double multiplier = 2.0 / (macdPeriod + 1);
		if (index < macdPeriod + bigPeriod) {
			emaMACDList.add(0.0);
		} else if (index == macdPeriod + bigPeriod) {
			// emaMACDList.add(calcEMA(macdList, macdPeriod));
			double sum = 0;
			for (Double d : macdList.subList(index - macdPeriod, index))
				sum += d;
			emaMACDList.add(sum / macdPeriod);
		} else {
			emaMACDList.add((macdList.get(index - 1) - emaMACDList
					.get(index - 2)) * multiplier + emaMACDList.get(index - 2));
		}

	}

	private void updateMACDList() {
		// TODO Auto-generated method stub
		int index = priceList.size();
		if (index < bigPeriod) {
			// calculateMean();
			smallPeriodMean.add(0.0);
			largePeriodMean.add(0.0);
			macdList.add(0.0);

		} else if (index == bigPeriod) {
			smallPeriodMean.add(calculateMean(priceList.subList(index
					- smallPeriod, index)));
			largePeriodMean.add(calculateMean(priceList.subList(index
					- bigPeriod, index)));
			macdList.add(calculateEMACD());
		} else {
			smallPeriodMean.add(calcEMA(smallPeriodMean, smallPeriod));
			largePeriodMean.add(calcEMA(largePeriodMean, bigPeriod));
			macdList.add(calculateEMACD());
			// macdList.add(calculateMACD());
		}
	}

	public Double calculateEMACD() {
		int index = priceList.size() - 1;

		return smallPeriodMean.get(index) - largePeriodMean.get(index);
		// return (priceList.get(index).getPrice()-macdList.get(index-1));
	}

	public Double calcEMA(List<Double> periodMean, int period) {
		double multiplier = 2.0 / (period + 1);
		int index = priceList.size() - 1;

		return (priceList.get(index).getClosePrice() - periodMean
				.get(index - 1)) * multiplier + periodMean.get(index - 1);

	}

	public Double calculateMean() {
		Double sum = 0.0;
		Double tempMean;
		int length = 0;
		for (Price price : priceList) {
			sum += price.getClosePrice();
			length++;
		}
		tempMean = sum / length;
		return tempMean;
	}

	public Double calculateMean(List<Price> priceList) {
		Double sum = 0.0;
		Double tempMean;
		int length = 0;
		for (Price price : priceList) {
			sum += price.getClosePrice();
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
