package shot.body.wepon;

import mine.paint.Colors;
import mine.paint.MineGraphics;

public class LineWepon extends Wepon {

	public LineWepon(int x, int y) {
		super(x, y, 3, 32);
	}

	public void paint(MineGraphics g) {
		g.setColor(Colors.BLUE);
		g.fillRect(getX(), getY(), getW(), getH());
		g.setColor(Colors.WHITE);
		g.fillRect(getX() + 1, getY() + 1, getW() - 2, getH() - 2);
	}

	public void move() {
		setY(getY() + 6);
	}
}
