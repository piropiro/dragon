package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MPanel.java

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;
import mine.Mine;

public class MPanel extends StatusBase {

	public MPanel(UnitWorks unitworks) {
		super(160, 128, true);
		uw = unitworks;
		list = new Vector();
		setFont(Mine.getFont(14));
	}

	public void setLocate() {
		Dimension dimension = getSize();
		int i = 300 - dimension.width / 2;
		int j = 240 - dimension.height / 2;
		setLocation(i, j);
	}

	public void setText(String s) {
		list.add(s);
	}

	public void display(Body body) {
		if (list.size() == 0)
			return;
		ba = body;
		hpb.setup(false, body.hp, body.hpMax);
		setLocate();
		setVisible(true);
		for (int i = 0; i < list.size(); i++) {
			n = i;
			String s = (String) list.elementAt(n);
			for (line = 0; line <= s.length(); line++) {
				repaint();
				uw.sleep(80L);
			}

			uw.sleep(200L);
		}

		uw.sleep(700L);
		list.removeAllElements();
		setVisible(false);
	}

	public void paint(Graphics g) {
		g.setFont(getFont());
		clear(ba.color, g);
		drawMain(uw, ba, g, true);
		int i = Math.max(0, (n - 3) + 1);
		for (int j = i; j <= n; j++) {
			if (j >= list.size())
				break;
			String s = (String) list.elementAt(j);
			if (j == n)
				drawLine(s.substring(0, Math.min(line, s.length())), 0,
						(j - i) + 1, g);
			else
				drawLine(s, 0, (j - i) + 1, g);
		}

	}

	UnitWorks uw;
	Vector list;
	Body ba;
	int n;
	int line;
	static final int MAX = 3;
	static final int C_WHITE = 0;
	static final int C_BLUE = 1;
	static final int C_RED = 2;
	static final int C_GREEN = 3;
	static final int C_YELLOW = 4;
}
