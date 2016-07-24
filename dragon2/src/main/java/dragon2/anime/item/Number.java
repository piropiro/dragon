package dragon2.anime.item;





import java.awt.Graphics;

import dragon2.Statics;

public class Number {

	public Number(int i, int j, int k, int l) {
		n = i;
		x = j;
		y = k;
		a = l;
		base = 20;
	}

	public void setBase(int i) {
		base = i;
	}

	public void move() {
		if (y < 0) {
			y++;
			return;
		}
		y += a;
		if (y >= base) {
			a = -(int) Math.max(0.0D, (double) a * 0.5D);
			y = base;
		} else {
			a += 2;
		}
	}

	public boolean end() {
		return a == 0 && y == base;
	}

	public void display(Graphics g1) {
		if (y < 0) {
			return;
		} else {
			g1.drawImage(Statics.num[n], x, y, null);
			return;
		}
	}

	static final double e = 0.5D;
	static final int g = 2;
	int base;
	public int n;
	public int x;
	public int y;
	public int a;
}
