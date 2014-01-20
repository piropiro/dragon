package mine.awt;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import mine.MineException;
import mine.io.FileIO;

/**
 * イメージをファイルから読むクラス。
 * @author k-saito
 * @version 1.0
 */
public class ImageLoader {

	/**
	 * ファイルからイメージを読み込む。<p>
	 *
	 * @param path イメージファイルのパス
	 * @return ファイルから読み込んだイメージ
	 * @throws MineException 読み込みに失敗した。
	 */
	public static BufferedImage load(String path) throws MineException {
		InputStream in = FileIO.getInputStream(path);
		try {
			BufferedImage img = ImageIO.read(in);

			for (int x=0; x<img.getWidth(); x++) {
				for (int y=0; y<img.getHeight(); y++) {
					if (img.getRGB(x, y) == 0xFFC9FFFF) {
						img.setRGB(x,  y, 0);
					}
				}
			}
			return img;
		} catch (IOException e) {
			throw new MineException(e);
		} finally {
			FileIO.close(in);
		}
	}

	/**
	 * ファイルからタイルを読み込む。<p>
	 *
	 * @param path イメージファイルのパス
	 * @param width タイルの横幅
	 * @param height タイルの高さ
	 * @return ファイルから読み込んだタイル
	 * @throws MineException ファイルの読み込みに失敗した。
	 */
	public static BufferedImage[][] loadTile(String path, int width, int height) throws MineException {
		BufferedImage img = load(path);

		int xNum = img.getWidth() / width;
		int yNum = img.getHeight() / height;

		if (xNum == 0 || yNum == 0) {
			return new BufferedImage[][] { { img }
			};
		}

		BufferedImage[][] tiles = new BufferedImage[yNum][xNum];
		for (int y = 0; y < yNum; y++) {
			for (int x = 0; x < xNum; x++) {
				tiles[y][x] = img.getSubimage(x * width, y * height, width, height);
			}
		}
		return tiles;
	}
}
