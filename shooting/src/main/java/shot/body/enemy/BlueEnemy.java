package shot.body.enemy;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.wepon.NormalWepon;

public class BlueEnemy extends Enemy {

	public BlueEnemy(int x) {
		init(x, 0, 7, 7, 2, 1, 1, MineColor.BLUE);
		setShootTime(50);
	}

	protected void moves(Body ore) {
		if (getTime() % 15 == 0 || getX() < 0 || getX() + getW() > ShotCanvas.SCREEN_WIDTH)
			setXv(-getXv());
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[4];
		newtama[0] = new NormalWepon(getX() + 1, getY(), -1, -1);
		newtama[1] = new NormalWepon(getX() + 1, getY(), 1, -1);
		newtama[2] = new NormalWepon(getX() + 1, getY(), -1, 1);
		newtama[3] = new NormalWepon(getX() + 1, getY(), 1, 1);
		return newtama;
	}
}
