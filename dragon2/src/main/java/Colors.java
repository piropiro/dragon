// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Colors.java

class Colors {

	Colors() {
	}

	public static void setup(int i, int j) {
		PC = i;
		EC = j;
	}

	public static int getPC() {
		return PC;
	}

	public static int getEC() {
		return EC;
	}

	public static boolean isPlayer(Body body) {
		return PC == body.color;
	}

	public static boolean isEnemy(Body body) {
		return EC == body.color;
	}

	private static int PC;
	private static int EC;
	static final int BLUE = 1;
	static final int GREEN = 2;
	static final int RED = 3;
}
