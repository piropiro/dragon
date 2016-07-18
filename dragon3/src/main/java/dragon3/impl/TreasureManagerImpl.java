package dragon3.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import mine.event.SleepManager;
import mine.paint.UnitMap;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.BodyKind;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.util.MoveUtils;
import dragon3.manage.TreasureManager;
import dragon3.panel.PanelManager;


public class TreasureManagerImpl implements TreasureManager {

	private static final int MAX = 30;

	private Body item;
	private Body[] treasure;
	private Body[] holder;
	private int[] status;


	static final int S_NONE = 0;
	static final int S_ENEMY = 1;
	static final int S_GROUND = 2;
	static final int S_BOX = 3;
	static final int S_CLEAR = 4;
	static final int S_HAVE = 5;

	private List<Body> sources;
	private List<Integer> comments;


	private UnitWorks uw;
	private UnitMap map;
	private AnimeManager anime;
	private SleepManager sm;
	private PanelManager pm;

	/*** Constructer *********************************************/

	public TreasureManagerImpl(UnitWorks uw, List<Body> Charas) {
		this.uw = uw;
		this.map = uw.getUnitMap();
		this.anime = uw.getAnimeManager();
		this.sm = uw.getSleepManager();
		this.pm = uw.getPanelManager();
		item = null;
		treasure = new Body[MAX];
		holder = new Body[MAX];
		status = new int[MAX];
		sources = new ArrayList<>();
		comments = new ArrayList<>();
		Point bc = uw.getCrystal(GameColors.BLUE);
		int n = 0;
		for (Body b : Charas) {
			if (uw.have(b)) {
				b.setHp(0);
				continue;
			} else if (b.getX() == bc.x && b.getY() == bc.y) {
				item = b;
				b.setHp(0);
				status[n] = S_CLEAR;
				n++;
				continue;
			} else {
				if (b.isKind(BodyKind.CHARA)) {
					continue;
				} else {
					treasure[n] = b;
					b.setHp(0);
					n++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			Body tre = treasure[i];
			if (tre == null)
				continue;
			if (tre.getGoalX() == 0 && tre.getGoalY() == 0) {
				continue;
			}
			status[i] = S_GROUND;
			for (Body b : Charas) {
				if (b == tre)
					continue;
				if (!b.isAlive())
					continue;
				if (b.getX() != tre.getGoalX())
					continue;
				if (b.getY() != tre.getGoalY())
					continue;
				holder[i] = b;
				status[i] = S_ENEMY;
				continue;
			}
		}
		for (int i = 0; i < n; i++) {
			if (holder[i] != null)
				continue;
			Body tre = treasure[i];
			if (tre == null)
				continue;
			if (tre.getLimitTurn() == 0)
				continue;
			map.setData(Page.P00, tre.getX(), tre.getY(), MoveUtils.CLOSE_BOX);
			status[i] = S_BOX;
		}

	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getLimitTurn(java.awt.Point)
	 */

	public int getLimitTurn(Point p) {
		for (int i = 0; i < treasure.length; i++) {
			Body tre = treasure[i];
			if (tre == null)
				continue;
			if (holder[i] != null)
				continue;
			if (p.x != tre.getX())
				continue;
			if (p.y != tre.getY())
				continue;
			return tre.getLimitTurn();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getLimitTurn()
	 */
	public int getLimitTurn() {
		if (item == null)
			return 0;
		return item.getLimitTurn();
	}
	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getCount()
	 */
	public String getCount() {
		String count = "";

		for (int i = 0; i < treasure.length; i++) {
			switch (status[i]) {
				case S_NONE :
					break;
				case S_ENEMY :
					if (holder[i].isAlive() && isAlive(treasure[i])) {
						count = count + Texts.kigo[0];
					} else {
						count = count + Texts.kigo[1];
					}
					break;
				case S_GROUND :
					if (isAlive(treasure[i])) {
						count = count + Texts.kigo[2];
					} else {
						count = count + Texts.kigo[3];
					}
					break;
				case S_BOX :
					if (isAlive(treasure[i])) {
						count = count + Texts.kigo[4];
					} else {
						count = count + Texts.kigo[5];
					}
					break;
				case S_CLEAR :
					if (isAlive(item)) {
						count = count + Texts.kigo[6];
					} else {
						count = count + Texts.kigo[7];
					}
					break;
				case S_HAVE :
					count = count + Texts.kigo[8];
					break;
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getSources()
	 */
	public List<Body> getSources() {
		return sources;
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#add(dragon3.common.Body)
	 */
	public void add(Body ba) {
		sources.add(ba);
		uw.getSaveManager().getSaveData().countItem();
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#limitOver()
	 */

	public void limitOver() {
		for (int i = 0; i < treasure.length; i++) {
			Body tre = treasure[i];
			if (tre == null)
				continue;
			if (tre.getLimitTurn() != uw.getTurnManager().getTurn())
				continue;
			if (map.getData(Page.P00, tre.getX(), tre.getY()) != MoveUtils.CLOSE_BOX)
				continue;
			map.setData(Page.P00, tre.getX(), tre.getY(), MoveUtils.BROKEN_BOX);
			anime.statusAnime(AnimeManager.STATUS_HAMMER, tre.getX(), tre.getY());
			sm.sleep(300);
			map.setData(Page.P50, tre.getX(), tre.getY(), 0);
		}
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#addMember(dragon3.common.Body)
	 */

	public void addMember(Body ba) {
		add(ba);
		getTreasure(ba, true);
		message();
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getTreasure(dragon3.common.Body, boolean)
	 */

	// flag true  Nakama
	//      false Kill

	public void getTreasure(Body ba, boolean flag) {
		for (int i = 0; i < holder.length; i++) {
			if (ba == holder[i]) {
				Body tre = treasure[i];
				if (!isAlive(tre))
					continue;
				if (tre.getLimitTurn() == 0 && !flag)
					continue;
				add(tre);
				comments.add(new Integer(i));
				status[i] = S_HAVE;
			}
		}
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#searchTreasure(dragon3.common.Body)
	 */

	public void searchTreasure(Body ba) {
		for (int i = 0; i < treasure.length; i++) {
			Body tre = treasure[i];
			if (!isAlive(tre))
				continue;
			if (holder[i] != null)
				continue;
			if (tre.getX() != ba.getX())
				continue;
			if (tre.getY() != ba.getY())
				continue;
			treasure[i] = null;
			status[i] = S_HAVE;
			message(ba, tre);
			add(tre);
			if (map.getData(Page.P00, ba.getX(), ba.getY()) == MoveUtils.CLOSE_BOX)
				map.setData(Page.P00, ba.getX(), ba.getY(), MoveUtils.OPEN_BOX);
		}
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#getClearItem()
	 */

	public void getClearItem() {
		if (!isAlive(item))
			return;
		message(null, item);
		add(item);
	}

	/*** Alive *************************************/

	private boolean isAlive(Body b) {
		if (b == null)
			return false;
		if (b.getLimitTurn() == 0)
			return true;
		if (b.getLimitTurn() < uw.getTurnManager().getTurn())
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#message()
	 */

	public void message() {
		for (Integer n : comments) {
			Body ba = holder[n.intValue()];
			Body tre = treasure[n.intValue()];
			pm.addMessage(ba.getName() + Texts.ha);
			pm.addMessage(Texts.treasure1);
			pm.addMessage(tre.getName() + Texts.wo);
			pm.addMessage(Texts.treasure2);
			pm.closeData();
			pm.startMessage(tre);
		}
		comments.clear();
	}

	/* (non-Javadoc)
	 * @see dragon3.TreasureManager#message(dragon3.common.Body, dragon3.common.Body)
	 */
	public void message(Body ba, Body tre) {
		if (ba != null) {
			if (ba.getName().length() <= 2) {
				pm.addMessage(ba.getName() + Texts.ha + Texts.treasure3);
			} else {
				pm.addMessage(ba.getName() + Texts.ha);
				pm.addMessage(Texts.treasure3);
			}
		} else {
			pm.addMessage(Texts.treasure3);
		}
		pm.addMessage(tre.getName() + Texts.wo);
		pm.addMessage(Texts.treasure2);
		pm.closeData();
		pm.startMessage(tre);
	}
}
