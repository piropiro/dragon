package dragon2.panel;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TPanel.java

import java.awt.*;

import dragon2.Iconable;
import dragon2.Map;
import dragon2.UnitWorks;
import dragon2.common.Body;
import mine.JCanvas;
import mine.Mine;

public class TPanel extends JCanvas {

	public TPanel(UnitWorks unitworks, Map map1) {
		super(30, 15);
		uw = unitworks;
		map = map1;
	}

	public void setLocate() {
		Dimension dimension = getSize();
		int i = (b.x * 32 + 16) - dimension.width / 2;
		i = Mine.mid(0, i, 640 - dimension.width);
		int j = b.y * 32 + 20;
		j = Mine.mid(0, j, 480 - dimension.height);
		setLocation(i, j);
	}

	public void display(Iconable iconable, Body body) {
		name = iconable.getSubName();
		b = body;
		switch (iconable.getColor()) {
		case 0: // '\0'
			setBackground(Color.white);
			setForeground(Color.black);
			break;

		case 1: // '\001'
			setBackground(Color.blue);
			setForeground(Color.white);
			break;

		case 2: // '\002'
			setBackground(Color.red);
			setForeground(Color.white);
			break;

		case 3: // '\003'
			setBackground(Color.green);
			setForeground(Color.black);
			break;

		case 4: // '\004'
			setBackground(Color.yellow);
			setForeground(Color.black);
			break;

		case 5: // '\005'
			setBackground(new Color(50, 100, 255));
			setForeground(Color.white);
			break;
		}
		setSize(getFontMetrics(getFont()).stringWidth(name) + 3, 15);
		setLocate();
		setVisible(true);
	}

	public void dispose() {
		setVisible(false);
	}

	public void paint(Graphics g) {
		Dimension dimension = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.black);
		g.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
		g.setColor(getForeground());
		g.drawString(name, 2, 12);
	}

	UnitWorks uw;
	Map map;
	static final int C_WHITE = 0;
	static final int C_BLUE = 1;
	static final int C_RED = 2;
	static final int C_GREEN = 3;
	static final int C_YELLOW = 4;
	static final int C_SKY = 5;
	String name;
	Body b;
}
