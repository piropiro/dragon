package dragon2.paint;





import mine.util.Point;

public class ButtonPaint extends PaintBase {

	public ButtonPaint(Point point, PaintListener paintlistener, int i) {
		p = point;
		pl = paintlistener;
		type = i;
		if (point.y == 0)
			PaintBase.V.S(4, 0, point.x, point.y + 1, i);
		else
			PaintBase.V.S(4, 0, point.x, point.y - 1, i);
		PaintBase.V.S(1, 0, point.x, point.y, i);
		PaintBase.V.S(4, 0, point.x, point.y, 0);
		PaintBase.map.repaint();
	}

	public void mouseMoved(Point point) {
		rightPressed();
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (point.x == p.x && point.y == p.y)
			switch (type) {
			case 5: // '\005'
				PaintBase.uw.mensTurnEnd();
				break;

			case 6: // '\006'
				PaintBase.uw.setMensEnd();
				break;

			case 7: // '\007'
				PaintBase.uw.backToCamp();
				break;
			}
		else
			rightPressed();
	}

	public void rightPressed() {
		PaintBase.V.S(4, 0, p.x, p.y - 1, 0);
		PaintBase.V.S(4, 0, p.x, p.y + 1, 0);
		PaintBase.V.S(1, 0, p.x, p.y, 0);
		PaintBase.map.setPaintListener(pl);
		PaintBase.map.repaint();
	}

	private Point p;
	private PaintListener pl;
	int type;
}
