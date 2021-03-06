// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SPanel.java

import java.awt.Graphics;
import java.awt.Point;
import java.util.StringTokenizer;

class SPanel extends StatusBase {

	SPanel(UnitWorks unitworks, SaveManager savemanager, boolean flag) {
		super(160, 128, flag);
		uw = unitworks;
		sm = savemanager;
	}

	public void displayScore(Point point, int i) {
		bgcolor = 1;
		setLocate(point, point, 1);
		type = i;
		pa = point;
		repaint();
		setVisible(true);
	}

	public void displayC(Point point, int i, int j) {
		setLocate(point, point, 1);
		type = 16;
		pa = point;
		tikei = i;
		bgcolor = j;
		repaint();
		setVisible(true);
	}

	public void displayP(Point point, int i) {
		setLocate(point, point, 1);
		type = 15;
		pa = point;
		tikei = i;
		bgcolor = 2;
		repaint();
		setVisible(true);
	}

	public void displayD(Point point, int i, int j) {
		setLocate(point, point, 1);
		type = j;
		pa = point;
		tikei = i;
		bgcolor = 2;
		repaint();
		setVisible(true);
	}

	public void displayS(Body body, int i) {
		if (body == null) {
			setVisible(false);
			return;
		}
		if (body.isType(52))
			i = 12;
		else if (i == 3)
			switch (type) {
			case 0: // '\0'
				i = 3;
				break;

			case 3: // '\003'
				i = 5;
				break;

			case 5: // '\005'
				i = 4;
				break;

			case 4: // '\004'
				i = 0;
				break;
			}
		type = i;
		ba = body;
		setLocate(body, 1);
		hpb.setup(false, body.hp, body.hpMax);
		bgcolor = body.color;
		repaint();
		setVisible(true);
	}

	public void displayA(Iconable iconable, Iconable iconable1) {
		atkb = iconable;
		if (iconable == null && iconable1 == null) {
			setVisible(false);
			return;
		}
		if (iconable != null) {
			ba = iconable.getBody(true);
			bb = iconable.getBody(false);
			type = 1;
		} else {
			ba = iconable1.getBody(false);
			bb = iconable1.getBody(true);
			type = 2;
		}
		setLocate(ba, bb, 2);
		if (iconable1 != null) {
			int i = (iconable1.getDamage() * iconable1.getRate()) / 100;
			hpb.setup(iconable1.isHit(), ba.hp, ba.hpMax);
			hpb.setMin(ba.hp - i, false);
		} else {
			hpb.setup(false, ba.hp, ba.hpMax);
		}
		bgcolor = ba.color;
		repaint();
		setVisible(true);
	}

	public void dispose() {
		setVisible(false);
	}

	public void paint(Graphics g) {
		g.setFont(getFont());
		clear(bgcolor, g);
		switch (type) {
		case 11: // '\013'
			drawScore2(g);
			return;

		case 10: // '\n'
			drawScore(g);
			return;

		case 6: // '\006'
			drawData(g);
			return;

		case 7: // '\007'
			drawItem(g);
			return;

		case 8: // '\b'
			drawSummon(g);
			return;

		case 9: // '\t'
			drawStartP(g);
			return;

		case 12: // '\f'
			drawWaza(g);
			return;

		case 13: // '\r'
			drawCBlue(g);
			return;

		case 14: // '\016'
			drawCRed(g);
			return;

		case 15: // '\017'
			drawPlace(g);
			return;

		case 4: // '\004'
			drawAtkList(g);
			return;

		case 5: // '\005'
			drawTypeList(g);
			return;

		case 16: // '\020'
			drawCamp(g);
			return;
		}
		if (ba == null)
			return;
		drawMain(uw, ba, g, true);
		switch (type) {
		case 3: // '\003'
			drawAnalyze(g);
			return;

		case 0: // '\0'
			drawStatus(g);
			return;

		case 1: // '\001'
			drawAttack(g);
			return;

		case 2: // '\002'
			drawCounter(g);
			return;
		}
	}

	private void drawText(String s, Graphics g) {
		StringTokenizer stringtokenizer = new StringTokenizer(s, "&");
		g.drawString(stringtokenizer.nextToken(), 50, 32);
		for (int i = 0; i <= 3; i++) {
			if (!stringtokenizer.hasMoreTokens())
				break;
			drawLine(stringtokenizer.nextToken(), 0, i, g);
		}

	}

