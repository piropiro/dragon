package shot.body.enemy;

import mine.paint.Colors;

public class LimeEnemyV extends GreenEnemyV {

	public LimeEnemyV(int x) {
		super(x);
		setColor(Colors.LIME);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(4);
		setLife(3);
	}
}
