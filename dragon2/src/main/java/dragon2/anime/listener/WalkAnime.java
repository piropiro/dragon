package dragon2.anime.listener;



import java.awt.Component;
import java.awt.Graphics;

import dragon2.Statics;
import dragon2.UnitWorks;
import mine.Mine;
import mine.UnitMap;
import mine.UnitSub;
import mine.util.Point;

public class WalkAnime implements AnimeListener {

	public WalkAnime(UnitWorks unitworks, UnitMap unitmap, Point point) {
		uw = unitworks;
		V = unitmap;
		ba = point;
		img = unitmap.G(2, 0, point.x, point.y);
		sts = unitmap.G(5, 0, point.x, point.y);
	}

	public void animation(Component component) {
		UnitSub unitsub = new UnitSub(V);
		V.S(2, 0, ba.x, ba.y, 0);
		V.S(5, 0, ba.x, ba.y, 0);
		while ((bb = unitsub.moveS(0, ba.x, ba.y)) != null) {
			for (int i = 1; i <= 8; i++) {
				int j = ba.x * 32 + ((bb.x - ba.x) * 32 * i) / 8;
				int k = ba.y * 32 + ((bb.y - ba.y) * 32 * i) / 8;
				component.setLocation(j, k);
				component.repaint();
				uw.sleep(15L);
			}

			ba = bb;
		}
		V.S(2, 0, ba.x, ba.y, img);
		V.S(5, 0, ba.x, ba.y, sts);
	}

	public void paint(Graphics g) {
		int i = (img % 15) * 32 + 1;
		int j = (img / 15) * 32 + 1;
		Mine.drawImage(Statics.chara, 1, 1, i, j, 30, 30, g);
		int k = sts * 32 + 1;
		int l = 1;
		Mine.drawImage(Statics.status, 1, 1, k, l, 30, 30, g);
	}

	static final int MAX = 8;
	UnitWorks uw;
	UnitMap V;
	Point ba;
	Point bb;
	int img;
	int sts;
}
