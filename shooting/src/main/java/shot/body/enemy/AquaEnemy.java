package shot.body.enemy;

import mine.paint.MineColor;

public class AquaEnemy extends BlueEnemy {

	public AquaEnemy(int x) {
		super(x);
		setColor(MineColor.AQUA);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setShootTime(10);
		setLife(3);
	}
}