	private void drawCamp(Graphics g) {
		drawImage(Statics.back, 0, 10, 10, g);
		switch (tikei) {
		case 0: // '\0'
			drawImage(Statics.waku, 16, 10, 10, g);
			drawText(Texts.sp[0], g);
			break;

		case 1: // '\001'
			drawImage(Statics.waku, 16, 10, 10, g);
			drawText(Texts.sp[1], g);
			break;

		case 2: // '\002'
			drawImage(Statics.waku, 16, 10, 10, g);
			drawText(Texts.sp[2], g);
			break;

		case 3: // '\003'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[3], g);
			break;

		case 4: // '\004'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[4], g);
			break;

		case 5: // '\005'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[5], g);
			break;

		case 7: // '\007'
			drawImage(Statics.waku, 18, 10, 10, g);
			drawText(Texts.sp[6], g);
			break;

		case 6: // '\006'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[7], g);
			break;

		case 8: // '\b'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[8], g);
			break;

		case 9: // '\t'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[9], g);
			break;

		case 10: // '\n'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[10], g);
			break;

		case 11: // '\013'
			drawImage(Statics.waku, 19, 10, 10, g);
			drawText(Texts.sp[11], g);
			break;
		}
	}

	private void drawScore(Graphics g) {
		drawImage(Statics.back, 17, 10, 10, g);
		g.drawString(sm.getPlayerName(), 50, 32);
		drawLine(Texts.sp[24] + sm.getStage(), 0, 0, g);
		drawLine(Texts.sp[25] + sm.getItem(), 0, 1, g);
		drawLine(Texts.sp[26] + sm.getKill(), 0, 2, g);
		long l = sm.getPlayTime();
		long l1 = l / 0x36ee80L;
		long l2 = (l % 0x36ee80L) / 60000L;
		long l3 = (l % 60000L) / 1000L;
		String s = "";
		s = s + (l1 <= 9L ? "0" + l1 : "" + l1);
		s = s + ":";
		s = s + (l2 <= 9L ? "0" + l2 : "" + l2);
		s = s + ":";
		s = s + (l3 <= 9L ? "0" + l3 : "" + l3);
		drawLine(Texts.sp[27] + s, 0, 3, g);
	}

	private void drawScore2(Graphics g) {
		drawLine(Texts.sp[28] + sm.getTurn(), 0, -1, g);
		drawLine(Texts.sp[29] + sm.getEscape(), 0, 0, g);
		drawLine(Texts.sp[30] + sm.getDead(), 0, 1, g);
		drawLine(Texts.sp[31] + Rank.getScore1(), 0, 2, g);
		drawLine(Texts.sp[32] + Rank.getScore2(), 0, 3, g);
	}

