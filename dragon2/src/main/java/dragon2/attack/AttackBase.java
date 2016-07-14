package dragon2.attack;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dragon2.Iconable;
import dragon2.Rewalk;
import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.Walk;
import dragon2.common.Body;
import dragon2.common.constant.Effects;
import dragon2.common.constant.Types;
import dragon2.common.util.Luck;
import dragon2.map.Map;
import mine.Mine;
import mine.UnitMap;

public class AttackBase implements Iconable {

	public AttackBase(Body body, int i) {
		ba = body;
		ad = Statics.getAttackData(i);
		bb = null;
		Targets = null;
		setECT();
		
		setRange(ad.targetType.getRangeType(), ad.targetType.getRangeN1(), ad.targetType.getRangeN2());
		getAttackUnit(ad.targetType.getTargetType(), ad.targetType.getTargetN1());
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





	private void setECT() {
		ECT = new HashSet<>(ad.getEffect());
	}

	private boolean countFuel() {
		if (ad.fuelType == 1 && ad.fuelN1 > ba.str)
			return false;
		if (ad.fuelType == 2 && ad.fuelN1 > ba.def)
			return false;
		if (ad.fuelType == 3 && ad.fuelN1 > ba.mst)
			return false;
		if (ad.fuelType == 4 && ad.fuelN1 > ba.mdf)
			return false;
		if (ad.fuelType == 5 && ad.fuelN1 > ba.hit)
			return false;
		return ad.fuelType != 6 || ad.fuelN1 <= ba.mis;
	}

	public boolean isEffect(Effects effect) {
		return ECT.contains(effect);
	}

	public int getBuki() {
		return ad.attackN1;
	}

	public boolean isCounterable(Body body, boolean flag) {
		if (ba.isType(Types.ANTI_SLEEP))
			return false;
		if (flag && !isEffect(Effects.COUNTER_ONLY))
			return false;
		if (!flag && !isEffect(Effects.COUNTER_ABLE))
			return false;
		if (!countFuel())
			return false;
		return V.G(2, 1, body.x, body.y) != 0;
	}

	public boolean isAlive(boolean flag) {
		if (isEffect(Effects.COUNTER_ONLY))
			return false;
		if (isEffect(Effects.TAME) && Rewalk.isWalked(ba))
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
					if (!body.isType(Types.S_LOCK)) {
						if (isPossible(body, Effects.CHARM))
							k += body.hp / 2;
						if (isPossible(body, Effects.SLEEP))
							k += body.hp / 2;
					}
					if (isPossible(body, Effects.DEATH))
						k += body.hp;
					if (ad.targetType.getTargetType() == 1)
						k *= 2;
					if (body.color == ba.color)
						k = -k;
					if (ba.isType(Types.CHARM))
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
		if (ad.fuelType == 1)
			ba.str -= ad.fuelN1;
		if (ad.fuelType == 2)
			ba.def -= ad.fuelN1;
		if (ad.fuelType == 3)
			ba.mst -= ad.fuelN1;
		if (ad.fuelType == 4)
			ba.mdf -= ad.fuelN1;
		if (ad.fuelType == 5)
			ba.hit -= ad.fuelN1;
		if (ad.fuelType == 6)
			ba.mis -= ad.fuelN1;
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
		int k = ad.targetType.getTargetType();
		int l = ad.targetType.getTargetN1();
		int i1 = ad.targetType.getTargetN2();
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
		switch (ad.attackType) {
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
			k = ad.fuelN1 / 2;
			break;

		case 5: // '\005'
			k = ba.str;
			break;

		case 6: // '\006'
			k = ba.mst;
			break;
		}
		if (ba.isType(Types.ATTACK_UP))
			k += (ba.str + ba.mst) / 4;
		if (body.isType(Types.GUARD_UP))
			k -= (body.def + body.mdf) / 4;
		if (isEffect(Effects.ICE) && body.isType(Types.ANTI_SLEEP))
			k = (int) ((double) k * 1.25D);
		if (isEffect(Effects.THUNDER) && body.isType(Types.CHARM))
			k = (int) ((double) k * 1.25D);
		if (isEffect(Effects.ICE) && body.isType(Types.CLOSE))
			k = (int) ((double) k * 1.25D);
		if (isEffect(Effects.THUNDER) && body.isType(Types.CLOSE))
			k = (int) ((double) k * 1.25D);
		if (isEffect(Effects.FIRE) && body.isType(Types.OIL))
			k = (int) ((double) k * 1.5D);
		if (isEffect(Effects.THUNDER) && i == 3)
			k = (int) ((double) k * 1.5D);
		if (isEffect(Effects.ICE) && i == 5)
			k = (int) ((double) k * 1.5D);
		if (ba.isType(Types.DRAGON_KILLER) && body.isType(Types.DRAGON))
			k = (int) ((double) k * 1.5D);
		if (ba.isType(Types.UNDEAD_KILLER) && body.isType(Types.UNDEAD))
			k = (int) ((double) k * 1.5D);
		j = Math.max(0, k - l);
		if (isEffect(Effects.REGENE) && !body.isType(Types.UNDEAD))
			j *= -1;
		return j;
	}

	private int getRate(Body body) {
		if (body == null)
			return 0;
		int i = Walk.getTikei(body);
		int j = 100;
		if (isEffect(Effects.DAMAGE_200))
			j += 100;
		if (isEffect(Effects.DAMAGE_300))
			j += 200;
		if (isEffect(Effects.DAMAGE_150))
			j += 50;
		if (isEffect(Effects.FIRE) && body.isType(Types.FIRE_200))
			j += 100;
		if (isEffect(Effects.ICE) && body.isType(Types.ICE_200))
			j += 100;
		if (isEffect(Effects.THUNDER) && body.isType(Types.THUNDER_200))
			j += 100;
		if (isEffect(Effects.SORA_200) && i == 1)
			j += 100;
		if (isEffect(Effects.MIZU_200) && i == 3)
			j += 100;
		if (isEffect(Effects.RIKU_150) && i == 2)
			j += 50;
		if (isEffect(Effects.DRAGON_200) && body.isType(Types.DRAGON))
			j += 100;
		if (isEffect(Effects.UNDEAD_200) && body.isType(Types.UNDEAD))
			j += 100;
		if (body.isType(Types.ANTI_SLEEP))
			j += 150;
		if (isEffect(Effects.FIRE) && body.isType(Types.CLOSE))
			j /= 2;
		if (isEffect(Effects.FIRE) && body.isType(Types.FIRE_50))
			j /= 2;
		if (isEffect(Effects.ICE) && body.isType(Types.ICE_50))
			j /= 2;
		if (isEffect(Effects.THUNDER) && body.isType(Types.THUNDER_50))
			j /= 2;
		if (isEffect(Effects.FIRE) && i == 3)
			j /= 2;
		if (isEffect(Effects.FIRE) && i == 5)
			j /= 2;
		if (Statics.getBukiType(ad.attackN1) == 1 && body.isType(Types.SWORD_50))
			j /= 2;
		if ((body.itype == 4 || body.itype == 5) && !isEffect(Effects.MIZU_100)) {
			if (i == 3 && !isEffect(Effects.THUNDER))
				j /= 2;
			if (i == 4 && !isEffect(Effects.FIRE))
				j /= 2;
		}
		if (ba.itype == 4 || ba.itype == 5) {
			if (Walk.getTikei(ba) == 1)
				j /= 2;
			if (Walk.getTikei(ba) == 2)
				j /= 2;
		}
		if (isEffect(Effects.RIKU_0) && i != 2)
			j = 0;
		if (isEffect(Effects.MIZU_0) && i != 3)
			j = 0;
		if (isEffect(Effects.FIRE) && body.isType(Types.FIRE_0))
			j = 0;
		if (isEffect(Effects.ICE) && body.isType(Types.ICE_0))
			j = 0;
		if (isEffect(Effects.THUNDER) && body.isType(Types.THUNDER_0))
			j = 0;
		return j;
	}

	private int getMeichu(Body body, boolean flag) {
		if (body == null)
			return 0;
		if (ba.hit == 0)
			return 0;
		if (isEffect(Effects.HICHU))
			return 16;
		int i = 32 - (body.mis * 32) / ba.hit;
		if (flag)
			i += Luck.rnd(1, ba);
		if (isEffect(Effects.HIT_12))
			i += 12;
		if (isEffect(Effects.HIT_4))
			i += 4;
		if (isEffect(Effects.MISS_4))
			i -= 4;
		if (body.isType(Types.ANTI_SLEEP))
			i = Math.max(16 - body.store, i);
		if (body.isType(Types.RIKU))
			i = Math.max(16 - body.store, i);
		if (isEffect(Effects.COMBO))
			i = Math.max(2, i);
		else
			i = Mine.mid(2, i, 32 - body.store - 1);
		if (ba.isType(Types.ATTACK_UP))
			i += 2;
		if (body.isType(Types.GUARD_UP))
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
		if (isEffect(Effects.NO_ATTACK)) {
			if (isEffect(Effects.REGENE) && body.color != ba.color)
				return false;
			if (!isEffect(Effects.REGENE) && body.color == ba.color)
				return false;
			if (isPossible(body, Effects.CRITICAL))
				return true;
			if (isPossible(body, Effects.DEATH))
				return true;
			if (isPossible(body, Effects.SLEEP))
				return true;
			if (isPossible(body, Effects.CHARM))
				return true;
			if (isPossible(body, Effects.POISON))
				return true;
			if (isPossible(body, Effects.WET))
				return true;
			if (isPossible(body, Effects.ATTACK_UP))
				return true;
			if (isPossible(body, Effects.GUARD_UP))
				return true;
			if (isPossible(body, Effects.UPPER))
				return true;
			if (isPossible(body, Effects.CHOP))
				return true;
			if (isPossible(body, Effects.REFRESH))
				return true;
			return isPossible(body, Effects.OIL);
		}
		if (body.isType(Types.CHARM))
			return true;
		int i = getDamage(body);
		if (i == 0)
			if (isEffect(Effects.REGENE))
				return body.color == ba.color;
			else
				return body.color != ba.color;
		if (i < 0 && body.hp >= body.hpMax)
			return false;
		boolean flag = true;
		if (body.color == ba.color)
			flag = !flag;
		if (ba.isType(Types.CHARM))
			flag = !flag;
		if (i < 0)
			flag = !flag;
		return flag;
	}

	public boolean searchTargets() {
		if (isEffect(Effects.COUNTER_ABLE) && bb == null)
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
		switch (ad.animeType) {
		default:
			break;

		case 2: // '\002'
		case 5: // '\005'
		case 6: // '\006'
			if (!flag)
				uw.setAnime(ad.animeType, ad.animeN1, point, point1);
			break;

		case 1: // '\001'
		case 3: // '\003'
			if (flag)
				uw.setAnime(ad.animeType, ad.animeN1, point, point1);
			break;

		case 4: // '\004'
			if (flag)
				break;
			if (ad.targetType.getTargetType() == 2)
				switch (face) {
				case 0: // '\0'
					point1.x = Math.max(0, point.x - ad.targetType.getTargetN1());
					break;

				case 1: // '\001'
					point1.x = Math.min(19, point.x + ad.targetType.getTargetN1());
					break;

				case 2: // '\002'
					point1.y = Math.max(0, point.y - ad.targetType.getTargetN1());
					break;

				case 3: // '\003'
					point1.y = Math.min(14, point.y + ad.targetType.getTargetN1());
					break;
				}
			uw.setAnime(ad.animeType, ad.animeN1, point, point1);
			break;
		}
	}

	public boolean isHit() {
		if (isEffect(Effects.HICHU))
			return true;
		if (bb.isType(Types.ANTI_SLEEP))
			return true;
		if (bb.isType(Types.RIKU))
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

	public boolean isPossible(Effects i) {
		return isPossible(bb, i);
	}

	private boolean isPossible(Body body, Effects i) {
		if (!isEffect(i))
			return false;
		if (body.isType(Types.ANTI_ALL))
			return false;
		switch (i) {
		case CRITICAL: // '\016'
		case DEATH: // '\017'
			if (body.isType(Types.NKILL))
				return false;
			if (ba.level < body.level)
				return false;
			if (body.isType(Types.GUARD_UP))
				return false;
			break;

		case SLEEP: // '\020'
		case CHARM: // '\023'
			if (body.mdf * 2 >= ba.mst)
				return false;
			if (body.isType(Types.GUARD_UP))
				return false;
			break;
		default:
		}
		switch (i) {
		case NO_ATTACK: // '\026'
		case HIT_4: // '\030'
		case MISS_4: // '\031'
		case CHOP: // '\033'
		case HEAL: // '\034'
		case RIKU_0: // '\035'
		case MIZU_0: // '\036'
		case DAMAGE_150: // '\037'
		case UNDEAD_200: // ' '
		default:
			break;

		case CRITICAL: // '\016'
			if (body.isType(Types.ANTI_CRITICAL))
				return false;
			if (!body.isType(Types.POISON) && body.def >= ba.str)
				return false;
			break;

		case DEATH: // '\017'
			if (body.isType(Types.UNDEAD))
				return false;
			if (body.isType(Types.ANTI_DEATH))
				return false;
			if (body.isType(Types.POISON))
				break;
			if (body.mdf >= ba.mst)
				return false;
			if (body.hp > (body.hpMax * 3) / 4)
				return false;
			break;

		case SLEEP: // '\020'
			if (body.isType(Types.SLEEP))
				return false;
			if (body.isType(Types.ANTI_SLEEP))
				return false;
			break;

		case CHARM: // '\023'
			if (body.isType(Types.ANTI_CHARM))
				return false;
			if (body.isType(Types.CHARM))
				return false;
			break;

		case WET: // '\022'
			if (body.isType(Types.CLOSE))
				return false;
			break;

		case OIL: // '!'
			if (body.isType(Types.OIL))
				return false;
			break;

		case POISON: // '\021'
			if (body.isType(Types.ANTI_POISON))
				return false;
			if (body.isType(Types.POISON))
				return false;
			break;

		case ATTACK_UP: // '\024'
			if (body.isType(Types.ATTACK_UP))
				return false;
			break;

		case GUARD_UP: // '\025'
			if (body.isType(Types.GUARD_UP))
				return false;
			break;

		case UPPER: // '\032'
			if (Walk.getTikei(body) == 1)
				return false;
			break;

		case REFRESH: // '\027'
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
		if (!isPossible(Effects.CRITICAL))
			return false;
		if (Luck.rnd(1, ba) != 1)
			return false;
		else
			return attackKill(-6, 3);
	}

	private boolean attackDeath() {
		if (!isPossible(Effects.DEATH))
			return false;
		else
			return attackKill(-7, 5);
	}

	private void attackStatus(int i, Types j) {
		if (bb.isType(Types.S_LOCK)) {
			bb.setTypeState(Types.S_LOCK, false);
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, i, point, point);
			bb.setTypeState(j, true);
			bb.setTypeState(Types.S_LOCK, false);
			bb.setTypeState(Types.S_WAIT, true);
			return;
		}
	}

	private void attackSleep() {
		if (!isPossible(Effects.SLEEP)) {
			return;
		} else {
			attackStatus(1, Types.ANTI_SLEEP);
			return;
		}
	}

	private void attackCharm() {
		if (!isPossible(Effects.CHARM)) {
			return;
		} else {
			attackStatus(6, Types.CHARM);
			return;
		}
	}

	private void attackClose() {
		if (!isPossible(Effects.WET)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 3, point, point);
			bb.setTypeState(Types.CLOSE, true);
			bb.setTypeState(Types.S_WAIT, true);
			return;
		}
	}

	private void attackOil() {
		if (!isPossible(Effects.OIL)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 10, point, point);
			bb.setTypeState(Types.OIL, true);
			bb.setTypeState(Types.S_WAIT, true);
			return;
		}
	}

