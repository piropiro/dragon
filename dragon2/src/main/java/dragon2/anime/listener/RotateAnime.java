package dragon2.anime.listener;





import java.awt.*;

import dragon2.UnitWorks;
import dragon2.anime.item.Arrow;

public class RotateAnime implements AnimeListener {

	public RotateAnime(UnitWorks unitworks, Point point, Point point1, int ai[], int i) {
		uw = unitworks;
		sleep = i;
		ba = point;
		r = Math.sqrt((point.x - point1.x) * (point.x - point1.x)
				+ (point.y - point1.y) * (point.y - point1.y));
		arr = new Arrow(point, point1, 36, ai, false);
		arr.theta = Math.atan2(point1.y - point.y, point1.x - point.x);
	}

	public void animation(Component component) {
		for (; !arr.end(); uw.sleep(sleep)) {
			arr.move();
			arr.theta += 0.17453292519943295D;
			int i = ba.x + (int) (r * Math.cos(arr.theta));
			int j = ba.y + (int) (r * Math.sin(arr.theta));
			component.setLocation(i, j);
			component.repaint();
		}

		uw.sleep(sleep);
	}

	public void paint(Graphics g) {
		arr.paint(g);
	}

	UnitWorks uw;
	int sleep;
	Arrow arr;
	Point ba;
	double r;
}
