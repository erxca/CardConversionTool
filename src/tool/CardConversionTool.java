package tool;

import java.awt.EventQueue;
import java.util.ArrayList;

import view.DropFile;

public class CardConversionTool {

	static DropFile window;
	// static CSVTokenizer token;
	// private static ArrayList<ArrayList<String>> cardList = new
	// ArrayList<ArrayList<String>>();
	// private static String OLD_CSV = ".\\inputdata\\OldData.csv";
	// private static String DIFF_CSV = ".\\data\\";
	// private int lineNum = 0;
	// private int oldNum = 0;

	public int startDay, endDay;

	public static ArrayList<ArrayList<String>> csvData = new ArrayList<ArrayList<String>>();

	static boolean error1 = false; // 郵便番号があるのに住所がない
	static String errorName;

	ManageLog ml;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window = new DropFile();
					window.frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		});

	}

	// private void initialize() {
	//
	// error1 = false;
	// errorName = null;
	//
	// lineNum = 0;
	// // oldNum = 0;
	// window.la.setText("");
	//
	// cardList = new ArrayList<ArrayList<String>>();
	// csvData = new ArrayList<ArrayList<String>>();
	//
	// // ml = new ManageLog();
	// //
	// // if (window.logIsSelected) {
	// //
	// // try {
	// // ml.LoadLog();
	// // startDay = ml.startDay;
	// // endDay = ml.endDay;
	// //
	// // } catch (IOException e) {
	// // // TODO 自動生成された catch ブロック
	// // e.printStackTrace();
	// // }
	// //
	// // } else {
	// // }
	//
	// }
	//
	// public void inputFile(File newFile, String newFileDate) {
	//
	// try {
	// initialize();
	//
	// BufferedReader newCsv = new BufferedReader(new InputStreamReader(new
	// FileInputStream(newFile), "MS932"));
	//
	// addList(newCsv);
	// if (window.getCp().getRadio2().isSelected()) {
	// startDay = window.getCp().date[0];
	// endDay = window.getCp().date[1];
	// extractData();
	// } else {
	// // ExtractByOther.extracting((String)
	// // window.getCp().getCombo().getSelectedItem(), window);
	// }
	// // oldNum = lineNum;
	//
	// newCsv.close();
	//
	// // エラー処理 or ファイル出力
	// if (error1) {
	// outputError(1);
	// } else {
	// writeCsv(newFile, newFileDate);
	// }
	//
	// } catch (IOException ex) {
	//
	// // 例外発生時処理
	// ex.printStackTrace();
	//
	// }
	//
	// }
	//
	// // 読み込んだデータをリストに入れる
	// private void addList(BufferedReader csv) throws IOException {
	// String line;
	//
	// for (int i = 0; i < 8; i++) {
	//
	// line = csv.readLine();
	// }
	//
	// while ((line = csv.readLine()) != null) {
	// ArrayList<String> lineData = new ArrayList<String>();
	// token = new CSVTokenizer(line);
	// lineNum++;
	//
	// String nextToken = token.nextToken();
	// lineData.add(nextToken);
	//
	// while (token.hasMoreTokens()) {
	//
	// String n = token.nextToken();
	// // lineData.add(token.nextToken());
	// if (n.equals("\"")) {
	// lineData.add("");
	// } else {
	// lineData.add(n);
	// }
	//
	// }
	// lineData.add(lineData.size(), Integer.toString(lineNum));
	//
	// csvData.add(lineData);
	// // System.out.println(lineData);
	// }
	//
	// }
	//
	// // 指定した日付のデータを抽出する
	// private void extractData() {
	//
	// for (ArrayList<String> data : csvData) {
	//
	// String dataDate = data.get(13);
	// String[] s = dataDate.split("/");
	//
	// s[1] = window.getCp().addZero(s[1]);
	// s[2] = window.getCp().addZero(s[2]);
	//
	// StringBuffer sb = new StringBuffer();
	// sb.append(s[0]);
	// sb.append(s[1]);
	// sb.append(s[2]);
	// int d = Integer.parseInt(sb.toString());
	//
	// if (!existError() && d >= startDay && d <= endDay) {
	// editData(data);
	// }
	//
	// }
	// }
	//
	// public static void editData(ArrayList<String> data) {
	//
	// window.la.append(data.get(data.size() - 1) + "\t" + data.get(0) + "\t" +
	// data.get(3) + "\n");
	// window.la.setCaretPosition(window.la.getText().length());
	//
	// data.add(4, "");
	// String zipcode = data.get(6);
	// String address = data.get(7);
	//
	// RegAddress addr = new RegAddress(zipcode, address);
	//
	// if ((zipcode.length() != 0 && address.length() != 0) || (zipcode.length()
	// == 0 && address.length() == 0)) {
	//
	// addr.search();
	// editAddress(data, addr);
	//
	// } else if (zipcode.length() != 0 && address.length() == 0) {
	//
	// error1 = true;
	// errorName = data.get(0);
	//
	// } else if (zipcode.length() == 0 && address.length() != 0) {
	//
	// addr.noZipcode(address);
	// editAddress(data, addr);
	//
	// }
	//
	// }
	//
	// private static void editAddress(ArrayList<String> data, RegAddress addr)
	// {
	//
	// data.set(6, addr.getZipcode()); // 郵便番号（ハイフンを除いたもの）
	// data.add(7, ""); // 国
	// data.add(8, addr.getPrefecture()); // 都道府県
	// data.add(9, addr.getCity()); // 市区郡
	// data.set(10, addr.getTown()); // それ以降
	//
	// data.remove(data.size() - 1);
	//
	// cardList.add(data);
	//
	// }
	//
	// private void writeCsv(File newFile, String newFileDate) {
	//
	// try {
	//
	// if (cardList.size() == 0) {
	//
	// window.ra.append("> 新しい名刺データはありません（出力ファイルなし）\n\n");
	// return;
	//
	// } else {
	// // System.out.println("リストのサイズ\t" + cardList.size());
	// }
	//
	// // 出力ファイル生成
	// StringBuffer newFilePath = new StringBuffer();
	// newFilePath.append(DIFF_CSV);
	//
	// StringBuffer newFileName = new StringBuffer();
	// newFileName.append(newFileDate);
	// newFileName.append(".csv");
	// newFilePath.append(newFileName);
	//
	// FileOutputStream fos = new FileOutputStream(newFilePath.toString());
	// OutputStreamWriter osw = new OutputStreamWriter(fos, "MS932");
	// BufferedWriter bw = new BufferedWriter(osw);
	//
	// // 会社名 部署名 役職 氏名 e-mail 郵便番号 住所 TEL会社 TEL部門 TEL直通 FAX 携帯電話 URL 名刺交換日
	// // Eightでつながっている人 再データ化中の名刺 '?'を含んだデータ
	//
	// // 変換後のデータの列名
	// String[] item = { "会社名", "部署名", "役職", "姓", "名", "メール", "郵便番号", "国",
	// "都道府県", "市区郡", "町名・番地", "電話", "TEL部門",
	// "TEL直通", "FAX", "携帯", "Webサイト", "名刺交換日", "Eightでつながっている人", "再データ化中の名刺",
	// "'?'を含んだデータ" };
	// for (String s : item) {
	// bw.write(s);
	// bw.write(",");
	// }
	// bw.newLine();
	//
	// // 差分データの出力
	// //
	// window.la.append("\n\n過去のデータと重複しているデータは除外されます。\n（メールアドレスが一致している場合、重複しているとみなしています。）\n");
	// // window.la.setCaretPosition(window.la.getText().length());
	//
	// int finalNum = 0;
	// // , tmpNum = 0;
	// for (ArrayList<String> dataList : cardList) {
	// // tmpNum++;
	//
	// // if (isDuplicate(dataList) == false) {
	//
	// CSVLine csvline = new CSVLine();
	// for (String data : dataList) {
	//
	// csvline.addItem(data);
	// }
	// String line = csvline.getLine();
	// bw.write(line);
	// bw.newLine();
	// finalNum++;
	//
	// // } else {
	//
	// // window.la.append(Integer.toString(oldNum + tmpNum) + "\t" +
	// // dataList.get(0) +
	// // "\t" + dataList.get(3)
	// // + "\t" + dataList.get(5) + "\n");
	// // window.la.setCaretPosition(window.la.getText().length());
	// // }
	//
	// }
	//
	// bw.close();
	//
	// String dataNum = Integer.toString(finalNum);
	//
	// window.ra.append("> エクスポートしたファイル：" + newFileName + "\n");
	// window.ra.append("> 新しい名刺データは " + dataNum + " 個です。\n");
	//
	// // ml.writeLog(startDay, endDay, dataNum, window.cb.isSelected());
	//
	// try {
	//
	// copyFile(new File(newFile.getPath()), new File(OLD_CSV));
	// window.ra.append("> OldData.csv を書き換えました。\n\n");
	// window.ra.setCaretPosition(window.ra.getText().length());
	//
	// } catch (IOException e) {
	//
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	//
	// }
	//
	// // window.callStopMethod();
	//
	// } catch (IOException e) {
	//
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	//
	// }
	// }
	//
	// // 重複チェック。重複があればtrueを返す
	// // private boolean isDuplicate(ArrayList<String> dataList) throws
	// // IOException {
	// //
	// // if (dataList.get(5).length() != 0) {
	// //
	// // BufferedReader oldCsv = new BufferedReader(new InputStreamReader(new
	// // FileInputStream(OLD_CSV), "MS932"));
	// // String line;
	// //
	// // while ((line = oldCsv.readLine()) != null) {
	// //
	// // ArrayList<String> list = new ArrayList<String>();
	// // CSVTokenizer token = new CSVTokenizer(line);
	// //
	// // while (token.hasMoreTokens()) {
	// //
	// // list.add(token.nextToken());
	// //
	// // }
	// //
	// // if (list.size() > 4 && dataList.get(5).equals(list.get(4))) {
	// // oldCsv.close();
	// // return true;
	// // }
	// //
	// // }
	// //
	// // oldCsv.close();
	// // }
	// // return false;
	// // }
	//
	// private boolean existError() {
	// if (error1) {
	// return true;
	// }
	// return false;
	// }
	//
	// public void outputError(int errNum) {
	// switch (errNum) {
	// case 1:
	// window.ra.append(
	// "!! ERROR !!\n> " + lineNum + " 行目 " + errorName + " 様のデータ\n>
	// 郵便番号が登録されていますが、住所の登録がありません。\n\n");
	// break;
	//
	// default:
	// window.ra.append("> 原因不明のエラーです。鈴木英梨花に報告をお願いします。\n\n");
	// break;
	// }
	// }
	//
	// @SuppressWarnings("resource")
	// public void copyFile(File in, File out) throws IOException {
	//
	// FileChannel inChannel = new FileInputStream(in).getChannel();
	// FileChannel outChannel = new FileOutputStream(out).getChannel();
	//
	// try {
	//
	// inChannel.transferTo(0, inChannel.size(), outChannel);
	//
	// } catch (IOException e) {
	//
	// throw e;
	// } finally {
	//
	// if (inChannel != null)
	// inChannel.close();
	// if (outChannel != null)
	// outChannel.close();
	//
	// }
	// }
}
