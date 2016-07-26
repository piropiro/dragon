package shot.body.boss;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.BlueEnemyWepon;

public class OrangeBoss extends Enemy {

	public OrangeBoss(int level) {
		int size = 15 + level;
		int life = 30 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, 0, size, size, 2, 2, life, MineColor.ORANGE);
		setShootTime(20);
	}

	protected void moves(Body ore) {
		if (getX() + getXv() < 0 && getXv() < 0)
			setXv(-getXv());
		if (getY() + getYv() < 0 && getYv() < 0)
			setYv(-getYv());
		if (getX() + getXv() > ShotCanvas.SCREEN_WIDTH - getW() && getXv() > 0)
			setXv(-getXv());
		if (getY() + getYv() > ShotCanvas.SCREEN_HEIGHT / 2 && getYv() > 0)
			setYv(-getYv());
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[4];
		newtama[0] = new BlueEnemyWepon(getX() + getW() / 2, getY() + getH() / 2, -2, 1);
		newtama[1] = new BlueEnemyWepon(getX() + getW() / 2, getY() + getH() / 2, -1, 2);
		newtama[2] = new BlueEnemyWepon(getX() + getW() / 2, getY() + getH() / 2, 1, 2);
		newtama[3] = new BlueEnemyWepon(getX() + getW() / 2, getY() + getH() / 2, 2, 1);
		return newtama;
	}
}
