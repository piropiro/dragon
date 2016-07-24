




package imo;

class Body {

	Body(double d, double d1, int i, int j, double d2, double d3) {
		RNDS = 0;
		x = d;
		y = d1;
		xs = i;
		ys = j;
		angle = d2;
		v = d3;
	}

	public void move() {
		x += v * Math.cos(angle);
		y += v * Math.sin(angle);
	}

	public void moveK(boolean flag, boolean flag1, boolean flag2, boolean flag3) {
		if (flag)
			y -= v;
		if (flag1)
			y += v;
		if (flag2)
			x -= v;
		if (flag3)
			x += v;
	}

	public void moveM(int i, int j) {
		if ((x - (double) i) * (x - (double) i) + (y - (double) j)
				* (y - (double) j) < v * v) {
			x = i;
			y = j;
		} else {
			double d = getAngle(this, i, j);
			x += v * Math.cos(d);
			y += v * Math.sin(d);
		}
	}

	public void moveC(Body body) {
		if (x + (double) xs >= body.x + (double) body.xs)
			x = (body.x + (double) body.xs) - (double) xs - 1.0D;
		if (y + (double) ys >= body.y + (double) body.ys)
			y = (body.y + (double) body.ys) - (double) ys - 1.0D;
		if (x < body.x)
			x = body.x;
		if (y < body.y)
			y = body.y;
	}

	static boolean Hit(Body body, Body body1) {
		if (body.xs <= 0)
			return false;
		if (body.ys <= 0)
			return false;
		if (body1.xs <= 0)
			return false;
		if (body1.ys <= 0)
			return false;
		if (body.x + (double) body.xs < body1.x)
			return false;
		if (body.x >= body1.x + (double) body1.xs)
			return false;
		if (body.y + (double) body.ys < body1.y)
			return false;
		return body.y < body1.y + (double) body1.ys;
	}

	static boolean Hit(Body body, double d, double d1) {
		if (d < body.x)
			return false;
		if (d >= body.x + (double) body.xs)
			return false;
		if (d1 < body.y)
			return false;
		return d1 < body.y + (double) body.ys;
	}

	static double getAngle(Body body, Body body1) {
		return Math.atan2((body1.y + (double) (body1.ys / 2))
				- (body.y + (double) (body.ys / 2)),
				(body1.x + (double) (body1.xs / 2))
						- (body.x + (double) (body.xs / 2)));
	}

	static double getAngle(Body body, double d, double d1) {
		return Math.atan2(d1 - (body.y + (double) (body.ys / 2)), d
				- (body.x + (double) (body.xs / 2)));
	}

	public int RND(int i) {
		RNDS += (long) (Math.random() * 1000000D);
		RNDS %= 0xf4240;
		return RNDS % i;
	}

	int xs;
	int ys;
	double x;
	double y;
	double angle;
	double v;
	int RNDS;
}
