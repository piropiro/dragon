package dragon3.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import mine.MineUtils;
import mine.event.SleepManager;
import mine.paint.UnitMap;
import dragon3.Rewalk;
import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.attack.calc.Damage;
import dragon3.attack.calc.DamageRate;
import dragon3.attack.calc.HitRate;
import dragon3.attack.range.BreathRange;
import dragon3.attack.range.DonutRange;
import dragon3.attack.range.LaserRange;
import dragon3.attack.range.NormalRange;
import dragon3.attack.range.Range;
import dragon3.attack.range.SquareRange;
import dragon3.attack.special.SpecialEffectManager;
import dragon3.attack.target.AllTarget;
import dragon3.attack.target.BreathTarget;
import dragon3.attack.target.LaserTarget;
import dragon3.attack.target.PointTarget;
import dragon3.attack.target.SpreadTarget;
import dragon3.attack.target.Target;
import dragon3.bean.AnimeData;
import dragon3.bean.WazaData;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Page;
import dragon3.common.constant.TargetType;
import dragon3.common.constant.Types;
import dragon3.common.util.Luck;
import dragon3.manage.AttackManager;
import dragon3.panel.PanelManager;

public class AttackManagerImpl implements AttackManager {

	private AttackImpl attack;

	private AnimeManager anime;
	private SleepManager sm;
	private PanelManager pm;
	private UnitWorks uw;
	private UnitMap map;
	private SpecialEffectManager se;

	private List<Body> enemy;

	private Body ba;
	private Body bb;

	private Body bestEnemy;
	private int bestDamage;

	private Target target;
	private Range range;



	private int meichu;

	private WazaData waza;

	/*** Constructer *********************************************************/

