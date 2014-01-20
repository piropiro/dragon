/*
 * Created on 2004/10/10
 */
package mine.awt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mine.MineException;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;

/**
 * @author saito
 */
public class ImageLoaderAWT implements MineImageLoader {
	public MineImage load(String path) throws MineException {
		return new ImageAWT(ImageLoader.load(path));
	}

	public MineImage[][] loadTile(String path, int width, int height) throws MineException {
		BufferedImage[][] btile = ImageLoader.loadTile(path, width, height);

		MineImage[][] tile = new MineImage[btile.length][btile[0].length];
		for (int i=0; i<btile.length; i++) {
			for (int j=0; j<btile[i].length; j++) {
				tile[i][j] = new ImageAWT(btile[i][j]);
			}
		}
		return tile;
	}
	
	public MineImage getBuffer(int width, int height) {
		return new ImageAWT(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
	}

	/**
	 * イメージを指定されたサイズに拡大縮小します。
	 * @param img - ソースイメージ
	 * @param width - 指定された幅
	 * @param height - 指定された高さ
	 * @return 指定されたサイズに拡大縮小されたイメージ
	 */
	public MineImage resize(MineImage img, int width, int height) {
		BufferedImage simg = (BufferedImage)img.getImage();
		BufferedImage rimg = new BufferedImage(width, height, simg.getType());
		Graphics g = rimg.getGraphics();
		g.drawImage(simg, 0, 0, width, height, null);
		return new ImageAWT(rimg);
	}

	/**
	 * イメージを指定された比率で拡大縮小します。
	 * @param img - ソースイメージ
	 * @param rate - サイズ比率
	 * @return 指定された比率で拡大縮小されたイメージ
	 */
	public MineImage resize(MineImage img, double rate) {
		int width = (int) (img.getWidth() * rate);
		int height = (int) (img.getHeight() * rate);
		return resize(img, width, height);
	}
}
