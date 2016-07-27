package shot.body.boss;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.Wepon;
import shot.body.wepon.WiderWeponH;

public class LimeBoss extends Enemy {

	public LimeBoss(int level) {
		int size = 15 + level;
		int life = 50 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, 0, size, size, 1, 1, life, MineColor.LIME);
		setShootTime(30);
	}

	protected void moves(Body ore) {
		if (getY() + getYv() < 0 && getYv() < 0)
			setYv(-getYv());
		if (getY() + getYv() > ShotCanvas.SCREEN_HEIGHT - getH() && getYv() > 0)
			setYv(-getYv());
		if (getX() + getW() / 2 < ore.getX() && getXv() < 0)
			setXv(-getXv());
		if (getX() + getW() / 2 > ore.getX() && getXv() > 0)
			setXv(-getXv());
		if (Math.abs(getX() + getW() / 2 - ore.getX()) < 5)
			setX(getX() - getXv());
	}

	protected List<Wepon> shoots() {
		return Arrays.asList(
			new WiderWeponH(getX(), getY(), -2, getW()),
			new WiderWeponH(getX(), getY() + getH(), 2, getW())
			);
	}
}
