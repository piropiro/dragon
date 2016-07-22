




package imo;

import java.awt.Graphics;
import java.awt.Image;

// Referenced classes of package imo:
//            Body

class Sister extends Body {

	Sister(double d, double d1, Image image) {
		super(d, d1, 32, 32, 0.0D, 0.0D);
		hp = 3;
		AnimeFlag = true;
		AnimeWait = 0;
		Status = 0;
		StatusWait = 0;
		Life = true;
		img = image;
	}

	private void anime() {
		if (0 >= StatusWait--)
			Status = 0;
		if (0 >= AnimeWait--) {
			AnimeFlag = !AnimeFlag;
			AnimeWait = 10;
		}
	}

	public void damage() {
		if (Status == 1) {
			return;
		} else {
			Status = 1;
			StatusWait = 10;
			hp--;
			return;
		}
	}

	public void move() {
		anime();
	}

	public void display(Graphics g) {
		int i = (int) x;
		int j = (int) y;
		int k = i + xs;
		int l = j + ys;
		int i1 = 0;
		char c = '\300';
		if (AnimeFlag)
			i1 += 32;
		if (Status == 1)
			i1 += 64;
		int j1 = i1 + xs;
		int k1 = c + ys;
		g.drawImage(img, i, j, k, l, i1, c, j1, k1, null);
	}

	int hp;
	static final int hpMax = 3;
	boolean AnimeFlag;
	int AnimeWait;
	static final int AnimeWaitMax = 10;
	int Status;
	int StatusWait;
	static final int DamageWaitMax = 10;
	static final int S_NONE = 0;
	static final int S_DAMAGE = 1;
	boolean Life;
	Image img;
}
