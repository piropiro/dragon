package dragon3.paint;

import java.awt.Point;

import dragon3.UnitWorks;
import dragon3.common.constant.Page;

/**
 * @author k-saito
 */
public class ButtonPaint extends PaintAdapter {

	private int x;
	private int y;
	private PaintListener pl;
	private int type;

	/**
	 * @param uw
	 * @param x
	 * @param y
	 * @param pl
	 * @param type
	 */
	public ButtonPaint(UnitWorks uw, int x, int y, PaintListener pl, int type) {
		super(uw);

		this.x = x;
		this.y = y;
		this.pl = pl;
		this.type = type;

		if (y == 0) {
			map.setData(Page.P40, x, y + 1, type);
		} else {
			map.setData(Page.P40, x, y - 1, type);
		}
		map.setData(Page.P10, x, y, type);
		map.setData(Page.P40, x, y, 0);
		mw.repaint();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#mouseMoved(int, int)
	 */
	public void mouseMoved(int x, int y) {
		rightPressed();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		Point ps = mw.getWaku();
		if (ps.x == x && ps.y == y) {
			switch (type) {
				case 5 :
					uw.getTurnManager().enemyTurnStart();
					break;
				case 6 :
					uw.setMensEnd();
					break;
				case 7 :
					uw.backToCamp();
					break;
			}
		} else {
			rightPressed();
		}
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		map.setData(Page.P40, x, y - 1, 0);
		map.setData(Page.P40, x, y + 1, 0);
		map.setData(Page.P10, x, y, 0);

		mw.setPaintListener(pl);
		mw.repaint();
	}
}
