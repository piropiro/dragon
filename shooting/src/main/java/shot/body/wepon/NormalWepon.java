package shot.body.wepon;

import mine.paint.MineGraphics;

public class NormalWepon extends Wepon {

	private int xv;
	private int yv;

	public NormalWepon(int x, int y, int xv, int yv) {
		super(x, y, 2, 2);
		this.xv = xv;
		this.yv = yv;
	}

	public void paint(MineGraphics g) {
		g.setColor(192, 160, 32);
		g.fillRect(getX() - xv * 2, getY() - yv * 2, getW(), getH());
		g.setColor(224, 192, 64);
		g.fillRect(getX() - xv, getY() - yv, getW(), getH());
		g.setColor(255, 224, 160);
		g.fillRect(getX(), getY(), getW(), getH());
	}

	public void move() {
		setX(getX() + xv);
		setY(getY() + yv);
	}
}
