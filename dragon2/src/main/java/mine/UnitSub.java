

// Decompiler options: packimports(3)


package mine;

import java.awt.Dimension;
import mine.util.Point;

// Referenced classes of package mine:
//            UnitMap, Mine

public class UnitSub {

	public UnitSub(UnitMap unitmap) {
		V = unitmap;
		face = 0;
	}

	public Point moveK(int i, int j, boolean flag, boolean flag1,
			boolean flag2, boolean flag3) {
		Dimension dimension = V.getSize();
		if (flag)
			j = Math.max(j - 1, 0);
		if (flag1)
			j = Math.min(j + 1, dimension.height - 1);
		if (flag2)
			i = Math.max(i - 1, 0);
		if (flag3)
			i = Math.min(i + 1, dimension.width - 1);
		return new Point(i, j);
	}

	public Point getH(int i, int j, int k, int l) {
		Dimension dimension = V.getSize();
		int i1 = Mine.mid(0, i - k / 2, dimension.width - k);
		int j1 = Mine.mid(0, j - l / 2, dimension.height - l);
		return new Point(i1, j1);
	}

	public Point moveS(int i, int j, int k) {
		Point point = new Point(j, k);
		int l = V.G(i, 3, j, k);
		switch (face) {
		case 0: // '\0'
			l = moves(i, j, k - 1, l, point, 0);
			break;

		case 1: // '\001'
			l = moves(i, j, k + 1, l, point, 1);
			break;

		case 2: // '\002'
			l = moves(i, j - 1, k, l, point, 2);
			break;

		case 3: // '\003'
			l = moves(i, j + 1, k, l, point, 3);
			break;
		}
		l = moves(i, j, k - 1, l, point, 0);
		l = moves(i, j, k + 1, l, point, 1);
		l = moves(i, j - 1, k, l, point, 2);
		l = moves(i, j + 1, k, l, point, 3);
		if (point.x != j)
			return point;
		if (point.y != k)
			return point;
		else
			return null;
	}

	private int moves(int i, int j, int k, int l, Point point, int i1) {
		int j1 = V.G(i, 3, j, k);
		if (l > j1) {
			point.x = j;
			point.y = k;
			face = i1;
			return j1;
		} else {
			return l;
		}
	}

	private UnitMap V;
	private int face;
	static final int F_UP = 0;
	static final int F_DOWN = 1;
	static final int F_LEFT = 2;
	static final int F_RIGHT = 3;
}
