package shot.body.enemy;

import mine.paint.MineColor;
import shot.body.Body;
import shot.body.wepon.NormalWepon;

public class GreenEnemyV extends Enemy {

	public GreenEnemyV(int x) {
		init(x, 0, 7, 7, 0, 3, 1, MineColor.GREEN);
		setShootTime(20);
	}

	protected void moves(Body ore) {
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[2];
		newtama[0] = new NormalWepon(getX() + 1, getY(), -2, 0);
		newtama[1] = new NormalWepon(getX() + 1, getY(), 2, 0);
		return newtama;
	}
}
