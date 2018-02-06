package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jp.ac.wakhok.tomoharu.csv.CSVLine;
import jp.ac.wakhok.tomoharu.csv.CSVTokenizer;

public class ManageLog {
	private final String LOG_FILE = ".\\inputdata\\DataLog.csv";
	private CSVTokenizer token;
	public int startDay, endDay;
	private String todaysDate;

	public ManageLog() {

		Date date = new Date();
		SimpleDateFormat todaysFormat = new SimpleDateFormat("yyyyMMdd");
		todaysDate = todaysFormat.format(date);

	}

	public void LoadLog() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LOG_FILE), "MS932"));
		ArrayList<ArrayList<String>> logData = new ArrayList<ArrayList<String>>();

		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			ArrayList<String> data = new ArrayList<String>();
			token = new CSVTokenizer(line);

			String nextToken = token.nextToken();
			data.add(nextToken);

			while (token.hasMoreTokens()) {

				data.add(token.nextToken());

			}
			System.out.println(data);
			logData.add(data);
		}

		for (int i = logData.size() - 1; i >= 0; i--) {
			System.out.println(i);
			if (logData.get(i).get(4).equals("1")) {

				String start = logData.get(i).get(2);
				startDay = Integer.parseInt(start) + 1;
				String end = todaysDate;
				endDay = Integer.parseInt(end);

				break;
			}
		}

		br.close();
	}

	public void writeLog(int date1, int date2, String dataNum, boolean isNewest) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_FILE, true), "MS932"));

		String start = Integer.toString(date1);
		String end = Integer.toString(date2);

		String newest = isNewest ? "1" : "0";

		String[] item = { todaysDate, start, end, dataNum, newest };
		CSVLine csvline = new CSVLine();
		for (String s : item) {
			csvline.addItem(s);
		}

		String line = csvline.getLine();
		bw.write(line);
		bw.newLine();

		bw.close();
	}
}
