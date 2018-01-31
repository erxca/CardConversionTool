package tool;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ConditionPart {

	private JFrame frame;
	private int width;
	private int ph;
	private int x, y;
	private int size;

	private JPanel ip1;
	private JRadioButton radio1, radio2, radio3;
	private JTextField ytf, mtf, dtf, ytf2, mtf2, dtf2, input;
	private JCheckBox cb;

	// 入力部があるラベルは3、ないラベルは2の割合で分割
	private final int NUM = 2 * 3 + 3 * 2;

	public ConditionPart(JFrame frame, int height, int width, int x, int y) {

		this.frame = frame;
		this.width = width;
		this.x = x;
		this.y = y;
		this.ph = height / NUM / 100;

	}

	public int makeConditionPart() {

		JPanel rp1 = makeRadioPanel();
		size = calcCharSize(rp1, ph * 2) - 3;
		radio1 = new MyRadioButton("前回取り込んだ日付以降のデータを抽出する", size, false);
		rp1.add(radio1, BorderLayout.WEST);

		JPanel rp2 = makeRadioPanel();
		radio2 = new MyRadioButton("日付を指定する　　(例：2017年1月20日　～　2017年10月1日)　　", size, true);
		rp2.add(radio2, BorderLayout.WEST);

		cb = new JCheckBox("最新データ", true);
		cb.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		rp2.add(cb);

		ip1 = makeInputPanel();

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

		JPanel rp3 = makeRadioPanel();
		radio3 = new MyRadioButton("名前で抽出する　　", size, false);
		rp3.add(radio3, BorderLayout.WEST);

		JPanel ip2 = makeInputPanel();

		String[] elements = { "会社名", "氏名", "e-mail", "携帯電話" };
		JComboBox combo = new JComboBox(elements);
		ip2.add(combo);

		input = new JTextField(20);
		input.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		ip2.add(input);

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);

		return y;

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

	// フォント指定後、日付パネルに追加
	private void settingComponent(Component c, int size) {
		c.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		ip1.add(c);
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
}
