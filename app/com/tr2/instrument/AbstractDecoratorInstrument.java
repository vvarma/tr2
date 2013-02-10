package com.tr2.instrument;

public abstract class AbstractDecoratorInstrument extends Instrument{

	public AbstractDecoratorInstrument(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	protected Instrument instrument;

	@Override
	public Level generateSignal() {
		// TODO Auto-generated method stub
		return instrument.generateSignal();
	}
	
	
	
}
