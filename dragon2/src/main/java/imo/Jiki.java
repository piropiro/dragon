




package imo;

import java.awt.Graphics;
import java.awt.Image;

// Referenced classes of package imo:
//            Body, Sword

class Jiki extends Body {

	Jiki(double d, double d1, String s, Body body, Image image) {
		super(d, d1, 32, 32, 0.0D, 1.0D);
		name = s;
		oldx = d;
		oldy = d1;
		hpMax = 10;
		hp = hpMax;
		ShotWait = 0;
		AnimeFlag = true;
		AnimeWait = 0;
		Face = 2;
		Faces = 2;
		Status = 0;
		StatusWait = 0;
		Life = true;
		screen = body;
		img = image;
	}

	public void move(boolean flag, boolean flag1, boolean flag2, boolean flag3) {
		ShotWait--;
		if (0 >= StatusWait--)
			Status = 0;
		anime();
		oldx = x;
		oldy = y;
		switch (Status) {
		case 0: // '\0'
		case 1: // '\001'
			v = 1.5D;
			moveK(Faces == 1, Faces == 2, Faces == 3, Faces == 4);
			Face = 0;
			if (flag)
				Face = 1;
			if (flag1)
				Face = 2;
			if (flag2)
				Face = 3;
			if (flag3)
				Face = 4;
			if (Face != 0)
				Faces = Face;
			v = 3D;
			moveK(Face == 1, Face == 2, Face == 3, Face == 4);
			moveC(screen);
			break;

		case 2: // '\002'
			v = 5D;
			super.move();
			break;
		}
	}

	public void moveS() {
		x = oldx;
		y = oldy;
	}

	private void anime() {
		if (0 >= AnimeWait--) {
			AnimeFlag = !AnimeFlag;
			if (Face != 0)
				AnimeWait = 7;
			if (Face == 0)
				AnimeWait = 14;
		}
	}

	public void shot(Sword sword) {
		if (ShotWait > 0)
			return;
		if (Status != 0) {
			return;
		} else {
			sword.make(this, 5);
			ShotWait = 15;
			Status = 1;
			StatusWait = 5;
			return;
		}
	}

	public void damage(Body body, int i) {
		if (Status == 2)
			return;
		Status = 2;
		StatusWait = 3;
		hp -= i;
		if (hp < 0)
			hp = 0;
		angle = Body.getAngle(body, this);
	}

	public void display(Graphics g) {
		int i = (int) x;
		int j = (int) y;
		int k = i + xs;
		int l = j + ys;
		int i1 = 0;
		int j1 = 0;
		switch (Faces) {
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
		g.drawImage(img, i, j, k, l, i1, j1, k1, l1, null);
	}

	public void displayHP(Graphics g) {
		byte byte0 = 72;
		int i = 0;
		int j = byte0 + 104;
		int k = i + 32;
		int l = 0;
		char c = '\344';
		int i1 = l + 104;
		int j1 = c + 32;
		g.drawImage(img, byte0, i, j, k, l, c, i1, j1, null);
		byte0 = 76;
		i = 21;
		j = byte0 + (hp * 96) / hpMax;
		k = i + 8;
		l = 4;
		c = '\u010B';
		i1 = l + (hp * 96) / hpMax;
		j1 = c + 8;
		g.drawImage(img, byte0, i, j, k, l, c, i1, j1, null);
		g.drawString(name, 72, 16);
	}

	int hp;
	int hpMax;
	String name;
	int ShotWait;
	static final int ShotWaitMax = 15;
	boolean AnimeFlag;
	int AnimeWait;
	static final int AnimeWaitMax = 7;
	int Face;
	int Faces;
	static final int F_UP = 1;
	static final int F_DOWN = 2;
	static final int F_LEFT = 3;
	static final int F_RIGHT = 4;
	int Status;
	int StatusWait;
	static final int SwordWaitMax = 5;
	static final int DamageWaitMax = 3;
	static final int S_MOVE = 0;
	static final int S_SHOT = 1;
	static final int S_DAMAGE = 2;
	static final double V_WALK = 1.5D;
	static final double V_DASH = 3D;
	static final double V_DAMAGE = 5D;
	boolean Life;
	Body screen;
	Image img;
	double oldx;
	double oldy;
}
