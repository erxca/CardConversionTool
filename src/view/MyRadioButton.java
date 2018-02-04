package view;

import java.awt.Font;

import javax.swing.JRadioButton;

public class MyRadioButton extends JRadioButton {

	public MyRadioButton(String text, int size, boolean selected) {

		setText(text);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		setSelected(selected);

	}

}
