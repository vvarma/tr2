package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.google.gson.Gson;
import com.tr2.instrument.Instrument;
import com.tr2.util.DownloadZipInstrument;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result test() {
		return ok("ammatte");
	}

	public static Result instrument() throws IOException, ParseException {
		Map<String, String[]> requestParams = request().queryString();
		Set<String> requestParamsKeys = requestParams.keySet();
		Calendar startDate = new GregorianCalendar();
		Calendar endDate = new GregorianCalendar();
		String instrCategory = "";
		String[] addOns={"blah"};
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		for (String paramsKey : requestParamsKeys) {
			if (paramsKey.equals("strtDate")) {
				startDate
						.setTime(formatter.parse(requestParams.get(paramsKey)[0]));
			} else if (paramsKey.equals("endDate")) {
				System.out.println(requestParams.get(paramsKey)[0]);
				endDate.setTime(formatter.parse(requestParams.get(paramsKey)[0]));
			} else if (paramsKey.equals("category")) {
				instrCategory = requestParams.get(paramsKey)[0];
			}else if(paramsKey.equals("AddOns")){
				addOns=requestParams.get(paramsKey)[0].split(",");
			}
		}

		Map<String, Instrument> resultMap = DownloadZipInstrument
				.getInstrumentGivenDateAndName(startDate, endDate,instrCategory,addOns);
		// System.out.println(resultMap);
		return ok(new Gson().toJson(resultMap));
		// return ok();
	}

	public static Result singleInstrument() throws IOException, ParseException {
		Map<String, String[]> requestParams = request().queryString();
		Set<String> requestParamsKeys = requestParams.keySet();
		Calendar startDate = new GregorianCalendar();
		Calendar endDate = new GregorianCalendar();
		String symbol = "";
		String[] addOns={"blah"};
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		for (String paramsKey : requestParamsKeys) {
			if (paramsKey.equals("strtDate")) {
				startDate
						.setTime(formatter.parse(requestParams.get(paramsKey)[0]));
			} else if (paramsKey.equals("endDate")) {
				System.out.println(requestParams.get(paramsKey)[0]);
				endDate.setTime(formatter.parse(requestParams.get(paramsKey)[0]));
			} else if (paramsKey.equals("symbol")) {
				symbol = requestParams.get(paramsKey)[0];
			}else if(paramsKey.equals("AddOns")){
				addOns=requestParams.get(paramsKey)[0].split(",");
			}
		}

		Instrument result = DownloadZipInstrument
				.getSingleInstrumentGivenDateAndName(startDate, endDate, symbol,addOns);
		// System.out.println(result);
		return ok(new Gson().toJson(result));
		// return ok();
	}
}
