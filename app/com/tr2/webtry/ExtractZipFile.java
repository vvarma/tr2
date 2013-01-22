package com.tr2.webtry;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import play.Logger;



public class ExtractZipFile {
	public static void main(String[] args) throws Exception {
		String fName = IConstants.TEMP_PATH;
		byte[] buf = new byte[1024];
		ZipInputStream zinstream = new ZipInputStream(
				new FileInputStream(fName));
		ZipEntry zentry = zinstream.getNextEntry();
		System.out.println("Name of current Zip Entry : " + zentry + "\n");
		while (zentry != null) {
			String entryName = zentry.getName();
			System.out.println("Name of  Zip Entry : " + entryName);
			FileOutputStream outstream = new FileOutputStream(IConstants.DATA_PATH+entryName);
			int n;

			while ((n = zinstream.read(buf, 0, 1024)) > -1) {
				outstream.write(buf, 0, n);

			}
			System.out.println("Successfully Extracted File Name : "
					+ entryName);
			outstream.close();

			zinstream.closeEntry();
			zentry = zinstream.getNextEntry();
		}
		zinstream.close();
	}

	public String extractTemp() throws IOException {
		String fName = IConstants.TEMP_PATH;
		String entryName="";
		byte[] buf = new byte[1024];
		ZipInputStream zinstream = new ZipInputStream(
				new FileInputStream(fName));
		ZipEntry zentry = zinstream.getNextEntry();
		Logger.info("Name of current Zip Entry : " + zentry + "\n");
		while (zentry != null) {
			 entryName = zentry.getName();
			Logger.info("Name of  Zip Entry : " + entryName);
			FileOutputStream outstream = new FileOutputStream(IConstants.DATA_PATH+entryName);
			int n;

			while ((n = zinstream.read(buf, 0, 1024)) > -1) {
				outstream.write(buf, 0, n);

			}
			Logger.info("Successfully Extracted File Name : "
					+ entryName);
			outstream.close();

			zinstream.closeEntry();
			zentry = zinstream.getNextEntry();
		}
		zinstream.close();
		return entryName;
		
		
	}
}
