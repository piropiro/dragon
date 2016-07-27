package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;

public class TitlePaint implements EventListener {

	private UnitWorks uw;
	private AnimeManager anime;

	/**
	 * @param uw
	 */
	public TitlePaint(UnitWorks uw) {
		this.uw = uw;
		this.anime = uw.getAnimeManager();
	}

	/**
	 * 
	 */
	public void action() {
		anime.closeTitleOut();
		uw.startup();
		anime.closeTitleIn();
	}

	@Override
	public void leftPressed() {
		//uw.nameChange();
		action();
	}

	@Override
	public void rightPressed() {
	}

	@Override
	public void setSelectBody(Body b) {
	}

	@Override
	public void mouseMoved(int x, int y) {
	}
	
	/*** Place *****************************************/

	@Override
	public void setSelectPlace(int x, int y) {
		uw.getPanelManager().displayPlace(x, y);
	}

	/*** Select Body *****************************************/

	@Override
	public boolean isNextPoint(int x, int y) {
		return false;
	}

	/*** Mouse Moved ***********************************/

	/*** Event ************************************/

	@Override
	public void leftReleased() {
	};
	
	@Override
	public void rightReleased() {
	};

}
