// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Luck.java

import java.util.Random;

class Luck {

	Luck() {
	}

	public static void setup(int i) {
		luckType = i;
		random = new Random();
	}

	public static int rnd(int i) {
		return random.nextInt(i + 1);
	}

	public static int rnd(int i, Body body) {
		switch (luckType) {
		case 0: // '\0'
			return random.nextInt() % (i + 1);

		case 1: // '\001'
			if (Colors.isPlayer(body))
				return i;
			else
				return -i;

		case 2: // '\002'
			if (Colors.isPlayer(body))
				return -i;
			else
				return i;

		case 3: // '\003'
			return 0;
		}
		return 0;
	}

	private static Random random;
	private static int luckType = 0;
	static final int FairLuck = 0;
	static final int GoodLuck = 1;
	static final int HardLuck = 2;
	static final int NoneLuck = 3;

}