	private void drawWaza(Graphics g) {
		drawImage(Statics.back, 0, 10, 10, g);
		drawImage(Statics.chara, ba.img, 10, 10, g);
		g.drawString(ba.name.substring(0, Math.min(7, ba.name.length())), 50,
				22);
		g.drawString("No." + ba.level, 52, 41);
		AttackData attackdata = Statics.getAttackData(ba.atk[0]);
		if (attackdata.AttackType != 0)
			drawLine(Statics.atype[attackdata.AttackType], 0, 0, g);
		else
			drawLine(Texts.sp[33], 0, 0, g);
		drawLine(Statics.trtype[attackdata.TRType], 1, 0, g);
		boolean aflag[] = new boolean[40];
		for (int i = 0; i < attackdata.effect.length; i++)
			aflag[attackdata.effect[i]] = true;

		int j = 0;
		if (aflag[25])
			j -= 4;
		if (aflag[24])
			j += 4;
		if (aflag[37])
			j += 12;
		if (aflag[3])
			j = 32;
		switch (j) {
		case -4:
			drawLine(Texts.sp[34], 0, 1, g);
			break;

		case 4: // '\004'
			drawLine(Texts.sp[35], 0, 1, g);
			break;

		case 12: // '\f'
			drawLine(Texts.sp[36], 0, 1, g);
			break;

		case 16: // '\020'
			drawLine(Texts.sp[37], 0, 1, g);
			break;

		case 32: // ' '
			drawLine(Texts.sp[38], 0, 1, g);
			break;
		}
		switch (attackdata.FuelType) {
		case 1: // '\001'
			drawLine(Texts.sp[39], 1, 1, g);
			break;

		case 2: // '\002'
			drawLine(Texts.sp[40], 1, 1, g);
			break;

		case 3: // '\003'
			drawLine(Texts.sp[41], 1, 1, g);
			break;

		case 4: // '\004'
			drawLine(Texts.sp[42], 1, 1, g);
			break;

		case 5: // '\005'
			drawLine(Texts.sp[43], 1, 1, g);
			break;

		case 6: // '\006'
			drawLine(Texts.sp[44], 1, 1, g);
			break;
		}
		n = 4;
		drawWazaEffect(g, aflag, 31);
		drawWazaEffect(g, aflag, 1);
		drawWazaEffect(g, aflag, 2);
		drawWazaEffect(g, aflag, 4);
		drawWazaEffect(g, aflag, 12);
		drawWazaEffect(g, aflag, 29);
		drawWazaEffect(g, aflag, 34);
		drawWazaEffect(g, aflag, 30);
		drawWazaEffect(g, aflag, 36);
		drawWazaEffect(g, aflag, 35);
		drawWazaEffect(g, aflag, 7);
		drawWazaEffect(g, aflag, 8);
		drawWazaEffect(g, aflag, 9);
		drawWazaEffect(g, aflag, 10);
		drawWazaEffect(g, aflag, 11);
		drawWazaEffect(g, aflag, 32);
		drawWazaEffect(g, aflag, 26);
		drawWazaEffect(g, aflag, 27);
		drawWazaEffect(g, aflag, 14);
		drawWazaEffect(g, aflag, 15);
		drawWazaEffect(g, aflag, 16);
		drawWazaEffect(g, aflag, 17);
		drawWazaEffect(g, aflag, 18);
		drawWazaEffect(g, aflag, 19);
		drawWazaEffect(g, aflag, 20);
		drawWazaEffect(g, aflag, 21);
		drawWazaEffect(g, aflag, 23);
		drawWazaEffect(g, aflag, 28);
		drawWazaEffect(g, aflag, 33);
	}

	private boolean drawWazaEffect(Graphics g, boolean aflag[], int i) {
		if (n == 8)
			return false;
		if (!aflag[i]) {
			return false;
		} else {
			drawLine(Statics.effect[i], n % 2, n / 2, g);
			n++;
			return true;
		}
	}

	private void drawAnalyze(Graphics g) {
		int i = ba.ido;
		if (ba.isType(48))
			i++;
		if (ba.isType(49))
			i += 2;
		if (ba.isType(56))
			i--;
		drawLine(Statics.idoType[ba.itype], i, 0, 1, g);
		drawLine(Texts.sp[45], ba.store, 1, 1, g);
		drawLine(Texts.sp[46], ba.maai, 0, 2, g);
		drawLine(Texts.sp[47], ba.scope, 1, 2, g);
		drawLine(Texts.sp[48], ba.moveturn, 0, 3, g);
		drawLine("EXP ", ba.exp, 1, 3, g);
	}

	private void drawAtkList(Graphics g) {
		drawMain(uw, ba, g, false);
		int i = 0;
		for (int j = 0; j < ba.atk.length; j++) {
			if (ba.atk[j] == 0)
				continue;
			AttackData attackdata = Statics.getAttackData(ba.atk[j]);
			drawLine(attackdata.name, 0, i++, g);
			if (i == 4)
				break;
		}

	}

	private void drawTypeList(Graphics g) {
		drawMain(uw, ba, g, false);
		n = 0;
		drawType(g, 44);
		drawType(g, 42);
		drawType(g, 5);
		drawType(g, 34);
		drawType(g, 40);
		drawType(g, 41);
		drawType(g, 43);
		drawType(g, 45);
		drawType(g, 6, 7, 8);
		drawType(g, 9, 10, 11);
		drawType(g, 12, 13, 14);
		if (drawType(g, 17))
			return;
		if (!drawType(g, 20)) {
			drawType(g, 18);
			drawType(g, 19);
		}
		drawType(g, 22);
		drawType(g, 24);
		drawType(g, 28);
		drawType(g, 23);
		drawType(g, 29);
		drawType(g, 46);
		drawType(g, 47);
		drawType(g, 57);
		int i = 0;
		if (ba.isType(48))
			i++;
		if (ba.isType(49))
			i += 2;
		if (ba.isType(56))
			i--;
		switch (i) {
		case -1:
			drawType(g, 56);
			break;

		case 1: // '\001'
			drawType(g, 48);
			break;

		case 2: // '\002'
			drawType(g, 49);
			break;
		}
		drawType(g, 35);
	}

