package shot.body.enemy;

import mine.paint.Colors;

public class AquaEnemy extends BlueEnemy {

	public AquaEnemy(int x) {
		super(x);
		setColor(Colors.AQUA);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(10);
		setLife(3);
	}
}
