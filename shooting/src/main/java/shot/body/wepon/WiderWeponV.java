package shot.body.wepon;

import mine.paint.MineColor;
import mine.paint.MineGraphics;

public class WiderWeponV extends Wepon {

	private int xv;

	public WiderWeponV(int x, int y, int xv, int size) {
		super(x, y, 4, size);
		this.xv = xv;
	}

	public void paint(MineGraphics g) {
		g.setColor(MineColor.YELLOW);
		g.fillRect(getX(), getY(), getW(), getH());
		g.setColor(MineColor.WHITE);
		g.fillRect(getX() + 1, getY() + 1, getW() - 2, getH() - 2);
	}

	public void move() {
		setX(getX() + xv);
	}
}
