// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Arrow.java

import java.awt.Graphics;
import java.awt.Point;
import mine.Mine;

class Arrow {

	Arrow(Point point, Point point1, int i, int ai[], boolean flag) {
		ba = point;
		bb = point1;
		max = i;
		imgs = ai;
		mflag = flag;
		theta = Math.atan2(point1.y - point.y, point1.x - point.x);
		n = 0;
	}

	public void move() {
		n = Math.min(n + 1, max);
		x = ba.x + ((bb.x - ba.x) * n) / max;
		y = ba.y + ((bb.y - ba.y) * n) / max;
	}

	public boolean end() {
		return n == max;
	}

	public void paint(Graphics g) {
		int i;
		if (end())
			i = imgs.length - 1;
		else
			i = n % Math.max(1, imgs.length - 1);
		int j = mflag ? x + 1 : 1;
		int k = mflag ? y + 1 : 1;
		int l = (imgs[i] % 15) * 32 + 1;
		int i1 = (imgs[i] / 15) * 32 + 1;
		Mine.drawRotateImage(Statics.anime, j, k, l, i1, 30, 30, theta, g);
	}

	Point ba;
	Point bb;
	int max;
	int x;
	int y;
	int n;
	int imgs[];
	boolean mflag;
	double theta;
}
