package dragon3.impl;

import mine.util.Point;
import java.util.ArrayList;
import java.util.List;

import mine.paint.UnitMap;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.DeployType;
import dragon3.common.constant.Page;
import dragon3.common.util.MoveUtils;
import dragon3.manage.SummonManager;

public class SummonManagerImpl implements SummonManager {
	private List<Body> devils;

	private AnimeManager anime;
	private UnitWorks uw;
	private UnitMap map;

	/*** Constructer *********************************************/

	public SummonManagerImpl(UnitWorks uw, List<Body> enemys) {
		this.uw = uw;
		this.anime = uw.getAnimeManager();
		this.map = uw.getUnitMap();

		this.devils = new ArrayList<>();

		for (Body b : enemys) {
			if (b.getDeployType() == DeployType.SUMMON) {
				map.setData(Page.P00, b.getGoalX(), b.getGoalY(), MoveUtils.CLOSE_MAGIC);
				b.setX(b.getGoalX());
				b.setY(b.getGoalY());
				b.setGoalX(0);
				b.setGoalY(0);
				b.setHp(0);
				devils.add(b);
			}
		}
	}

	/* (non-Javadoc)
	 * @see dragon3.SummonManager#getLimitTurn(java.awt.Point)
	 */

	public int getLimitTurn(Point p) {
		int turn = 64;
		for (int i=0; i<devils.size(); i++) {
			Body b = (Body)devils.get(i);
			if (b == null)
				continue;
			if (p.x != b.getX())
				continue;
			if (p.y != b.getY())
				continue;
			if (turn > b.getLimitTurn())
				turn = b.getLimitTurn();
		}
		return turn;
	}

	/* (non-Javadoc)
	 * @see dragon3.SummonManager#summon()
	 */

	public void summon() {
		int turn = uw.getTurnManager().getTurn();
		for (int i=0; i<devils.size(); i++) {
			Body b = (Body)devils.get(i);
			if (b.getLimitTurn() > turn)
				continue;
			if (map.getData(Page.P20, b.getX(), b.getY()) != 0)
				continue;
			map.setData(Page.P00, b.getX(), b.getY(), MoveUtils.OPEN_MAGIC);
			anime.systemAnime(AnimeManager.ID_SUMMON, b.getX(), b.getY());

			anime.summonAnime(b.getImageNum(), b.getX(), b.getY());
			b.setHp(b.getHpMax());
		}
	}
}
