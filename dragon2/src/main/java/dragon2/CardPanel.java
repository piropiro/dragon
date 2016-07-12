package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CardPanel.java

import java.awt.Graphics;
import java.awt.Point;
import mine.JCanvas;
import mine.UnitMap;

@SuppressWarnings("serial")
public class CardPanel extends JCanvas {

	public CardPanel(UnitWorks unitworks, UnitMap unitmap) {
		super(352, 416);
		setLocation(128, 32);
		uw = unitworks;
		V = unitmap;
		Vinit();
	}

	public void setup(Body body, Body body1) {
		ba = body;
		bb = body1;
		cards = new Card(0, 0, 0, 0);
		bluec = newCards(body, 0);
		redc = newCards(body1, 1);
//		for (int i = 0; i < redc.length; i++)
//			redc[i].n += (4 * body1.hp) / body1.hpMax - 2;

//		for (int j = 0; j < bluec.length; j++)
//			bluec[j].n += (4 * body.hp) / body.hpMax - 2;

		for (int k = 0; k < bluec.length; k++) {
			bluec[k].n = Math.max(0, Math.min(bluec[k].n, 99));
			redc[k].n = Math.max(0, Math.min(redc[k].n, 99));
		}

		endFlag = false;
		openNum = 0;
		usedNum = 0;
		enemyNum = 0;
		rwin = 0;
		bwin = 0;
	}

	private Card[] newCards(Body body, int i) {
		byte byte0 = ((byte) (i != 0 ? 4 : 8));
		Card acard[] = new Card[7];
		acard[0] = new Card(body.str / 3, 64, byte0 * 32, i);
		acard[1] = new Card(body.def / 3, 96, byte0 * 32, i);
		acard[2] = new Card(body.mst / 3, 128, byte0 * 32, i);
		acard[3] = new Card(body.mdf / 3, 160, byte0 * 32, i);
		acard[4] = new Card(body.hit / 3, 192, byte0 * 32, i);
		acard[5] = new Card(body.mis / 3, 224, byte0 * 32, i);
		acard[6] = new Card(body.hp / 3, 256, byte0 * 32, i);
		for (int j = 0; j < 6; j++) {
			int k = Luck.rnd(5);
			int l = acard[j].n;
			acard[j].n = acard[k].n;
			acard[k].n = l;
		}

		return acard;
	}

	private void Vinit() {
		byte byte0 = 11;
		byte byte1 = 13;
		byte byte2 = 32;
		byte byte3 = 32;
		CV = new UnitMap();
		CV.C(3, 0, 0, byte0, byte1, byte2, byte3);
		CV.P(0, 0, 0, 15, 1, 9, Statics.card);
		CV.P(1, 0, 0, 15, 15, 0, Statics.chara);
		CV.P(2, 0, 64, 5, 1, 0, Statics.waku);
		CV.V(0, true);
		CV.V(1, true);
		CV.V(2, true);
	}

	public void ppaint(int i, int j) {
		repaint(i * 32, j * 32, 32, 32);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		CV.E(0, 0, 11, 13, false, g);
		for (int i = 0; i < 7; i++)
			bluec[i].paint(g);

		for (int j = 6; j >= 0; j--)
			redc[j].paint(g);

		cards.paint(g);
	}

	public void display() {
		CV.clear(0, 0, 9);
		CV.clear(2, 0, 0);
		CV.clear(1, 0, 0);
		setVisible(true);
		int i = 0;
		int j = 0;
		for (int k = 0; k < 45; k++) {
			int l = 0;
			int j1 = 0;
			if (k < 10) {
				l = k;
				j1 = 0;
			} else if (k < 22) {
				l = 10;
				j1 = k - 10;
			} else if (k < 32) {
				l = 32 - k;
				j1 = 12;
			} else if (k < 45) {
				l = 0;
				j1 = 44 - k;
			}
			anime(i, j, l, j1, 0, 8);
			CV.S(0, 0, l, j1, 1);
			i = l;
			j = j1;
		}

		for (int i1 = 1; i1 < 12; i1++) {
			CV.fillRect(0, 0, 1, i1, 9, 1, 0);
			repaint();
			uw.sleep(100L);
		}

		CV.S(1, 0, 5, 10, ba.img);
		ppaint(5, 10);
		uw.sleep(100L);
		CV.S(1, 0, 5, 2, bb.img);
		ppaint(5, 2);
		uw.sleep(100L);
		redc[0].x = redc[6].x;
		redc[0].close();
		for (int k1 = 0; k1 < 6; k1++) {
			redc[6 - k1].close();
			for (int l1 = 0; l1 < 8; l1++) {
				redc[0].x -= 4;
				repaint(redc[0].x, redc[0].y, 36, 32);
				uw.sleep(10L);
			}

		}

		bluec[6].x = bluec[0].x;
		bluec[6].close();
		for (int i2 = 0; i2 < 6; i2++) {
			bluec[i2].close();
			for (int j2 = 0; j2 < 8; j2++) {
				bluec[6].x += 4;
				repaint(bluec[6].x - 4, bluec[6].y, 36, 32);
				uw.sleep(10L);
			}

		}

		for (int k2 = 0; k2 < 3; k2++) {
			enemyOpen();
			repaint();
			uw.sleep(200L);
		}

	}

