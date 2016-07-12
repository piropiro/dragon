// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttackBase.java

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import mine.Mine;
import mine.UnitMap;

class AttackBase implements Iconable {

	AttackBase(Body body, int i) {
		ba = body;
		ad = Statics.getAttackData(i);
		bb = null;
		Targets = null;
		setECT();
		setTRType();
		setRange(ad.RangeType, ad.RangeN1, ad.RangeN2);
		getAttackUnit(ad.TargetType, ad.TargetN1);
	}

	public void show() {
		V.copy(2, 1, 1, 0);
		V.S(3, 0, ba.x, ba.y, 0);
	}

	public static void setup(UnitWorks unitworks, UnitMap unitmap, Map map1,
			List<Body> vector) {
		uw = unitworks;
		V = unitmap;
		map = map1;
		Charas = vector;
	}

	private void setTRType(int i, int j, int k, int l, int i1, int j1) {
		ad.RangeType = i;
		ad.RangeN1 = j;
		ad.RangeN2 = k;
		ad.TargetType = l;
		ad.TargetN1 = i1;
		ad.TargetN2 = j1;
	}

	private void setTRType() {
		switch (ad.TRType) {
		case 0: // '\0'
			setTRType(0, 1, 0, 0, 0, 0);
			break;

		case 1: // '\001'
			setTRType(0, 2, 0, 0, 0, 0);
			break;

		case 2: // '\002'
			setTRType(0, 3, 0, 0, 0, 0);
			break;

		case 3: // '\003'
			setTRType(1, 1, 0, 1, 0, 0);
			break;

		case 4: // '\004'
			setTRType(1, 2, 0, 1, 0, 0);
			break;

		case 5: // '\005'
			setTRType(1, 3, 0, 1, 0, 0);
			break;

		case 6: // '\006'
			setTRType(1, 4, 0, 1, 0, 0);
			break;

		case 7: // '\007'
			setTRType(1, 2, 1, 0, 0, 0);
			break;

		case 8: // '\b'
			setTRType(1, 3, 1, 0, 0, 0);
			break;

		case 9: // '\t'
			setTRType(1, 2, 1, 1, 0, 0);
			break;

		case 10: // '\n'
			setTRType(1, 3, 1, 1, 0, 0);
			break;

		case 11: // '\013'
			setTRType(3, 2, 0, 2, 2, 0);
			break;

		case 12: // '\f'
			setTRType(3, 3, 0, 2, 3, 0);
			break;

		case 13: // '\r'
			setTRType(3, 2, 0, 1, 0, 0);
			break;

		case 14: // '\016'
			setTRType(3, 3, 0, 1, 0, 0);
			break;

		case 15: // '\017'
			setTRType(4, 2, 0, 4, 2, 0);
			break;

		case 16: // '\020'
			setTRType(4, 2, 1, 4, 2, 1);
			break;

		case 17: // '\021'
			setTRType(2, 1, 0, 1, 0, 0);
			break;

		case 18: // '\022'
			setTRType(1, 2, 1, 3, 1, 0);
			break;
		}
	}

	private void setECT() {
		ECT = new boolean[40];
		for (int i = 0; i < ad.effect.length; i++)
			ECT[ad.effect[i]] = true;

	}

	private boolean countFuel() {
		if (ad.FuelType == 1 && ad.FuelN1 > ba.str)
			return false;
		if (ad.FuelType == 2 && ad.FuelN1 > ba.def)
			return false;
		if (ad.FuelType == 3 && ad.FuelN1 > ba.mst)
			return false;
		if (ad.FuelType == 4 && ad.FuelN1 > ba.mdf)
			return false;
		if (ad.FuelType == 5 && ad.FuelN1 > ba.hit)
			return false;
		return ad.FuelType != 6 || ad.FuelN1 <= ba.mis;
	}

	public boolean isEffect(int i) {
		return ECT[i];
	}

	public int getBuki() {
		return ad.AttackN1;
	}

