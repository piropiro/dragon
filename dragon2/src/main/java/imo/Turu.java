




package imo;

import java.awt.Graphics;
import java.awt.Image;

// Referenced classes of package imo:
//            Body, Imo

class Turu extends Body {

	Turu(Image image) {
		super(0.0D, 0.0D, 32, 32, 0.0D, 0.0D);
		Life = false;
		AnimeWait = 0;
		img = image;
	}

	public boolean isAlive() {
		return Life;
	}

	public void make(Imo imo, int i) {
		AnimeWait = i;
		Life = true;
		Time1 = (i * 140) / 200;
		Time2 = (i * 20) / 200;
		Time3 = (i * 10) / 200;
		Time4 = (i * 30) / 200;
		move(imo);
	}

	public void move(Imo imo) {
		if (0 >= AnimeWait--)
			Life = false;
		double d = AnimeWait;
		if (d < Time4) {
			int i = (int) ((d * 32D) / Time4);
			x = imo.x - (double) i;
			y = imo.y - (double) i;
			xs = 32 + i * 2;
			ys = 32 + i * 2;
			return;
		}
		d -= Time4;
		if (d < Time3) {
			x = imo.x - 32D;
			y = imo.y - 32D;
			xs = 96;
			ys = 96;
			return;
		}
		d -= Time3;
		if (d < Time2) {
			int j = (int) (((Time2 - d) * 32D) / Time2);
			x = imo.x - (double) j;
			y = imo.y - (double) j;
			xs = 32 + j * 2;
			ys = 32 + j * 2;
			return;
		} else {
			x = imo.x;
			y = imo.y;
			xs = 32;
			ys = 32;
			return;
		}
	}

	public void display(Graphics g) {
		if (!Life) {
			return;
		} else {
			int i = (int) x;
			int j = (int) y;
			int k = i + xs;
			int l = j + ys;
			int i1 = 288 - (xs - 32) / 2;
			int j1 = 32 - (ys - 32) / 2;
			int k1 = i1 + xs;
			int l1 = j1 + ys;
			g.drawImage(img, i, j, k, l, i1, j1, k1, l1, null);
			return;
		}
	}

	boolean Life;
	int AnimeWait;
	Image img;
	private double Time1;
	private double Time2;
	private double Time3;
	private double Time4;
}
