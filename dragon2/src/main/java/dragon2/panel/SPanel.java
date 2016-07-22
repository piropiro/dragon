package dragon2.panel;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import dragon2.Iconable;
import dragon2.SaveManager;
import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.attack.AttackData;
import dragon2.common.Body;
import dragon2.common.constant.DamageType;
import dragon2.common.constant.Effects;
import dragon2.common.constant.Kinds;
import dragon2.common.constant.Texts;
import dragon2.common.constant.Types;
import dragon2.common.util.Rank;

@SuppressWarnings("serial")
public class SPanel extends StatusBase {

	public SPanel(UnitWorks unitworks, SaveManager savemanager, boolean flag) {
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
		if (body.kind == Kinds.WAZA )
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
		if (attackdata.damageType != DamageType.NONE)
			drawLine(attackdata.damageType.getText(), 0, 0, g);
		else
			drawLine(Texts.sp[33], 0, 0, g);
		drawLine(attackdata.getTargetType().getText(), 1, 0, g);
		Set<Effects> aflag = new HashSet<>(attackdata.getEffect());
		
		int j = 0;
		if (aflag.contains(Effects.MISS_4))
			j -= 4;
		if (aflag.contains(Effects.HIT_4))
			j += 4;
		if (aflag.contains(Effects.HIT_12))
			j += 12;
		if (aflag.contains(Effects.HICHU))
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
		switch (attackdata.energyType) {
		case STR: // '\001'
			drawLine(Texts.sp[39], 1, 1, g);
			break;

		case DEF: // '\002'
			drawLine(Texts.sp[40], 1, 1, g);
			break;

		case MST: // '\003'
			drawLine(Texts.sp[41], 1, 1, g);
			break;

		case MDF: // '\004'
			drawLine(Texts.sp[42], 1, 1, g);
			break;

		case HIT: // '\005'
			drawLine(Texts.sp[43], 1, 1, g);
			break;

		case MIS: // '\006'
			drawLine(Texts.sp[44], 1, 1, g);
			break;
		default:
		}
		n = 4;
		drawWazaEffect(g, aflag, Effects.DAMAGE_150);
		drawWazaEffect(g, aflag, Effects.DAMAGE_200);
		drawWazaEffect(g, aflag, Effects.DAMAGE_300);
		drawWazaEffect(g, aflag, Effects.TAME);
		drawWazaEffect(g, aflag, Effects.COUNTER_ONLY);

		drawWazaEffect(g, aflag, Effects.RIKU_0);
		drawWazaEffect(g, aflag, Effects.RIKU_150);
		drawWazaEffect(g, aflag, Effects.MIZU_0);
		drawWazaEffect(g, aflag, Effects.MIZU_100);
		drawWazaEffect(g, aflag, Effects.MIZU_200);

		drawWazaEffect(g, aflag, Effects.FIRE);
		drawWazaEffect(g, aflag, Effects.ICE);
		drawWazaEffect(g, aflag, Effects.THUNDER);
		drawWazaEffect(g, aflag, Effects.SORA_200);
		drawWazaEffect(g, aflag, Effects.DRAGON_200);
		drawWazaEffect(g, aflag, Effects.UNDEAD_200);

		drawWazaEffect(g, aflag, Effects.UPPER);
		drawWazaEffect(g, aflag, Effects.CHOP);
		drawWazaEffect(g, aflag, Effects.CRITICAL);
		drawWazaEffect(g, aflag, Effects.DEATH);
		drawWazaEffect(g, aflag, Effects.SLEEP);
		drawWazaEffect(g, aflag, Effects.POISON);
		drawWazaEffect(g, aflag, Effects.WET);
		drawWazaEffect(g, aflag, Effects.CHARM);
		drawWazaEffect(g, aflag, Effects.ATTACK_UP);
		drawWazaEffect(g, aflag, Effects.GUARD_UP);
		drawWazaEffect(g, aflag, Effects.REFRESH);
		drawWazaEffect(g, aflag, Effects.HEAL);
		drawWazaEffect(g, aflag, Effects.OIL);
	}

