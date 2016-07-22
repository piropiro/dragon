package dragon2.anime.listener;





import java.awt.*;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.anime.item.Number;
import mine.Mine;
import mine.UnitMap;

public class SummonAnime implements AnimeListener {

	public SummonAnime(UnitWorks unitworks, UnitMap unitmap, Point point, int i) {
		uw = unitworks;
		V = unitmap;
		bb = point;
		img = i;
		n = new Number(30, 1, 56, -14);
		n.setBase(100);
	}

	public void animation(Component component) {
		component.setSize(32, 56);
		component.setLocation(bb.x * 32, bb.y * 32 - 32);
		while (n.y > 8) {
			n.move();
			component.repaint();
			uw.sleep(30L);
		}
		component.setSize(32, 64);
		n.setBase(36);
		for (; !n.end(); uw.sleep(30L)) {
			n.move();
			component.repaint();
		}

		V.S(2, 0, bb.x, bb.y, img);
		uw.sleep(100L);
	}

	public void paint(Graphics g) {
		int i = n.x;
		int j = n.y - 4;
		int k = (img % 15) * 32 + 1;
		int l = (img / 15) * 32 + 1;
		Mine.drawImage(Statics.chara, i, j, k, l, 30, 30, g);
	}

	private UnitWorks uw;
	private UnitMap V;
	private Point bb;
	private int img;
	Number n;
}
