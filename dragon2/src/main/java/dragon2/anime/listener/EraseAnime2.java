package dragon2.anime.listener;




import java.awt.Component;
import java.awt.Graphics;

import dragon2.Statics;
import dragon2.UnitWorks;
import mine.Mine;
import mine.UnitMap;
import mine.util.Point;

public class EraseAnime2 implements AnimeListener {

	public EraseAnime2(UnitWorks unitworks, UnitMap unitmap, Point point) {
		uw = unitworks;
		V = unitmap;
		ba = point;
	}

	public void animation(Component component) {
		component.setSize(96, 32);
		component.setLocation((ba.x - 1) * 32, ba.y * 32);
		component.setVisible(true);
		img = V.G(2, 0, ba.x, ba.y);
		V.S(2, 0, ba.x, ba.y, 0);
		for (count = 0; count <= 8; count++) {
			component.repaint();
			uw.sleep(50L);
		}

	}

	public void paint(Graphics g) {
		Mine.setAlpha(g, (1.0D * (double) (8 - count)) / 8D);
		halfPaint(g, 32 + count * 4, 0, true);
		halfPaint(g, 32 - count * 2, 0, false);
		Mine.setAlpha(g, 1.0D);
	}

	private void halfPaint(Graphics g, int i, int j, boolean flag) {
		int k = i + 1;
		int l = k + 30;
		int i1 = flag ? j + 1 : j + 16;
		int j1 = i1 + 15;
		int k1 = (img % 15) * 32 + 1;
		int l1 = k1 + 30;
		int i2 = (img / 15) * 32 + (flag ? 1 : 16);
		int j2 = i2 + 15;
		g.drawImage(Statics.chara, k, i1, l, j1, k1, i2, l1, j2, null);
	}

	UnitWorks uw;
	UnitMap V;
	Point ba;
	int img;
	int count;
	static final int MAX = 8;
}
