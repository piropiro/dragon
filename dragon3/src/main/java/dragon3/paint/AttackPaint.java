package dragon3.paint;

import java.awt.Point;

import dragon3.FightManager;
import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;

public class AttackPaint extends PaintAdapter {

	private FightManager fm;
	private Body ba;
	
	/**
	 * @param uw
	 */
	public AttackPaint(UnitWorks uw, FightManager fm, Body ba) {
		super(uw);
		this.fm = fm;
		this.ba = ba;
	}


	/**
	 * Left Pressed
	 */
	public void leftPressed() {
		Point waku = mw.getWaku();
		if (waku.x == ba.getX() && waku.y == ba.getY()) {
			fm.nextSelect();
			return;
		}
		if (map.getData(Page.P10, waku.x, waku.y) != 0) {
			if (fm.searchTargets()) {
				fm.attack();
				uw.setEnd(ba, false);
			}
		}
	}

	/**
	 * Right Pressed
	 */
	public void rightPressed() {
		Rewalk.rewalk(ba);
	}

	/**
	 * Select Body
	 */
	public void setSelectBody(Body b) {
	}

	/**
	 * Next Select
	 */
	public boolean isNextPoint(Point p) {
		if (map.getData(Page.P10, p.x, p.y) == 3) {
			Body b = uw.search(p.x, p.y);
			return (b != null);
		}
		return false;
	}


	/**
	 * Mouse MOved
	 */
	public void mouseMoved(Point p) {
		fm.setTarget(p);
	}
}
