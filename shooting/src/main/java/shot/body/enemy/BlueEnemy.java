package shot.body.enemy;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.wepon.NormalWepon;
import shot.body.wepon.Wepon;

public class BlueEnemy extends Enemy {

	public BlueEnemy(int x) {
		init(x, 0, 7, 7, 2, 1, 1, MineColor.BLUE);
		setShootTime(50);
	}

	protected void moves(Body ore) {
		if (getTime() % 15 == 0 || getX() < 0 || getX() + getW() > ShotCanvas.SCREEN_WIDTH)
			setXv(-getXv());
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
