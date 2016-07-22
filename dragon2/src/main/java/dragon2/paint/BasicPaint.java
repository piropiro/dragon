package dragon2.paint;





import java.awt.Point;

import dragon2.Statics;
import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.Texts;
import dragon2.common.constant.Types;

public class BasicPaint extends PaintBase {

	public BasicPaint() {
		setHelp();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[1], 1);
	}

	private void gotoWalk(Body body) {
		if (Statics.isDebug())
			PaintBase.V.S(3, 0, body.x, body.y, 0);
		if (PaintBase.V.G(3, 0, body.x, body.y) == 0) {
			PaintBase.map.setWalkPaint(body);
			PaintBase.map.repaint();
			PaintBase.uw.setAtkList(body);
		} else if (PaintBase.uw.isAllClear() && body.isType(Types.SISTER)) {
			PaintBase.map.setKakuseiPaint(body);
			PaintBase.map.repaint();
		} else if (body.isType(Types.SUMMON) && !body.isType(Types.BERSERK)) {
			PaintBase.map.setBerserkPaint(body);
			PaintBase.map.repaint();
		} else {
			Body body1 = PaintBase.uw.getChangeChara(body);
			if (body1 != null) {
				PaintBase.map.setChangePaint(body, body1);
				PaintBase.map.repaint();
				body1.x = body.x;
				body1.y = body.y;
				PaintBase.uw.setSPanel(body1);
			}
		}
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		Body body = PaintBase.uw.search(point.x, point.y);
		if (body != null)
			gotoWalk(body);
		else
			PaintBase.uw.displayData(point);
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		Body body = PaintBase.uw.search(point.x, point.y);
		if (body != null)
			PaintBase.uw.setAnalyze(body);
		else
			PaintBase.map.setButtonPaint(point, this, 5);
	}

	public boolean isNextPoint(Point point) {
		Body body = PaintBase.uw.search(point.x, point.y);
		if (body == null)
			return false;
		if (body.isType(Types.ANTI_SLEEP))
			return false;
		if (PaintBase.V.G(3, 0, point.x, point.y) != 0)
			return false;
		if (Colors.isPlayer(body)) {
			if (body.isType(Types.CHARM))
				return false;
		} else if (!body.isType(Types.CHARM))
			return false;
		return true;
	}
}
