package dragon3.paint;

import java.awt.Point;

import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.map.MapWorks;
import dragon3.panel.PanelManager;
import mine.paint.UnitMap;

public class EndPaint implements PaintListener {

	private UnitWorks uw;
	private MapWorks mw;
	private UnitMap map;
	private PanelManager pm;
	
	private Body ba;


	/**
	 * @param uw
	 * @param ba
	 */
	public EndPaint(UnitWorks uw, Body ba) {
		this.uw = uw;
		this.mw = uw.getMapWorks();
		this.map = uw.getUnitMap();
		this.pm = uw.getPanelManager();
		this.pm = uw.getPanelManager();
		
		this.ba = ba;
		map.clear(Page.P10, 0);
		map.setData(Page.P10, ba.getX(), ba.getY(), 3);
		map.setData(Page.P30, ba.getX(), ba.getY(), 1);
		setHelp();
	}

	/**
	 * 
	 */
	private void setHelp() {
		if (!GameColor.isPlayer(ba))
			return;
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_END], GameColor.BLUE);
	}

	/**
	 * 
	 */
	public void action() {
		Point p = mw.getWaku();
		if (p.x == ba.getX() && p.y == ba.getY()) {
			uw.setEnd(ba, false);
		} else {
			uw.setEnd(ba, true);
		}
	}


	@Override
	public void leftPressed() {
		map.setData(Page.P10, ba.getX(), ba.getY(), 0);
		action();
	}


	@Override
	public void rightPressed() {
		Rewalk.rewalk(ba);
	}


	@Override
	public boolean isNextPoint(int x, int y) {
		Body b = uw.search(x, y);
		if (b == null)
			return false;
		if (b.hasAttr(BodyAttribute.SLEEP))
			return false;
		if (map.getData(Page.P30, x, y) != 0)
			return false;
		if (GameColor.isPlayer(b)) {
			if (b.hasAttr(BodyAttribute.CHARM))
				return false;
		} else {
			if (!b.hasAttr(BodyAttribute.CHARM))
				return false;
		}
		return true;
	}
	
	/*** Place *****************************************/

	@Override
	public void setSelectPlace(int x, int y) {
		uw.getPanelManager().displayPlace(x, y);
	}

	/*** Select Body *****************************************/

	@Override
	public void setSelectBody(Body b) {
		pm.displayStatus(b);
	}

	/*** Mouse Moved ***********************************/

	@Override
	public void mouseMoved(int x, int y) {
		mw.wakuMove(x, y);
		mw.wakuPaint(true);
	}

	/*** Event ************************************/
	
	@Override
	public void leftReleased() {
	};
	
	@Override
	public void rightReleased() {
	};

}
