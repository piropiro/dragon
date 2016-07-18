package dragon3.impl;

import java.util.List;

import dragon3.DragonBuster;
import dragon3.FrameWorks;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.MoveType;
import dragon3.common.constant.Page;
import dragon3.common.constant.Types;
import dragon3.common.util.MoveUtils;
import dragon3.cpu.EnemyTurn;
import dragon3.manage.TurnManager;
import dragon3.paint.PaintUtils;
import mine.paint.UnitMap;

public class TurnManagerImpl implements TurnManager {

	private int turn;
	private List<Body> charaList;
	private UnitWorks uw;

	private UnitMap map;
	private AnimeManager anime;
	private FrameWorks fw;

	/*** Constructer ************************************/

	public TurnManagerImpl(UnitWorks uw) {
		this.uw = uw;
		this.anime = uw.getAnimeManager();
		this.charaList = uw.getCharaList();
		this.map = uw.getUnitMap();
		this.fw = uw.getFrameWorks();
	}

	/*** Reset *****************************************/

	public void reset() {
		turn = 0;
	}

	/*** Get Data ***************************************/

	public int getTurn() {
		return turn;
	}

	/*** Mens ********************************/

	public void playerTurnStart() {
		uw.getSaveManager().getSaveData().countTurn();
		turn++;
		turnChange(true);
		PaintUtils.setBasicPaint(uw);
		fw.setMenu(DragonBuster.T_PLAYER);
		uw.getMapWorks().repaint();
	}

	/*** Enemy ******************************/

	public void enemyTurnStart() {
		turnChange(false);
		uw.limitOver();
		EnemyTurn et = new EnemyTurn(uw);
		PaintUtils.setWaitPaint(uw);
		fw.setMenu(DragonBuster.T_ENEMY);
		et.start(turn);
	}

	/*** Change *****************************/

	private void turnChange(boolean flag) {
		map.clear(Page.P10, 0);
		map.clear(Page.P30, 0);
		map.clear(Page.P40, 0);
		for (Body b : charaList) {
			if (!b.isAlive())
				continue;
			b.removeType(Types.SORA);
			b.removeType(Types.RIKU);
			setTikei(b, flag);
			setPoison(b, flag);
			setRegene(b, flag);
			setBersek(b, flag);

			setStatus(b, Types.ATTACK_UP, Types.ATTACK_UP_LOCK);
			setStatus(b, Types.GUARD_UP, Types.GUARD_UP_LOCK);
			setStatus(b, Types.WET, Types.WET_LOCK);
			setStatus(b, Types.OIL, Types.OIL_LOCK);
			setStatus(b, Types.CHARM, Types.CHARM_LOCK);
			setStatus(b, Types.SLEEP, Types.SLEEP_LOCK);

			paintStatus(b);
		}
	}

