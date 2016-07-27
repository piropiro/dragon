package dragon2.anime.listener;





import java.awt.Component;
import java.awt.Graphics;

import dragon2.Statics;
import dragon2.UnitWorks;
import mine.Mine;
import mine.util.Point;

public class SingleAnime implements AnimeListener {

	public SingleAnime(UnitWorks unitworks, Point point, int ai[], int i) {
		uw = unitworks;
		ba = point;
		imgs = ai;
		sleep = i;
	}

	public void animation(Component component) {
		for (int i = 0; i < imgs.length; i++) {
			img = imgs[i];
			component.repaint();
			uw.sleep(sleep);
		}

	}

	public void paint(Graphics g) {
		int i = (img % 15) * 32;
		int j = (img / 15) * 32;
		Mine.drawImage(Statics.anime, 0, 0, i, j, 32, 32, g);
	}

	UnitWorks uw;
	Point ba;
	int imgs[];
	int img;
	int sleep;
}