	private boolean drawType(Graphics g, int i, int j, int k) {
		if (drawType(g, k))
			return true;
		if (ba.isType(i) && ba.isType(j)) {
			return false;
		} else {
			drawType(g, i);
			drawType(g, j);
			return true;
		}
	}

	private boolean drawType(Graphics g, int i) {
		if (n == 8)
			return false;
		if (!ba.isType(i)) {
			return false;
		} else {
			drawLine(Statics.tokusei[i], n / 4, n % 4, g);
			n++;
			return true;
		}
	}

	private void drawStatus(Graphics g) {
		drawLine(Texts.sp[49], ba.str, 0, 1, g);
		drawLine(Texts.sp[50], ba.def, 1, 1, g);
		drawLine(Texts.sp[51], ba.mst, 0, 2, g);
		drawLine(Texts.sp[52], ba.mdf, 1, 2, g);
		drawLine(Texts.sp[53], ba.hit, 0, 3, g);
		drawLine(Texts.sp[54], ba.mis, 1, 3, g);
	}

	private void drawAttack(Graphics g) {
		if (atkb == null)
			return;
		drawLine(atkb.getName(), 0, 1, g);
		if (atkb.isEffect(22)) {
			drawEffect(g);
		} else {
			drawLine(Texts.sp[55], Math.abs(atkb.getDamage()), 0, 2, g);
			double d = atkb.getRate();
			drawLine(Texts.sp[56] + d / 100D, 1, 2, g);
		}
		int i = atkb.getMeichu();
		int j = atkb.getStore();
		drawLine(Texts.sp[57] + i, 0, 3, g);
		drawLine(Texts.sp[58] + j, 1, 3, g);
	}

	private void drawEffect(Graphics g) {
		String s = "NO EFFECT";
		if (atkb.isPossible(23))
			s = "REFRESH";
		if (atkb.isPossible(33))
			s = "OIL";
		if (atkb.isPossible(20))
			s = "ATTACK";
		if (atkb.isPossible(21))
			s = "GUARD";
		if (atkb.isPossible(26))
			s = "UP";
		if (atkb.isPossible(27))
			s = "DOWN";
		if (atkb.isPossible(18))
			s = "CLOSE";
		if (atkb.isPossible(17))
			s = "POISON";
		if (atkb.isPossible(16))
			s = "SLEEP";
		if (atkb.isPossible(19))
			s = "CHARM";
		if (atkb.isPossible(14))
			s = "FINISH";
		if (atkb.isPossible(15))
			s = "DEATH";
		if ((s.equals("SLEEP") || s.equals("CHARM")) && bb.isType(35))
			s = "LOCK";
		drawLine(Texts.sp[59] + s, 0, 2, g);
	}

	private void drawCounter(Graphics g) {
		if (ba.isType(21))
			drawLine("  SLEEPING...", 0, 2, g);
	}

	private void drawData(Graphics g) {
		drawImage(Statics.back, 17, 10, 10, g);
		g.drawString(Texts.sp[60], 50, 32);
		drawLine(
				Texts.sp[61] + uw.getTurn() + " / " + uw.getTreasureLimit(null),
				0, 0, g);
		drawLine(Texts.sp[62] + uw.getMaterialCount(), 0, 1, g);
		drawLine(Texts.sp[63] + uw.getTreasureCount(), 0, 2, g);
		switch (uw.getTurn() % 3) {
		case 0: // '\0'
			drawLine(Texts.sp[64], 0, 3, g);
			break;

		case 1: // '\001'
			drawLine(Texts.sp[65], 0, 3, g);
			break;

		case 2: // '\002'
			drawLine(Texts.sp[66], 0, 3, g);
			break;
		}
	}

	private void drawCBlue(Graphics g) {
		drawImage(Statics.back, 17, 10, 10, g);
		drawText(Texts.sp[12], g);
	}

	private void drawCRed(Graphics g) {
		drawImage(Statics.back, 18, 10, 10, g);
		drawText(Texts.sp[13], g);
	}

	private void drawStartP(Graphics g) {
		drawImage(Statics.back, 15, 10, 10, g);
		drawText(Texts.sp[14], g);
	}

