// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BersekPaint.java

import java.awt.Point;
import mine.UnitMap;

class BersekPaint extends ActionBase {

	BersekPaint(Body body) {
		ba = body;
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(1, 0, body.x, body.y, 4);
		PaintBase.V.S(3, 0, body.x, body.y, 6);
		PaintBase.V.S(4, 0, body.x, body.y, 0);
	}

	public void actionMain() {
		PaintBase.V.S(3, 0, ba.x, ba.y, 0);
		Point point = new Point(ba.x, ba.y);
		PaintBase.uw.setAnime(1, -1, point, point);
		PaintBase.uw.setAnime(-7, 12, point, point);
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		PaintBase.map.setBasicPaint();
	}

	private void setStatus() {
		ba.setTypeState(33, true);
		ba.hp = ba.hpMax;
		ba.str *= 1.5D;
		ba.def *= 1.5D;
		ba.mst *= 1.5D;
		ba.mdf *= 1.5D;
		ba.hit *= 1.5D;
		ba.mis *= 1.5D;
	}

	public void mouseMoved(Point point) {
		rightPressed();
	}

	public void leftPressed() {
		setStatus();
		PaintBase.uw.bersekChara(ba);
		action();
	}

	public void rightPressed() {
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		PaintBase.V.S(3, 0, ba.x, ba.y, 1);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private Body ba;
}
