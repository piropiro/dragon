




package mine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Vector;
import javax.swing.UIManager;

// Referenced classes of package mine:
//            ImageLoader

public class Mine {

	public Mine() {
	}

	public static Font getFont(int i) {
		return new Font("Dialog", 0, i);
	}

	public static boolean isWindows() {
		String s = System.getProperty("os.name");
		if (s.length() < 7)
			return false;
		else
			return s.substring(0, 7).equals("Windows");
	}

	public static void setLookAndFeel(int i) {
		try {
			switch (i) {
			case 1: // '\001'
				String s = "javax.swing.plaf.metal.MetalLookAndFeel";
				UIManager.setLookAndFeel(s);
				break;

			case 2: // '\002'
				String s1 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				UIManager.setLookAndFeel(s1);
				break;

			case 3: // '\003'
				String s2 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				UIManager.setLookAndFeel(s2);
				break;
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static String[] readStrings(String s) {
		try {
			Object obj = s.getClass().getResourceAsStream(s);
			if (obj == null)
				obj = new FileInputStream(s);
			InputStreamReader inputstreamreader = new InputStreamReader(
					((java.io.InputStream) (obj)));
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);
			Vector vector = new Vector();
			do {
				String s1 = bufferedreader.readLine();
				if (s1 != null)
					vector.add(s1);
				else
					return (String[]) vector.toArray(new String[1]);
			} while (true);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
	}

	public static void setErr(String s) {
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(s);
			System.setErr(new PrintStream(fileoutputstream));
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static void drawString(String s, int i, int j, int k, Graphics g) {
		Font font = g.getFont();
		int l = g.getFontMetrics(font).stringWidth(s);
		g.drawString(s, i + (k - l) / 2, j);
	}

	public static int mid(int i, int j, int k) {
		return Math.max(i, Math.min(j, k));
	}

	public static void setCenter(Window window) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension1 = window.getSize();
		dimension1.height = Math.min(dimension1.height, dimension.height);
		dimension1.width = Math.min(dimension1.width, dimension.width);
		window.setLocation((dimension.width - dimension1.width) / 2,
				(dimension.height - dimension1.height) / 2);
	}

	public static void setSprite(BufferedImage bufferedimage, int i, int j,
			int k) {
		WritableRaster writableraster = bufferedimage.getRaster();
		for (int l = 0; l < writableraster.getWidth(); l++) {
			for (int i1 = 0; i1 < writableraster.getHeight(); i1++) {
				int ai[] = new int[4];
				writableraster.getPixel(l, i1, ai);
				if (ai[0] == i && ai[1] == j && ai[2] == k) {
					ai[3] = 0;
					writableraster.setPixel(l, i1, ai);
				}
			}

		}

	}

	public static void setAlpha(Graphics g, double d) {
		AlphaComposite alphacomposite = AlphaComposite
				.getInstance(3, (float) d);
		((Graphics2D) g).setComposite(alphacomposite);
	}

	public static void setAntialias(Graphics g, boolean flag) {
		Object obj;
		if (flag)
			obj = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
		else
			obj = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
		java.awt.RenderingHints.Key key = RenderingHints.KEY_TEXT_ANTIALIASING;
		RenderingHints renderinghints = new RenderingHints(key, obj);
		((Graphics2D) g).setRenderingHints(renderinghints);
	}

	public static void drawImage(Image image, int i, int j, int k, int l,
			int i1, int j1, Graphics g) {
		g.drawImage(image, i, j, i + i1, j + j1, k, l, k + i1, l + j1, null);
	}

	public static void drawRotateImage(Image image, int i, int j, int k, int l,
			int i1, int j1, double d, Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		java.awt.geom.AffineTransform affinetransform = graphics2d
				.getTransform();
		graphics2d.rotate(d, i + i1 / 2, j + j1 / 2);
		graphics2d.drawImage(image, i, j, i + i1, j + j1, k, l, k + i1, l + j1,
				null);
		graphics2d.setTransform(affinetransform);
	}

	public static BufferedImage getScaleImage(Image image, double d,
			boolean flag) {
		int i = (int) ((double) image.getWidth(null) * d);
		int j = (int) ((double) image.getHeight(null) * d);
		return ImageLoader.getBuffer(image, i, j, flag);
	}

	public static BufferedImage getClipImage(Image image, int i, int j, int k,
			int l, boolean flag) {
		BufferedImage bufferedimage = ImageLoader.createImage(k, l, flag);
		Graphics g = bufferedimage.getGraphics();
		g.drawImage(image, 0, 0, k, l, i, j, i + k, j + l, null);
		return bufferedimage;
	}

	public static BufferedImage getRotateImage(Image image, int i, int j,
			int k, int l, double d) {
		BufferedImage bufferedimage = ImageLoader.createImage(k, l, true);
		Graphics2D graphics2d = (Graphics2D) bufferedimage.getGraphics();
		graphics2d.rotate(d, k / 2, l / 2);
		graphics2d.drawImage(image, 0, 0, k, l, i, j, i + k, j + l, null);
		return bufferedimage;
	}

	public static double refAngle(double d, double d1) {
		double d2;
		for (d2 = d - d1; d2 < 0.0D; d2 += 6.2831853071795862D)
			;
		for (; d2 >= 6.2831853071795862D; d2 -= 6.2831853071795862D)
			;
		if (d2 <= 1.5707963267948966D)
			return d;
		if (d2 >= 4.7123889803846897D)
			return d;
		for (d2 = d1 * 2D - d - 3.1415926535897931D; d2 < 0.0D; d2 += 6.2831853071795862D)
			;
		for (; d2 >= 6.2831853071795862D; d2 -= 6.2831853071795862D)
			;
		return d2;
	}

	public static final int METAL = 1;
	public static final int MOTIF = 2;
	public static final int WINDOWS = 3;
}
