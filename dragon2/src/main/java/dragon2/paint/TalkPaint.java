package dragon2.paint;





import java.awt.Point;

import dragon2.ActionBase;
import dragon2.Rewalk;
import dragon2.card.CardPaint;
import dragon2.common.Body;
import dragon2.common.constant.Texts;
import dragon2.common.constant.BodyAttribute;

public class TalkPaint extends ActionBase {

	public TalkPaint(Body body) {
		ba = body;
		target = new Body[4];
		target[0] = getTarget(body.x - 1, body.y);
		target[1] = getTarget(body.x + 1, body.y);
		target[2] = getTarget(body.x, body.y - 1);
		target[3] = getTarget(body.x, body.y + 1);
	}

	public void show() {
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(3, 0, ba.x, ba.y, 3);
		PaintBase.V.S(1, 0, ba.x - 1, ba.y, 2);
		PaintBase.V.S(1, 0, ba.x + 1, ba.y, 2);
		PaintBase.V.S(1, 0, ba.x, ba.y - 1, 2);
		PaintBase.V.S(1, 0, ba.x, ba.y + 1, 2);
		for (int i = 0; i < target.length; i++)
			if (target[i] != null)
				PaintBase.V.S(1, 0, target[i].x, target[i].y, 3);

		setHelp();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[17], 1);
	}

	public boolean isEnable() {
		for (int i = 0; i < target.length; i++)
			if (target[i] != null)
				return true;

		return false;
	}

	private Body getTarget(int i, int j) {
		Body body = PaintBase.uw.search(i, j);
		if (body == null)
			return null;
		if (body.color == ba.color)
			return null;
		if (body.isType(BodyAttribute.ANTI_SLEEP))
			return null;
		if (ba.level != 0 && body.level > ba.level)
			return null;
		if (PaintBase.uw.have(body))
			return null;
		if (ba.isType(BodyAttribute.MASTER))
			return body;
		if ((ba.isType(BodyAttribute.ASK) || ba.isType(BodyAttribute.TALKABLE))
				&& (body.isType(BodyAttribute.ASK) || body.isType(BodyAttribute.TALKABLE)))
			return body;
		else
			return null;
	}

	public void actionMain() {
		PaintBase.map.setWaitPaint();
		CardPaint cardpaint = new CardPaint(ba, bb);
		PaintBase.map.setPaintListener(cardpaint);
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (point.x == ba.x && point.y == ba.y) {
			PaintBase.map.setEndPaint(ba);
			PaintBase.map.repaint();
		}
		if (PaintBase.V.G(1, 0, point.x, point.y) != 3)
			return;
		bb = getTarget(point.x, point.y);
		if (bb != null) {
			PaintBase.V.clear(1, 0, 0);
			PaintBase.map.repaint();
			PaintBase.uw.closeAPanel();
			action();
		}
	}

	public void rightPressed() {
		Rewalk.rewalk(ba);
	}

	public boolean isNextPoint(Point point) {
		return PaintBase.V.G(1, 0, point.x, point.y) == 3;
	}

	private Body ba;
	private Body bb;
	private Body target[];
}
