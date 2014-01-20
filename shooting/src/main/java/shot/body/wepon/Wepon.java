package shot.body.wepon;

import mine.paint.MineGraphics;
import shot.body.Body;

public abstract class Wepon extends Body {
	
	public Wepon(int x, int y, int w, int h) {
		super(x, y, w, h, 1);
	}

	public abstract void move();
	public abstract void paint(MineGraphics g);
}
