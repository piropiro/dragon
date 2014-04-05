// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Kakusei.java

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;
import mine.DataStream;
import mine.UnitMap;
import mine.io.BeanIO;

class Kakusei extends ActionBase {

	Kakusei(Vector vector) {
		Charas = vector;
		sister = getSister(vector);
		kakusei = getKakuseiData();
		if (!isChangeable()) {
			return;
		} else {
			setStatus();
			vector.remove(sister);
			vector.add(kakusei);
			action();
			return;
		}
	}

	private Body getSister(Vector vector) {
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isType(50))
				return body;
		}

		return null;
	}

	private Body getKakuseiData() {
            Vector vector = (Vector) BeanIO.read("data/body/E99.xml");
		return (Body) vector.firstElement();
	}

	private boolean isChangeable() {
		if (sister == null)
			return false;
		if (!sister.isAlive())
			return false;
		return kakusei != null;
	}

	private void setStatus() {
		int i = sister.defMax + sister.mdfMax + sister.misMax;
		kakusei.strMax = sister.strMax * 2 + i;
		kakusei.mstMax = sister.mstMax + i;
		kakusei.hitMax = sister.hitMax + i;
		kakusei.ido = sister.ido * 2;
		kakusei.setMax();
		kakusei.newType(100);
		kakusei.x = sister.x;
		kakusei.y = sister.y;
	}

	public void actionMain() {
		PaintBase.V.S(3, 0, kakusei.x, kakusei.y, 0);
		PaintBase.V.S(5, 0, kakusei.x, kakusei.y, 0);
		Point point = new Point(kakusei.x, kakusei.y);
		PaintBase.uw.setAnime(1, -11, point, point);
		PaintBase.uw.setAnime(1, -9, point, point);
		PaintBase.V.S(2, 0, kakusei.x, kakusei.y, kakusei.img);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	public void leftPressed() {
	}

	public void rightPressed() {
	}

	private Body sister;
	private Body kakusei;
}
