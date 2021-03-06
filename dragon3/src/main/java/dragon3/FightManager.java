package dragon3;

import java.awt.Point;

import mine.event.SleepManager;
import mine.paint.UnitMap;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.Types;
import dragon3.impl.AttackManagerImpl;
import dragon3.manage.AttackManager;
import dragon3.map.MapWorks;
import dragon3.paint.PaintUtils;
import dragon3.paint.TalkPaint;
import dragon3.panel.PanelManager;

public class FightManager {

	private UnitWorks uw;
	private MapWorks mw;
	private UnitMap map;
	private PanelManager pm;
	private SleepManager sm;
	private AnimeManager anime;

	private Body ba;
	private Body bb;
	private String[] baWazaList;
	private AttackManager attack;
	private AttackManager counter;
	private int n;

	/*** Constructer ***************************************/

	public FightManager(UnitWorks uw, Body ba) {
		super();
		this.uw = uw;
		this.ba = ba;
		mw = uw.getMapWorks();
		map = uw.getUnitMap();
		pm = uw.getPanelManager();
		sm = uw.getSleepManager();
		anime = uw.getAnimeManager();
		baWazaList = ba.getWazaList();
		n = -1;
		setHelp(false);
	}

	private void setHelp(boolean flag) {
		if (!GameColors.isPlayer(ba))
			return;
		if (flag) {
			pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_FIGHT1], GameColors.GREEN);
		} else {
			pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_FIGHT2], GameColors.BLUE);
		}
	}

	/*** Select *********************************************/

	public void nextSelect() {
		while (++n < baWazaList.length) {
			if (select(n, false)) {
				PaintUtils.setAttackPaint(uw, this, ba);
				return;
			}
		}
		TalkPaint tp = new TalkPaint(uw, ba);
		if (tp.isEnable()) {
			mw.setPaintListener(tp);
			tp.show();
			mw.repaint();
		} else {
			PaintUtils.setEndPaint(uw, ba);
			mw.repaint();
		}
		pm.closeSmall();
	}

	public boolean select(int i, boolean enemyFlag) {
		if (baWazaList[i].equals("none"))
			return false;
		if (i > 0 && ba.isType(Types.WET))
			return false;
		
		attack = new AttackManagerImpl(uw, map, ba, baWazaList[i]);
		if (attack.isAlive(enemyFlag)) {
			attack.show();
			pm.selectHp(true);
			pm.displaySmall(attack.getAttack().getLabel(), attack.getAttack().getLabelColor(), ba);
			mw.repaint();
			return true;
		}
		return false;
	}

	public boolean enemySelect() {
		int n = -1;
		int dmax = -1;
		for (int i = 0; i < baWazaList.length; i++) {
			if (i > 0 && ba.isType(Types.WET))
				break;
			AttackManager ab = new AttackManagerImpl(uw, map, ba, baWazaList[i]);
			if (ab.isAlive(true)) {
				if (dmax < ab.getBestDamage()) {
					dmax = ab.getBestDamage();
					n = i;
				}
			}
		}
		if (n != -1) {
			return select(n, true);
		} else {
			return false;
		}
	}

	/*** Counter ***************************************/

	private void setCounter(Body bb) {
		this.bb = bb;
		counter = null;
		if (!attack.getAttack().hasEffect(Effects.COUNTER_ABLE))
			return;

		String[] bbWazaList = bb.getWazaList();
		for (int i = bbWazaList.length - 1; i >= 0; i--) {
			if (bbWazaList[i].equals("none"))
				continue;
			AttackManager ab = new AttackManagerImpl(uw, map, bb, bbWazaList[i]);
			if (ab.isCounterable(ba, true)) {
				counter = ab;
				break;
			}
		}
		for (int i = 0; i < bbWazaList.length; i++) {
			if (counter != null)
				break;
			if (bbWazaList[i].equals("none"))
				continue;
			AttackManager ab = new AttackManagerImpl(uw, map, bb, bbWazaList[i]);
			if (ab.isCounterable(ba, false)) {
				counter = ab;
				break;
			}
		}
		if (counter != null) {
			pm.selectHp(false);
			counter.selectTarget(ba);
			pm.selectHp(true);
		}
	}

	public boolean searchTargets() {
		return attack.searchTargets();
	}

	/*** Target ***********************************************************/

	public void setTarget(Point p) {
		Point ps = mw.getWaku();
		mw.wakuMove(p.x, p.y);
		if (map.getData(Page.P10, ps.x, ps.y) == 0) {
			if (map.getData(Page.P10, p.x, p.y) == 0) {
				mw.wakuPaint(true);
			} else {
				map.clear(Page.P40, 0);
				mw.wakuPaint(false);
				attack.setTarget(p.x, p.y);
				mw.repaint();
			}
		} else {
			if (map.getData(Page.P10, p.x, p.y) == 0) {
				map.clear(Page.P40, 0);
				mw.wakuPaint(false);
				mw.repaint();
			} else {
				map.clear(Page.P40, 0);
				mw.wakuPaint(false);
				attack.setTarget(p.x, p.y);
				mw.repaint();
			}
		}

		bb = uw.search(p.x, p.y);
		attack.selectTarget(bb);
		if (uw.getSaveManager().isFirst()) {
			Tutorial.setHelp(ba, bb, n, uw);
		} else {
			setHelp(bb != null);
		}

		if (bb != null && bb != ba) {
			if (!attack.isTarget(bb))
				return;
			setCounter(bb);
			pm.displayAttack(attack.getAttack(), counter.getAttack());
		} else {
			pm.closeData();
			attack.selectTarget(null);
		}
	}


	/*** Control Enemy **************************************************/

	public void enemy() {
		bb = attack.getBestTarget();
		attack.setTarget(bb.getX(), bb.getY());
		auto();
	}

	private void auto() {
		attack.searchTargets();
		attack.selectTarget(bb);
		pm.displaySmall(attack.getAttack().getLabel(), attack.getAttack().getLabelColor(), ba);
		map.clear(Page.P40, 0);
		mw.repaint();
		setCounter(bb);
		if (bb != null)
			pm.displayAttack(attack.getAttack(), counter.getAttack());
		sm.sleep(300);
		attack();
		mw.repaint();
	}

	/*** Counter ******************************************************/

	private void counter() {
		if (counter == null)
			return;
		if (bb == null)
			return;
		if (!bb.isAlive())
			return;
		if (bb.isType(Types.SLEEP))
			return;
		pm.displaySmall(counter.getAttack().getLabel(), counter.getAttack().getLabelColor(), bb);
		pm.selectHp(false);
		counter.attack();
		pm.selectHp(true);
	}

	/*** Attack **************************************************************/

	public void attack() {
		attack.attack();
		counter();
		pm.closeHp();
		anime.dispose();
		pm.closeSmall();
		pm.closeData();
		uw.levelup(ba);
		uw.levelup(bb);
		uw.message();
		map.clear(Page.P10, 0);
		map.clear(Page.P40, 0);
	}
}