	public boolean isCounterable(Body body, boolean flag) {
		if (ba.isType(21))
			return false;
		if (flag && !ECT[12])
			return false;
		if (!flag && !ECT[5])
			return false;
		if (!countFuel())
			return false;
		return V.G(2, 1, body.x, body.y) != 0;
	}

	public boolean isAlive(boolean flag) {
		if (ECT[12])
			return false;
		if (ECT[4] && Rewalk.isWalked(ba))
			return false;
		if (!countFuel())
			return false;
		if (!flag) {
			Dimension dimension = V.getSize();
			for (int i = 0; i < dimension.width; i++) {
				for (int j = 0; j < dimension.height; j++)
					if (V.G(2, 1, i, j) != 0 && V.G(1, 1, i, j) != 0)
						return true;

			}

		} else if (bbs != null)
			return true;
		return false;
	}

	private void getAttackUnit(int i, int j) {
		V.clear(1, 1, 0);
		dmax = -1;
		for (Body body : Charas) {
			if (isTarget(body)) {
				setSearchData(i, j, body);
				if (V.G(2, 1, body.x, body.y) != 0) {
					V.S(2, 1, body.x, body.y, 3);
					int k = getDamage(body) * getRate(body);
					if (!body.isType(35)) {
						if (isPossible(body, 19))
							k += body.hp / 2;
						if (isPossible(body, 16))
							k += body.hp / 2;
					}
					if (isPossible(body, 15))
						k += body.hp;
					if (ad.TargetType == 1)
						k *= 2;
					if (body.color == ba.color)
						k = -k;
					if (ba.isType(27))
						k = -k;
					if (dmax < k) {
						dmax = k;
						bbs = body;
					}
				}
			}
		}

	}

	private void setSearchData(int i, int j, Body body) {
		switch (i) {
		case 3: // '\003'
			V.fillDia(1, 1, body.x, body.y, j, 1);
			break;

		default:
			V.S(1, 1, body.x, body.y, 1);
			break;
		}
	}

	private void decrease() {
		if (ad.FuelType == 1)
			ba.str -= ad.FuelN1;
		if (ad.FuelType == 2)
			ba.def -= ad.FuelN1;
		if (ad.FuelType == 3)
			ba.mst -= ad.FuelN1;
		if (ad.FuelType == 4)
			ba.mdf -= ad.FuelN1;
		if (ad.FuelType == 5)
			ba.hit -= ad.FuelN1;
		if (ad.FuelType == 6)
			ba.mis -= ad.FuelN1;
	}

	private int getFace(int i, int j) {
		int k = i - ba.x;
		int l = j - ba.y;
		if (Math.abs(k) > Math.abs(l))
			return k >= 0 ? 1 : 0;
		return l >= 0 ? 3 : 2;
	}

	public void setTarget(int i, int j) {
		target = new Point(i, j);
		int k = ad.TargetType;
		int l = ad.TargetN1;
		int i1 = ad.TargetN2;
		switch (k) {
		default:
			break;

		case 0: // '\0'
			V.S(4, 0, i, j, 3);
			break;

		case 1: // '\001'
			V.change(1, 0, 0, 4, 0, 0, 3);
			break;

		case 2: // '\002'
			face = getFace(i, j);
			for (int j1 = i1 + 1; j1 <= l; j1++)
				switch (face) {
				case 0: // '\0'
					V.S(4, 0, ba.x - j1, ba.y, 3);
					break;

				case 1: // '\001'
					V.S(4, 0, ba.x + j1, ba.y, 3);
					break;

				case 2: // '\002'
					V.S(4, 0, ba.x, ba.y - j1, 3);
					break;

				case 3: // '\003'
					V.S(4, 0, ba.x, ba.y + j1, 3);
					break;
				}

			break;

		case 3: // '\003'
			V.fillDia(4, 0, i, j, l, 3);
			break;

		case 4: // '\004'
			face = getFace(i, j);
			for (int k1 = i1 + 1; k1 <= l; k1++) {
				for (int l1 = -k1 + 1; l1 <= k1 - 1; l1++)
					switch (face) {
					case 0: // '\0'
						V.S(4, 0, ba.x - k1, ba.y + l1, 3);
						break;

					case 1: // '\001'
						V.S(4, 0, ba.x + k1, ba.y + l1, 3);
						break;

					case 2: // '\002'
						V.S(4, 0, ba.x + l1, ba.y - k1, 3);
						break;

					case 3: // '\003'
						V.S(4, 0, ba.x + l1, ba.y + k1, 3);
						break;
					}

			}

			break;
		}
		V.S(4, 0, ba.x, ba.y, 0);
		V.copy(4, 0, 4, 1);
	}

