package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tutorial.java

public class Tutorial {

	public Tutorial() {
	}

	public static void setHelp(Body body, Body body1, int i, UnitWorks unitworks) {
		String as[] = Texts.tuto[0];
		byte byte0 = 1;
		if (body1 != null)
			if (body.isType(51)) {
				if (i == 0) {
					if (body1.name.equals(Texts.goburin)) {
						as = Texts.tuto[1];
						byte0 = 3;
					} else if (body1.name.equals(Texts.pikusi))
						as = Texts.tuto[2];
					else if (body1.name.equals(Texts.gaikotu))
						if (body1.hp == body1.hpMax) {
							as = Texts.tuto[3];
							byte0 = 2;
						} else {
							as = Texts.tuto[4];
						}
				} else if (body1.name.equals(Texts.goburin)) {
					as = Texts.tuto[5];
					byte0 = 3;
				} else if (body1.name.equals(Texts.pikusi)) {
					as = Texts.tuto[6];
					byte0 = 3;
				} else if (body1.name.equals(Texts.gaikotu))
					if (body1.hp == body1.hpMax) {
						as = Texts.tuto[7];
					} else {
						as = Texts.tuto[8];
						byte0 = 3;
					}
			} else if (body.isType(50))
				if (i == 0) {
					if (body1.name.equals(Texts.goburin))
						as = Texts.tuto[9];
					else if (body1.name.equals(Texts.pikusi)) {
						as = Texts.tuto[10];
						byte0 = 3;
					} else if (body1.name.equals(Texts.gaikotu))
						if (body1.hp == body1.hpMax) {
							as = Texts.tuto[11];
						} else {
							as = Texts.tuto[12];
							byte0 = 3;
						}
				} else if (body1.name.equals(Texts.goburin))
					as = Texts.tuto[13];
				else if (body1.name.equals(Texts.pikusi)) {
					as = Texts.tuto[14];
					byte0 = 3;
				} else if (body1.name.equals(Texts.gaikotu))
					if (body1.hp == body1.hpMax) {
						as = Texts.tuto[15];
						byte0 = 2;
					} else {
						as = Texts.tuto[16];
					}
		unitworks.setHelp(as, byte0);
	}
}
