package dragon3.paint;

import java.awt.Point;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.BodyAttribute;

public class BasicPaint extends PaintAdapter {


	/**
	 * @param uw
	 */
	public BasicPaint(UnitWorks uw) {
		super(uw);
		setHelp();
	}

	/**
	 * 
	 */
	private void setHelp() {
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_BASIC], GameColors.BLUE);
	}

	/**
	 * @param ba
	 */
	private void gotoWalk(Body ba) {

		if (map.getData(Page.P30, ba.getX(), ba.getY()) == 0) {
			PaintUtils.setWalkPaint(uw, ba);
			mw.repaint();
			pm.displayWazaList(ba);
		} else if (uw.getSaveManager().getSaveData().isAllClear() && ba.hasAttr(BodyAttribute.SISTER)) {
			PaintUtils.setKakuseiPaint(uw, ba);
			mw.repaint();
		} else if (!ba.hasAttr(BodyAttribute.BERSERK)) {
			PaintUtils.setBerserkPaint(uw, ba);
			mw.repaint();
		} else {
			Body bb = uw.getChangeChara(ba);
			if (bb != null) {
				PaintUtils.setChangePaint(uw, ba, bb);
				mw.repaint();
				bb.setX(ba.getX());
				bb.setY(ba.getY());
				pm.displayStatus(bb);
			}
		}
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		Point p = mw.getWaku();
		Body b = uw.search(p.x, p.y);
		if (b != null) {
			gotoWalk(b);
		} else {
			pm.displayData(p.x, p.y);
		}
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		Point p = mw.getWaku();
		Body b = uw.search(p.x, p.y);
		if (b != null) {
			pm.displayAnalyze(b);
		} else {
			PaintUtils.setButtonPaint(uw, p.x, p.y, this, 5);
		}
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#isNextPoint(int, int)
	 */
	public boolean isNextPoint(int x, int y) {
		Body b = uw.search(x, y);
		if (b == null)
			return false;
		if (b.hasAttr(BodyAttribute.SLEEP))
			return false;
		if (map.getData(Page.P30, x, y) != 0)
			return false;
		if (GameColors.isPlayer(b)) {
			if (b.hasAttr(BodyAttribute.CHARM))
				return false;
		} else {
			if (!b.hasAttr(BodyAttribute.CHARM))
				return false;
		}
		return true;
	}

}
