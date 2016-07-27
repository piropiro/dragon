package shot.body.enemy;

import mine.paint.MineColor;

public class RedEnemy extends WhiteEnemy {

	public RedEnemy(int y) {
		super(y);
		setColor(MineColor.RED);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setLife(3);
	}
}
