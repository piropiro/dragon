package shot.body.enemy;

import mine.paint.MineColor;

public class LimeEnemyV extends GreenEnemyV {

	public LimeEnemyV(int x) {
		super(x);
		setColor(MineColor.LIME);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(4);
		setLife(3);
	}
}
