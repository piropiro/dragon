// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HPanel.java

import java.awt.*;
import mine.JCanvas;
import mine.Mine;

public class HPanel extends JCanvas {

	public HPanel(UnitWorks unitworks, boolean flag) {
		super(96, 14);
		setFont(Mine.getFont(14));
		uw = unitworks;
		high = flag;
		hpb = new HPBar();
	}

	public void setLocate(Body body, Body body1) {
		int i = body.x;
		int j = body.y;
		int k;
		if (body.x >= body1.x)
			k = i * 32 + 32;
		else
			k = i * 32 - getSize().width;
		if (i < 3) {
			k = i * 32 + 32;
			if (body.y == body1.y && Math.abs(body.x - body1.x) < 5)
				k = Math.max(body.x, body1.x) * 32 + 32;
		}
		if (i > 16) {
			k = i * 32 - getSize().width;
			if (body.y == body1.y && Math.abs(body.x - body1.x) < 5)
				k = Math.min(body.x, body1.x) * 32 - getSize().width;
		}
		int l;
		if (high)
			l = j * 32 + 1;
		else
			l = j * 32 + 17;
		setLocation(k, l);
	}

	public void display(Body body, Body body1, int i, boolean flag) {
		if (body == null) {
			dispose();
			return;
		}
		ba = body;
		bb = body1;
		hpb.setup(flag, body.hp, body.hpMax);
		hpb.setMin(body.hp - i, false);
		switch (body.color) {
		case 1: // '\001'
			setBackground(new Color(0, 0, 150));
			break;

		case 3: // '\003'
			setBackground(new Color(150, 0, 0));
			break;

		case 2: // '\002'
			setBackground(new Color(0, 100, 0));
			break;
		}
		setLocate(body, body1);
		setVisible(true);
		repaint();
	}

	public void dispose() {
		setVisible(false);
	}

	public void paint(Graphics g) {
		if (ba == null) {
			return;
		} else {
			Dimension dimension = getSize();
			g.setColor(getBackground());
			g.fillRect(0, 0, dimension.width, dimension.height);
			g.setColor(Color.black);
			g.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
			hpb.paint(2, 12, g);
			return;
		}
	}

	public void damage(int i) {
		hpb.setMin(ba.hp - i, true);
		repaint();
	}

	public void henka() {
		int i = hpb.getSleepTime();
		for (; hpb.henka(); uw.sleep(i))
			repaint();

		repaint();
	}

	UnitWorks uw;
	Body ba;
	Body bb;
	boolean high;
	HPBar hpb;
}
