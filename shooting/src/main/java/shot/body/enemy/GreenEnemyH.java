package shot.body.enemy;

import mine.paint.MineColor;
import mine.util.Randomer;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.wepon.NormalWepon;

public class GreenEnemyH extends Enemy {

	public GreenEnemyH(int y) {
		init(0, y, 7, 7, 0, 0, 1, MineColor.GREEN);
		if (Randomer.getInstance().nextBoolean()) {
			setX(0);
			setXv(2);
		} else {
			setX(ShotCanvas.SCREEN_WIDTH);
			setXv(-2);
		}
		setShootTime(25);
	}

	protected void moves(Body ore) {
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[2];
		newtama[0] = new NormalWepon(getX() + 1, getY(), 0, -2);
		newtama[1] = new NormalWepon(getX() + 1, getY(), 0, 2);
		return newtama;
	}
}