	private void setRange(int i, int j, int k) {
		V.clear(2, 1, 0);
		switch (i) {
		default:
			break;

		case 0: // '\0'
			V.fillDia(2, 1, ba.x, ba.y, j, 2);
			V.S(2, 1, ba.x, ba.y, 0);
			break;

		case 1: // '\001'
			V.fillDia(2, 1, ba.x, ba.y, j, 2);
			V.fillDia(2, 1, ba.x, ba.y, k, 0);
			break;

		case 2: // '\002'
			V.fillRect(2, 1, ba.x - j, ba.y - j, j * 2 + 1, j * 2 + 1, 2);
			V.S(2, 1, ba.x, ba.y, 0);
			break;

		case 3: // '\003'
			for (int l = k + 1; l <= j; l++) {
				V.S(2, 1, ba.x - l, ba.y, 2);
				V.S(2, 1, ba.x + l, ba.y, 2);
				V.S(2, 1, ba.x, ba.y - l, 2);
				V.S(2, 1, ba.x, ba.y + l, 2);
			}

			break;

		case 4: // '\004'
			for (int i1 = k + 1; i1 <= j; i1++) {
				for (int j1 = -i1 + 1; j1 <= i1 - 1; j1++) {
					V.S(2, 1, ba.x - i1, ba.y + j1, 2);
					V.S(2, 1, ba.x + i1, ba.y + j1, 2);
					V.S(2, 1, ba.x + j1, ba.y - i1, 2);
					V.S(2, 1, ba.x + j1, ba.y + i1, 2);
				}

			}

			break;
		}
	}

	private int getDamage(Body body) {
		if (body == null)
			return 0;
		int i = Walk.getTikei(body);
		int j = 0;
		int k = 0;
		int l = 0;
		switch (ad.AttackType) {
		case 1: // '\001'
			k = ba.str;
			l = body.def;
			break;

		case 2: // '\002'
			k = ba.mst;
			l = body.mdf;
			break;

		case 3: // '\003'
			k = ba.hp / 2;
			break;

		case 4: // '\004'
			k = ad.FuelN1 / 2;
			break;

		case 5: // '\005'
			k = ba.str;
			break;

		case 6: // '\006'
			k = ba.mst;
			break;
		}
		if (ba.isType(30))
			k += (ba.str + ba.mst) / 4;
		if (body.isType(31))
			k -= (body.def + body.mdf) / 4;
		if (ECT[8] && body.isType(21))
			k = (int) ((double) k * 1.25D);
		if (ECT[9] && body.isType(27))
			k = (int) ((double) k * 1.25D);
		if (ECT[8] && body.isType(25))
			k = (int) ((double) k * 1.25D);
		if (ECT[9] && body.isType(25))
			k = (int) ((double) k * 1.25D);
		if (ECT[7] && body.isType(26))
			k = (int) ((double) k * 1.5D);
		if (ECT[9] && i == 3)
			k = (int) ((double) k * 1.5D);
		if (ECT[8] && i == 5)
			k = (int) ((double) k * 1.5D);
		if (ba.isType(40) && body.isType(5))
			k = (int) ((double) k * 1.5D);
		if (ba.isType(41) && body.isType(34))
			k = (int) ((double) k * 1.5D);
		j = Math.max(0, k - l);
		if (ECT[13] && !body.isType(34))
			j *= -1;
		return j;
	}

