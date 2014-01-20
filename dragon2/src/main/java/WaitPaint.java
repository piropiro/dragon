// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WaitPaint.java

import java.awt.Point;
import mine.UnitMap;

class WaitPaint extends PaintBase {

	WaitPaint() {
		PaintBase.V.clear(4, 0, 0);
		PaintBase.map.repaint();
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
	}

	public void rightPressed() {
		PaintBase.uw.waitFast();
	}

	public void rightReleased() {
		PaintBase.uw.waitSlow();
	}
}
