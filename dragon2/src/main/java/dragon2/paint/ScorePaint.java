package dragon2.paint;





import mine.util.Point;

import dragon2.common.Body;
import dragon2.common.constant.Texts;

public class ScorePaint extends PaintBase {

	public ScorePaint() {
		PaintBase.V.clear(4, 0, 0);
		PaintBase.map.repaint();
		setHelp();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[15], 1);
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
		PaintBase.uw.backToCamp();
	}

	public void rightPressed() {
		PaintBase.uw.backToCamp();
	}
}
