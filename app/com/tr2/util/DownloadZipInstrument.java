package com.tr2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.tr2.instrument.Instrument;
import com.tr2.instrument.Price;
import com.tr2.instrument.TrigMACDInstrument;
import com.tr2.reader.ReadPriceCsv;
import com.tr2.webtry.DownloadZip;
import com.tr2.webtry.ExtractZipFile;

public class DownloadZipInstrument {

	/**
	 * @param args
	 */

	public static void getInstrumentGivenDateAndName(Calendar startDate,
			Calendar endDate) throws IOException {
		// TODO Auto-generated method stub
		String url;
		DownloadZip dwnlder = new DownloadZip();
		// List<Instrument> instrumentList=new ArrayList<Instrument>();
		Instrument instr = new Instrument("ACC");
		// instrumentList.add(new Instrument("ACC"));
		ExtractZipFile extractZip = new ExtractZipFile();

		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = "C://Users//Vinay//workspace//tr2//data//";
			if (isWeekDay(i)) {
				url = createUrlGivenDate(i);
				dwnlder.downloadZip(url);
				fileName = fileName + extractZip.extractTemp();
				Price instrPrice = new Price(ReadPriceCsv.readPrice(fileName,
						"ACC"), i.getTime());
				instr.addPrice(instrPrice);
			}

		}
		System.out.println(instr);
	}

	public static Map<String, Instrument> getInstrumentGivenDateAndName(
			Calendar startDate, Calendar endDate, String symbolGroup)
			throws IOException {
		List<String> symbolList = loadInterestedSymbols(symbolGroup);

		Map<String, Instrument> instrumentList = new HashMap<String, Instrument>();

		for (String i : symbolList) {
			instrumentList.put(i, new TrigMACDInstrument(i));
			//instrumentList.put(i, new Instrument(i));
		}

		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = "C://Users//Vinay//workspace//tr2//data//";
			if (isWeekDay(i)) {
				String genFileName = createFilenamGivenDate(i);
				File file = new File(fileName + genFileName);
				if (file.exists()) {
					System.out.println(genFileName + " exists!");
					fileName = fileName + genFileName;
				} else {
					fileName = fileName
							+ createUrlDownloadAndExtractFileGivenDate(i);
				}

				for (String i2 : symbolList) {
					Price instrPrice = new Price(ReadPriceCsv.readPrice(
							fileName, i2), i.getTime());
					instrumentList.get(i2).addPrice(instrPrice);
				}

			}

		}
		System.out.println(instrumentList);
		return instrumentList;
	}

	private static boolean isWeekDay(Calendar i) {
		// TODO Auto-generated method stub
		if (i.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| i.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return false;
		return true;
	}

	private static String createUrlGivenDate(Calendar i) {

		/*
		 * String url=
		 * "http://www.nseindia.com/content/historical/EQUITIES/2013/JAN/cm09JAN2013bhav.csv.zip"
		 * ;
		 */
		StringBuilder urlBuilder = new StringBuilder(
				"http://www.nseindia.com/content/historical/EQUITIES/");
		// urlBuilder.append("20");
		urlBuilder.append(i.get(Calendar.YEAR));
		urlBuilder.append("/");
		urlBuilder.append(i.getDisplayName(Calendar.MONTH, Calendar.SHORT,
				Locale.US).toUpperCase());
		urlBuilder.append("/cm");
		if (i.get(Calendar.DATE) < 10) {
			urlBuilder.append("0");
		}
		urlBuilder.append(i.get(Calendar.DATE));
		urlBuilder.append(i.getDisplayName(Calendar.MONTH, Calendar.SHORT,
				Locale.US).toUpperCase());
		// urlBuilder.append("20");
		urlBuilder.append(i.get(Calendar.YEAR));
		urlBuilder.append("bhav.csv.zip");
		System.out.println(urlBuilder.toString());
		return urlBuilder.toString();
	}

	private static String createFilenamGivenDate(Calendar i) {
		StringBuilder fileNameBuilder = new StringBuilder("cm");
		if (i.get(Calendar.DATE) < 10) {
			fileNameBuilder.append("0");
		}
		fileNameBuilder.append(i.get(Calendar.DATE));
		fileNameBuilder.append(i.getDisplayName(Calendar.MONTH, Calendar.SHORT,
				Locale.US).toUpperCase());
		// urlBuilder.append("20");
		fileNameBuilder.append(i.get(Calendar.YEAR));
		fileNameBuilder.append("bhav.csv");
		return fileNameBuilder.toString();
	}

	private static String createUrlDownloadAndExtractFileGivenDate(Calendar i)
			throws IOException {
		String url;
		DownloadZip dwnlder = new DownloadZip();
		ExtractZipFile extractZip = new ExtractZipFile();
		url = createUrlGivenDate(i);
		dwnlder.downloadZip(url);
		return extractZip.extractTemp();
	}

	private static List<String> loadInterestedSymbols(String fileName)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				new File(fileName)));
		List<String> symbolList = new ArrayList<String>();
		String temp;
		while ((temp = br.readLine()) != null) {
			symbolList.add(temp);
		}
		br.close();
		return symbolList;

	}

}
