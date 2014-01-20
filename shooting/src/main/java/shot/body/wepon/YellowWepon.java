package shot.body.wepon;

import mine.paint.Colors;
import mine.paint.MineGraphics;
import shot.ShotCanvas;

public class YellowWepon extends Wepon {

	private int xv;
	private int yv;

	public YellowWepon(int x, int y, int xv, int yv) {
		super(x, y, 5, 5);
		this.xv = xv;
		this.yv = yv;
	}

	public void paint(MineGraphics g) {
		g.setColor(Colors.WHITE);
		g.fillRect(getX(), getY() + 2, getW(), 1);
		g.fillRect(getX() + 2, getY(), 1, getH());
	}

	public void move() {
		if (getX() < 0 && xv < 0)
			xv = -xv;
		if (getX() > ShotCanvas.SCREEN_WIDTH - getW() && xv > 0)
			xv = -xv;
		if (getY() > ShotCanvas.SCREEN_HEIGHT - getH() && yv > 0)
			yv = -yv;

		setX(getX() + xv);
		setY(getY() + yv);
	}
}
