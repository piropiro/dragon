package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.common.Body;

public class TitlePaint extends PaintAdapter {


	/**
	 * @param uw
	 */
	public TitlePaint(UnitWorks uw) {
		super(uw);
	}

	/**
	 * 
	 */
	public void action() {
		anime.closeTitleOut();
		uw.startup();
		anime.closeTitleIn();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		//uw.nameChange();
		action();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
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
}
