package dragon2.paint;





import java.awt.Point;

import dragon2.ActionBase;
import dragon2.common.Body;
import dragon2.common.constant.Types;

public class BerserkPaint extends ActionBase {

	public BerserkPaint(Body body) {
		ba = body;
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.S(1, 0, body.x, body.y, 4);
		PaintBase.V.S(3, 0, body.x, body.y, 6);
		PaintBase.V.S(4, 0, body.x, body.y, 0);
	}

	public void actionMain() {
		PaintBase.V.S(3, 0, ba.x, ba.y, 0);
		Point point = new Point(ba.x, ba.y);
		PaintBase.uw.setAnime(1, -1, point, point);
		PaintBase.uw.setAnime(-7, 12, point, point);
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		PaintBase.map.setBasicPaint();
	}

	private void setStatus() {
		ba.setTypeState(Types.BERSERK, true);
		ba.hp = ba.hpMax;
		ba.str *= 1.5D;
		ba.def *= 1.5D;
		ba.mst *= 1.5D;
		ba.mdf *= 1.5D;
		ba.hit *= 1.5D;
		ba.mis *= 1.5D;
	}

	public void mouseMoved(Point point) {
		rightPressed();
	}

	public void leftPressed() {
		setStatus();
		PaintBase.uw.bersekChara(ba);
		action();
	}

	public void rightPressed() {
		PaintBase.V.S(1, 0, ba.x, ba.y, 0);
		PaintBase.V.S(3, 0, ba.x, ba.y, 1);
		PaintBase.map.repaint();
		PaintBase.map.setBasicPaint();
	}

	private Body ba;
}
