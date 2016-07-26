package shot.body.enemy;

import mine.paint.MineColor;
import mine.util.Randomer;
import shot.ShotCanvas;
import shot.body.Body;

public class WhiteEnemy extends Enemy {

	public WhiteEnemy(int y) {
		init(0, y, 7, 7, 1, 1, 1, MineColor.WHITE);
		if (Randomer.getInstance().nextBoolean()) {
			setX(0);
		} else {
			setX(ShotCanvas.SCREEN_WIDTH);
		}
	}

	protected void moves(Body ore) {
		if (getX() < ore.getX() && getXv() < 0)
			setXv(-getXv());
		if (getX() + getW() > ore.getX() && getXv() > 0)
			setXv(-getXv());
		if (getY() < ore.getY() && getYv() < 0)
			setYv(-getYv());
		if (getY() + getH() > ore.getY() && getYv() > 0)
			setYv(-getYv());
		if (Math.abs(getX() + 3 - ore.getX()) < 5)
			setX(getX() - getXv());
		if (Math.abs(getY() + 3 - ore.getY()) < 5)
			setY(getY() - getYv());
	}

	protected Body[] shoots() {
		return new Body[0];
	}
}
