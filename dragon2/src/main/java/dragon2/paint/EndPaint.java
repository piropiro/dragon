package dragon2.paint;





import java.awt.Point;

import dragon2.ActionBase;
import dragon2.Rewalk;
import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.Texts;
import dragon2.common.constant.BodyAttribute;

public class EndPaint extends ActionBase {

	public EndPaint(Body body) {
		ba = body;
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(1, 0, body.x, body.y, 3);
		PaintBase.V.S(3, 0, body.x, body.y, 1);
		setHelp();
	}

	private void setHelp() {
		if (!GameColor.isPlayer(ba)) {
			return;
		} else {
			PaintBase.uw.setHelp(Texts.help[11], 1);
			return;
		}
	}

	public void actionMain() {
		Point point = PaintBase.map.getWaku();
		if (point.x == ba.x && point.y == ba.y)
			PaintBase.uw.setEnd(ba, false);
		else
			PaintBase.uw.setEnd(ba, true);
	}

	public void leftPressed() {
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		action();
	}

	public void rightPressed() {
		Rewalk.rewalk(ba);
	}

	public boolean isNextPoint(Point point) {
		Body body = PaintBase.uw.search(point.x, point.y);
		if (body == null)
			return false;
		if (body.isType(BodyAttribute.ANTI_SLEEP))
			return false;
		if (PaintBase.V.G(3, 0, point.x, point.y) != 0)
			return false;
		if (GameColor.isPlayer(body)) {
			if (body.isType(BodyAttribute.CHARM))
				return false;
		} else if (!body.isType(BodyAttribute.CHARM))
			return false;
		return true;
	}

	private Body ba;
}
