// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LPanel.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import mine.JCanvas;
import mine.Mine;

class LPanel extends JCanvas implements ActionListener {

	LPanel(UnitWorks unitworks) {
		super(200, 100);
		uw = unitworks;
		time = new Timer(1000, this);
		time.setRepeats(false);
		setFont(Mine.getFont(24));
	}

	public void setLocate() {
		Dimension dimension = getSize();
		int i = 300 - dimension.width / 2;
		int j = 200 - dimension.height / 2;
		setLocation(i, j);
	}

	public void display(String s, int i) {
		text = s;
		switch (i) {
		case 1: // '\001'
			setBackground(new Color(0, 0, 150, 200));
			break;

		case 3: // '\003'
			setBackground(new Color(150, 0, 0, 200));
			break;

		case 2: // '\002'
			setBackground(new Color(0, 100, 0, 200));
			break;
		}
		setSize(getFontMetrics(getFont()).stringWidth(s) + 20, 32);
		setLocate();
		setVisible(true);
		repaint();
	}

	public void displayT(String s, int i, int j) {
		display(s, i);
		time.setInitialDelay(j);
		time.restart();
	}

	public void displayR(String s, int i, int j) {
		display(s, i);
		uw.sleep(j);
		setVisible(false);
	}

	public void paint(Graphics g) {
		Dimension dimension = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.white);
		g.drawRect(2, 2, dimension.width - 5, dimension.height - 5);
		Mine.setAntialias(g, true);
		Mine.drawString(text, 0, 24, dimension.width, g);
	}

	public void actionPerformed(ActionEvent actionevent) {
		setVisible(false);
	}

	UnitWorks uw;
	String text;
	Timer time;
}