	private void drawPlace(Graphics g) {
		drawImage(Statics.back, tikei, 10, 10, g);
		switch (tikei) {
		case 1: // '\001'
			drawText(Texts.sp[15], g);
			break;

		case 2: // '\002'
			drawText(Texts.sp[16], g);
			break;

		case 3: // '\003'
			drawText(Texts.sp[17], g);
			break;

		case 4: // '\004'
			drawText(Texts.sp[18], g);
			break;

		case 5: // '\005'
			drawText(Texts.sp[19], g);
			break;

		case 6: // '\006'
			drawText(Texts.sp[20], g);
			break;

		case 7: // '\007'
			drawText(Texts.sp[21], g);
			break;

		case 8: // '\b'
			drawText(Texts.sp[22], g);
			break;

		case 9: // '\t'
			drawText(Texts.sp[23], g);
			break;

		case 10: // '\n'
			drawText(Texts.sp[84], g);
			break;
		}
	}

	private void drawItem(Graphics g) {
		int i = uw.getTreasureLimit(pa);
		drawImage(Statics.back, tikei, 10, 10, g);
		if (i == 0)
			g.drawString(Texts.sp[67], 50, 32);
		else if (i < uw.getTurn()) {
			g.drawString(Texts.sp[68], 50, 32);
			drawLine(Texts.sp[69] + uw.getTurn() + " / " + i, 0, 0, g);
			drawLine(Texts.sp[70], 0, 2, g);
			drawLine(Texts.sp[71], 0, 3, g);
		} else {
			g.drawString(Texts.sp[72], 50, 32);
			drawLine(Texts.sp[73] + uw.getTurn() + " / " + i, 0, 0, g);
			drawLine(Texts.sp[74], 0, 2, g);
			drawLine(Texts.sp[75], 0, 3, g);
		}
	}

	private void drawSummon(Graphics g) {
		int i = uw.getSummonLimit(pa);
		drawImage(Statics.back, tikei, 10, 10, g);
		if (i == 64)
			drawText(Texts.sp[76], g);
		else if (i < uw.getTurn()) {
			g.drawString(Texts.sp[77], 50, 32);
			drawLine(Texts.sp[78] + uw.getTurn() + " / " + i, 0, 0, g);
			drawLine(Texts.sp[79], 0, 2, g);
		} else {
			g.drawString(Texts.sp[80], 50, 32);
			drawLine(Texts.sp[81] + uw.getTurn() + " / " + i, 0, 0, g);
			drawLine(Texts.sp[82], 0, 2, g);
			drawLine(Texts.sp[83], 0, 3, g);
		}
	}

	public void damage(int i) {
		hpb.setMin(ba.hp - i, true);
		repaint(50, 50, 96, 12);
	}

	public void henka() {
		int i = hpb.getSleepTime() / 2;
		for (; hpb.henka(); uw.sleep(i))
			repaint(50, 50, 96, 12);

		repaint();
	}

	private UnitWorks uw;
	private Body ba;
	private Body bb;
	private Iconable atkb;
	private SaveManager sm;
	private int type;
	static final int T_STATUS = 0;
	static final int T_ATTACK = 1;
	static final int T_COUNTER = 2;
	static final int T_ANALYZE = 3;
	static final int T_ATKLIST = 4;
	static final int T_TYPELIST = 5;
	static final int T_DATA = 6;
	static final int T_ITEM = 7;
	static final int T_SUMMON = 8;
	static final int T_STARTP = 9;
	static final int T_SCORE = 10;
	static final int T_SCORE2 = 11;
	static final int T_WAZA = 12;
	static final int T_CBLUE = 13;
	static final int T_CRED = 14;
	static final int T_PLACE = 15;
	static final int T_CAMP = 16;
	static final int C_CHARA1 = 0;
	static final int C_CHARA2 = 1;
	static final int C_CLASS = 2;
	static final int C_WEPON = 3;
	static final int C_ARMOR = 4;
	static final int C_ITEM = 5;
	static final int C_DOLL = 6;
	static final int C_DUST = 7;
	static final int C_DITEM1 = 8;
	static final int C_DITEM2 = 9;
	static final int C_DWEPON = 10;
	static final int C_DARMOR = 11;
	private int bgcolor;
	private int n;
	private Point pa;
	private int tikei;
}
