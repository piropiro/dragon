// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TitleAnime.java

import java.awt.*;
import mine.Mine;

class TitleAnime implements AnimeListener {

	TitleAnime(UnitWorks unitworks, int i) {
		uw = unitworks;
		font = Mine.getFont(24);
		setText(i);
	}

	private void setText(int i) {
		switch (i) {
		case 0: // '\0'
			text = "STAGE CLEAR";
			break;
		}
	}

	public void animation(Component component) {
		component.setLocation(0, 0);
		component.setSize(640, 100);
		step = 0;
		for (time = 1000; time >= 0; time -= 10) {
			component.repaint();
			uw.sleep(10L);
		}

	}

	public void paint(Graphics g) {
		Mine.setAntialias(g, true);
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.scale((double) time / 100D + 1.0D, 1.0D);
		graphics2d.setColor(new Color(255, 0, 0, ((1000 - time) * 255) / 1000));
		graphics2d.setFont(font);
		for (int i = 0; i < text.length(); i++) {
			graphics2d.translate(20 * i, 0);
			graphics2d.drawString("" + text.charAt(i), -12, 24);
			graphics2d.translate(-20 * i, 0);
		}

	}

	private UnitWorks uw;
	private int time;
	private int step;
	private String text;
	private Font font;
	private int px[];
	static final int IN = 0;
	static final int WAIT = 1;
	static final int OUT = 2;
}
