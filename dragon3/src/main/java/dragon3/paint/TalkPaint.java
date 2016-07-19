package dragon3.paint;

import java.awt.Point;

import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.BodyAttribute;

public class TalkPaint extends PaintAdapter {

	private Body ba, bb;
	private Body[] target;

	/**
	 * @param uw
	 * @param cp
	 * @param ba
	 */
	public TalkPaint(UnitWorks uw, Body ba) {
		super(uw);
		this.ba = ba;
		target = new Body[4];
		target[0] = getTarget(ba.getX() - 1, ba.getY());
		target[1] = getTarget(ba.getX() + 1, ba.getY());
		target[2] = getTarget(ba.getX(), ba.getY() - 1);
		target[3] = getTarget(ba.getX(), ba.getY() + 1);
	}

	/**
	 * 
	 */
	public void show() {
		map.clear(Page.P10, 0);
		map.setData(Page.P30, ba.getX(), ba.getY(), 3);
		map.setData(Page.P10, ba.getX() - 1, ba.getY(), 2);
		map.setData(Page.P10, ba.getX() + 1, ba.getY(), 2);
		map.setData(Page.P10, ba.getX(), ba.getY() - 1, 2);
		map.setData(Page.P10, ba.getX(), ba.getY() + 1, 2);
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				map.setData(Page.P10, target[i].getX(), target[i].getY(), 3);
			}
		}
		setHelp();
	}

	/**
	 * 
	 */
	private void setHelp() {
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_TALK], GameColors.BLUE);
	}

	/**
	 * @return
	 */
	public boolean isEnable() {
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null)
				return true;
		}
		return false;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private Body getTarget(int x, int y) {
		Body bb = uw.search(x, y);

		if (bb == null)
			return null;
		if (bb.getColor() == ba.getColor())
			return null;
		if (bb.hasAttr(BodyAttribute.SLEEP))
			return null;
		if (ba.getLevel() != 0) {
			if (bb.getLevel() > ba.getLevel())
				return null;
		}

		if (uw.have(bb))
			return null;

		if (bb.hasAttr(BodyAttribute.TALKABLE)) {
			return bb;
		}

		return null;
	}

	/**
	 * 
	 */
	public void action() {
		PaintUtils.setCardPaint(uw, ba, bb);
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		Point p = mw.getWaku();
		if (p.x == ba.getX() && p.y == ba.getY()) {
			PaintUtils.setEndPaint(uw, ba);
			mw.repaint();
		}
		if (map.getData(Page.P10, p.x, p.y) != 3)
			return;
		bb = getTarget(p.x, p.y);
		if (bb != null) {
			map.clear(Page.P10, 0);
			mw.repaint();
			pm.closeData();
			action();
		}
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
		return (map.getData(Page.P10, x, y) == 3);
	}

}
