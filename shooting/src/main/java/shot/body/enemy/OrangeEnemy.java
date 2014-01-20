package shot.body.enemy;

import mine.paint.Colors;

public class OrangeEnemy extends YellowEnemy {

	public OrangeEnemy(int y) {
		super(y);
		setColor(Colors.ORANGE);
		setXv(getXv()*2);
		setYv(getYv()*2);
		setLife(5);
	}
}
