package shot.body.boss;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.LineWepon;
import shot.body.wepon.Wepon;

public class AquaBoss extends Enemy {

	public AquaBoss(int level) {
		int size = 15 + level;
		int life = 30 + level * 5;
		init(0, 30, size, size, 3, 0, life, MineColor.AQUA);
		setShootTime(20);
	}

	protected void moves(Body ore) {
		if (getX() < 10 && getXv() < 0)
			setXv(-getXv());
		if (getX() + 10 > ShotCanvas.SCREEN_WIDTH - getW() && getXv() > 0)
			setXv(-getXv());
	}

	protected List<Wepon> shoots() {
		return Arrays.asList(
				new LineWepon(getX() + getW() / 2, getY()));
	}
}
