package shot.body.boss;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.BlueEnemyWepon;

public class BlueBoss extends Enemy {

	public BlueBoss(int level) {
		int size = 15 + level;
		int life = 30 + level * 5;
		init(0, ShotCanvas.SCREEN_HEIGHT - size - 25, size, size, 1, 0, life, MineColor.BLUE);
		setShootTime(20);
	}

	protected void moves(Body ore) {
		if (getX() + 15 > ShotCanvas.SCREEN_WIDTH - getW() && getXv() > 0) {
			setYv(-getXv());
			setXv(0);
		}
		if (getY() < 15 && getYv() < 0) {
			setXv(getYv());
			setYv(0);
		}
		if (getX() < 15 && getXv() < 0) {
			setYv(-getXv());
			setXv(0);
		}
		if (getY() + 15 > ShotCanvas.SCREEN_HEIGHT - getH() && getYv() > 0) {
			setXv(getYv());
			setYv(0);
		}
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[8];
		newtama[0] = new BlueEnemyWepon(getX(), getY(), -2, 0);
		newtama[1] = new BlueEnemyWepon(getX(), getY(), 0, -2);
		newtama[2] = new BlueEnemyWepon(getX() + getW(), getY(), 0, -2);
		newtama[3] = new BlueEnemyWepon(getX() + getW(), getY(), 2, 0);
		newtama[4] = new BlueEnemyWepon(getX(), getY() + getH(), 0, 2);
		newtama[5] = new BlueEnemyWepon(getX(), getY() + getH(), -2, 0);
		newtama[6] = new BlueEnemyWepon(getX() + getW(), getY() + getH(), 2, 0);
		newtama[7] = new BlueEnemyWepon(getX() + getW(), getY() + getH(), 0, 2);
		return newtama;
	}
}
