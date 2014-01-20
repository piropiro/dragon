// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrowAnime.java

import java.awt.*;

class ArrowAnime implements AnimeListener {

	ArrowAnime(UnitWorks unitworks, Point point, Point point1, int i, int ai[],
			int j) {
		uw = unitworks;
		sleep = j;
		arr = new Arrow(point, point1, i, ai, false);
	}

	public void animation(Component component) {
		for (; !arr.end(); uw.sleep(sleep)) {
			arr.move();
			component.setLocation(arr.x, arr.y);
			component.repaint();
		}

		uw.sleep(sleep);
	}

	public void paint(Graphics g) {
		arr.paint(g);
	}

	UnitWorks uw;
	Arrow arr;
	int sleep;
}
