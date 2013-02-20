package com.tr2.observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import play.Logger;

import com.tr2.instrument.Instrument;
import com.tr2.instrument.Level;

public class ObserveAbstractInstrument implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		Logger.info("Observer called..");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"buySell.txt"), true));
			bw.append('\n'+((Level) arg1).toString()
					+ ((Instrument) arg0).getPriceList().get(
							((Instrument) arg0).getPriceList().size() - 1));
			bw.flush();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
