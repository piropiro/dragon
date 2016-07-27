package shot.body.enemy;

import java.util.Arrays;
import java.util.List;

import mine.paint.MineColor;
import mine.util.Randomer;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.wepon.Wepon;

public class YellowEnemy extends Enemy {

	public YellowEnemy(int y) {
		init(0, y, 7, 7, 2, 2, 1, MineColor.YELLOW);
		if (Randomer.getInstance().nextBoolean()) {
			setX(0);
		} else {
			setX(ShotCanvas.SCREEN_WIDTH);
		}
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

	protected List<Wepon> shoots() {
		return Arrays.asList();
	}
}
