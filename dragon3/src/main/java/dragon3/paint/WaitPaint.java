package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;

public class WaitPaint implements PaintListener {

	public WaitPaint(UnitWorks uw) {
		super();
		uw.getUnitMap().clear(Page.P40, 0);
		uw.getMapWorks().repaint();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#setSelectPlace(int, int)
	 */
	public void setSelectPlace(int x, int y) {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#isNextPoint(int, int)
	 */
	public boolean isNextPoint(int x, int y) {
		return false;
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#mouseMoved(int, int)
	 */
	public void mouseMoved(int x, int y) {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftReleased()
	 */
	public void leftReleased() {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightReleased()
	 */
	public void rightReleased() {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#setSelectBody(dragon3.common.Body)
	 */
	public void setSelectBody(Body b) {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
	}

}
