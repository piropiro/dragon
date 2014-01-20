package shot.body.wepon;

import mine.paint.Colors;
import mine.paint.MineGraphics;

public class BlueEnemyWepon extends Wepon {

	private int xv;
	private int yv;

	public BlueEnemyWepon(int x, int y, int xv, int yv) {
		super(x, y, 2, 2);
		this.xv = xv;
		this.yv = yv;
	}

	public void paint(MineGraphics g) {
		g.setColor(Colors.BLUE);
		g.fillRect(getX() - xv * 2, getY() - yv * 2, getW(), getH());
		g.setColor(Colors.AQUA);
		g.fillRect(getX() - xv, getY() - yv, getW(), getH());
		g.setColor(Colors.WHITE);
		g.fillRect(getX(), getY(), getW(), getH());

	}

	public void move() {
		setX(getX() + xv);
		setY(getY() + yv);
	}
}
