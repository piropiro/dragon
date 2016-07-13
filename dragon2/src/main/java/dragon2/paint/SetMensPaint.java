package dragon2.paint;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SetMensPaint.java

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.Texts;
import mine.UnitMap;

public class SetMensPaint extends PaintBase {

	public SetMensPaint(Vector vector, Vector vector1) {
		Players = vector;
		Charas = vector1;
		n = 0;
		setColor();
		autoPut();
		setHelp();
		PaintBase.map.repaint();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[16], 1);
	}

	private void setColor() {
		if (Colors.getPC() == 1) {
			BC = 15;
			PaintBase.V.change(0, 0, 16, 0, 0, 0);
		} else {
			BC = 16;
			PaintBase.V.change(0, 0, 15, 0, 0, 0);
		}
	}

	private void autoPut() {
		Dimension dimension = PaintBase.V.getSize();
		max = 0;
		for (int i = 0; i < dimension.height; i++) {
			for (int j = 0; j < dimension.width; j++) {
				int k = PaintBase.V.G(0, 0, j, i);
				if (k == BC) {
					if (!putChara(j, i))
						return;
					max++;
					if (max == 5)
						return;
				}
			}

		}

	}

	private boolean putChara(int i, int j) {
		if (Players.size() == 0) {
			return false;
		} else {
			Body body = (Body) Players.lastElement();
			Charas.add(body);
			Players.remove(body);
			body.x = i;
			body.y = j;
			PaintBase.V.S(2, 0, i, j, body.img);
			ps = null;
			n++;
			return true;
		}
	}

	private boolean pickChara(int i, int j) {
		if (n == 0)
			return false;
		Body body = PaintBase.uw.search(i, j);
		if (body == null) {
			return false;
		} else {
			Charas.remove(body);
			Players.add(body);
			ps = new Point(i, j);
			n--;
			return true;
		}
	}

	private void nextChara(int i, int j, boolean flag) {
		if (Players.size() <= 1)
			return;
		if (flag) {
			Body body = (Body) Players.lastElement();
			Players.remove(body);
			Players.insertElementAt(body, 0);
		} else {
			Body body1 = (Body) Players.firstElement();
			Players.remove(body1);
			Players.add(body1);
		}
		moveChara(i, j);
		PaintBase.map.ppaint(i, j);
	}

	private void changeChara(int i, int j) {
		if (Players.size() == 0) {
			return;
		} else {
			pickChara(i, j);
			nextChara(i, j, true);
			putChara(i, j);
			PaintBase.map.ppaint(i, j);
			PaintBase.uw.setSPanel(PaintBase.uw.search(i, j));
			return;
		}
	}

	private synchronized void moveChara(int i, int j) {
		if (Players.size() == 0)
			return;
		if (n == max)
			return;
		int k = PaintBase.V.G(0, 0, i, j);
		if (ps != null)
			PaintBase.V.S(2, 0, ps.x, ps.y, 0);
		if (k == 21)
			return;
		if (k == 17)
			return;
		if (k == 18)
			return;
		if (k == 25)
			return;
		if (PaintBase.V.G(2, 0, i, j) == 0) {
			Body body = (Body) Players.lastElement();
			PaintBase.V.S(2, 0, i, j, body.img);
			ps = new Point(i, j);
		} else {
			ps = null;
		}
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) == 0) {
			PaintBase.uw.displayData(point);
			return;
		}
		if (PaintBase.V.G(0, 0, point.x, point.y) == BC) {
			if (PaintBase.uw.search(point.x, point.y) == null)
				putChara(point.x, point.y);
			else
				pickChara(point.x, point.y);
		} else {
			nextChara(point.x, point.y, true);
		}
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) == 0) {
			PaintBase.map.setButtonPaint(point, this, 6);
			return;
		}
		Body body = PaintBase.uw.search(point.x, point.y);
		if (PaintBase.V.G(0, 0, point.x, point.y) == BC && body != null) {
			changeChara(point.x, point.y);
			return;
		}
		if (body != null)
			PaintBase.uw.setAnalyze(body);
		else
			nextChara(point.x, point.y, false);
	}

	public void mouseMoved(Point point) {
		PaintBase.map.wakuMove(point);
		moveChara(point.x, point.y);
		PaintBase.map.wakuPaint(true);
	}

	public boolean isNextPoint(Point point) {
		Body body = PaintBase.uw.search(point.x, point.y);
		if (body != null)
			return Colors.isPlayer(body);
		else
			return false;
	}

	volatile Vector Charas;
	volatile Vector Players;
	Point ps;
	static final int MAX = 5;
	int max;
	int n;
	int BC;
}
