




package mine;

import java.awt.*;
import java.awt.image.BufferedImage;

// Referenced classes of package mine:
//            JCanvas

public abstract class BufferCanvas extends JCanvas {

	public BufferCanvas(int i, int j) {
		super(i, j);
		offi = null;
	}

	public void setSize(int i, int j) {
		super.setSize(i, j);
		offi = null;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (offi == null) {
			offi = new BufferedImage(size.width, size.height, 2);
			offg = (Graphics2D) offi.getGraphics();
		}
		paints(offg);
		g.drawImage(offi, 0, 0, null);
	}

	public abstract void paints(Graphics g);

	public void clear() {
		if (offg == null) {
			return;
		} else {
			offg.setBackground(new Color(0, 0, 0, 0));
			offg.clearRect(0, 0, size.width, size.height);
			return;
		}
	}

	private BufferedImage offi;
	private Graphics2D offg;
}
