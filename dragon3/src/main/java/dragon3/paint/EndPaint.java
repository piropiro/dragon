package dragon3.paint;

import java.awt.Point;

import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.Types;

public class EndPaint extends PaintAdapter {

	private Body ba;


	/**
	 * @param uw
	 * @param ba
	 */
	public EndPaint(UnitWorks uw, Body ba) {
		super(uw);
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
		if (!GameColors.isPlayer(ba))
			return;
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_END], GameColors.BLUE);
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


	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		map.setData(Page.P10, ba.getX(), ba.getY(), 0);
		action();
	}


	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		Rewalk.rewalk(ba);
	}


	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#isNextPoint(int, int)
	 */
	public boolean isNextPoint(int x, int y) {
		Body b = uw.search(x, y);
		if (b == null)
			return false;
		if (b.isType(Types.SLEEP))
			return false;
		if (map.getData(Page.P30, x, y) != 0)
			return false;
		if (GameColors.isPlayer(b)) {
			if (b.isType(Types.CHARM))
				return false;
		} else {
			if (!b.isType(Types.CHARM))
				return false;
		}
		return true;
	}
}
