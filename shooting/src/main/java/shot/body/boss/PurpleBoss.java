package shot.body.boss;

import mine.paint.Colors;
import shot.ShotCanvas;
import shot.body.Body;
import shot.body.enemy.Enemy;
import shot.body.wepon.BlueEnemyWepon;

public class PurpleBoss extends Enemy {

	private int shootCount = 0;

	public PurpleBoss(int level) {
		int size = 15 + level;
		int life = 10 + level * 5;
		init(ShotCanvas.SCREEN_WIDTH / 2 - size / 2, ShotCanvas.SCREEN_HEIGHT,
				size, size, 0, -1, life, Colors.PURPLE);
		setShootTime(3);
	}

	protected void moves(Body ore) {
		if (getY() < 0 && getYv() < 0)
			setYv(-getYv());
		if (getY() > ShotCanvas.SCREEN_HEIGHT - getH() && getYv() > 0)
			setYv(-getYv());
	}

	protected Body[] shoots() {
		int x = 0;
		int y = 0;
		switch (shootCount++ % 8) {
		case 0:
			x = 2;
			y = 1;
			break;
		case 1:
			x = 1;
			y = 2;
			break;
		case 2:
			x = -1;
			y = 2;
			break;
		case 3:
			x = -2;
			y = 1;
			break;
		case 4:
			x = -2;
			y = -1;
			break;
		case 5:
			x = -1;
			y = -2;
			break;
		case 6:
			x = 1;
			y = -2;
			break;
		case 7:
			x = 2;
			y = -1;
			break;
		}
		Body[] newtama = new Body[1];
		newtama[0] = new BlueEnemyWepon(getX() + getW() / 2, getY() + getH() / 2, x, y);
		return newtama;
	}
}