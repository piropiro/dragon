package shot.body.boss;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.WiderWeponV;

public class GreenBoss extends Enemy {

	public GreenBoss(int level) {
		int size = 15 + level;
		int life = 5 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH, ShotCanvas.SCREEN_HEIGHT / 2 - size / 2, size, size, 1, 1, life, MineColor.GREEN);
		setShootTime(30);
	}

	protected void moves(Body ore) {
		if (getX() + getXv() < 0 && getXv() < 0)
			setXv(-getXv());
		if (getX() + getXv() > ShotCanvas.SCREEN_WIDTH - getW() && getXv() > 0)
			setXv(-getXv());
		if (getY() + getH() / 2 < ore.getY() && getYv() < 0)
			setYv(-getYv());
		if (getY() + getH() / 2 > ore.getY() && getYv() > 0)
			setYv(-getYv());
		if (Math.abs(getY() + getH() / 2 - ore.getY()) < 5)
			setY(getY() - getYv());
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[2];
		newtama[0] = new WiderWeponV(getX(), getY(), -2, getH());
		newtama[1] = new WiderWeponV(getX() + getW(), getY(), 2, getW());
		return newtama;
	}
}
