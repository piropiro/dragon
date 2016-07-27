package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;

public class WaitPaint implements EventListener {

	public WaitPaint(UnitWorks uw) {
		super();
		uw.getUnitMap().clear(Page.P40, 0);
		uw.getMapWorks().repaint();
	}

	@Override
	public void setSelectPlace(int x, int y) {
	}

	@Override
	public boolean isNextPoint(int x, int y) {
		return false;
	}

	@Override
	public void mouseMoved(int x, int y) {
	}

	@Override
	public void leftReleased() {
	}

	@Override
	public void rightReleased() {
	}

	@Override
	public void setSelectBody(Body b) {
	}

	@Override
	public void leftPressed() {
	}

	@Override
	public void rightPressed() {
	}

}
