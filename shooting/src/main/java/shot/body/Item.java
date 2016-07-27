package shot.body;

import shot.ShotCanvas;

import mine.paint.MineColor;
import mine.paint.MineGraphics;

public class Item extends Body {

	private int xv;
	private int yv;
	private MineColor color;

	public Item(int x, MineColor color) {
		super(x, 0, 10, 10, 1);
		this.color = color;
		xv = 1;
		yv = 1;
	}

	public void paint(MineGraphics g) {
		g.setColor(color);
		g.fillRect(getX(), getY(), getW(), getH());
		g.setColor(MineColor.WHITE);
		g.fillRect(getX() + 2, getY() + 2, getW() - 4, getH() - 4);
	}

	public void move() {
		if (getX() < 0 && xv < 0)
			xv = -xv;
		if (getY() < 0 && yv < 0)
			yv = -yv;
		if (getX() > ShotCanvas.SCREEN_WIDTH - getW() && xv > 0)
			xv = -xv;
		if (getY() > ShotCanvas.SCREEN_HEIGHT - getH() && yv > 0)
			yv = -yv;

		setX(getX() + xv);
		setY(getY() + yv);
	}

	public MineColor getColor() {
		return color;
	}
}
