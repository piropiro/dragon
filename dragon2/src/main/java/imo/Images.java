// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Images.java

package imo;

import java.awt.Image;
import mine.ImageLoader;

class Images {

	Images() {
	}

	static void setup() {
		chara = ImageLoader.getImage("image/imos.gif", true);
	}

	static void setEndImage(int i) {
		endi = ImageLoader.getImage("image/end" + i + ".gif", true);
	}

	static Image chara;
	static Image endi;
}
