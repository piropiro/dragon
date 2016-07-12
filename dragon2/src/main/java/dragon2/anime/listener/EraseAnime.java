package dragon2.anime.listener;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EraseAnime.java

import java.awt.*;
import java.awt.image.BufferedImage;

import dragon2.UnitWorks;
import mine.UnitMap;

public class EraseAnime implements AnimeListener {

	public EraseAnime(UnitWorks unitworks, UnitMap unitmap, Point point) {
		uw = unitworks;
		V = unitmap;
		b = point;
		setOffi(point);
	}

	private void setOffi(Point point) {
		offi = new BufferedImage(32, 32, 2);
		Graphics g = offi.getGraphics();
		int i = V.G(2, 0, point.x, point.y);
		int j = V.G(5, 0, point.x, point.y);
		V.S(2, 0, point.x, point.y, 0);
		V.S(3, 0, point.x, point.y, 0);
		V.S(5, 0, point.x, point.y, 0);
		V.EP(point.x, point.y, point.x, point.y, g);
		V.S(2, 0, point.x, point.y, i);
		V.S(5, 0, point.x, point.y, j);
	}

	public void animation(Component component) {
		for (count = 1; count <= 4; count++) {
			component.repaint();
			uw.sleep(100L);
		}

		V.S(2, 0, b.x, b.y, 0);
		V.S(5, 0, b.x, b.y, 0);
		component.setVisible(false);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 8; i++) {
			int j = i * 4;
			int l = 0;
			int j1 = i * 4 + count;
			byte byte0 = 32;
			g.drawImage(offi, j, l, j1, byte0, j, l, j1, byte0, null);
		}

		for (int k = 0; k < 8; k++) {
			int i1 = 0;
			int k1 = k * 4;
			byte byte1 = 32;
			int l1 = k * 4 + count;
			g.drawImage(offi, i1, k1, byte1, l1, i1, k1, byte1, l1, null);
		}

	}

	UnitWorks uw;
	UnitMap V;
	Point b;
	BufferedImage offi;
	int count;
}
