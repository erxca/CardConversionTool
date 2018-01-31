package tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class DropFile {

	CardConversionTool cct = new CardConversionTool();
	public JFrame frame;
	public MyTextArea ra, la;

	private int fWidth, fHeight;
	private int xPos; // 各コンポーネントのxの位置
	private int cWidth, blank, yPos;
	private int raSize = 0;

	private MyRadioButton radio1, radio2, radio3;
	private JTextField ytf, mtf, dtf, ytf2, mtf2, dtf2;
	int date1, date2;

	private JPanel dtp;
	public JCheckBox cb;
	public boolean logIsSelected = false;
	public JTextField name;
	MyThread thread;

	private final int FRAME_WIDTH_RATIO = 50; // フレームの画面に対する幅の割合
	private final int WIDTH_RATIO = 94; // 各コンポーネントのフレームに対する幅の割合
	private final int BRANK_RATIO = 3; // 各コンポーネントの間の余白の割合
	private final int X_RATIO = 3; // 横の余白の割合
	private final int LBL_RATIO = 5; // フレームに対する説明ラベルの割合
	private final int DATE_RATIO = 20;
	private final int DDLBL_RATIO = 15;
	private final int RESULT_RATIO = 22;
	private final int LOG_RATIO = 15;

	public DropFile() {

		// 画面サイズの取得
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;

		// フレームのサイズ、コンポーネントの位置・サイズ設定
		fWidth = w * FRAME_WIDTH_RATIO / 100;
		fHeight = h * 3 / 5;
		xPos = X_RATIO * fWidth / 100;
		cWidth = WIDTH_RATIO * fWidth / 100;
		blank = fHeight * BRANK_RATIO / 100;
		yPos = blank;

		// フレームの生成
		frame = new JFrame("新規名刺データ抽出ツール");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.getContentPane().setPreferredSize(new Dimension(fWidth, fHeight));
		frame.pack();

		// 説明ラベルの生成
		makeDescriptionLabel();

		// 日付設定部分生成
		ConditionPart cp = new ConditionPart(frame, fHeight * DATE_RATIO, cWidth, xPos, yPos);
		yPos += cp.makeConditionPart() + blank;
		// makeDateLabel();

		// ファイルをドロップさせるためのラベル生成
		makeDdLabel();

		// 結果などを載せるためのテキストエリア生成
		makeResultArea();

		// 途中経過表示用テキストエリア
		makeLogArea();

	}

	// 説明ラベルの生成
	private void makeDescriptionLabel() {

		JLabel lbl = new JLabel("新規名刺データを抽出したいcsvファイルをインポートしてください。");
		int lblHeight = fHeight * LBL_RATIO / 100;
		lbl.setBounds(xPos, yPos, cWidth, lblHeight);

		lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, calcCharSize(lbl, lblHeight) - 1));
		frame.getContentPane().add(lbl);
		yPos += lblHeight + blank;

	}

	// ファイルをドロップさせるためのラベル生成
	private void makeDdLabel() {

		JLabel ddlbl = new JLabel("ここにファイルをドロップ");
		int ddlblHeight = fHeight * DDLBL_RATIO / 100;
		ddlbl.setBounds(xPos, yPos, cWidth, ddlblHeight);
		ddlbl.setBackground(Color.WHITE);
		ddlbl.setOpaque(true);
		ddlbl.setHorizontalAlignment(JLabel.CENTER);

		ddlbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, calcCharSize(ddlbl, ddlblHeight / 2) - 1));
		frame.getContentPane().add(ddlbl);
		yPos += ddlblHeight + blank;

		// ドロップ操作を有効にする
		ddlbl.setTransferHandler(new DropFileHandler());

	}

	// 日付設定部分生成
	private void makeDateLabel() {

		// 入力部があるラベルは3、ないラベルは2
		JPanel p1 = new JPanel();
		int pHeight = fHeight * DATE_RATIO / 10 / 100;
		int size = calcCharSize(p1, pHeight * 2) - 3;
		p1.setBounds(xPos, yPos, cWidth, pHeight * 2);
		p1.setLayout(new BorderLayout());
		frame.getContentPane().add(p1);
		yPos += pHeight * 2;

		JPanel p2 = new JPanel();
		p2.setBounds(xPos, yPos, cWidth, pHeight * 2);
		p2.setLayout(new BorderLayout());
		frame.getContentPane().add(p2);
		yPos += pHeight * 2;

		dtp = new JPanel();
		dtp.setBounds(xPos, yPos, cWidth, pHeight * 3);
		frame.getContentPane().add(dtp);
		dtp.setOpaque(true);
		dtp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		yPos += pHeight * 3;

		JPanel p3 = new JPanel();
		p3.setBounds(xPos, yPos, cWidth, pHeight * 3);
		p3.setLayout(new BorderLayout());
		frame.getContentPane().add(p3);
		yPos += pHeight * 3;

		name = new JTextField(20);
		name.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		p3.add(name);

		ytf = new JTextField(6);
		settingComponent(ytf, size);

		JLabel year = new JLabel("年");
		settingComponent(year, size);

		mtf = new JTextField(4);
		settingComponent(mtf, size);

		JLabel month = new JLabel("月");
		settingComponent(month, size);

		dtf = new JTextField(4);
		settingComponent(dtf, size);

		JLabel day = new JLabel("日");
		settingComponent(day, size);

		JLabel kara = new JLabel("～");
		settingComponent(kara, size);

		ytf2 = new JTextField(6);
		settingComponent(ytf2, size);

		JLabel year2 = new JLabel("年");
		settingComponent(year2, size);

		mtf2 = new JTextField(4);
		settingComponent(mtf2, size);

		JLabel month2 = new JLabel("月");
		settingComponent(month2, size);

		dtf2 = new JTextField(4);
		settingComponent(dtf2, size);

		JLabel day2 = new JLabel("日");
		settingComponent(day2, size);

		yPos += blank;

		radio1 = new MyRadioButton("前回取り込んだ日付以降のデータを抽出する", size, false);
		radio2 = new MyRadioButton("日付を指定する　　(例：2017年1月20日　～　2017年10月1日)　　", size, true);
		radio3 = new MyRadioButton("名前で抽出する　　", size, false);

		cb = new JCheckBox("最新データ", true);
		cb.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);

		p1.add(radio1, BorderLayout.WEST);
		p2.add(radio2, BorderLayout.WEST);
		p2.add(cb);
		p3.add(radio3, BorderLayout.WEST);

	}

	// フォント指定後、日付パネルに追加
	private void settingComponent(Component c, int size) {
		c.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		dtp.add(c);
	}

	// 結果などを載せるためのテキストエリア生成
	private void makeResultArea() {

		ra = new MyTextArea();

		JScrollPane jsp = new JScrollPane();
		int jspHeight = fHeight * RESULT_RATIO / 100;
		jsp.setBounds(xPos, yPos, cWidth, jspHeight);

		FontMetrics raFm;
		do {
			raSize++;
			raFm = ra.getFontMetrics(new Font(Font.SANS_SERIF, Font.PLAIN, raSize));
		} while (raFm.getAscent() + raFm.getDescent() < (jspHeight / 8));

		ra.setting(raSize);
		jsp.setViewportView(ra);
		frame.getContentPane().add(jsp);
		yPos += jspHeight + blank;
	}

	// 途中経過表示用テキストエリア
	private void makeLogArea() {
		la = new MyTextArea();
		la.setting(raSize);

		la.setFocusable(false);

		JScrollPane jsp2 = new JScrollPane();
		int jsp2Height = fHeight * LOG_RATIO / 100;
		jsp2.setBounds(xPos, yPos, cWidth, jsp2Height);
		jsp2.setViewportView(la);
		frame.getContentPane().add(jsp2);
	}

	// 文字サイズ計算
	private int calcCharSize(Component comp, int compHeight) {

		int size = 0;
		FontMetrics fm;
		do {
			size++;
			fm = comp.getFontMetrics(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		} while (fm.getAscent() + fm.getDescent() < (compHeight));

		return size;

	}

	// 日付に０を追加
	public String addZero(String s) {
		if (s.length() == 1) {
			StringBuffer sb = new StringBuffer("0");
			sb.append(s);
			s = sb.toString();
		}
		return s;
	}

	// 日付が正しく入力されているか
	private boolean checkInputValue() {
		String year1 = ytf.getText();
		String month1 = mtf.getText();
		String day1 = dtf.getText();
		String year2 = ytf2.getText();
		String month2 = mtf2.getText();
		String day2 = dtf2.getText();

		if (!year1.isEmpty() && !month1.isEmpty() && !dtf.getText().isEmpty()) {
			if (year1.length() == 4 && month1.length() < 3 && day1.length() < 3) {
				// 日付1の値を取得
				month1 = addZero(month1);
				day1 = addZero(day1);

				StringBuffer sb1 = new StringBuffer();
				sb1.append(year1);
				sb1.append(month1);
				sb1.append(day1);
				date1 = Integer.parseInt(sb1.toString());
			} else {
				return false;
			}
		} else {
			return false;
		}

		if (!ytf2.getText().isEmpty() && !mtf2.getText().isEmpty() && !dtf2.getText().isEmpty()) {
			if (year2.length() == 4 && month2.length() < 3 && day2.length() < 3) {
				// 日付2の値を取得
				month2 = addZero(month2);
				day2 = addZero(day2);

				StringBuffer sb2 = new StringBuffer();
				sb2.append(year2);
				sb2.append(month2);
				sb2.append(day2);
				date2 = Integer.parseInt(sb2.toString());
			} else {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}
	//
	// public void callStopMethod() {
	// thread.stopRunning();
	// }

	// ドロップ操作の処理を行うクラス
	private class DropFileHandler extends TransferHandler {

		// ドロップされたものを受け取るか判断 (ファイルのときだけ受け取る)
		@Override
		public boolean canImport(TransferSupport support) {
			if (!support.isDrop()) {
				// ドロップ操作でない場合は受け取らない
				return false;
			}

			if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				// ドロップされたのがファイルでない場合は受け取らない
				return false;
			}

			return true;
		}

		// ドロップされたファイルを受け取る
		@Override
		public boolean importData(TransferSupport support) {
			// 受け取っていいものか確認する
			if (!canImport(support)) {
				return false;
			}

			// ドロップ処理
			Transferable t = support.getTransferable();
			logIsSelected = radio1.isSelected();

			try {
				// ファイルを受け取る
				@SuppressWarnings("unchecked")
				List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				if (radio2.isSelected() && !checkInputValue()) {
					ra.append("日付を正しく入力してください。\n\n");
					return false;
				}

				if (files.size() != 1) {
					ra.append("一度にインポートできるファイルは一つだけです。\n");
					return false;
				}

				Date date = new Date();
				SimpleDateFormat textAreaFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String newFileDate = fileNameFormat.format(date);
				ra.append(textAreaFormat.format(date) + "\n");
				ra.append("> インポートしたファイル：" + files.get(0).getName() + "\n");

				thread = new MyThread(files.get(0), newFileDate);
				thread.start();

			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	private class MyThread extends Thread {
		File newFile;
		String fileDate;
		// private boolean running = true;

		public MyThread(File newFile, String fileDate) {
			this.newFile = newFile;
			this.fileDate = fileDate;
		}

		public void run() {
			// while (running) {
			cct.inputFile(newFile, fileDate);
			// }
		}

		// public void stopRunning() {
		// running = false;
		// }
	}
}
