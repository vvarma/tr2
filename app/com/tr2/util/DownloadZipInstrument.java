package com.tr2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import play.Logger;

import com.tr2.instrument.Instrument;
import com.tr2.instrument.InstrumentFactory;
import com.tr2.instrument.MACDInstrument;
import com.tr2.instrument.Price;
import com.tr2.instrument.TrigMACDInstrument;
import com.tr2.observer.ObserveTrigMACDInstrument;
import com.tr2.reader.ReadPriceCsv;
import com.tr2.webtry.DownloadZip;
import com.tr2.webtry.ExtractZipFile;
import com.tr2.webtry.IConstants;

public class DownloadZipInstrument {

	private static InstrumentFactory factory = new InstrumentFactory();

	@SuppressWarnings("deprecation")
	public static Map<String, Instrument> getInstrumentGivenDateAndName(
			Calendar startDate, Calendar endDate, String symbolGroup)
			throws IOException {
		Logger.info("application started ..");
		List<String> symbolList = loadInterestedSymbols(symbolGroup);

		Map<String, Instrument> instrumentList = new HashMap<String, Instrument>();
		ObserveTrigMACDInstrument obs = new ObserveTrigMACDInstrument();
		for (String i : symbolList) {
			TrigMACDInstrument tr = new TrigMACDInstrument(i);
			instrumentList.put(i, tr);
			tr.addObserver(obs);
			// instrumentList.put(i, new Instrument(i));
		}

		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = IConstants.DATA_PATH;

			if (isWeekDay(i)) {
				String genFileName = createFilenamGivenDate(i);
				File file = new File(fileName + genFileName);
				if (file.exists()) {
					Logger.trace(genFileName + " exists!");
					fileName = fileName + genFileName;
				} else {
					Logger.trace("downloader called.. ");
					fileName = fileName
							+ createUrlDownloadAndExtractFileGivenDate(i);
				}

				for (String i2 : symbolList) {
					Price instrPrice = new Price(ReadPriceCsv.readClosePrice(
							fileName, i2), i.getTime());
					instrumentList.get(i2).addPrice(instrPrice);
				}

			}

		}
		obs.close();
		return instrumentList;
	}

	@SuppressWarnings("deprecation")
	public static Instrument getSingleInstrumentGivenDateAndNameUsingClosePrice(
			Calendar startDate, Calendar endDate, String symbol)
			throws IOException {

		Logger.info("application started ..");

		TrigMACDInstrument instrument = new TrigMACDInstrument(symbol);
		ObserveTrigMACDInstrument obs = new ObserveTrigMACDInstrument();
		instrument.addObserver(obs);

		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = IConstants.DATA_PATH;

			if (isWeekDay(i)) {
				String genFileName = createFilenamGivenDate(i);
				File file = new File(fileName + genFileName);
				if (file.exists()) {
					Logger.trace(genFileName + " exists!");
					fileName = fileName + genFileName;
				} else {
					Logger.trace("downloader called.. ");
					fileName = fileName
							+ createUrlDownloadAndExtractFileGivenDate(i);
				}

				
				Price instrPrice = new Price(ReadPriceCsv.readClosePrice(
						fileName, symbol), i.getTime());
				instrument.addPrice(instrPrice);
			}

		}
		exportToCSV(instrument);

		// obs.close();
		return instrument;
	}

	
	public static Instrument getSingleInstrumentGivenDateAndName(
			Calendar startDate, Calendar endDate, String symbol)
			throws IOException {
		Logger.info("application started ..");
		Instrument instrument = factory.getInstrument("Simple", symbol);
		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = IConstants.DATA_PATH;

			if (isWeekDay(i)) {
				String genFileName = createFilenamGivenDate(i);
				File file = new File(fileName + genFileName);
				if (file.exists()) {
					Logger.trace(genFileName + " exists!");
					fileName = fileName + genFileName;
				} else {
					Logger.trace("downloader called.. ");
					fileName = fileName
							+ createUrlDownloadAndExtractFileGivenDate(i);
				}

				
				instrument.addPrice(ReadPriceCsv.readPrice(fileName, symbol, i.getTime()));
			}

		}
		return instrument;
	}
	public static Instrument getRSIInstrumentGivenDateAndName(
			Calendar startDate, Calendar endDate, String symbol)
			throws IOException {
		Logger.info("application started ..");
		Instrument instrument = factory.getInstrument("RSI", symbol);
		for (Calendar i = startDate; i.before(endDate); i.add(Calendar.DATE, 1)) {
			String fileName = IConstants.DATA_PATH;

			if (isWeekDay(i)) {
				String genFileName = createFilenamGivenDate(i);
				File file = new File(fileName + genFileName);
				if (file.exists()) {
					Logger.trace(genFileName + " exists!");
					fileName = fileName + genFileName;
				} else {
					Logger.trace("downloader called.. ");
					fileName = fileName
							+ createUrlDownloadAndExtractFileGivenDate(i);
				}

				
				instrument.addPrice(ReadPriceCsv.readPrice(fileName, symbol, i.getTime()));
			}

		}
		return instrument;
	}

	private static void exportToCSV(MACDInstrument instrument)
			throws IOException {
		// BufferedWriter bw=new BufferedWriter(new FileWriter(new
		// File("price.csv")));
		PrintWriter pw = new PrintWriter(new File("price.csv"));
		for (Price p : instrument.getPriceList()) {
			int index = instrument.getPriceList().indexOf(p);
			pw.print(p.getClosePrice() + ","
					+ instrument.getMacdList().get(index) + ","
					+ instrument.getEmaMACDList());
			pw.print('\n');
		}
		pw.close();

	}

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

			String fileName = IConstants.DATA_PATH;
			if (isWeekDay(i)) {
				url = createUrlGivenDate(i);
				dwnlder.downloadZip(url);
				fileName = fileName + extractZip.extractTemp();
				Price instrPrice = new Price(ReadPriceCsv.readClosePrice(
						fileName, "ACC"), i.getTime());
				instr.addPrice(instrPrice);
			}

		}
		System.out.println(instr);
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
		urlBuilder.append(i.get(Calendar.YEAR));
		urlBuilder.append("bhav.csv.zip");
		Logger.trace(urlBuilder.toString());
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
