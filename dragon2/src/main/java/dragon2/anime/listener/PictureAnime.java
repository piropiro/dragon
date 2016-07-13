package dragon2.anime.listener;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PictureAnime.java

import java.awt.*;

import dragon2.UnitWorks;
import mine.ImageLoader;

public class PictureAnime implements AnimeListener {

	public PictureAnime(UnitWorks unitworks, String s) {
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
