package shot.body.enemy;

import mine.paint.MineColor;

public class PurpleEnemy extends PinkEnemy {

	public PurpleEnemy(int x) {
		super(x);
		setColor(MineColor.PURPLE);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(0);
		setLife(3);
	}
}
