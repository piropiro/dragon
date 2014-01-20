// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChangePaint.java

import java.awt.Point;
import mine.UnitMap;

class ChangePaint extends ActionBase {

	ChangePaint(Body body, Body body1) {
		ba = body;
		bb = body1;
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(1, 0, body.x, body.y, 4);
		PaintBase.V.S(3, 0, body.x, body.y, 5);
		PaintBase.V.S(4, 0, body.x, body.y, 0);
	}

	public void actionMain() {
		Point point = new Point(ba.x, ba.y);
		PaintBase.uw.setAnime(-10, 0, point, point);
		PaintBase.uw.setAnime(-8, bb.img, point, point);
		PaintBase.V.S(1, 0, bb.x, bb.y, 0);
		PaintBase.V.S(2, 0, bb.x, bb.y, bb.img);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private void setStatus() {
		bb.x = ba.x;
		bb.y = ba.y;
	}

	public void mouseMoved(Point point) {
		rightPressed();
	}

	public void leftPressed() {
		setStatus();
		PaintBase.uw.changeChara(ba, bb);
		action();
	}

	public void rightPressed() {
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		PaintBase.V.S(3, 0, ba.x, ba.y, 1);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private Body ba;
	private Body bb;
}
