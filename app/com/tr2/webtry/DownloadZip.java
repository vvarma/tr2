package com.tr2.webtry;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import play.Logger;

public class DownloadZip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 try
	     {
	        /*
	         * Get a connection to the URL and start up
	         * a buffered reader.
	         */
	        long startTime = System.currentTimeMillis();
	 
	        System.out.println("Connecting to NSE...\n");
	 
	        URL url = new URL("http://www.nseindia.com/content/historical/EQUITIES/2013/JAN/cm09JAN2013bhav.csv.zip");
	        url.openConnection();
	        InputStream reader = url.openStream();
	 
	        /*
	         * Setup a buffered file writer to write
	         * out what we read from the website.
	         */
	        FileOutputStream writer = new FileOutputStream(IConstants.TEMP_PATH);
	        byte[] buffer = new byte[153600];
	        int totalBytesRead = 0;
	        int bytesRead = 0;
	 
	        System.out.println("Reading ZIP file 150KB blocks at a time.\n");
	 
	        while ((bytesRead = reader.read(buffer)) > 0)
	        {  
	           writer.write(buffer, 0, bytesRead);
	           buffer = new byte[153600];
	           totalBytesRead += bytesRead;
	        }
	 
	        long endTime = System.currentTimeMillis();
	 
	        System.out.println("Done. " + (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).\n");
	        writer.close();
	        reader.close();
	     }
	     catch (MalformedURLException e)
	     {
	        e.printStackTrace();
	     }
	     catch (IOException e)
	     {
	        e.printStackTrace();
	     }

	}

	public void downloadZip(String urlFormed) {
		 try
	     {
	        /*
	         * Get a connection to the URL and start up
	         * a buffered reader.
	         */
	        long startTime = System.currentTimeMillis();
	 
	        Logger.info("Connecting to NSE...\n");
	 
	        URL url = new URL(urlFormed);
	        url.openConnection();
	        InputStream reader = url.openStream();
	 
	        /*
	         * Setup a buffered file writer to write
	         * out what we read from the website.
	         */
	        FileOutputStream writer = new FileOutputStream(IConstants.TEMP_PATH);
	        byte[] buffer = new byte[153600];
	        int totalBytesRead = 0;
	        int bytesRead = 0;
	 
	       Logger.info("Reading ZIP file 150KB blocks at a time.\n");
	 
	        while ((bytesRead = reader.read(buffer)) > 0)
	        {  
	           writer.write(buffer, 0, bytesRead);
	           buffer = new byte[153600];
	           totalBytesRead += bytesRead;
	        }
	 
	        long endTime = System.currentTimeMillis();
	 
	        Logger.info("Done. " + (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).\n");
	        writer.close();
	        reader.close();
	     }
	     catch (MalformedURLException e)
	     {
	        e.printStackTrace();
	     }
	     catch (IOException e)
	     {
	        e.printStackTrace();
	     }

		
	}

}
