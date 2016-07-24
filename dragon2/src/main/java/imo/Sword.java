




package imo;

import java.awt.Graphics;
import java.awt.Image;

// Referenced classes of package imo:
//            Body, Jiki

class Sword extends Body {

	Sword(Image image) {
		super(0.0D, 0.0D, 32, 32, 0.0D, 0.0D);
		Life = false;
		AnimeWait = 0;
		Face = 2;
		img = image;
	}

	public boolean isAlive() {
		return Life;
	}

	public void make(Jiki jiki, int i) {
		Face = jiki.Faces;
		AnimeWait = i;
		Life = true;
		move(jiki);
	}

	public void move(Jiki jiki) {
		Face = jiki.Faces;
		switch (Face) {
		case 1: // '\001'
			x = jiki.x;
			y = jiki.y - 32D;
			break;

		case 2: // '\002'
			x = jiki.x;
			y = jiki.y + 32D;
			break;

		case 3: // '\003'
			x = jiki.x - 32D;
			y = jiki.y;
			break;

		case 4: // '\004'
			x = jiki.x + 32D;
			y = jiki.y;
			break;
		}
	}

	public void display(Graphics g) {
		if (0 >= AnimeWait--)
			Life = false;
		if (!Life)
			return;
		int i = (int) x;
		int j = (int) y;
		int k = i + xs;
		int l = j + ys;
		int i1 = 128;
		char c = '\300';
		switch (Face) {
		case 1: // '\001'
			i1 += 96;
			break;

		case 2: // '\002'
			i1 += 0;
			break;

		case 3: // '\003'
			i1 += 32;
			break;

		case 4: // '\004'
			i1 += 64;
			break;
		}
		int j1 = i1 + xs;
		int k1 = c + ys;
		g.drawImage(img, i, j, k, l, i1, c, j1, k1, null);
	}

	boolean Life;
	int AnimeWait;
	int Face;
	static final int F_UP = 1;
	static final int F_DOWN = 2;
	static final int F_LEFT = 3;
	static final int F_RIGHT = 4;
	Image img;
}
