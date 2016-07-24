




package mine;

import java.awt.*;

// Referenced classes of package mine:
//            JCanvas, UnitMap

public class UnitCanvas extends JCanvas {

	public UnitCanvas(int i, int j) {
		super(i, j);
	}

	protected void setUnitMap(UnitMap unitmap) {
		V = unitmap;
		Dimension dimension = unitmap.getUnitSize();
		Dimension dimension1 = getSize();
		dx = dimension1.width / dimension.width;
		dy = dimension1.height / dimension.height;
		hjx = 0;
		hjy = 0;
	}

	public Point getHJ() {
		return new Point(hjx, hjy);
	}

	public void setHJ(int i, int j) {
		hjx = i;
		hjy = j;
	}

	public Dimension getMapSize() {
		return new Dimension(dx, dy);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (offi == null) {
			Dimension dimension = V.getUnitSize();
			Dimension dimension2 = V.getSize();
			offi = createImage(dimension2.width * dimension.width,
					dimension2.height * dimension.height);
			offg = offi.getGraphics();
		}
		Dimension dimension1 = V.getSize();
		Dimension dimension3 = V.getUnitSize();
		V.E(0, 0, dimension1.width, dimension1.height, true, offg);
		int i = 0;
		int j = 0;
		int k = dx * dimension3.width;
		int l = dy * dimension3.height;
		int i1 = hjx * dimension3.width;
		int j1 = hjy * dimension3.height;
		int k1 = i1 + dx * dimension3.width;
		int l1 = j1 + dy * dimension3.height;
		g.drawImage(offi, i, j, k, l, i1, j1, k1, l1, null);
	}

	private Image offi;
	private Graphics offg;
	protected int hjx;
	protected int hjy;
	protected int dx;
	protected int dy;
	protected UnitMap V;
}