	public AttackManagerImpl(UnitWorks uw, UnitMap map, Body ba, String wazaId) {

		this.uw = uw;
		this.map = map;
		this.anime = uw.getAnimeManager();
		this.sm = uw.getSleepManager();
		this.pm = uw.getPanelManager();
		this.ba = ba;
		this.waza = (WazaData)Statics.wazaList.getData(wazaId);

		se = SpecialEffectManager.getInstance(map);
		this.attack = new AttackImpl(uw.getAnimeManager(), map, ba, waza);

		bb = null;
		enemy = null;

		setTargetType(waza.getTargetType(), ba.getX(), ba.getY());

		range.paint(map, Page.P21, ba.getX(), ba.getY());

		getAttackUnit(uw.getCharaList());
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#show()
	 */

	public void show() {
		map.copyPage(Page.P21, Page.P10);
		map.setData(Page.P30, ba.getX(), ba.getY(), 0);
	}

	/*** Set Range and Target ************************************************/

	private void setTargetType(String targetType, int x, int y) {
		if (targetType.equals(TargetType.TARGET_SINGLE_1)) {
			range = new NormalRange(1);
			target = new PointTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_SINGLE_2)) {
			range = new NormalRange(2);
			target = new PointTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_SINGLE_3)) {
			range = new NormalRange(3);
			target = new PointTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_ALL_1)) {
			range = new NormalRange(1);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_ALL_2)) {
			range = new NormalRange(2);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_ALL_3)) {
			range = new NormalRange(3);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_ALL_4)) {
			range = new NormalRange(4);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_LONG_2)) {
			range = new DonutRange(2, 1);
			target = new PointTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_LONG_3)) {
			range = new DonutRange(3, 1);
			target = new PointTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_RING_2)) {
			range = new DonutRange(2, 1);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_RING_3)) {
			range = new DonutRange(3, 1);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_LASER_2)) {
			range = new LaserRange(2, 0);
			target = new LaserTarget(x, y, 2, 0);
		} else if (targetType.equals(TargetType.TARGET_LASER_3)) {
			range = new LaserRange(3, 0);
			target = new LaserTarget(x, y, 3, 0);
		} else if (targetType.equals(TargetType.TARGET_CROSS_2)) {
			range = new LaserRange(2, 0);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_CROSS_3)) {
			range = new LaserRange(3, 0);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_BREATH)) {
			range = new BreathRange(2, 0);
			target = new BreathTarget(x, y, 2, 0);
		} else if (targetType.equals(TargetType.TARGET_HLINE)) {
			range = new BreathRange(2, 1);
			target = new BreathTarget(x, y, 2, 1);
		} else if (targetType.equals(TargetType.TARGET_SQUARE_1)) {
			range = new SquareRange(1);
			target = new AllTarget(x, y);
		} else if (targetType.equals(TargetType.TARGET_SPREAD)) {
			range = new DonutRange(2, 1);
			target = new SpreadTarget(x, y, 1);
		}
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#isCounterable(dragon3.common.Body, boolean)
	 */

	// 2-1 Counter Range

	public boolean isCounterable(Body b, boolean flag) {
		if (ba.isType(Types.SLEEP))
			return false;
		if (flag && !attack.hasEffect(Effects.COUNTER_ONLY))
			return false;
		if (!flag && !attack.hasEffect(Effects.COUNTER_ABLE))
			return false;

		if (map.getData(Page.P21, b.getX(), b.getY()) == 0)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#isAlive(boolean)
	 */

	// 2-1 Attack Range
	// 1-1 Search Enemy

	public boolean isAlive(boolean enemyFlag) {
		if (attack.hasEffect(Effects.COUNTER_ONLY))
			return false;
		if (attack.hasEffect(Effects.TAME) && Rewalk.isWalked(ba))
			return false;

		if (!enemyFlag) {
			int width = map.getMapWidth();
			int height = map.getMapHeight();
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++)
					if (map.getData(Page.P21, x, y) != 0)
						if (map.getData(Page.P11, x, y) != 0)
							return true;
		} else {
			if (bestEnemy != null)
				return true;
		}
		return false;
	}



	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#setTarget(int, int)
	 */

	// 4-0 Over Frame

	public void setTarget(int x, int y) {
		target.paint(map, Page.P40, x, y);
		map.setData(Page.P40, ba.getX(), ba.getY(), 0);
		map.copyPage(Page.P40, Page.P41);
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#selectTarget(dragon3.common.Body)
	 */

	public void selectTarget(Body bb_) {
		this.bb = bb_;
		if (bb == null) {
			pm.closeHp();
		} else {
			if (enemy == null) {
				enemy = new Vector<Body>();
				enemy.add(bb);
			}
			meichu = HitRate.calcPredict(ba, bb, attack.getEffectSet());
			pm.displayHp(
				bb,
				ba,
				Damage.calc(waza.getDamageType(), map, ba, bb, attack.getEffectSet())
					* DamageRate.calc(map, ba, bb, attack.getEffectSet())
					/ 100,
				attack.isHit());
		}
	}

	/*** Deside Enemy *************************************************/

	// 4-0 Over Frame

	public boolean searchTargets() {
		if (attack.hasEffect(Effects.COUNTER_ABLE) && bb == null)
			return false;

		enemy = new ArrayList<Body>();
		for (Body b: uw.getCharaList()) {
			if (!isTarget(b))
				continue;
			if (map.getData(Page.P40, b.getX(), b.getY()) != 0) {
				enemy.add(b);
			}
		}
		return (enemy.size() != 0);
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#isTarget(dragon3.common.Body)
	 */

	public boolean isTarget(Body b) {
		if (b == ba)
			return false;
		if (!b.isAlive())
			return false;

		if (attack.hasEffect(Effects.NO_ATTACK)) {
			if (attack.hasEffect(Effects.HEAL) && b.getColor() != ba.getColor())
				return false;
			if (!attack.hasEffect(Effects.HEAL) && b.getColor() == ba.getColor())
				return false;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.CRITICAL))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.DEATH))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.SLEEP))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.CHARM))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.POISON))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.WET))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.ATTACK_UP))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.GUARD_UP))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.UPPER))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.CHOP))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.REFRESH))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.OIL))
				return true;
			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.REGENE))
				return true;

			return false;
		}

		if (b.isType(Types.CHARM))
			return true;
		int damage = Damage.calc(waza.getDamageType(), map, ba, bb, attack.getEffectSet());
		if (damage == 0) {
			if (attack.hasEffect(Effects.HEAL))
				return (b.getColor() == ba.getColor());
			else
				return (b.getColor() != ba.getColor());
		}
		if (damage < 0) {
			if (b.getHp() >= b.getHpMax())
				return false;
		}
		boolean flag = true;
		if (b.getColor() == ba.getColor())
			flag = !flag;
		if (ba.isType(Types.CHARM))
			flag = !flag;
		if (damage < 0)
			flag = !flag;
		return flag;
	}




	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#getBestTarget()
	 */

	public Body getBestTarget() {
		return bestEnemy;
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#getBestDamage()
	 */
	public int getBestDamage() {
		return bestDamage;
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#getAttacker()
	 */
	public Body getAttacker() {
		return ba;
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#getReceiver()
	 */
	public Body getReceiver() {
		return bb;
	}

	/*** Anime *********************************************/

	private void singleAnime() {
		AnimeData data = anime.getData(waza.getAnimeId());
		if (data.getType().equals(AnimeManager.TYPE_SINGLE)) {
			anime.singleAnime(data, bb.getX(), bb.getY());
		} else if (data.getType().equals(AnimeManager.TYPE_SINGLE_ARROW)) {
			anime.singleArrowAnime(data, ba.getX(), ba.getY(), bb.getX(), bb.getY());
		}
	}

	private void allAnime() {
		AnimeData data = anime.getData(waza.getAnimeId());
		if (data.getType().equals(AnimeManager.TYPE_ALL)) {
			anime.allAnime(data);
		} else if (data.getType().equals(AnimeManager.TYPE_ROTATE)) {
			anime.rotateAnime(data, ba.getX(), ba.getY(), target.getX(), target.getY());
		} else if (data.getType().equals(AnimeManager.TYPE_SOME_ARROW)) {
			anime.someArrowAnime(data, ba.getX(), ba.getY());
		} else if (data.getType().equals(AnimeManager.TYPE_LASER_ARROW_2)) {
			laserAnime(data, 2);
		} else if (data.getType().equals(AnimeManager.TYPE_LASER_ARROW_3)) {
			laserAnime(data, 3);
		}
	}

	private void laserAnime(AnimeData data, int length) {
		Point goal = new Point(target.getX(), target.getY());
		switch (target.getFace(target.getX(), target.getY())) {
			case Target.WEST :
				goal.x = Math.max(0, ba.getX() - length);
				break;
			case Target.EAST :
				goal.x = Math.min(19, ba.getX() + length);
				break;
			case Target.NORTH :
				goal.y = Math.max(0, ba.getY() - length);
				break;
			case Target.SOUTH :
				goal.y = Math.min(14, ba.getY() + length);
				break;
		}
		anime.singleArrowAnime(data, ba.getX(), ba.getY(), goal.x, goal.y);
	}

	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#getAttackUnit(java.util.List)
	 */

	// 2-1 Under Frame
	// 1-1 Search Enemy

	public void getAttackUnit(List<Body> charaList) {
		map.clear(Page.P11, 0);
		bestDamage = -1;
		for (Body b: charaList) {
			if (!isTarget(b))
				continue;
			target.setSearchField(map, Page.P11, b.getX(), b.getY());
			if (map.getData(Page.P21, b.getX(), b.getY()) != 0) {
				map.setData(Page.P21, b.getX(), b.getY(), 3);
				int d =
					Damage.calc(waza.getDamageType(), map, ba, b, attack.getEffectSet()) * DamageRate.calc(map, ba, b, attack.getEffectSet());

				if (!b.isType(Types.CHARM_LOCK) && se.isEffective(ba, bb, attack.getEffectSet(), Effects.CHARM))
					d += b.getHp() / 2;

				if (!b.isType(Types.SLEEP_LOCK) && se.isEffective(ba, bb, attack.getEffectSet(), Effects.SLEEP))
					d += b.getHp() / 2;

				if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.DEATH))
					d += b.getHp();
				if (b.getColor() == ba.getColor())
					d = -d;
				if (ba.isType(Types.CHARM))
					d = -d;
				if (bestDamage < d) {
					bestDamage = d;
					bestEnemy = b;
				}
			}
		}
	}


	/* (non-Javadoc)
	 * @see dragon3.impl.AttackManager#attack()
	 */

	public void attack() {

		allAnime();
		if (bb != null) {
			enemy.remove(bb);
			enemy.add(0, bb);
		} else {
			bb = (Body) enemy.get(0);
			pm.displayAttack(attack, null);
		}

		int i = 0;
		for (Body b : enemy) {
			bb = b;
			meichu = HitRate.calcReal(ba, bb, attack.getEffectSet());
			pm.displayHp(
				bb,
				ba,
				Damage.calc(waza.getDamageType(), map, ba, bb, attack.getEffectSet())
					* DamageRate.calc(map, ba, bb, attack.getEffectSet())
					/ 100,
				attack.isHit());
			if (i > 0)
				pm.displayAttack(attack, null);

			if (map.getData(Page.P30, bb.getX(), bb.getY()) == 2) {
				map.setData(Page.P30, bb.getX(), bb.getY(), 0);
			}

			if (attackMiss()) {
				continue;
			}

			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.CRITICAL) && Luck.rnd(1, ba) != 1) {
				pm.repaintData();
				pm.damageHp(bb, bb.getHpMax());
				se.execute(ba, bb, anime, attack.getEffectSet(), Effects.CRITICAL);
				pm.repaintData();
				sm.sleep(200);
				pm.animeHp();
				bb.setHp(0);
				uw.dead(ba, bb);
				anime.dispose();
				continue;
			}

			if (se.isEffective(ba, bb, attack.getEffectSet(), Effects.DEATH)) {
				pm.repaintData();
				pm.damageHp(bb, bb.getHpMax());
				se.execute(ba, bb, anime, attack.getEffectSet(), Effects.DEATH);
				pm.repaintData();
				sm.sleep(200);
				pm.animeHp();
				bb.setHp(0);
				uw.dead(ba, bb);
				anime.dispose();
				continue;
			}

			if (attackHit()) {
				continue;
			}

			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.SLEEP);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.CHARM);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.POISON);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.REGENE);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.WET);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.OIL);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.ATTACK_UP);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.GUARD_UP);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.UPPER);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.CHOP);
			se.execute(ba, bb, anime, attack.getEffectSet(), Effects.REFRESH);
			sm.sleep(300);
		}
	}

	/*** Hit *********************************/

	private boolean attackHit() {
		if (attack.hasEffect(Effects.NO_ATTACK)) {
			bb.setStore(bb.getStore() + meichu);
			bb.setStore(bb.getStore() % HitRate.SINGLE_HIT);
			meichu = 0;
			pm.repaintData();
			singleAnime();
			return false;
		}

		int damages = 0;
		while (meichu > 0) {
			bb.setStore(bb.getStore() + 1);
			meichu--;
			if (bb.getStore() >= HitRate.SINGLE_HIT) {
				pm.repaintData();
				singleAnime();
				int damage =
					Damage.calc(waza.getDamageType(), map, ba, bb, attack.getEffectSet())
						* DamageRate.calc(map, ba, bb, attack.getEffectSet())
						/ 100;
				if (damage >= 0)
					damages += Math.max(1, damage + Luck.rnd(1, ba));
				else
					damages += Math.min(-1, damage - Luck.rnd(1, ba));
				pm.damageHp(bb, damages);
				anime.numberAnime(damage, bb.getX(), bb.getY());
				bb.setStore(bb.getStore() - HitRate.SINGLE_HIT);
			}
		}
		pm.repaintData();
		sm.sleep(200);
		pm.animeHp();
		bb.setHp(MineUtils.mid(0, bb.getHp() - damages, bb.getHpMax()));
		if (bb.getHp() <= 0) {
			uw.dead(ba, bb);
			anime.dispose();
			sm.sleep(300);
			return true;
		}
		return false;
	}

	/*** Miss *********************************/

	private boolean attackMiss() {
		if (meichu + bb.getStore() >= HitRate.SINGLE_HIT)
			return false;

		bb.setStore(bb.getStore() + meichu);
		meichu = 0;
		pm.repaintData();
		singleAnime();
		pm.damageHp(bb, 0);
		anime.dropText(AnimeManager.TEXT_MISS, bb.getX(), bb.getY());
		sm.sleep(500);
		return true;
	}

	public AttackImpl getAttack() {
		return attack;
	}
}
