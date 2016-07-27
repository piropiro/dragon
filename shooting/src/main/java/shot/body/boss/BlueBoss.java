package shot.body.boss;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.BlueEnemyWepon;
import shot.body.wepon.Wepon;

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

	protected List<Wepon> shoots() {
		return Arrays.asList(
			new BlueEnemyWepon(getX(), getY(), -2, 0),
			new BlueEnemyWepon(getX(), getY(), 0, -2),
			new BlueEnemyWepon(getX() + getW(), getY(), 0, -2),
			new BlueEnemyWepon(getX() + getW(), getY(), 2, 0),
			new BlueEnemyWepon(getX(), getY() + getH(), 0, 2),
			new BlueEnemyWepon(getX(), getY() + getH(), -2, 0),
			new BlueEnemyWepon(getX() + getW(), getY() + getH(), 2, 0),
			new BlueEnemyWepon(getX() + getW(), getY() + getH(), 0, 2)
		);
	}
}
