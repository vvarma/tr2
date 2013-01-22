package com.tr2.observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import play.Logger;

public class ObserveTrigMACDInstrument implements Observer {

//private BufferedWriter bw;

	public ObserveTrigMACDInstrument() throws IOException {
		//bw = new BufferedWriter(new FileWriter(new File("buySell.txt")));
	}

	@Override
	public void update(Observable o, Object arg) {
		Logger.info("called");

		try {
			 BufferedWriter bw = new BufferedWriter(new FileWriter(new File("buySell.txt"),true));
			bw.newLine();
			bw.write(arg + o.toString());
			bw.flush();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void close() throws IOException{
		//bw.close();
	}

}
