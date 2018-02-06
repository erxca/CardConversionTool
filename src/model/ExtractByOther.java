package model;

import java.util.ArrayList;

import view.DropFile;

public class ExtractByOther {
	String item;
	DropFile df;
	ExtractionProcessing ep;

	public ExtractByOther(String item, DropFile df, ExtractionProcessing ep) {
		this.item = item;
		this.df = df;
		this.ep = ep;
	}

	public void extracting() {

		switch (item) {
		case "会社名":// 0
			search(0);
			break;

		case "氏名":// 3
			search(3);
			break;

		case "e-mail":// 4
			search(4);
			break;

		case "携帯電話":// 11
			search(11);
			break;

		}
	}

	private void search(int index) {

		ArrayList<ArrayList<String>> csvData = ep.csvData;
		String input = df.getCp().getInput().getText();

		for (ArrayList<String> data : csvData) {
			String iData = data.get(index);

			if (iData.equals(input)) {
				ep.editData(data);
			}
		}
	}
}
