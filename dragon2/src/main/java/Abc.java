// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Abc.java

class Abc {

	Abc(int i, int j, int k) {
		w = i;
		xf = j;
		wait = k;
		x = j - 32;
		y = 10;
		time1 = 0;
		time2 = 0;
	}

	public void move1() {
		if (++time1 > wait)
			x = Math.min(x + 2, xf);
	}

	public void move2() {
		if (++time2 > wait)
			x += 2;
	}

	public boolean end1() {
		return x == xf;
	}

	public boolean end2() {
		return x > 32;
	}

	static final int v = 2;
	int w;
	int xf;
	int x;
	int y;
	int wait;
	int time1;
	int time2;
}
