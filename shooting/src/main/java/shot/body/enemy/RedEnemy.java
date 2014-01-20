package shot.body.enemy;

import mine.paint.Colors;

public class RedEnemy extends WhiteEnemy {

	public RedEnemy(int y) {
		super(y);
		setColor(Colors.RED);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setLife(3);
	}
}
