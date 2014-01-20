package shot.body.boss;

import mine.paint.Colors;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.WiderWeponH;

public class RedBoss extends Enemy {

	public RedBoss(int level) {
		int size = 15 + level;
		int life = 10 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, 0, size, size, 0, 1, life, Colors.RED);
		setShootTime(30);
	}

	protected void moves(Body ore) {
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[2];
		newtama[0] = new WiderWeponH(getX() - getW(), getY() + getH(), 2, getW());
		newtama[1] = new WiderWeponH(getX() + getW(), getY() + getH(), 2, getW());
		return newtama;
	}
}
