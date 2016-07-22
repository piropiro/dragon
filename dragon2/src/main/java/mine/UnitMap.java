




package mine;

import java.awt.*;
import java.util.Vector;

public class UnitMap {

	public UnitMap() {
	}

	protected boolean trueP(int i) {
		if (i < 0)
			return false;
		return i < PageN;
	}

	protected boolean trueT(int i) {
		if (i < 0)
			return false;
		return i < 4;
	}

	protected boolean trueX(int i) {
		if (i < 0)
			return false;
		return i < mapW;
	}

	protected boolean trueY(int i) {
		if (i < 0)
			return false;
		return i < mapH;
	}

	protected boolean trueD(int i, int j) {
		if (j < 0)
			return false;
		if (j >= numW[i] * numH[i])
			return false;
		return j != spData[i];
	}

	protected boolean trueA(int i, int j, int k, int l) {
		if (!trueP(i))
			return false;
		if (!trueT(j))
			return false;
		if (!trueX(k))
			return false;
		return trueY(l);
	}

	public int getPageNum() {
		return PageN;
	}

	public Point getLocation() {
		return new Point(mapX, mapY);
	}

	public Dimension getSize() {
		return new Dimension(mapW, mapH);
	}

	public Dimension getUnitSize() {
		return new Dimension(unitW, unitH);
	}

	public Point getUnitLocation(int i) {
		return new Point(unitX[i], unitY[i]);
	}

	public Dimension getUnitNum(int i) {
		return new Dimension(numW[i], numH[i]);
	}

	public void setLocation(int i, int j) {
		mapX = i;
		mapY = j;
	}

	public void setUnitSize(int i, int j) {
		unitW = i;
		unitH = j;
	}

	public void keep(int i) {
		int ai[][] = map[i][3];
		map[i][3] = map[i][2];
		map[i][2] = map[i][1];
		map[i][1] = ai;
		copy(i, 0, i, 1);
	}

	public void redo(int i) {
		int ai[][] = map[i][0];
		map[i][0] = map[i][1];
		map[i][1] = map[i][2];
		map[i][2] = map[i][3];
		map[i][3] = ai;
		repaint();
	}

	public void repaint() {
		repaint(true);
	}

	protected void repaint(boolean flag) {
		for (int i = 0; i < mapW; i++) {
			for (int j = 0; j < mapH; j++)
				Repaint[i][j] = flag;

		}

	}

	public int[] getPaintBox(int i, int j, int k, int l, int i1, int j1) {
		int k1 = k + i1;
		int l1 = l + j1;
		int i2 = k;
		int j2 = l;
		for (int k2 = k; k2 < k + i1; k2++) {
			for (int l2 = l; l2 < l + j1; l2++)
				if (map[i][j][k2][l2] != 0) {
					if (k2 < k1)
						k1 = k2;
					if (l2 < l1)
						l1 = l2;
					if (k2 > i2)
						i2 = k2;
					if (l2 > j2)
						j2 = l2;
				}

		}

		int ai[] = new int[4];
		ai[0] = k1 - k;
		ai[1] = l1 - l;
		ai[2] = (i2 - k1) + 1;
		ai[3] = (j2 - l1) + 1;
		return ai;
	}

	public int[] getPaintBox(int i, int j, int k, int l) {
		int i1 = i + k;
		int j1 = j + l;
		int k1 = i;
		int l1 = j;
		for (int i2 = i; i2 < i + k; i2++) {
			for (int j2 = j; j2 < j + l; j2++)
				if (Repaint[i2][j2]) {
					if (i2 < i1)
						i1 = i2;
					if (j2 < j1)
						j1 = j2;
					if (i2 > k1)
						k1 = i2;
					if (j2 > l1)
						l1 = j2;
				}

		}

		int ai[] = new int[4];
		ai[0] = i1 - i;
		ai[1] = j1 - j;
		ai[2] = (k1 - i1) + 1;
		ai[3] = (l1 - j1) + 1;
		return ai;
	}

	public void C(int i, int j, int k, int l, int i1, int j1, int k1) {
		PageN = i;
		mapX = j;
		mapY = k;
		mapW = l;
		mapH = i1;
		unitW = j1;
		unitH = k1;
		map = new int[PageN][4][mapW][mapH];
		Repaint = new boolean[mapW][mapH];
		repaint();
		unitX = new int[PageN];
		unitY = new int[PageN];
		numW = new int[PageN];
		numH = new int[PageN];
		spData = new int[PageN];
		unit = new Image[PageN];
		Visible = new boolean[PageN];
	}