	private void paintStatus(Body b) {
		if (b.isType(Types.SLEEP)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_SLEEP);
		}
		if (b.isType(Types.POISON)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_POISON);
		}
		if (b.isType(Types.WET)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_WET);
		}
		if (b.isType(Types.OIL)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_OIL);
		}
		if (b.isType(Types.ATTACK_UP)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_ATTACK_UP);
		}
		if (b.isType(Types.GUARD_UP)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_GUARD_UP);
		}
		if (b.isType(Types.SORA)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_SORA);
		}
		if (b.isType(Types.RIKU)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_RIKU);
		}
		if (b.isType(Types.BERSERK)) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_BERSERK);
		}
	}

	private void setStatus(Body b, String type, String lock) {
		if (b.isType(lock)) {
			b.removeType(lock);
		} else {
			b.removeType(type);
		}
	}

	/*** Tikei ****************************/

	private void setTikei(Body b, boolean flag) {
		if (MoveUtils.getTikei(map, b) == MoveUtils.T_SKY)
			return;

		switch (map.getData(Page.P00, b.getX(), b.getY())) {
			case MoveUtils.C_BLUE :
				blueCrystal(b, flag);
				break;
			case MoveUtils.AQUA :
			case MoveUtils.BLUE :
				waterPanel(b, flag);
				break;
			case MoveUtils.POISONP :
				poisonPanel(b, flag);
				break;
			case MoveUtils.OILP :
				oilPanel(b, flag);
				break;
			case MoveUtils.FIREP :
				firePanel(b, flag);
				break;

		}
	}

	private void blueCrystal(Body b, boolean flag) {
		if (GameColors.isPlayer(b) != flag)
			return;
		if (b.getHp() == b.getHpMax() && !b.isType(Types.POISON))
			return;
		b.removeType(Types.POISON);
		anime.systemAnime(AnimeManager.ID_REFRESH, b.getX(), b.getY());
		b.setHp(b.getHp() + Math.max(2, b.getHpMax() / 4));
		b.setHp(Math.min(b.getHp(), b.getHpMax()));
	}

	/**
	 * 水パネル
	 *
	 * @param b
	 * @param flag
	 */
	private void waterPanel(Body b, boolean flag) {
		if (b.isType(Types.ANTI_ALL))
			return;
		if (b.isType(Types.SWIM_ABLE))
			return;
		if (b.getMoveType().equals(MoveType.SWIM))
			return;
		if (b.getMoveType().equals(MoveType.TWIN))
			return;
		b.addType(Types.WET);
		b.addType(Types.WET_LOCK);
		return;
	}


	/**
	 * 毒パネル
	 *
	 * @param b
	 * @param flag
	 */
	private void poisonPanel(Body b, boolean flag) {
		if (b.isType(Types.ANTI_ALL))
			return;
		if (b.isType(Types.ANTI_POISON))
			return;

		b.addType(Types.POISON);
	}

	/**
	 * オイルパネル
	 *
	 * @param b
	 * @param flag
	 */
	private void oilPanel(Body b, boolean flag) {
		if (b.isType(Types.ANTI_ALL))
			return;
		b.addType(Types.OIL);
		b.addType(Types.OIL_LOCK);
	}

	/**
	 * 溶岩パネル
	 *
	 * @param b
	 * @param flag
	 */
	private void firePanel(Body b, boolean flag) {
		if (GameColors.isPlayer(b) != flag)
			return;
		if (b.isType(Types.FIRE_0))
			return;

		int rate = 4;
		if (b.isType(Types.FIRE_200))
			rate *= 2;
		if (b.isType(Types.OIL))
			rate *= 2;
		if (b.isType(Types.FIRE_50))
			rate /= 2;
		anime.systemAnime(AnimeManager.ID_FIRE, b.getX(), b.getY());
		b.setHp(b.getHp() - Math.max(2, b.getHpMax() * rate / 16));
		b.setHp(Math.max(1, b.getHp()));
	}

	/*** Poison ******************************/

	private void setPoison(Body b, boolean flag) {
		if (!b.isType(Types.POISON))
			return;
		if (GameColors.isPlayer(b) != flag || b.getHp() == 1) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_POISON);
		} else {
			anime.statusAnime(AnimeManager.STATUS_POISON, b.getX(), b.getY());
			b.setHp(b.getHp() - Math.max(1, b.getHpMax() / 8));
			b.setHp(Math.max(1, b.getHp()));
		}
		if (b.getHp() == 1)
			b.removeType(Types.POISON);
	}

	/*** Bersek ******************************/

	private void setBersek(Body b, boolean flag) {
		if (!b.isType(Types.BERSERK))
			return;
		if (GameColors.isPlayer(b) != flag) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_BERSERK);
		} else {
			b.setStr(Math.max(0, b.getStr() - 1));
			b.setDef(Math.max(0, b.getDef() - 1));
			b.setMst(Math.max(0, b.getMst() - 1));
			b.setMdf(Math.max(0, b.getMdf() - 1));
			b.setHit(Math.max(0, b.getHit() - 1));
			b.setMis(Math.max(0, b.getMis() - 1));
			anime.statusAnime(AnimeManager.STATUS_BERSERK, b.getX(), b.getY());
		}
	}

	/*** Heal ********************************/

	private void setRegene(Body b, boolean flag) {
		if (!b.isType(Types.REGENE))
			return;
		if (GameColors.isPlayer(b) != flag || b.getHp() == b.getHpMax()) {
			map.setData(Page.P50, b.getX(), b.getY(), AnimeManager.STATUS_REGENE);
		} else if (b.getMst() > 0) {
			anime.statusAnime(AnimeManager.STATUS_REGENE, b.getX(), b.getY());
			b.setMst(Math.max(0, b.getMst() - 2));
			b.setHp(Math.min(b.getHpMax(), b.getHp() + b.getHpMax() / 2));
		} else {
			b.removeType(Types.REGENE);
		}
	}

}
