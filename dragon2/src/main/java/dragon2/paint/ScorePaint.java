package dragon2.paint;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScorePaint.java

import java.awt.Point;

import dragon2.Body;
import dragon2.Texts;

public class ScorePaint extends PaintBase {

	public ScorePaint() {
		PaintBase.V.clear(4, 0, 0);
		PaintBase.map.repaint();
		setHelp();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[15], 1);
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
		PaintBase.uw.backToCamp();
	}

	public void rightPressed() {
		PaintBase.uw.backToCamp();
	}
}
