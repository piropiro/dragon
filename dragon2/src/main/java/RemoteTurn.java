// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteTurn.java

import java.awt.Point;
import mine.UnitMap;

class RemoteTurn extends ActionBase {

	RemoteTurn() {
		action();
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

	public void actionMain() {
		PaintBase.uw.closeAPanel();
		PaintBase.uw.sleep(1000L);
		do {
			Action action1 = SlgClient.getAction();
			if (action1 == null)
				break;
			ba = PaintBase.uw.search(action1.x, action1.y);
			if (ba == null)
				break;
			action(action1);
			if (PaintBase.uw.endJudge(ba))
				return;
		} while (true);
		PaintBase.uw.mensTurnStart();
	}

	public void action(Action action1) {
		Rewalk.set(ba);
		walk(action1.wx, action1.wy);
		attack(action1.atk, action1.tx, action1.ty);
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.clear(4, 0, 0);
		PaintBase.map.repaint();
		PaintBase.uw.sleep(200L);
	}

	public void attack(int i, int j, int k) {
		if (i < 0)
			return;
		if (ba.atk[i] == 0) {
			return;
		} else {
			FightManager fightmanager = new FightManager(ba);
			fightmanager.remote(i, j, k);
			return;
		}
	}

	public void walk(int i, int j) {
		if (ba.x == i && ba.y == j) {
			return;
		} else {
			Walk walk1 = new Walk(ba);
			PaintBase.V.S(1, 0, ba.x, ba.y, 4);
			PaintBase.V.S(4, 0, ba.x, ba.y, 4);
			PaintBase.map.repaint();
			PaintBase.uw.sleep(300L);
			PaintBase.V.S(1, 0, ba.x, ba.y, 1);
			PaintBase.V.S(4, 0, ba.x, ba.y, 0);
			walk1.enemy(i, j);
			return;
		}
	}

	Body ba;
}
