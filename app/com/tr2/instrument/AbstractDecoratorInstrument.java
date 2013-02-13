package com.tr2.instrument;

public abstract class AbstractDecoratorInstrument extends Instrument{

	public AbstractDecoratorInstrument(Instrument instrument) {
		
		this.instrument=instrument;
		this.priceList=instrument.priceList;
		this.instrumentName=instrument.instrumentName;
		// TODO Auto-generated constructor stub
	}

	protected Instrument instrument;

	@Override
	public Level generateSignal() {
		// TODO Auto-generated method stub
		return instrument.generateSignal();
	}
	
	
	
}
