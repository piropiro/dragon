// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AllAnime.java

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import mine.Mine;
import mine.UnitMap;

class AllAnime implements AnimeListener {

	AllAnime(UnitWorks unitworks, UnitMap unitmap, int ai[], int i) {
		uw = unitworks;
		V = unitmap;
		sleep = i;
		imgs = ai;
		box = unitmap.getPaintBox(4, 1, 0, 0, 20, 15);
		
		points = new ArrayList<>();
		for (int j = 0; j < 20; j++) {
			for (int k = 0; k < 15; k++)
				if (unitmap.G(4, 1, j, k) != 0)
					points.add(new Point((j - box[0]) * 32, (k - box[1]) * 32));

		}
	}

	public void animation(Component component) {
		component.setLocation(box[0] * 32, box[1] * 32);
		component.setSize(box[2] * 32, box[3] * 32);
		for (n = 0; n < imgs.length; n++) {
			component.repaint();
			uw.sleep(sleep);
		}

	}

	public void paint(Graphics g) {
		for (Point point : points) {
			int j = (imgs[n] % 15) * 32;
			int k = (imgs[n] / 15) * 32;
			Mine.drawImage(Statics.anime, point.x, point.y, j, k, 32, 32, g);
		}

	}

	UnitWorks uw;
	UnitMap V;
	int sleep;
	int box[];
	int imgs[];
	List<Point> points;
	int n;
}