	private void attackMode(Types i, Types j, int k) {
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
		if (!isPossible(Effects.POISON)) {
			return;
		} else {
			attackMode(Types.POISON, Types.HEAL, 2);
			return;
		}
	}

	private void attackHeal() {
		if (!isPossible(Effects.HEAL)) {
			return;
		} else {
			attackMode(Types.HEAL, Types.POISON, 7);
			return;
		}
	}

	private void attackOffence() {
		if (!isPossible(Effects.ATTACK_UP)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 4, point, point);
			bb.setTypeState(Types.ATTACK_UP, true);
			bb.setTypeState(Types.S_WAIT, true);
			return;
		}
	}

	private void attackDefence() {
		if (!isPossible(Effects.GUARD_UP)) {
			return;
		} else {
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(-7, 5, point, point);
			bb.setTypeState(Types.GUARD_UP, true);
			bb.setTypeState(Types.S_WAIT, true);
			return;
		}
	}

	private void attackUpper() {
		if (!isPossible(Effects.UPPER)) {
			return;
		} else {
			attackMode(Types.SORA, Types.RIKU, 8);
			return;
		}
	}

	private void attackDowner() {
		if (!isPossible(Effects.CHOP)) {
			return;
		} else {
			attackMode(Types.RIKU, Types.SORA, 9);
			return;
		}
	}

	private void attackDance() {
		if (!isPossible(Effects.REFRESH)) {
			return;
		} else {
			V.S(3, 0, bb.x, bb.y, 0);
			Point point = new Point(bb.x, bb.y);
			uw.setAnime(1, -1, point, point);
			return;
		}
	}

	private boolean attackMain() {
		if (isEffect(Effects.NO_ATTACK)) {
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

	public Body getBa() {
		return ba;
	}

	public Body getBb() {
		return bb;
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
	Set<Effects> ECT;
	static final int HIT = 16;
	static final int DHIT = 32;
	int meichu;
	int face;
}
