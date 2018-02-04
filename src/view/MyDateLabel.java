package view;

import java.awt.Font;

import javax.swing.JLabel;

public class MyDateLabel extends JLabel {

	public MyDateLabel(String s, int size) {

		setText(s);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));

	}
}