	public void P(int i, int j, int k, int l, int i1, int j1, Image image) {
		unitX[i] = j;
		unitY[i] = k;
		numW[i] = l;
		numH[i] = i1;
		spData[i] = j1;
		unit[i] = image;
	}

	public void V(int i, boolean flag) {
		if (!trueP(i))
			return;
		if (Visible[i] != flag) {
			Visible[i] = flag;
			repaint();
		}
	}

	protected void VS(int i, int j, int k, int l, int i1) {
		if (map[i][j][k][l] == i1)
			return;
		map[i][j][k][l] = i1;
		if (j == 0)
			Repaint[k][l] = true;
	}

	public void S(int i, int j, int k, int l, int i1) {
		if (!trueA(i, j, k, l)) {
			return;
		} else {
			VS(i, j, k, l, i1);
			return;
		}
	}

	public int G(int i, int j, int k, int l) {
		if (!trueA(i, j, k, l))
			return 65535;
		else
			return map[i][j][k][l];
	}

	public void R(int i, int j, int ai[]) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		int k = Math.min(ai.length, mapW * mapH);
		for (int l = 0; l < k; l++) {
			int i1 = l % mapW;
			int j1 = l / mapW;
			VS(i, j, i1, j1, ai[l]);
		}

	}

	public void R(int i, int j, int ai[][]) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		int k = Math.min(mapW, ai.length);
		int l = Math.min(mapH, ai[0].length);
		for (int i1 = 0; i1 < k; i1++) {
			for (int j1 = 0; j1 < l; j1++)
				VS(i, j, i1, j1, ai[i1][j1]);

		}

	}

	public int[][] W(int i, int j) {
		if (!trueP(i))
			return null;
		if (!trueT(j))
			return null;
		if (mapW <= 0)
			return null;
		if (mapH <= 0)
			return null;
		int ai[][] = new int[mapW][mapH];
		for (int k = 0; k < mapW; k++) {
			for (int l = 0; l < mapH; l++)
				ai[k][l] = map[i][j][k][l];

		}

		return ai;
	}

	public synchronized void EP(int i, int j, int k, int l, Graphics g) {
		if (!trueX(k))
			return;
		if (!trueY(l))
			return;
		for (int i1 = 0; i1 < PageN; i1++)
			if (Visible[i1]) {
				int j1 = map[i1][0][k][l];
				if (trueD(i1, j1))
					VE(i1, k - i, l - j, j1, g);
			}

		Repaint[k][l] = false;
	}

	private void VE(int i, int j, int k, int l, Graphics g) {
		int i1 = mapX + j * unitW;
		int j1 = mapY + k * unitH;
		int k1 = i1 + unitW;
		int l1 = j1 + unitH;
		int i2 = unitX[i] + (l % numW[i]) * unitW;
		int j2 = unitY[i] + (l / numW[i]) * unitH;
		int k2 = i2 + unitW;
		int l2 = j2 + unitH;
		g.drawImage(unit[i], i1, j1, k1, l1, i2, j2, k2, l2, null);
	}

	public synchronized void E(int i, int j, int k, int l, boolean flag,
			Graphics g) {
		if (!flag || i != pxs || j != pys)
			repaint();
		pxs = i;
		pys = j;
		for (int i1 = 0; i1 < k; i1++) {
			for (int j1 = 0; j1 < l; j1++) {
				int k1 = i1 + i;
				int l1 = j1 + j;
				for (int i2 = 0; i2 < PageN; i2++)
					if (Visible[i2] && Repaint[k1][l1]) {
						int j2 = G(i2, 0, k1, l1);
						if (trueD(i2, j2))
							VE(i2, i1, j1, j2, g);
					}

				Repaint[k1][l1] = false;
			}

		}

	}

	public void F(Graphics g) {
		E(0, 0, mapW, mapH, false, g);
	}

	public void H(int i, int j, int k, int l) {
		if (!trueP(i))
			return;
		Vector vector = new Vector();
		clear(i, 3, 65535);
		HS(i, j, k, 0, vector);
		while (!vector.isEmpty()) {
			Point point = (Point) vector.firstElement();
			vector.remove(0);
			int i1 = map[i][3][point.x][point.y];
			if (i1 < l) {
				HS(i, point.x - 1, point.y, i1 + 1, vector);
				HS(i, point.x + 1, point.y, i1 + 1, vector);
				HS(i, point.x, point.y - 1, i1 + 1, vector);
				HS(i, point.x, point.y + 1, i1 + 1, vector);
			}
		}
	}

	private void HS(int i, int j, int k, int l, Vector vector) {
		if (!trueX(j))
			return;
		if (!trueY(k))
			return;
		if (map[i][2][j][k] != 0)
			return;
		if (map[i][3][j][k] <= l) {
			return;
		} else {
			map[i][3][j][k] = l;
			vector.add(new Point(j, k));
			return;
		}
	}

	public void J(int i, int j, int k, int l) {
		if (!trueP(i))
			return;
		Vector vector = new Vector();
		clear(i, 3, 65535);
		JS(i, j, k, 0, l, vector);
		Point point;
		int i1;
		for (; !vector.isEmpty(); JS(i, point.x, point.y + 1, i1, l, vector)) {
			point = (Point) vector.firstElement();
			vector.remove(0);
			i1 = map[i][3][point.x][point.y];
			JS(i, point.x - 1, point.y, i1, l, vector);
			JS(i, point.x + 1, point.y, i1, l, vector);
			JS(i, point.x, point.y - 1, i1, l, vector);
		}

	}

	private void JS(int i, int j, int k, int l, int i1, Vector vector) {
		if (!trueX(j))
			return;
		if (!trueY(k))
			return;
		int j1 = l + map[i][2][j][k];
		if (j1 > i1)
			return;
		if (map[i][3][j][k] <= j1)
			return;
		map[i][3][j][k] = j1;
		if (j1 == i1) {
			return;
		} else {
			vector.add(new Point(j, k));
			return;
		}
	}

	public void paint(int i, int j, int k, int l, int i1) {
		if (!trueA(i, j, k, l))
			return;
		Vector vector = new Vector();
		int j1 = map[i][j][k][l];
		if (i1 == j1)
			return;
		paint(i, j, k, l, j1, i1, vector);
		Point point;
		for (; vector.size() != 0; paint(i, j, point.x, point.y + 1, j1, i1,
				vector)) {
			point = (Point) vector.firstElement();
			vector.remove(0);
			paint(i, j, point.x - 1, point.y, j1, i1, vector);
			paint(i, j, point.x + 1, point.y, j1, i1, vector);
			paint(i, j, point.x, point.y - 1, j1, i1, vector);
		}

	}

	private void paint(int i, int j, int k, int l, int i1, int j1, Vector vector) {
		if (!trueX(k))
			return;
		if (!trueY(l))
			return;
		if (map[i][j][k][l] != i1) {
			return;
		} else {
			VS(i, j, k, l, j1);
			vector.add(new Point(k, l));
			return;
		}
	}

	public void fillRect(int i, int j, int k, int l, int i1, int j1, int k1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		int l1 = Math.max(0, k);
		int i2 = Math.max(0, l);
		int j2 = Math.min(k + i1, mapW);
		int k2 = Math.min(l + j1, mapH);
		for (int l2 = l1; l2 < j2; l2++) {
			for (int i3 = i2; i3 < k2; i3++)
				VS(i, j, l2, i3, k1);

		}

	}

	public void drawRect(int i, int j, int k, int l, int i1, int j1, int k1) {
		drawBox(i, j, k, l, (k + i1) - 1, (l + j1) - 1, k1);
	}

	public void fillBox(int i, int j, int k, int l, int i1, int j1, int k1) {
		int l1 = Math.min(k, i1);
		int i2 = Math.min(l, j1);
		int j2 = Math.abs(k - i1);
		int k2 = Math.abs(l - j1);
		fillRect(i, j, l1, i2, j2, k2, k1);
	}

	public void drawBox(int i, int j, int k, int l, int i1, int j1, int k1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		int l1 = Math.min(k, i1);
		int i2 = Math.min(l, j1);
		int j2 = Math.max(k, i1);
		int k2 = Math.max(l, j1);
		for (int l2 = l1; l2 <= j2; l2++)
			if (trueX(l2)) {
				if (trueY(i2))
					VS(i, j, l2, i2, k1);
				if (trueY(k2))
					VS(i, j, l2, k2, k1);
			}

		for (int i3 = i2; i3 <= k2; i3++)
			if (trueY(i3)) {
				if (trueX(l1))
					VS(i, j, l1, i3, k1);
				if (trueX(j2))
					VS(i, j, j2, i3, k1);
			}

	}

	public void fillDia(int i, int j, int k, int l, int i1, int j1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		for (int k1 = 0; k1 <= i1; k1++) {
			for (int l1 = k1 - i1; l1 <= i1 - k1; l1++)
				if (trueX(k + l1)) {
					if (trueY(l - k1))
						VS(i, j, k + l1, l - k1, j1);
					if (trueY(l + k1))
						VS(i, j, k + l1, l + k1, j1);
				}

		}

	}

	public void drawDia(int i, int j, int k, int l, int i1, int j1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		for (int k1 = 0; k1 <= i1; k1++) {
			int l1 = i1 - k1;
			if (trueX(k + k1)) {
				if (trueY(l - l1))
					VS(i, j, k + k1, l - l1, j1);
				if (trueY(l + l1))
					VS(i, j, k + k1, l + l1, j1);
			}
			if (trueX(k - k1)) {
				if (trueY(l - l1))
					VS(i, j, k - k1, l - l1, j1);
				if (trueY(l + l1))
					VS(i, j, k - k1, l + l1, j1);
			}
		}

	}

	public void copy(int i, int j, int k, int l) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(k))
			return;
		if (!trueT(l))
			return;
		for (int i1 = 0; i1 < mapW; i1++) {
			for (int j1 = 0; j1 < mapH; j1++)
				VS(k, l, i1, j1, map[i][j][i1][j1]);

		}

	}

	public void copy(int i, int j, int k, int l, int i1, int j1, int k1,
			int l1, int i2, int j2) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(k1))
			return;
		if (!trueT(l1))
			return;
		for (int k2 = 0; k2 < i1; k2++)
			if (trueX(k + k2) && trueX(i2 + k2)) {
				for (int l2 = 0; l2 < j1; l2++)
					if (trueY(l + l2) && trueY(j2 + l2))
						VS(k1, l1, i2 + k2, j2 + l2, map[i][j][k + k2][l + l2]);

			}

	}

	public void clear(int i, int j, int k) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		for (int l = 0; l < mapW; l++) {
			for (int i1 = 0; i1 < mapH; i1++)
				VS(i, j, l, i1, k);

		}

	}

	public void change(int i, int j, int k, int l, int i1, int j1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(l))
			return;
		if (!trueT(i1))
			return;
		for (int k1 = 0; k1 < mapW; k1++) {
			for (int l1 = 0; l1 < mapH; l1++)
				if (map[i][j][k1][l1] == k)
					VS(l, i1, k1, l1, j1);

		}

	}

	public void change(int i, int j, int k, int l, int i1, int j1, int k1) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(l))
			return;
		if (!trueT(i1))
			return;
		for (int l1 = 0; l1 < mapW; l1++) {
			for (int i2 = 0; i2 < mapH; i2++) {
				int j2 = map[i][j][l1][i2] != k ? k1 : j1;
				VS(l, i1, l1, i2, j2);
			}

		}

	}

	public void change(int i, int j, int k, int l, int ai[]) {
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(k))
			return;
		if (!trueT(l))
			return;
		for (int i1 = 0; i1 < mapW; i1++) {
			for (int j1 = 0; j1 < mapH; j1++) {
				int k1 = map[i][j][i1][j1];
				if (k1 < ai.length)
					VS(k, l, i1, j1, ai[k1]);
			}

		}

	}

	public void affine(int i, int j, int k, int l, int i1, int j1, int k1,
			int l1, int i2, int j2, int ai[]) {
		if (ai == null)
			return;
		if (ai.length < 6)
			return;
		if (!trueP(i))
			return;
		if (!trueT(j))
			return;
		if (!trueP(k1))
			return;
		if (!trueT(l1))
			return;
		for (int k2 = 0; k2 < i1; k2++)
			if (trueX(k + k2)) {
				for (int l2 = 0; l2 < j1; l2++)
					if (trueY(l + l2)) {
						int i3 = k2 * ai[0] + l2 * ai[1] + ai[2];
						int j3 = k2 * ai[3] + l2 * ai[4] + ai[5];
						if (trueX(i2 + i3) && trueY(j2 + j3))
							VS(k1, l1, i3, j3, map[i][j][k + k2][l + l2]);
					}

			}

	}

	public static final int FALSE = 65535;
	protected int map[][][][];
	protected int PageN;
	protected int mapX;
	protected int mapY;
	protected int mapW;
	protected int mapH;
	protected int unitW;
	protected int unitH;
	protected int unitX[];
	protected int unitY[];
	protected int numW[];
	protected int numH[];
	protected int spData[];
	protected Image unit[];
	protected boolean Repaint[][];
	protected boolean Visible[];
	protected int pxs;
	protected int pys;
}
