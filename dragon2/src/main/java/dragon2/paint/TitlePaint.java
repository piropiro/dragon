package dragon2.paint;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TitlePaint.java

import java.awt.Point;

import dragon2.ActionBase;
import dragon2.Body;

public class TitlePaint extends ActionBase {

	public TitlePaint() {
	}

	public void actionMain() {
		PaintBase.uw.setAnime(-12, 0, null, null);
		PaintBase.uw.startup();
		PaintBase.uw.setAnime(-12, 1, null, null);
	}

	public void leftPressed() {
		PaintBase.uw.nameChange();
		action();
	}

	public void rightPressed() {
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}
}
