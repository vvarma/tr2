package com.tr2.instrument;

public class InstrumentFactory {

	public static Instrument  getInstrument(String symbolName,
			String... args) {
		Instrument instrument = new Instrument(symbolName);
		for (String s : args)
			switch (s) {
			case "MACD":
				instrument = new AddOnMACDInstrument(instrument);
				break;
			case "RSI":
				instrument = new AddOnRSIInstrument(instrument);
				break;
			}
		return instrument;

	}

}
