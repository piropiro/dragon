// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CloseAnime.java

import java.awt.*;
import mine.ImageLoader;
import mine.Mine;

class CloseAnime implements AnimeListener {

	CloseAnime(UnitWorks unitworks, int i, String s) {
		uw = unitworks;
		type = i;
		img = ImageLoader.getImage(s, true);
	}

	public void animation(Component component) {
		for (count = 0; count <= 16; count++) {
			component.repaint();
			if (type == 0)
				uw.sleep(20L);
			else
				uw.sleep(20L);
		}

	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		switch (type) {
		case 0: // '\0'
			g.drawImage(img, 0, 0, null);
			Mine.setAlpha(g, Math.min(1.0D, (1.0D * (double) count) / 16D));
			g.fillRect(0, 0, 640, 480);
			break;

		case 1: // '\001'
			Mine.setAlpha(g,
					Math.max(0.0D, 1.0D - (1.0D * (double) count) / 16D));
			g.fillRect(0, 0, 640, 480);
			break;
		}
	}

	static final int MAX = 16;
	static final int OUT = 0;
	static final int IN = 1;
	private UnitWorks uw;
	private Image img;
	int count;
	int type;
}
