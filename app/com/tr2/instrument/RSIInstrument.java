package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;

public class RSIInstrument extends Instrument {

	private List<Double> rsiList, avgGain, avgLoss;

	public RSIInstrument(String string) {
		super(string);
		rsiList = new ArrayList<Double>();
		avgGain = new ArrayList<Double>();
		avgLoss = new ArrayList<Double>();
	}

	@Override
	public void addPrice(Price price) {
		// TODO Auto-generated method stub
		// System.out.println(this);
		super.addPrice(price);
		updateLists();

	}

	private void updateLists() {
		int index = priceList.size() - 1;
		if (index < 13) {
			rsiList.add(0.0);
			avgGain.add(0.0);
			avgLoss.add(0.0);
		} else if (index == 13) {
			double gain = 0.0;
			double loss = 0.0;
			for (Price p : priceList) {
				double margin = p.getClosePrice() - p.getLastClosePrice();
				if (margin > 0) {
					gain += margin;
				} else {
					loss += (margin * -1);
				}
			}
			avgGain.add(gain / 14);
			avgLoss.add(loss / 14);
			// System.out.println(avgGain);
			// System.out.println(avgGain.size()+" "+index);
			rsiList.add(100 - (100 / (1 + avgGain.get(index)
					/ avgLoss.get(index))));
		} else {
			double margin = priceList.get(index).getClosePrice()
					- priceList.get(index).getLastClosePrice();
			if (margin > 0) {
				avgGain.add((margin + 13 * avgGain.get(index - 1)) / 14);
				avgLoss.add((0.0 + 13 * avgLoss.get(index - 1)) / 14);
			} else {
				margin=margin*-1;
				avgGain.add((0.0 + 13 * avgGain.get(index - 1)) / 14);
				avgLoss.add((margin + 13 * avgLoss.get(index - 1)) / 14);
			}
			rsiList.add(100 - (100 / (1 + avgGain.get(index)
					/ avgLoss.get(index))));
		}

	}

	@Override
	public String toString() {
		return "RSIInstrument [rsiList=" + rsiList + ", avgGain=" + avgGain
				+ ", avgLoss=" + avgLoss + "]";
	}

}
