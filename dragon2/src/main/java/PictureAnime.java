// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PictureAnime.java

import java.awt.*;
import mine.ImageLoader;

class PictureAnime implements AnimeListener {

	PictureAnime(UnitWorks unitworks, String s) {
		uw = unitworks;
		img = ImageLoader.getImage(s, true);
	}

	public void animation(Component component) {
		component.repaint();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	private UnitWorks uw;
	private Image img;
}
