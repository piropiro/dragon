package dragon2.anime.listener;





import java.awt.*;

import dragon2.UnitWorks;
import mine.ImageLoader;

public class PictureAnime implements AnimeListener {

	public PictureAnime(UnitWorks unitworks, String s) {
		uw = unitworks;
		img = ImageLoader.getImage(s, true);
	}

	public void animation(Component component) {
		component.repaint();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	private UnitWorks uw;
	private Image img;
}
