package com.tr2.webtry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class HTMLParser {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		URL url = new URL("http://www.nseindia.com/content/historical/EQUITIES/2013/JAN/cm09JAN2013bhav.csv.zip");
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

		    for (String line; (line = reader.readLine()) != null;) {
		        System.out.println(line);
		    }
		} finally {
		    if (reader != null) try { reader.close(); } catch (IOException ignore) {}
		}

	}

}
