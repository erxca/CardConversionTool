package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ConditionPart {

	private JFrame frame;
	private int width;
	private int ph;
	private int x, y;
	private int size;
	public int date[] = new int[2];
	private int i = 0;

	private JPanel ip1;
	private JRadioButton radio2, radio3;
	private JComboBox<String> combo;
	private MyTextField y1, m1, d1, y2, m2, d2;

	// 入力部があるラベルは3、ないラベルは2の割合で分割
	private final int NUM = 2 * 2 + 3 * 2;

	public ConditionPart(JFrame frame, int height, int width, int x, int y) {

		this.frame = frame;
		this.width = width;
		this.x = x;
		this.y = y;
		this.ph = height / NUM;

	}

	public void makeConditionPart() {

		JPanel rp2 = makeRadioPanel();
		size = calcCharSize(rp2, ph * 2);
		radio2 = new MyRadioButton("日付を指定する　　(例：2017年1月20日　～　2017年10月1日)　　", size, true);
		rp2.add(radio2, BorderLayout.WEST);

		ip1 = makeInputPanel();

		y1 = new MyTextField(6, size);
		ip1.add(y1);
		ip1.add(new MyDateLabel("年", size));

		m1 = new MyTextField(4, size);
		ip1.add(m1);
		ip1.add(new MyDateLabel("月", size));

		d1 = new MyTextField(4, size);
		ip1.add(d1);
		ip1.add(new MyDateLabel("日", size));
		ip1.add(new MyDateLabel("～", size));

		y2 = new MyTextField(6, size);
		ip1.add(y2);
		ip1.add(new MyDateLabel("年", size));

		m2 = new MyTextField(4, size);
		ip1.add(m2);
		ip1.add(new MyDateLabel("月", size));

		d2 = new MyTextField(4, size);
		ip1.add(d2);
		ip1.add(new MyDateLabel("日", size));

		JPanel rp3 = makeRadioPanel();
		radio3 = new MyRadioButton("その他の条件を指定する　　", size, false);
		rp3.add(radio3, BorderLayout.WEST);

		JPanel ip2 = makeInputPanel();

		String[] elements = { "会社名", "氏名", "e-mail", "携帯電話" };
		combo = new JComboBox<String>(elements);
		combo.setPreferredSize(new Dimension(size * 10, ph * 2));
		ip2.add(combo);

		ip2.add(new MyTextField(30, size));

		ButtonGroup group = new ButtonGroup();
		// group.add(radio1);
		group.add(radio2);
		group.add(radio3);

	}

	private JPanel makeRadioPanel() {

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBounds(x, y, width, ph * 2);
		frame.getContentPane().add(p);
		y += ph * 2;

		return p;

	}

	private JPanel makeInputPanel() {

		JPanel p = new JPanel();
		p.setBounds(x, y, width, ph * 3);
		frame.getContentPane().add(p);
		y += ph * 3;

		return p;
	}

	private int calcCharSize(Component comp, int compHeight) {

		int size = 0;
		FontMetrics fm;
		do {
			size++;
			fm = comp.getFontMetrics(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		} while (fm.getAscent() + fm.getDescent() < (compHeight));

		return size;

	}

	// 日付が正しく入力されているか
	public boolean checkInputValue() {
		String year1 = y1.getText();
		String month1 = m1.getText();
		String day1 = d1.getText();
		String year2 = y2.getText();
		String month2 = m2.getText();
		String day2 = d2.getText();

		if (check(year1, month1, day1)) {
			System.out.println(date[0]);
			return check(year2, month2, day2);
		}

		return false;
	}

	private boolean check(String y, String m, String d) {
		if ((!y.isEmpty() && !m.isEmpty() && !d.isEmpty()) && (y.length() == 4 && m.length() < 3 && d.length() < 3)) {
			// 日付1の値を取得
			m = addZero(m);
			d = addZero(d);

			StringBuffer sb = new StringBuffer();
			sb.append(y);
			sb.append(m);
			sb.append(d);
			if (isNumber(sb.toString())) {
				date[i++] = Integer.parseInt(sb.toString());
			} else {
				return false;
			}

		} else {
			return false;
		}
		return true;
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

	// 数字かチェック
	public boolean isNumber(String val) {
		String regex = "\\A[-]?[0-9]+\\z";
		Pattern p = Pattern.compile(regex);
		Matcher m1 = p.matcher(val);
		return m1.find();
	}

	public JRadioButton getRadio2() {
		return radio2;
	}

	public JComboBox<String> getCombo() {
		return combo;
	}
}
