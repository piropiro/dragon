package shot.body.enemy;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.body.Body;
import shot.body.wepon.NormalWepon;
import shot.body.wepon.Wepon;

public class GreenEnemyV extends Enemy {

	public GreenEnemyV(int x) {
		init(x, 0, 7, 7, 0, 3, 1, MineColor.GREEN);
		setShootTime(20);
	}

	protected void moves(Body ore) {
	}

	protected List<Wepon> shoots() {
		return Arrays.asList(
			new NormalWepon(getX() + 1, getY(), -2, 0),
			new NormalWepon(getX() + 1, getY(), 2, 0)
		);
	}
}
