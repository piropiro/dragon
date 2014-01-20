package shot.body.boss;

import mine.paint.Colors;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.WiderWeponH;
import shot.body.wepon.WiderWeponV;

public class YellowBoss extends Enemy {

	public YellowBoss(int level) {
		int size = 15 + level;
		int life = 10 + level * 5;
		init(0, ShotCanvas.SCREEN_HEIGHT / 2 - size / 2, size, size, 3, 3, life, Colors.YELLOW);
		setShootTime(90);
	}

	protected void moves(Body ore) {
		if (getX() < 0 && getXv() < 0)
			setXv(-getXv());
		if (getY() < 0 && getYv() < 0)
			setYv(-getYv());
		if (getX() > ShotCanvas.SCREEN_WIDTH - getW() && getXv() > 0)
			setXv(-getXv());
		if (getY() > ShotCanvas.SCREEN_HEIGHT - getH() && getYv() > 0)
			setYv(-getYv());
	}

	protected Body[] shoots() {
		Body[] newtama = new Body[4];
		newtama[0] = new WiderWeponV(getX(), getY(), -2, getH());
		newtama[1] = new WiderWeponH(getX(), getY(), -2, getW());
		newtama[2] = new WiderWeponV(getX() + getW(), getY(), 2, getW());
		newtama[3] = new WiderWeponH(getX(), getY() + getH(), 2, getW());
		return newtama;
	}
}
