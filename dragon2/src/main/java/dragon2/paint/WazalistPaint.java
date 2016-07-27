package dragon2.paint;





import mine.util.Point;

import dragon2.Collection;
import dragon2.common.constant.Texts;

public class WazalistPaint extends PaintBase {

	public WazalistPaint(Collection collection) {
		col = collection;
		collection.deployWazas(PaintBase.V);
		setHelp();
	}

	private void setHelp() {
		Texts.help[18][1] = Texts.waza + col.countWaza() + " / "
				+ col.wazaMax();
		PaintBase.uw.setHelp(Texts.help[18], 1);
	}

	public void leftPressed() {
	}

	public void mouseMoved(Point point) {
		PaintBase.map.wakuMove(point);
		PaintBase.map.wakuPaint(true);
		if (PaintBase.V.G(2, 0, point.x, point.y) != 0)
			setSelectBody(col.searchWaza(point.x, point.y));
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(2, 0, point.x, point.y) == 0)
			PaintBase.map.setButtonPaint(point, this, 7);
	}

	public boolean isNextPoint(Point point) {
		return PaintBase.V.G(2, 0, point.x, point.y) != 0;
	}

	private Collection col;
}
