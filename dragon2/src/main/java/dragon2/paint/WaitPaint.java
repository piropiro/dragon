package dragon2.paint;





import mine.util.Point;

import dragon2.common.Body;
import mine.UnitMap;

public class WaitPaint extends PaintBase {

	public WaitPaint() {
		PaintBase.V.clear(4, 0, 0);
		PaintBase.map.repaint();
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
	}

	public void rightPressed() {
		PaintBase.uw.waitFast();
	}

	public void rightReleased() {
		PaintBase.uw.waitSlow();
	}
}