	private int getRate(Body body) {
		if (body == null)
			return 0;
		int i = Walk.getTikei(body);
		int j = 100;
		if (ECT[1])
			j += 100;
		if (ECT[2])
			j += 200;
		if (ECT[31])
			j += 50;
		if (ECT[7] && body.isType(6))
			j += 100;
		if (ECT[8] && body.isType(9))
			j += 100;
		if (ECT[9] && body.isType(12))
			j += 100;
		if (ECT[10] && i == 1)
			j += 100;
		if (ECT[35] && i == 3)
			j += 100;
		if (ECT[34] && i == 2)
			j += 50;
		if (ECT[11] && body.isType(5))
			j += 100;
		if (ECT[32] && body.isType(34))
			j += 100;
		if (body.isType(21))
			j += 150;
		if (ECT[7] && body.isType(25))
			j /= 2;
		if (ECT[7] && body.isType(7))
			j /= 2;
		if (ECT[8] && body.isType(10))
			j /= 2;
		if (ECT[9] && body.isType(13))
			j /= 2;
		if (ECT[7] && i == 3)
			j /= 2;
		if (ECT[7] && i == 5)
			j /= 2;
		if (Statics.getBukiType(ad.AttackN1) == 1 && body.isType(43))
			j /= 2;
		if ((body.itype == 4 || body.itype == 5) && !ECT[36]) {
			if (i == 3 && !ECT[9])
				j /= 2;
			if (i == 4 && !ECT[7])
				j /= 2;
		}
		if (ba.itype == 4 || ba.itype == 5) {
			if (Walk.getTikei(ba) == 1)
				j /= 2;
			if (Walk.getTikei(ba) == 2)
				j /= 2;
		}
		if (ECT[29] && i != 2)
			j = 0;
		if (ECT[30] && i != 3)
			j = 0;
		if (ECT[7] && body.isType(8))
			j = 0;
		if (ECT[8] && body.isType(11))
			j = 0;
		if (ECT[9] && body.isType(14))
			j = 0;
		return j;
	}

	private int getMeichu(Body body, boolean flag) {
		if (body == null)
			return 0;
		if (ba.hit == 0)
			return 0;
		if (ECT[3])
			return 16;
		int i = 32 - (body.mis * 32) / ba.hit;
		if (flag)
			i += Luck.rnd(1, ba);
		if (ECT[37])
			i += 12;
		if (ECT[24])
			i += 4;
		if (ECT[25])
			i -= 4;
		if (body.isType(21))
			i = Math.max(16 - body.store, i);
		if (body.isType(37))
			i = Math.max(16 - body.store, i);
		if (ECT[6])
			i = Math.max(2, i);
		else
			i = Mine.mid(2, i, 32 - body.store - 1);
		if (ba.isType(30))
			i += 2;
		if (body.isType(31))
			i -= 2;
		return i;
	}

	public void selectTarget(Body body) {
		bb = body;
		if (body == null) {
			uw.closeHPanel();
		} else {
			if (Targets == null) {
				Targets = new ArrayList<>();
				Targets.add(body);
			}
			meichu = getMeichu(body, false);
			uw.setHPanel(body, ba, (getDamage(body) * getRate(body)) / 100,
					isHit());
		}
	}

