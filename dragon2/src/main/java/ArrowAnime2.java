// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrowAnime2.java

import java.awt.*;
import java.util.Vector;
import mine.UnitMap;

class ArrowAnime2 implements AnimeListener {

	ArrowAnime2(UnitWorks unitworks, UnitMap unitmap, Point point, int i,
			int ai[], int j) {
		uw = unitworks;
		V = unitmap;
		sleep = j;
		box = unitmap.getPaintBox(4, 1, 0, 0, 20, 15);
		point.x -= box[0] * 32;
		point.y -= box[1] * 32;
		Vector vector = new Vector();
		for (int k = 0; k < 20; k++) {
			for (int l = 0; l < 15; l++)
				if (unitmap.G(4, 1, k, l) != 0)
					vector.add(new Point(k * 32, l * 32));

		}

		arr = new Arrow[vector.size()];
		for (int i1 = 0; i1 < vector.size(); i1++) {
			Point point1 = (Point) vector.elementAt(i1);
			point1.x -= box[0] * 32;
			point1.y -= box[1] * 32;
			arr[i1] = new Arrow(point, point1, i, ai, true);
		}

	}

	public void animation(Component component) {
		component.setLocation(box[0] * 32, box[1] * 32);
		component.setSize(box[2] * 32, box[3] * 32);
		for (; !arr[0].end(); uw.sleep(sleep))
			component.repaint();

		uw.sleep(sleep);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < arr.length; i++) {
			arr[i].move();
			arr[i].paint(g);
		}

	}

	UnitWorks uw;
	UnitMap V;
	int sleep;
	Arrow arr[];
	int box[];
}
