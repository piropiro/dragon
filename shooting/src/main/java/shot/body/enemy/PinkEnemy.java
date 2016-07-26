package shot.body.enemy;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.body.Body;
import shot.body.wepon.NormalWepon;
import shot.body.wepon.Wepon;

public class PinkEnemy extends Enemy {

	public PinkEnemy(int x) {
		init(x, 0, 7, 7, 0, 2, 1, MineColor.PINK);
		setShootTime(50);
	}

	protected void moves(Body ore) {
		if (getY() >= ore.getY() && getYv() > 0) {
			if (getX() < ore.getX())
				setXv(getYv());
			else
				setXv(-getYv());
			setYv(0);
		}
	}

	protected List<Wepon> shoots() {
		return Arrays.asList(
			new NormalWepon(getX() + 1, getY(), -1, -1),
			new NormalWepon(getX() + 1, getY(), 1, -1),
			new NormalWepon(getX() + 1, getY(), -1, 1),
			new NormalWepon(getX() + 1, getY(), 1, 1)
			);
	}
}
