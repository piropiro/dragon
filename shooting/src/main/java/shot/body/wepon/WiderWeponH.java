package shot.body.wepon;

import mine.paint.Colors;
import mine.paint.MineGraphics;

public class WiderWeponH extends Wepon {

	private int yv;

	public WiderWeponH(int x, int y, int yv, int size) {
		super(x, y, size, 4);
		this.yv = yv;
	}

	public void paint(MineGraphics g) {
		g.setColor(Colors.YELLOW);
		g.fillRect(getX(), getY(), getW(), getH());
		g.setColor(Colors.WHITE);
		g.fillRect(getX() + 1, getY() + 1, getW() - 2, getH() - 2);
	}

	public void move() {
		setY(getY() + yv);
	}
}