	public boolean isTarget(Body body) {
		if (body == ba)
			return false;
		if (!body.isAlive())
			return false;
		if (ECT[22]) {
			if (ECT[13] && body.color != ba.color)
				return false;
			if (!ECT[13] && body.color == ba.color)
				return false;
			if (isPossible(body, 14))
				return true;
			if (isPossible(body, 15))
				return true;
			if (isPossible(body, 16))
				return true;
			if (isPossible(body, 19))
				return true;
			if (isPossible(body, 17))
				return true;
			if (isPossible(body, 18))
				return true;
			if (isPossible(body, 20))
				return true;
			if (isPossible(body, 21))
				return true;
			if (isPossible(body, 26))
				return true;
			if (isPossible(body, 27))
				return true;
			if (isPossible(body, 23))
				return true;
			return isPossible(body, 33);
		}
		if (body.isType(27))
			return true;
		int i = getDamage(body);
		if (i == 0)
			if (ECT[13])
				return body.color == ba.color;
			else
				return body.color != ba.color;
		if (i < 0 && body.hp >= body.hpMax)
			return false;
		boolean flag = true;
		if (body.color == ba.color)
			flag = !flag;
		if (ba.isType(27))
			flag = !flag;
		if (i < 0)
			flag = !flag;
		return flag;
	}

	public boolean searchTargets() {
		if (ECT[5] && bb == null)
			return false;
		Targets = new ArrayList<>();
		for (Body body : Charas) {
			if (isTarget(body) && V.G(4, 0, body.x, body.y) != 0)
				Targets.add(body);
		}

		return Targets.size() != 0;
	}

	public String getName() {
		return ad.name;
	}

	public String getSubName() {
		return ad.sname;
	}

	public int getColor() {
		return ad.color;
	}

	public int getStore() {
		return bb.store;
	}

	public int getDamage() {
		return getDamage(bb);
	}

	public int getMeichu() {
		return meichu;
	}

	public int getRate() {
		return getRate(bb);
	}

	public Body getBody(boolean flag) {
		if (flag)
			return ba;
		else
			return bb;
	}

	private void anime(boolean flag) {
		Point point = new Point(ba.x, ba.y);
		Point point1;
		if (bb != null)
			point1 = new Point(bb.x, bb.y);
		else
			point1 = target;
		switch (ad.AnimeType) {
		default:
			break;

		case 2: // '\002'
		case 5: // '\005'
		case 6: // '\006'
			if (!flag)
				uw.setAnime(ad.AnimeType, ad.AnimeN1, point, point1);
			break;

		case 1: // '\001'
		case 3: // '\003'
			if (flag)
				uw.setAnime(ad.AnimeType, ad.AnimeN1, point, point1);
			break;

		case 4: // '\004'
			if (flag)
				break;
			if (ad.TargetType == 2)
				switch (face) {
				case 0: // '\0'
					point1.x = Math.max(0, point.x - ad.TargetN1);
					break;

				case 1: // '\001'
					point1.x = Math.min(19, point.x + ad.TargetN1);
					break;

				case 2: // '\002'
					point1.y = Math.max(0, point.y - ad.TargetN1);
					break;

				case 3: // '\003'
					point1.y = Math.min(14, point.y + ad.TargetN1);
					break;
				}
			uw.setAnime(ad.AnimeType, ad.AnimeN1, point, point1);
			break;
		}
	}

	public boolean isHit() {
		if (ECT[3])
			return true;
		if (bb.isType(21))
			return true;
		if (bb.isType(37))
			return true;
		int i = getMeichu(bb, false);
		return i + bb.store > 16;
	}

	public Body getBestTarget() {
		return bbs;
	}

	public int getBestDamage() {
		return dmax;
	}

	public void attack() {
		anime(false);
		if (bb != null) {
			Targets.remove(bb);
			Targets.add(0, bb);
		} else {
			bb = Targets.get(0);
			uw.setAPanel(this, null);
		}
		int i = 0;
		for (Body b : Targets) {
			bb = b;
			meichu = getMeichu(bb, true);
			uw.setHPanel(bb, ba, (getDamage(bb) * getRate(bb)) / 100, isHit());
			if (i > 0)
				uw.setAPanel(this, null);
			if (V.G(3, 0, bb.x, bb.y) == 2)
				V.S(3, 0, bb.x, bb.y, 0);
			if (!attackMiss() && !attackFinish() && !attackDeath()
					&& !attackMain()) {
				attackSleep();
				attackCharm();
				attackPoison();
				attackHeal();
				attackClose();
				attackOil();
				attackOffence();
				attackDefence();
				attackUpper();
				attackDowner();
				attackDance();
				uw.sleep(300L);
			}
			i++;
		}

		decrease();
	}

