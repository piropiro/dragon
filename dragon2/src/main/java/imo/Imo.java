




package imo;

import java.awt.Graphics;
import java.awt.Image;

// Referenced classes of package imo:
//            Body, Turu, Jiki

class Imo extends Body {

	Imo(double d, double d1, int i, Body body, Image image) {
		super(d, d1, 32, 32, 0.0D, 0.5D);
		AnimeFlag = true;
		level = i;
		oldx = d;
		oldy = d1;
		hpMax = 10 + i;
		hp = hpMax;
		ShotWait = 300;
		TurnWait = 0;
		AnimeWait = 0;
		Face = 0;
		Status = 0;
		StatusWait = 0;
		Life = true;
		screen = body;
		img = image;
	}

	public void shot(Turu turu) {
		if (ShotWait > 0)
			return;
		if (Status != 0) {
			return;
		} else {
			turu.make(this, 60);
			ShotWait = 150 + RND(100);
			Status = 1;
			StatusWait = 60;
			return;
		}
	}

	public void move(Jiki jiki) {
		ShotWait--;
		if (Life && 0 >= StatusWait--)
			Status = 0;
		anime();
		if (Status != 1)
			turn();
		oldx = x;
		oldy = y;
		switch (Status) {
		case 1: // '\001'
		default:
			break;

		case 0: // '\0'
			v = 1.0D;
			moveK(Face == 1, Face == 2, Face == 3, Face == 4);
			moveC(screen);
			break;

		case 2: // '\002'
			if ((Face != 1 || jiki.Faces != 2)
					&& (Face != 2 || jiki.Faces != 1)
					&& (Face != 3 || jiki.Faces != 4)
					&& (Face != 4 || jiki.Faces != 3)) {
				v = 6D;
				super.move();
			}
			break;
		}
	}

	public void turn() {
		if (0 <= TurnWait--)
			return;
		switch (RND(10)) {
		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
			Face = 1;
			break;

		case 3: // '\003'
		case 4: // '\004'
			Face = 2;
			break;

		case 5: // '\005'
		case 6: // '\006'
		case 7: // '\007'
			Face = 3;
			break;

		case 8: // '\b'
		case 9: // '\t'
			Face = 4;
			break;
		}
		TurnWait = 10 + RND(10);
	}

	public void moveS() {
		x = oldx;
		y = oldy;
	}

	private void anime() {
		if (0 >= AnimeWait--) {
			AnimeFlag = !AnimeFlag;
			AnimeWait = 7;
		}
	}

	public void damage(Body body, int i) {
		if (Status == 2)
			return;
		if (Status == 1)
			return;
		Status = 2;
		StatusWait = 5;
		hp -= i;
		if (hp <= 0) {
			hp = 0;
			Life = false;
			Status = 3;
		}
		angle = Body.getAngle(body, this);
	}

	public boolean isAlive() {
		return Life;
	}

	public void display(Graphics g) {
		int i = (int) x;
		int j = (int) y;
		int k = i + xs;
		int l = j + ys;
		int i1 = 0;
		int j1 = 96;
		switch (Face) {
		case 1: // '\001'
			i1 += 192;
			break;

		case 2: // '\002'
			i1 += 0;
			break;

		case 3: // '\003'
			i1 += 64;
			break;

		case 4: // '\004'
			i1 += 128;
			break;
		}
		if (AnimeFlag)
			i1 += 32;
		switch (Status) {
		case 0: // '\0'
			j1 += 0;
			break;

		case 2: // '\002'
			j1 += 32;
			break;

		case 1: // '\001'
			j1 += 64;
			break;
		}
		int k1 = i1 + xs;
		int l1 = j1 + ys;
		if (Life)
			g.drawImage(img, i, j, k, l, i1, j1, k1, l1, null);
		else
			g.drawImage(img, i, j, k, l, 256, 128, 288, 160, null);
	}

	public void displayHP(Graphics g) {
		char c = '\276';
		int i = 0;
		int j = c + 104;
		int k = i + 32;
		byte byte0 = 107;
		char c1 = '\344';
		int l = byte0 + 104;
		int i1 = c1 + 32;
		g.drawImage(img, c, i, j, k, byte0, c1, l, i1, null);
		c = '\302';
		i = 21;
		j = c + (hp * 96) / hpMax;
		k = i + 8;
		byte0 = 111;
		c1 = '\u010B';
		l = byte0 + (hp * 96) / hpMax;
		i1 = c1 + 8;
		g.drawImage(img, c, i, j, k, byte0, c1, l, i1, null);
		g.drawString("\u828B\u52A9\u3000Lv." + level, 190, 16);
	}

	int hp;
	int hpMax;
	int level;
	int ShotWait;
	static final int ShotWaitMax = 150;
	int TurnWait;
	boolean AnimeFlag;
	int AnimeWait;
	static final int AnimeWaitMax = 7;
	int Face;
	static final int F_UP = 1;
	static final int F_DOWN = 2;
	static final int F_LEFT = 3;
	static final int F_RIGHT = 4;
	int Status;
	int StatusWait;
	static final int SwordWaitMax = 60;
	static final int DamageWaitMax = 5;
	static final int S_MOVE = 0;
	static final int S_SHOT = 1;
	static final int S_DAMAGE = 2;
	static final int S_DEAD = 3;
	static final int V_WALK = 1;
	static final int V_DAMAGE = 6;
	boolean Life;
	Body screen;
	Image img;
	double oldx;
	double oldy;
}
