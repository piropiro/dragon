package dragon2.anime.listener;





import java.awt.Component;
import java.awt.Graphics;
import mine.util.Point;
import java.util.ArrayList;
import java.util.List;

import dragon2.UnitWorks;
import dragon2.anime.item.Arrow;
import mine.UnitMap;

public class ArrowAnime2 implements AnimeListener {

	public ArrowAnime2(UnitWorks unitworks, UnitMap unitmap, Point point, int i,
			int ai[], int j) {
		uw = unitworks;
		V = unitmap;
		sleep = j;
		box = unitmap.getPaintBox(4, 1, 0, 0, 20, 15);
		point.x -= box[0] * 32;
		point.y -= box[1] * 32;
		List<Point> vector = new ArrayList<>();

		for (int k = 0; k < 20; k++) {
			for (int l = 0; l < 15; l++)
				if (unitmap.G(4, 1, k, l) != 0)
					vector.add(new Point(k * 32, l * 32));

		}

		arr = new ArrayList<>();
		for (Point point1 : vector) {
			point1.x -= box[0] * 32;
			point1.y -= box[1] * 32;
			arr.add(new Arrow(point, point1, i, ai, true));
		}

	}

	public void animation(Component component) {
		component.setLocation(box[0] * 32, box[1] * 32);
		component.setSize(box[2] * 32, box[3] * 32);
		for (; !arr.get(0).end(); uw.sleep(sleep))
			component.repaint();

		uw.sleep(sleep);
	}

	public void paint(Graphics g) {
		for (Arrow arrow : arr) {
			arrow.move();
			arrow.paint(g);
		}

	}

	UnitWorks uw;
	UnitMap V;
	int sleep;
	List<Arrow> arr;
	int box[];
}
