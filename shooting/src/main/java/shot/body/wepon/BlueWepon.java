package shot.body.wepon;

import mine.paint.Colors;
import mine.paint.MineGraphics;

public class BlueWepon extends Wepon {

	public BlueWepon(int x, int y) {
		super(x, y, 5, 5);
	}

	public void paint(MineGraphics g) {
		g.setColor(Colors.WHITE);
		g.fillRect(getX(), getY(), 1, 4);
		g.fillRect(getX() + 4, getY(), 1, 4);
	}

	public void move() {
		setY(getY() - 4);
	}
}
