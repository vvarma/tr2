package com.tr2.instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.tr2.exceptions.UnknownLevelException;

public class Portfolio implements Observer {

	private List<Investment> investmentList;
	private String portfolioName;

	public Portfolio(String portfolioName) {
		super();
		this.portfolioName = portfolioName;
		investmentList = new ArrayList<>();
	}

	@Override
	public void update(Observable instrument, Object level) {
		if (!(instrument instanceof Instrument&& level instanceof Level))
			throw new UnknownLevelException();
		Instrument instr = (Instrument) instrument;
		// if(investmentList.isEmpty()){

		// }
		if (((Level) level).equals(Level.BUY)) {
			investmentList.add(new Investment(instr.getInstrumentName(), instr
					.getCurrentPrice()));
			
		} else if (((Level) level).equals(Level.SELL)) {
			for (Investment i : investmentList) {
				if (i.getSymbolName().equals(instr.getInstrumentName()))
					if (i.getQuantity() != 0) {
						i.setCurrentPrice(instr.getCurrentPrice()
								.getClosePrice());
						i.setQuantity(0);
						i.setSellDate(instr.getCurrentPrice().getTimeStamp());
					}
			}
		}

	}

	public Double calculatePnL() {
		Double pnl = 0.0;
		for (Investment i : investmentList) {

			if(i.getQuantity()==0)
				pnl+=i.getCurrentPrice()-i.getBuyPrice();
		}
		return pnl;
	}

}
