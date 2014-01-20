// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CollectionPaint.java

import java.awt.Point;
import mine.UnitMap;

class CollectionPaint extends PaintBase {

	CollectionPaint(Collection collection) {
		col = collection;
		collection.deployItems(PaintBase.V);
		collection.deployCharas(PaintBase.V);
		setHelp();
	}

	private void setHelp() {
		String as[] = new String[2];
		Texts.help[10][1] = Texts.item + col.countItem() + " / "
				+ col.itemMax() + Texts.material + col.countChara() + " / "
				+ col.charaMax();
		PaintBase.uw.setHelp(Texts.help[10], 1);
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) != 0)
			if (point.y < 8)
				PaintBase.uw.setAnalyze(col.searchChara(point.x, point.y));
			else
				PaintBase.uw.setAnalyze(col.searchItem(point.x, point.y));
	}

	public void mouseMoved(Point point) {
		PaintBase.map.wakuMove(point);
		PaintBase.map.wakuPaint(true);
		if (PaintBase.V.G(2, 0, point.x, point.y) != 0)
			if (point.y < 8)
				setSelectBody(col.searchChara(point.x, point.y));
			else
				setSelectBody(col.searchItem(point.x, point.y));
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) == 0)
			PaintBase.map.setButtonPaint(point, this, 7);
		else
			leftPressed();
	}

	public boolean isNextPoint(Point point) {
		return PaintBase.V.G(2, 0, point.x, point.y) != 0;
	}

	private Collection col;
}
