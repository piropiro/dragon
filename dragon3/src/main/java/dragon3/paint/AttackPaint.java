package dragon3.paint;

import java.awt.Point;

import dragon3.FightManager;
import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.map.MapWorks;
import mine.paint.UnitMap;

public class AttackPaint implements EventListener {

	private UnitWorks uw;
	private MapWorks mw;
	private UnitMap map;
	private FightManager fm;
	private Body ba;
	
	/**
	 * @param uw
	 */
	public AttackPaint(UnitWorks uw, FightManager fm, Body ba) {
		this.uw = uw;
		this.mw = uw.getMapWorks();
		this.map = uw.getUnitMap();
		this.fm = fm;
		this.ba = ba;
	}

	@Override
	public void setSelectPlace(int x, int y) {
		uw.getPanelManager().displayPlace(x, y);
	}
	
	/**
	 * Left Pressed
	 */
	@Override
	public void leftPressed() {
		Point waku = mw.getWaku();
		if (waku.x == ba.getX() && waku.y == ba.getY()) {
			fm.nextSelect();
			return;
		}
		if (map.getData(Page.P10, waku.x, waku.y) != 0) {
			if (fm.searchTargets()) {
				PaintUtils.setWaitPaint(uw);
				fm.attack();
				uw.setEnd(ba, false);
			}
		}
	}

	/**
	 * Right Pressed
	 */
	@Override
	public void rightPressed() {
		Rewalk.rewalk(ba);
	}

	/**
	 * Select Body
	 */
	@Override
	public void setSelectBody(Body b) {
	}

	/**
	 * Next Select
	 */
	@Override
	public boolean isNextPoint(int x, int y) {
		if (map.getData(Page.P10, x, y) == 3) {
			Body b = uw.search(x, y);
			return (b != null);
		}
		return false;
	}


	/**
	 * Mouse Moved
	 */
	@Override
	public void mouseMoved(int x, int y) {
		fm.setTarget(new Point(x, y));
	}
	
	@Override
	public void leftReleased() {
	};
	
	@Override
	public void rightReleased() {
	};
}
