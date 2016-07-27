package dragon3.paint;

import mine.paint.UnitMap;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.map.MapWorks;
import dragon3.panel.PanelManager;

public abstract class PaintAdapter implements EventListener {

	protected UnitWorks uw;
	protected MapWorks mw;
	protected UnitMap map;
	protected AnimeManager anime;
	protected PanelManager pm;

	/*** Constructer **********************************************/

	public PaintAdapter(UnitWorks uw) {
		this.uw = uw;
		this.mw = uw.getMapWorks();
		this.map = uw.getUnitMap();
		this.anime = uw.getAnimeManager();
		this.pm = uw.getPanelManager();
	}

	/*** Place *****************************************/

	public void setSelectPlace(int x, int y) {
		uw.getPanelManager().displayPlace(x, y);
	}

	/*** Select Body *****************************************/

	public void setSelectBody(Body b) {
		pm.displayStatus(b);
	}

	public boolean isNextPoint(int x, int y) {
		return false;
	}

	/*** Mouse Moved ***********************************/

	public void mouseMoved(int x, int y) {
		mw.wakuMove(x, y);
		mw.wakuPaint(true);
	}

	/*** Event ************************************/

	public abstract void leftPressed();
	public abstract void rightPressed();
	public void leftReleased() {
	};
	public void rightReleased() {
	};

}