	public boolean isPossible(int i) {
		return isPossible(bb, i);
	}

	private boolean isPossible(Body body, int i) {
		if (!ECT[i])
			return false;
		if (body.isType(17))
			return false;
		switch (i) {
		case 14: // '\016'
		case 15: // '\017'
			if (body.isType(20))
				return false;
			if (ba.level < body.level)
				return false;
			if (body.isType(31))
				return false;
			break;

		case 16: // '\020'
		case 19: // '\023'
			if (body.mdf * 2 >= ba.mst)
				return false;
			if (body.isType(31))
				return false;
			break;
		}
		switch (i) {
		case 22: // '\026'
		case 24: // '\030'
		case 25: // '\031'
		case 27: // '\033'
		case 28: // '\034'
		case 29: // '\035'
		case 30: // '\036'
		case 31: // '\037'
		case 32: // ' '
		default:
			break;

		case 14: // '\016'
			if (body.isType(18))
				return false;
			if (!body.isType(23) && body.def >= ba.str)
				return false;
			break;

		case 15: // '\017'
			if (body.isType(34))
				return false;
			if (body.isType(19))
				return false;
			if (body.isType(23))
				break;
			if (body.mdf >= ba.mst)
				return false;
			if (body.hp > (body.hpMax * 3) / 4)
				return false;
			break;

		case 16: // '\020'
			if (body.isType(22))
				return false;
			if (body.isType(21))
				return false;
			break;

		case 19: // '\023'
			if (body.isType(28))
				return false;
			if (body.isType(27))
				return false;
			break;

		case 18: // '\022'
			if (body.isType(25))
				return false;
			break;

		case 33: // '!'
			if (body.isType(26))
				return false;
			break;

		case 17: // '\021'
			if (body.isType(24))
				return false;
			if (body.isType(23))
				return false;
			break;

		case 20: // '\024'
			if (body.isType(30))
				return false;
			break;

		case 21: // '\025'
			if (body.isType(31))
				return false;
			break;

		case 26: // '\032'
			if (Walk.getTikei(body) == 1)
				return false;
			break;

		case 23: // '\027'
			if (V.G(3, 0, body.x, body.y) == 0)
				return false;
			break;
		}
		return true;
	}

	private boolean attackKill(int i, int j) {
		uw.setAPanel();
		uw.hpdamage(bb.hpMax);
		Point point = new Point(bb.x, bb.y);
		uw.setAnime(1, i, point, point);
		uw.setAnime(-3, 0, point, point);
		uw.setAnime(-6, j, point, point);
		uw.setAPanel();
		uw.sleep(200L);
		uw.hphenka();
		bb.hp = 0;
		uw.dead(this);
		uw.closeNPanel();
		return true;
	}

	private boolean attackFinish() {
		if (!isPossible(14))
			return false;
		if (Luck.rnd(1, ba) != 1)
			return false;
		else
			return attackKill(-6, 3);
	}

	private boolean attackDeath() {
		if (!isPossible(15))
			return false;
		else
			return attackKill(-7, 5);
	}

