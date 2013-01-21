package com.tr2.observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ObserveTrigMACDInstrument implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"buySell.txt")));
			bw.newLine();
			bw.append(arg + o.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
