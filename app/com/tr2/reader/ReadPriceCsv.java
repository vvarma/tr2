package com.tr2.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tr2.instrument.Price;

public class ReadPriceCsv {

	public static List<Price> readPrice(String fileName) throws IOException,
			NumberFormatException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<Price> priceList = new ArrayList<Price>();
		String str = "";
		String[] arrString;
		br.readLine();
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		while ((str = br.readLine()) != null) {
			arrString = str.split(",");
			priceList.add(new Price(Double.parseDouble(arrString[4]),
					(Date) format.parse(arrString[0])));
		}
		br.close();
		return priceList;

	}

	@SuppressWarnings("null")
	@Deprecated
	public static double readClosePrice(String fileName, String instrName)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Double price = null;
		String str = "";
		String[] arrString;
		br.readLine();
		// DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
		while ((str = br.readLine()) != null) {
			arrString = str.split(",");
			if (arrString[0].equals(instrName)) {
				br.close();
				return Double.parseDouble(arrString[5]);
			}
			// priceList.add(new
			// Price(Double.parseDouble(arrString[4]),(Date)format.parse(arrString[0])));
		}
		br.close();
		return price;
	}

	public static Price readPrice(String fileName, String instrName,
			Date date) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		// Double price = null;
		String str = "";
		String[] arrString;
		br.readLine();

		while ((str = br.readLine()) != null) {
			arrString = str.split(",");
			if (arrString[0].equals(instrName)) {
				br.close();
				return new Price(date, Double.parseDouble(arrString[5]),
						Double.parseDouble(arrString[2]),
						Double.parseDouble(arrString[4]),
						Double.parseDouble(arrString[3]),
						Double.parseDouble(arrString[7]),
						Long.parseLong(arrString[8]));
			}

		}
		br.close();
		return null;
	}
}