	public void dispose() {
		for (int i = 0; i < 7; i++) {
			bluec[i].dispose();
			redc[i].dispose();
		}

		for (int j = 0; j < 13; j++) {
			CV.fillRect(0, 0, 0, j, 11, 1, 9);
			CV.fillRect(0, 0, 0, j + 1, 11, 1, 1);
			CV.fillRect(1, 0, 0, j + 1, 11, 1, 0);
			CV.fillRect(2, 0, 0, j + 1, 11, 1, 0);
			repaint();
			uw.sleep(100L);
		}

		setVisible(false);
	}

	private void anime(int i, int j, int k, int l, int i1, int j1) {
		Point point = new Point(i * 32, j * 32);
		Point point1 = new Point(k * 32, l * 32);
		cards.color = i1;
		cards.setLocation(point.x, point.y);
		cards.close();
		for (int k1 = 1; k1 <= 8; k1++) {
			move(cards, point.x, point.y, point1.x, point1.y, k1, 8);
			repaint(cards.x - 16, cards.y - 16, 64, 64);
			uw.sleep(j1);
		}

		cards.dispose();
	}

	public void open(int i) {
		switch (bluec[i].getStatus()) {
		case 0: // '\0'
			return;

		case 1: // '\001'
			if (openNum >= 3) {
				return;
			} else {
				openNum++;
				bluec[i].open();
				Point point = new Point(6 + i, 9);
				CV.clear(2, 0, 0);
				uw.setAnime(1, -5, point, point);
				repaint();
				judgeDouble();
				return;
			}

		case 2: // '\002'
			openNum--;
			battle(i);
			endFlag = endJudge();
			if (!endFlag && enemyNum <= 4) {
				enemyOpen();
				repaint();
			}
			judgeDouble();
			return;
		}
	}

	private void judgeDouble() {
		dleft = null;
		dright = null;
		for (int i = 0; i < 6; i++)
			if (bluec[i].getStatus() == 2) {
				dleft = bluec[i];
				for (int j = i + 1; j < 7; j++)
					if (bluec[j].getStatus() == 2 && bluec[j].n == dleft.n) {
						dright = bluec[j];
						if (!doubleFlag) {
							doubleFlag = true;
							CV.S(0, 0, 3, 10, 6);
							Point point = new Point(7, 11);
							uw.setAnime(1, -5, point, point);
							repaint();
						}
						return;
					}

			}

		doubleFlag = false;
		CV.S(0, 0, 3, 10, 0);
		repaint();
	}

	public void doubleCard() {
		if (!doubleFlag) {
			return;
		} else {
			doubleAnime(dleft, dright);
			openNum--;
			usedNum++;
			judgeDouble();
			return;
		}
	}

	private void doubleAnime(Card card, Card card1) {
		Point point = new Point(card.x, card.y);
		Point point1 = new Point(card1.x, card1.y);
		//Point point2 = new Point(card.x / 32 + 4, card.y / 32 + 1);
		//Point point3 = new Point(card1.x / 32 + 4, card1.y / 32 + 1);
		Point point4 = new Point(7, 11);
		for (int i = 0; i <= 20; i++) {
			move(card, point.x, point.y, 96, 320, i, 20);
			move(card1, point1.x, point1.y, 96, 320, i, 20);
			repaint();
			uw.sleep(15L);
		}

		uw.sleep(100L);
		card.n = Math.min(card.n * 2, 99);
		uw.setAnime(1, -5, point4, point4);
		card1.dispose();
		CV.S(0, 0, 3, 10, 0);
		repaint();
		for (int j = 0; j <= 20; j++) {
			move(card, card.x, card.y, point.x, point.y, j, 20);
			repaint();
			uw.sleep(15L);
		}

		repaint();
	}

	private boolean endJudge() {
		if (rwin == 3) {
			lose();
			return true;
		}
		if (bwin == 3) {
			win();
			return true;
		}
		if (usedNum == 7) {
			rwin += 7 - enemyNum;
			if (bwin > rwin) {
				win();
				return true;
			} else {
				lose();
				return true;
			}
		} else {
			return false;
		}
	}

	private void enemyOpen() {
		int i;
		do
			i = Luck.rnd(6);
		while (redc[i].getStatus() != 1);
		redc[i].open();
		Point point = new Point(6 + i, 5);
		uw.setAnime(1, -3, point, point);
		repaint();
	}

