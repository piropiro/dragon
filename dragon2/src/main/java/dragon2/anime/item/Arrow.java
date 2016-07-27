package dragon2.anime.item;





import java.awt.Graphics;
import mine.util.Point;

import dragon2.Statics;
import mine.Mine;

public class Arrow {

	public Arrow(Point point, Point point1, int i, int ai[], boolean flag) {
		ba = point;
		bb = point1;
		max = i;
		imgs = ai;
		mflag = flag;
		theta = Math.atan2(point1.y - point.y, point1.x - point.x);
		n = 0;
	}

	public void move() {
		n = Math.min(n + 1, max);
		x = ba.x + ((bb.x - ba.x) * n) / max;
		y = ba.y + ((bb.y - ba.y) * n) / max;
	}

	public boolean end() {
		return n == max;
	}

	public void paint(Graphics g) {
		int i;
		if (end())
			i = imgs.length - 1;
		else
			i = n % Math.max(1, imgs.length - 1);
		int j = mflag ? x + 1 : 1;
		int k = mflag ? y + 1 : 1;
		int l = (imgs[i] % 15) * 32 + 1;
		int i1 = (imgs[i] / 15) * 32 + 1;
		Mine.drawRotateImage(Statics.anime, j, k, l, i1, 30, 30, theta, g);
	}

	Point ba;
	Point bb;
	public int max;
	public int x;
	public int y;
	int n;
	int imgs[];
	boolean mflag;
	public double theta;
}
