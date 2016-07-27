package dragon2.paint;





import mine.util.Point;

import dragon2.ActionBase;
import dragon2.common.Body;

public class TitlePaint extends ActionBase {

	public TitlePaint() {
	}

	public void actionMain() {
		PaintBase.uw.setAnime(-12, 0, null, null);
		PaintBase.uw.startup();
		PaintBase.uw.setAnime(-12, 1, null, null);
	}

	public void leftPressed() {
		PaintBase.uw.nameChange();
		action();
	}

	public void rightPressed() {
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}
}
