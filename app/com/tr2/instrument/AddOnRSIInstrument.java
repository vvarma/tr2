package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;

public class AddOnRSIInstrument extends AbstractDecoratorInstrument {

	private int rsiPeriod = 14;
	private List<Double> gainList, lossList, rsiList;

	public AddOnRSIInstrument(Instrument instrument) {
		super(instrument);
		gainList = new ArrayList<>();
		lossList = new ArrayList<>();
		rsiList = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Level generateSignal() {
		calculateRSI();
		return super.generateSignal().addLevel(addRSI());
	}

	private void calculateRSI() {
		int index = priceList.size();
		if (index < rsiPeriod) {
			gainList.add(0.0);
			lossList.add(0.0);
			rsiList.add(0.0);
		} else if (index == rsiPeriod) {
			Double gainSum = 0.0;
			Double lossSum = 0.0;
			for (Price p : priceList.subList(index - rsiPeriod, index - 1)) {

				if (p.getClosePrice() > p.getLastClosePrice())
					gainSum += (p.getClosePrice() - p.getLastClosePrice());
				else if (p.getClosePrice() < p.getLastClosePrice())
					lossSum += (p.getLastClosePrice() - p.getClosePrice());
			}
			gainList.add(gainSum / rsiPeriod);
			lossList.add(lossSum / rsiPeriod);
			rsiList.add(0.0);
			/*rsiList.add(100 - 100 / (1 + (gainList.get(index - 1) / lossList
					.get(index - 1))));*/
		} else {
			Double gain, loss;
			if (priceList.get(index - 1).getClosePrice() > priceList.get(
					index - 1).getLastClosePrice()) {
				gain = priceList.get(index - 1).getClosePrice()
						- priceList.get(index - 1).getLastClosePrice();
				loss = 0.0;
			} else if (priceList.get(index - 1).getClosePrice() < priceList
					.get(index - 1).getLastClosePrice()) {
				gain = 0.0;
				loss = priceList.get(index - 1).getLastClosePrice()
						- priceList.get(index - 1).getClosePrice();
			} else {
				gain = 0.0;
				loss = 0.0;
			}
			gainList.add((gainList.get(index - 2) * 13 + gain) / 14);
			lossList.add((lossList.get(index - 2) * 13 + loss) / 14);
			rsiList.add(100 - 100 / (1 + (gainList.get(index - 1) / lossList
					.get(index - 1))));
		}
	}

	private Level addRSI() {

		int index = priceList.size();
		if (rsiList.get(index - 1) >= 70)
			return Level.SELL;
		else if (rsiList.get(index - 1) <= 30)
			return Level.BUY;
		else
			return Level.HOLD;
	}

	@Override
	public String toString() {
		super.toString();
		return "AddOnRSIInstrument [rsiPeriod=" + rsiPeriod + ", gainList="
				+ gainList + ", lossList=" + lossList + ", rsiList=" + rsiList
				+ "]";
	}

	
}
