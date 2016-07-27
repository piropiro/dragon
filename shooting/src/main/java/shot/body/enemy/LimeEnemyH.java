package shot.body.enemy;

import mine.paint.MineColor;

public class LimeEnemyH extends GreenEnemyH {

	public LimeEnemyH(int y) {
		super(y);
		setColor(MineColor.LIME);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(5);
		setLife(3);
	}
}