	private boolean drawWazaEffect(Graphics g, Set<Effects> aflag, Effects i) {
		if (n == 8)
			return false;
		if (!aflag.contains(i)) {
			return false;
		} else {
			drawLine(i.getText(), n % 2, n / 2, g);
			n++;
			return true;
		}
	}

	private void drawAnalyze(Graphics g) {
		int i = ba.moveStep;
		if (ba.isType(Types.MOVE_UP_1))
			i++;
		if (ba.isType(Types.MOVE_UP_2))
			i += 2;
		if (ba.isType(Types.MOVE_DOWN_1))
			i--;
		drawLine(ba.moveType.getText(), i, 0, 1, g);
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
		drawType(g, Types.BADITEM);
		drawType(g, Types.MASTER);
		drawType(g, Types.DRAGON);
		drawType(g, Types.UNDEAD);
		drawType(g, Types.DRAGON_KILLER);
		drawType(g, Types.UNDEAD_KILLER);
		drawType(g, Types.SWORD_50);
		drawType(g, Types.MAGIC_50);
		drawType(g, Types.FIRE_200, Types.FIRE_50, Types.FIRE_0);
		drawType(g, Types.ICE_200, Types.ICE_50, Types.ICE_0);
		drawType(g, Types.THUNDER_200, Types.THUNDER_50, Types.THUNDER_0);
		if (drawType(g, Types.ANTI_ALL))
			return;
		if (!drawType(g, Types.NKILL)) {
			drawType(g, Types.ANTI_CRITICAL);
			drawType(g, Types.ANTI_DEATH);
		}
		drawType(g, Types.ANTI_SLEEP);
		drawType(g, Types.ANTI_POISON);
		drawType(g, Types.ANTI_CHARM);
		drawType(g, Types.POISON);
		drawType(g, Types.HEAL);
		drawType(g, Types.FLY_ABLE);
		drawType(g, Types.SWIM_ABLE);
		drawType(g, Types.LITE_WALK);
		int i = 0;
		if (ba.isType(Types.MOVE_UP_1))
			i++;
		if (ba.isType(Types.MOVE_UP_2))
			i += 2;
		if (ba.isType(Types.MOVE_DOWN_1))
			i--;
		switch (i) {
		case -1:
			drawType(g, Types.MOVE_DOWN_1);
			break;

		case 1: // '\001'
			drawType(g, Types.MOVE_UP_1);
			break;

		case 2: // '\002'
			drawType(g, Types.MOVE_UP_2);
			break;
		}
		drawType(g, Types.S_LOCK);
	}

	private boolean drawType(Graphics g, Types i, Types j, Types k) {
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

	private boolean drawType(Graphics g, Types type) {
		if (n == 8)
			return false;
		if (!ba.isType(type)) {
			return false;
		} else {
			drawLine(type.getText(), n / 4, n % 4, g);
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
		if (atkb.isEffect(Effects.NO_ATTACK)) {
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
		if (atkb.isPossible(Effects.REFRESH))
			s = "REFRESH";
		if (atkb.isPossible(Effects.OIL))
			s = "OIL";
		if (atkb.isPossible(Effects.ATTACK_UP))
			s = "ATTACK";
		if (atkb.isPossible(Effects.ATTACK_UP))
			s = "GUARD";
		if (atkb.isPossible(Effects.UPPER))
			s = "UP";
		if (atkb.isPossible(Effects.CHOP))
			s = "DOWN";
		if (atkb.isPossible(Effects.WET))
			s = "CLOSE";
		if (atkb.isPossible(Effects.POISON))
			s = "POISON";
		if (atkb.isPossible(Effects.SLEEP))
			s = "SLEEP";
		if (atkb.isPossible(Effects.CHARM))
			s = "CHARM";
		if (atkb.isPossible(Effects.CRITICAL))
			s = "FINISH";
		if (atkb.isPossible(Effects.DEATH))
			s = "DEATH";
		if ((s.equals("SLEEP") || s.equals("CHARM")) && bb.isType(Types.S_LOCK))
			s = "LOCK";
		drawLine(Texts.sp[59] + s, 0, 2, g);
	}

	private void drawCounter(Graphics g) {
		if (ba.isType(Types.ANTI_SLEEP))
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
