package dragon3.anime.listener;

import java.awt.image.BufferedImage;

import mine.awt.GraphicsAWT;
import mine.awt.ImageAWT;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.UnitMap;
import dragon3.anime.AnimeWorks;
import dragon3.common.constant.Page;

public class EraseAnime implements AnimeListener {

	private UnitMap map;

	private int bodyX;
	private int bodyY;
	private MineImage offi;
	private int count;

	/*** Constructer ***********************/

	public EraseAnime(UnitMap map, int x, int y) {
		this.map = map;
		this.bodyX = x;
		this.bodyY = y;
		offi = createOffi(map, x, y);
	}

	/*** SourceImage ********************/

	private static MineImage createOffi(UnitMap map, int x, int y) {
		BufferedImage offi = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		MineGraphics offg = new GraphicsAWT(offi.getGraphics());
		int img = map.getData(Page.P20, x, y);
		int sts = map.getData(Page.P50, x, y);
		map.setData(Page.P20, x, y, 0);
		map.setData(Page.P30, x, y, 0);
		map.setData(Page.P50, x, y, 0);
		offg.drawImage(map.getBuffer(x, y), 0, 0);
		map.setData(Page.P20, x, y, img);
		map.setData(Page.P50, x, y, sts);
		return new ImageAWT(offi);
	}

	/*** Display **********************************/

	public void animation(AnimeWorks ac) {
		for (count = 1; count <= 4; count++) {
			ac.repaint();
			ac.sleep(100);
		}
		map.setData(Page.P20, bodyX, bodyY, 0);
		map.setData(Page.P50, bodyX, bodyY, 0);
		ac.setVisible(false);
	}

	/*** Paint *********************************/

	public void paint(MineGraphics g) {
		for (int x = 0; x < 8; x++) {
			int sx = x * 4;
			int sy = 0;
			int w = count;
			int h = 32;
			g.drawImage(offi, sx, sy, w, h, sx, sy);
		}
		for (int y = 0; y < 8; y++) {
			int sx = 0;
			int sy = y * 4;
			int w = 32;
			int h = count;
			g.drawImage(offi, sx, sy, w, h, sx, sy);
		}
	}
}
