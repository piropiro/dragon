package dragon2.card;





import mine.util.Point;

import dragon2.ActionBase;
import dragon2.common.Body;
import dragon2.common.constant.Texts;
import dragon2.paint.PaintBase;
import mine.UnitMap;

public class CardPaint extends ActionBase {

	public CardPaint(Body body, Body body1) {
		cp.setup(body, body1);
		cp.display();
		setHelp();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[9], 1);
	}

	public static void setup(CardPanel cardpanel) {
		cp = cardpanel;
		CV = cp.CV;
	}

	public void actionMain() {
		if (select == -1)
			cp.doubleCard();
		else
			cp.open(select);
		if (!cp.isEnd())
			PaintBase.map.setPaintListener(this);
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (point.x == 7 && point.y == 11) {
			select = -1;
			action();
		}
		if (point.y != 9)
			return;
		if (point.x < 6)
			return;
		if (point.x > 12) {
			return;
		} else {
			select = point.x - 6;
			action();
			return;
		}
	}

	public void rightPressed() {
	}

	public void mouseMoved(Point point) {
		Point point1 = PaintBase.map.getWaku();
		CV.S(2, 0, point1.x - 4, point1.y - 1, 0);
		CV.S(2, 0, point.x - 4, point.y - 1, 1);
		PaintBase.map.wakuMove(point);
		PaintBase.map.wakuPaint(true);
	}

	public void setSelectBody(Body body) {
	}

	public void setSelectPlace(Point point) {
	}

	public boolean isNextPoint(Point point) {
		if (point.y != 9)
			return false;
		if (point.x < 6)
			return false;
		if (point.x > 12)
			return false;
		else
			return cp.isAlive(point.x - 6);
	}

	private static CardPanel cp;
	private static UnitMap CV;
	int select;
}
