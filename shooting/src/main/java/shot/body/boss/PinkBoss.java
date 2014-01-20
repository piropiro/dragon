package shot.body.boss;

import mine.paint.Colors;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.LineWepon;

public class PinkBoss extends Enemy {

	public PinkBoss(int level) {
		int size = 15 + level;
		int life = 30 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, 0, size, size, -2, 1, life, Colors.PINK);
		setShootTime(25);
	}

	protected void moves(Body ore) {
		if (getX() < ShotCanvas.SCREEN_WIDTH / 2 - getW() && getXv() < 0)
			setXv(-getXv());
		if (getY() < ShotCanvas.SCREEN_HEIGHT / 3 - getH() && getYv() < 0)
			setYv(-getYv());
		if (getX() > ShotCanvas.SCREEN_WIDTH / 2 && getXv() > 0)
			setXv(-getXv());
		if (getY() > ShotCanvas.SCREEN_HEIGHT / 3 && getYv() > 0)
			setYv(-getYv());
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[2];
		newtama[0] = new LineWepon(getX() - 3, getY() + getH());
		newtama[1] = new LineWepon(getX() + getW(), getY() + getH());
		return newtama;
	}
}
