package shot.body.wepon;

import mine.paint.MineColor;
import mine.paint.MineGraphics;

public class BeamWepon extends Wepon {

	public BeamWepon(int x, int y) {
		super(x, y, 5, 5);
	}

	public void paint(MineGraphics g) {
		g.setColor(MineColor.WHITE);
		g.fillRect(getX(), getY(), 1, 4);
		g.fillRect(getX() + 4, getY(), 1, 4);
	}

	public void move() {
		setY(getY() - 4);
	}
}
