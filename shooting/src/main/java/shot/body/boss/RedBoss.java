package shot.body.boss;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.Wepon;
import shot.body.wepon.WiderWeponH;

public class RedBoss extends Enemy {

	public RedBoss(int level) {
		int size = 15 + level;
		int life = 10 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, 0, size, size, 0, 1, life, MineColor.RED);
		setShootTime(30);
	}

	protected void moves(Body ore) {
	}

	protected List<Wepon> shoots() {
		return Arrays.asList(
			new WiderWeponH(getX() - getW(), getY() + getH(), 2, getW()),
			new WiderWeponH(getX() + getW(), getY() + getH(), 2, getW())
			);
	}
}
