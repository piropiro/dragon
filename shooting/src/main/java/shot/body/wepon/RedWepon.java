package shot.body.wepon;

import mine.paint.MineColor;
import mine.paint.MineGraphics;

public class RedWepon extends Wepon {

	private int xv;
	private int yv;

	public RedWepon(int x, int y, int xv, int yv) {
		super(x, y, 2, 2);
		this.xv = xv;
		this.yv = yv;
	}

	public void paint(MineGraphics g) {
		g.setColor(MineColor.GRAY);
		g.fillRect(getX() - xv * 2, getY() - yv * 2, getW(), getH());
		g.setColor(MineColor.SILVER);
		g.fillRect(getX() - xv, getY() - yv, getW(), getH());
		g.setColor(MineColor.WHITE);
		g.fillRect(getX(), getY(), getW(), getH());
	}

	public void move() {
		setX(getX() + xv);
		setY(getY() + yv);
	}
}