	private int getMaxN(Card acard[]) {
		int i = -1;
		int j = 0;
		for (int k = 0; k < 7; k++)
			if (acard[k].getStatus() == 2 && acard[k].n > i) {
				i = acard[k].n;
				j = k;
			}

		return j;
	}

	private int getMinN(Card acard[], int i) {
		int j = 100;
		int k = -1;
		for (int l = 0; l < 7; l++)
			if (acard[l].getStatus() == 2 && acard[l].n > i && acard[l].n < j) {
				j = acard[l].n;
				k = l;
			}

		return k;
	}

	private int getRndN() {
		int i;
		do
			i = Luck.rnd(6);
		while (redc[i].getStatus() != 2);
		return i;
	}

	private int enemySelect() {
		if (rwin == 2 || bwin == 2) {
			int i = getMaxN(bluec);
			int j = getMinN(redc, bluec[i].n);
			if (j != -1 && bluec[i].n < redc[j].n)
				return j;
		}
		return getRndN();
	}

	private void battle(int i) {
		usedNum++;
		enemyNum++;
		int j = enemySelect();
		Card card = bluec[i];
		Card card1 = redc[j];
		battleAnime(card, card1);
		if (card.n > card1.n) {
			result(6, 6, 6 + bwin, 10, card, card1);
			bwin++;
		} else if (card.n < card1.n) {
			result(4, 6, 4 - rwin, 2, card1, card);
			rwin++;
		} else {
			draw(card, card1);
		}
	}

	private void battleAnime(Card card, Card card1) {
		Point point = new Point(card.x, card.y);
		Point point1 = new Point(card1.x, card1.y);
		for (int i = 0; i <= 20; i++) {
			move(card, point.x, point.y, 96, 192, i, 20);
			move(card1, point1.x, point1.y, 224, 192, i, 20);
			repaint();
			uw.sleep(15L);
		}

		uw.sleep(500L);
		point = new Point(card.x, card.y);
		point1 = new Point(card1.x, card1.y);
		for (int j = 0; j <= 20; j++) {
			move(card, point.x, point.y, 192, 192, j, 20);
			move(card1, point1.x, point1.y, 128, 192, j, 20);
			repaint();
			uw.sleep(15L);
		}

	}

	private void draw(Card card, Card card1) {
		card.lose();
		card1.lose();
		for (int i = 0; i <= 20; i++) {
			repaint();
			uw.sleep(50L);
		}

		card.dispose();
		card1.dispose();
	}

	private void result(int i, int j, int k, int l, Card card, Card card1) {
		card1.lose();
		for (int i1 = 0; i1 <= 20; i1++) {
			repaint();
			uw.sleep(50L);
		}

		card1.dispose();
		Point point = new Point(i + 4, j + 1);
		uw.setAnime(-5, 4, point, point);
		uw.sleep(500L);
		card.win();
		if (card.color == 0)
			uw.setAnime(1, -4, point, point);
		else
			uw.setAnime(1, -2, point, point);
		for (int j1 = 0; j1 <= 20; j1++) {
			move(card, i * 32, j * 32, k * 32, l * 32, j1, 20);
			repaint();
			uw.sleep(15L);
		}

		uw.sleep(200L);
	}

	private void move(Card card, int i, int j, int k, int l, int i1, int j1) {
		int k1 = i + ((k - i) * i1) / j1;
		int l1 = j + ((l - j) * i1) / j1;
		card.setLocation(k1, l1);
	}

	public void win() {
		uw.closeAPanel();
		uw.setMPanel(Texts.card_success);
		uw.setMPanel(bb.name + Texts.ga);
		uw.setMPanel(Texts.card_success2);
		uw.startMPanel(bb);
		V.S(2, 0, bb.x, bb.y, 0);
		V.S(5, 0, bb.x, bb.y, 0);
		bb.hp = 0;
		uw.addMember(bb);
		uw.setEnd(ba, false);
		dispose();
	}

	public void lose() {
		bb.hp = bb.hpMax;
		uw.closeAPanel();
		uw.setMPanel(Texts.card_fail);
		uw.setMPanel(bb.name + Texts.no);
		uw.setMPanel(Texts.card_fail2);
		uw.startMPanel(bb);
		uw.setEnd(ba, false);
		dispose();
	}

	public boolean isEnd() {
		return endFlag;
	}

	public boolean isAlive(int i) {
		switch (bluec[i].getStatus()) {
		case 1: // '\001'
		case 2: // '\002'
			return true;
		}
		return false;
	}

	UnitMap V;
	UnitMap CV;
	UnitWorks uw;
	Body ba;
	Body bb;
	static final int dx = 11;
	static final int dy = 13;
	static final int openMax = 3;
	Card bluec[];
	Card redc[];
	Card cards;
	Card dleft;
	Card dright;
	int bwin;
	int rwin;
	int usedNum;
	int enemyNum;
	int openNum;
	boolean endFlag;
	boolean doubleFlag;
}
