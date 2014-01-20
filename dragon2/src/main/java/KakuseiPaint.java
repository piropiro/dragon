// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KakuseiPaint.java

import java.awt.Point;
import java.util.Vector;
import mine.DataStream;
import mine.UnitMap;

class KakuseiPaint extends ActionBase {

	KakuseiPaint(Body body) {
		sister = body;
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(1, 0, body.x, body.y, 4);
		PaintBase.V.S(3, 0, body.x, body.y, 2);
		PaintBase.V.S(4, 0, body.x, body.y, 0);
	}

	public void actionMain() {
		PaintBase.V.S(1, 0, sister.x, sister.y, 0);
		PaintBase.V.S(3, 0, kakusei.x, kakusei.y, 0);
		PaintBase.V.S(5, 0, kakusei.x, kakusei.y, 0);
		Point point = new Point(kakusei.x, kakusei.y);
		PaintBase.uw.setAnime(1, -11, point, point);
		PaintBase.uw.setAnime(1, -9, point, point);
		PaintBase.V.S(2, 0, kakusei.x, kakusei.y, kakusei.img);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private Body getKakuseiData() {
		Vector vector = (Vector) DataStream.read("data/E99.txt");
		return (Body) vector.firstElement();
	}

	private void setStatus() {
		int i = sister.defMax + sister.mdfMax + sister.misMax;
		kakusei.strMax = sister.strMax * 2 + i;
		kakusei.mstMax = sister.mstMax + i;
		kakusei.hitMax = sister.hitMax + i;
		kakusei.setMax();
		kakusei.newType(100);
		kakusei.x = sister.x;
		kakusei.y = sister.y;
		kakusei.gx = sister.gx;
		kakusei.gy = sister.gy;
	}

	public void mouseMoved(Point point) {
		rightPressed();
	}

	public void leftPressed() {
		kakusei = getKakuseiData();
		setStatus();
		PaintBase.uw.changeChara(sister, kakusei);
		action();
	}

	public void rightPressed() {
		PaintBase.V.S(1, 0, sister.x, sister.y, 0);
		PaintBase.V.S(3, 0, sister.x, sister.y, 1);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private Body sister;
	private Body kakusei;
}
