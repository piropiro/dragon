package dragon2.card;





import java.awt.Graphics;

import dragon2.Statics;
import mine.Mine;

public class Card {

	public Card(int i, int j, int k, int l) {
		n = i;
		x = j;
		y = k;
		color = l;
		status = 0;
	}

	public void close() {
		status = 1;
	}

	public void open() {
		status = 2;
	}

	public void dispose() {
		status = 0;
	}

	public void lose() {
		status = 3;
		count = 0;
	}

	public void win() {
		status = 4;
	}

	public int getStatus() {
		return status;
	}

	public void setLocation(int i, int j) {
		x = i;
		y = j;
	}

	public void paint(Graphics g) {
		switch (status) {
		case 1: // '\001'
			cardPaint(g, 2 + color);
			break;

		case 2: // '\002'
			cardPaint(g, 4 + color);
			numPaint(g);
			break;

		case 3: // '\003'
			losePaint(g);
			break;

		case 4: // '\004'
			cardPaint(g, 2 + color);
			break;
		}
	}

	private void cardPaint(Graphics g, int i) {
		int j = x + 1;
		int k = y + 1;
		int l = j + 30;
		int i1 = k + 30;
		int j1 = 32 * i + 1;
		int k1 = 1;
		int l1 = j1 + 30;
		int i2 = k1 + 30;
		g.drawImage(Statics.card, j, k, l, i1, j1, k1, l1, i2, null);
	}

	private void numPaint(Graphics g) {
		if (n < 10) {
			g.drawImage(Statics.num[n], x + 11, y + 10, null);
		} else {
			g.drawImage(Statics.num[n / 10], x + 6, y + 10, null);
			g.drawImage(Statics.num[n % 10], x + 15, y + 10, null);
		}
	}

	private void losePaint(Graphics g) {
		count = Math.min(count + 1, 20);
		Mine.setAlpha(g, (1.0D * (double) (20 - count)) / 20D);
		if (color == 0) {
			halfPaint(g, x + count * 3, y, true);
			halfPaint(g, x + count * 1, y, false);
		} else {
			halfPaint(g, x - count * 3, y, true);
			halfPaint(g, x - count * 1, y, false);
		}
		Mine.setAlpha(g, 1.0D);
	}

	private void halfPaint(Graphics g, int i, int j, boolean flag) {
		int k = i + 1;
		int l = k + 30;
		int i1 = flag ? j + 1 : j + 16;
		int j1 = i1 + 15;
		int k1 = 32 * (4 + color) + 1;
		int l1 = k1 + 30;
		byte byte0 = ((byte) (flag ? 1 : 16));
		int i2 = byte0 + 15;
		g.drawImage(Statics.card, k, i1, l, j1, k1, byte0, l1, i2, null);
	}

	int n;
	int x;
	int y;
	int color;
	int count;
	private int status;
	static final int NONE = 0;
	static final int CLOSE = 1;
	static final int OPEN = 2;
	static final int LOSE = 3;
	static final int WIN = 4;
	static final int BLUE = 0;
	static final int RED = 1;
}
