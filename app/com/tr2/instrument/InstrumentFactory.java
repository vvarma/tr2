package com.tr2.instrument;

public class InstrumentFactory {
	// private Instrument instrument;

	public Instrument getInstrument(String key, String symbol) {

		switch (key) {
		case "Simple":
			return new Instrument(symbol);
		case "RSI":
			return new RSIInstrument(symbol);
		default:
			return null;
		}

	}

}
