package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WazalistPaint.java

import java.awt.Point;

public class WazalistPaint extends PaintBase {

	public WazalistPaint(Collection collection) {
		col = collection;
		collection.deployWazas(PaintBase.V);
		setHelp();
	}

	private void setHelp() {
		Texts.help[18][1] = Texts.waza + col.countWaza() + " / "
				+ col.wazaMax();
		PaintBase.uw.setHelp(Texts.help[18], 1);
	}

	public void leftPressed() {
	}

	public void mouseMoved(Point point) {
		PaintBase.map.wakuMove(point);
		PaintBase.map.wakuPaint(true);
		if (PaintBase.V.G(2, 0, point.x, point.y) != 0)
			setSelectBody(col.searchWaza(point.x, point.y));
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) == 0)
			PaintBase.map.setButtonPaint(point, this, 7);
	}

	public boolean isNextPoint(Point point) {
		return PaintBase.V.G(2, 0, point.x, point.y) != 0;
	}

	private Collection col;
}
