package tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jp.ac.wakhok.tomoharu.csv.CSVTokenizer;

public class RegAddress {
	private String zipcode;
	private String address;
	private String prefecture = "";
	private String city = "";
	private String town = "";

	public RegAddress(String zipcode, String address) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.zipcode = zipcode;
		this.address = address;
		// search();
	}

	public void search() {

		int gbg = 0;
		boolean flag = false;

		while ((gbg = zipcode.indexOf("-")) >= 0 // 郵便番号にハイフンが含まれていたら
				|| (gbg = zipcode.indexOf(" ")) >= 0) { // 郵便番号に空白が含まれていたら

			StringBuffer sb = new StringBuffer(zipcode);
			sb.deleteCharAt(gbg);
			zipcode = sb.toString();

		}

		try {

			// 郵便番号のデータ読み込み
			BufferedReader zipData = new BufferedReader(
					new InputStreamReader(new FileInputStream(".\\inputdata\\KEN_ALL.CSV"), "Shift_JIS"));

			String line;
			while ((line = zipData.readLine()) != null) {

				ArrayList<String> list = new ArrayList<String>();
				CSVTokenizer token = new CSVTokenizer(line);
				while (token.hasMoreTokens()) {
					list.add(token.nextToken());
				}

				if (list.get(2).equals(zipcode)) {
					prefecture = list.get(6);
					city = list.get(7);

					int index = address.indexOf(city);
					town = address.substring(index + city.length());
					flag = true;
					break;

				}
			}

			if (!flag) {

				noZipcode(address);

			}
			zipData.close();

		} catch (IOException e) {

			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
	}

	public void noZipcode(String address) {
		int pIndex = 0;

		if ((address.indexOf("東京都") >= 0) || (address.indexOf("北海道") >= 0) || (address.indexOf("京都府") >= 0)
				|| (address.indexOf("大阪府") >= 0)) {
			pIndex = 2;
		} else {
			pIndex = address.indexOf("県");
		}

		prefecture = address.substring(0, pIndex + 1);
		city = address.substring(pIndex + 1, this.address.length());
		if (city.length() > 40) {
			town = city;
			city = "";
		}
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public String getCity() {
		return city;
	}

	public String getTown() {
		return town;
	}

}
