// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SingleAnime.java

import java.awt.*;
import mine.Mine;

class SingleAnime implements AnimeListener {

	SingleAnime(UnitWorks unitworks, Point point, int ai[], int i) {
		uw = unitworks;
		ba = point;
		imgs = ai;
		sleep = i;
	}

	public void animation(Component component) {
		for (int i = 0; i < imgs.length; i++) {
			img = imgs[i];
			component.repaint();
			uw.sleep(sleep);
		}

	}

	public void paint(Graphics g) {
		int i = (img % 15) * 32;
		int j = (img / 15) * 32;
		Mine.drawImage(Statics.anime, 0, 0, i, j, 32, 32, g);
	}

	UnitWorks uw;
	Point ba;
	int imgs[];
	int img;
	int sleep;
}
