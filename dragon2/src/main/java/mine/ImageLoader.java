




package mine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;

public class ImageLoader {

	public ImageLoader() {
	}

	public static void setup(Object obj) {
		c = obj.getClass();
	}

	public static BufferedImage getImage(Object obj, boolean flag) {
		Image image = loadImage(obj);
		if (image != null) {
			int i = image.getWidth(null);
			int j = image.getHeight(null);
			return getBuffer(image, i, j, flag);
		} else {
			return getBuffer(null, 0, 0, flag);
		}
	}

	public static BufferedImage createImage(int i, int j, boolean flag) {
		if (flag)
			return new BufferedImage(i, j, 2);
		else
			return new BufferedImage(i, j, 1);
	}

	public static BufferedImage getBuffer(Image image, int i, int j,
			boolean flag) {
		if (image == null || i <= 0 || j <= 0)
			return new BufferedImage(1, 1, 1);
		BufferedImage bufferedimage;
		if (flag)
			bufferedimage = new BufferedImage(i, j, 2);
		else
			bufferedimage = new BufferedImage(i, j, 1);
		Graphics g = bufferedimage.getGraphics();
		g.drawImage(image, 0, 0, i, j, null);
		return bufferedimage;
	}

	private static Image loadImage(Object obj) {
		Image image = null;
		if (obj instanceof File)
			image = loadImageF((File) obj);
		else if (obj instanceof String)
			image = loadImageS((String) obj);
		if (image != null)
			waitImage(image);
		else
			System.out.println("File not found. ( " + obj + " )");
		return image;
	}

	private static Image loadImageF(File file) {
		if (file.exists())
			return Toolkit.getDefaultToolkit().getImage(file.getPath());
		else
			return null;
	}

	private static Image loadImageS(String s) {
		if (c != null) {
			java.net.URL url = c.getResource(s);
			if (url != null)
				return Toolkit.getDefaultToolkit().getImage(url);
		}
		return loadImageF(new File(s));
	}

	private static void waitImage(Image image) {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			for (int i = 0; i < 100; i++) {
				if (toolkit.prepareImage(image, -1, -1, null))
					return;
				Thread.currentThread();
				Thread.sleep(100L);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static Class c;
}