	private void attackStatus(int i, int j) {
		if (bb.isType(35)) {
			bb.setTypeState(35, false);
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, i, point, point);
			bb.setTypeState(j, true);
			bb.setTypeState(35, false);
			bb.setTypeState(32, true);
			return;
		}
	}

	private void attackSleep() {
		if (!isPossible(16)) {
			return;
		} else {
			attackStatus(1, 21);
			return;
		}
	}

	private void attackCharm() {
		if (!isPossible(19)) {
			return;
		} else {
			attackStatus(6, 27);
			return;
		}
	}

	private void attackClose() {
		if (!isPossible(18)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 3, point, point);
			bb.setTypeState(25, true);
			bb.setTypeState(32, true);
			return;
		}
	}

	private void attackOil() {
		if (!isPossible(33)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 10, point, point);
			bb.setTypeState(26, true);
			bb.setTypeState(32, true);
			return;
		}
	}

	private void attackMode(int i, int j, int k) {
		Point point = new Point(bb.x, bb.y);
		uw.setAnime(-7, k, point, point);
		if (bb.isType(j)) {
			bb.setTypeState(j, false);
			V.S(5, 0, bb.x, bb.y, 0);
		} else {
			bb.setTypeState(i, true);
		}
	}

	private void attackPoison() {
		if (!isPossible(17)) {
			return;
		} else {
			attackMode(23, 29, 2);
			return;
		}
	}

	private void attackHeal() {
		if (!isPossible(28)) {
			return;
		} else {
			attackMode(29, 23, 7);
			return;
		}
	}

	private void attackOffence() {
		if (!isPossible(20)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 4, point, point);
			bb.setTypeState(30, true);
			bb.setTypeState(32, true);
			return;
		}
	}

	private void attackDefence() {
		if (!isPossible(21)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 5, point, point);
			bb.setTypeState(31, true);
			bb.setTypeState(32, true);
			return;
		}
	}

	private void attackUpper() {
		if (!isPossible(26)) {
			return;
		} else {
			attackMode(36, 37, 8);
			return;
		}
	}

	private void attackDowner() {
		if (!isPossible(27)) {
			return;
		} else {
			attackMode(37, 36, 9);
			return;
		}
	}

	private void attackDance() {
		if (!isPossible(23)) {
			return;
		} else {
			V.S(3, 0, bb.x, bb.y, 0);
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(1, -1, point, point);
			return;
		}
	}

	private boolean attackMain() {
		if (ECT[22]) {
			bb.store += meichu;
			bb.store %= 16;
			meichu = 0;
			uw.setAPanel();
			anime(true);
			return false;
		}
		int i = 0;
		while (meichu > 0) {
			bb.store++;
			meichu--;
			if (bb.store >= 16) {
				uw.setAPanel();
				anime(true);
				int j = (getDamage(bb) * getRate(bb)) / 100;
				if (j >= 0)
					i += Math.max(1, j + Luck.rnd(1, ba));
				else
					i += Math.min(-1, j - Luck.rnd(1, ba));
				uw.hpdamage(i);
				uw.setNPanel(bb, i);
				bb.store -= 16;
			}
		}
		uw.setAPanel();
		uw.sleep(200L);
		uw.hphenka();
		bb.hp = Mine.mid(0, bb.hp - i, bb.hpMax);
		if (bb.hp <= 0) {
			uw.dead(this);
			uw.closeNPanel();
			uw.sleep(300L);
			return true;
		} else {
			return false;
		}
	}

	private boolean attackMiss() {
		if (meichu + bb.store >= 16) {
			return false;
		} else {
			bb.store += meichu;
			meichu = 0;
			uw.setAPanel();
			anime(true);
			uw.hpdamage(0);
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-5, 0, point, point);
			uw.sleep(500L);
			return true;
		}
	}

	static UnitWorks uw;
	static UnitMap V;
	static Map map;
	static List<Body> Charas;
	private List<Body> Targets;
	Body ba;
	Body bb;
	Body bbs;
	Point target;
	int dmax;
	AttackData ad;
	static final int N_STR = 1;
	static final int N_MST = 2;
	static final int N_HPH = 3;
	static final int N_FUELH = 4;
	static final int N_STRA = 5;
	static final int N_MSTA = 6;
	boolean ECT[];
	static final int HIT = 16;
	static final int DHIT = 32;
	int meichu;
	int face;
}
