package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;

public class ScorePaint extends PaintAdapter {


	/**
	 * @param uw
	 */
	public ScorePaint(UnitWorks uw) {
		super(uw);
		map.clear(Page.P40, 0);
		mw.repaint();
		setHelp();
	}

	/**
	 * 
	 */
	private void setHelp() {
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_SCORE], GameColors.BLUE);
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#setSelectBody(dragon3.common.Body)
	 */
	public void setSelectBody(Body b) {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#mouseMoved(int, int)
	 */
	public void mouseMoved(int x, int y) {
	}
	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		uw.backToCamp();
	}
	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		uw.backToCamp();
	}

}
