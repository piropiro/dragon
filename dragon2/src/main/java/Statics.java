// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Statics.java

import java.awt.Image;
import java.util.Vector;
import mine.*;

class Statics {

	Statics() {
	}

	static void setup() {
		AttackDatas = (Vector) DataStream.read("data/AD.txt");
		AnimeDatas = (Vector) DataStream.read("data/AN.txt");
		idoType = (String[]) DataStream.read("data/itype.txt");
		tokusei = (String[]) DataStream.read("data/tokusei.txt");
		effect = (String[]) DataStream.read("data/effect.txt");
		trtype = (String[]) DataStream.read("data/trtype.txt");
		atype = (String[]) DataStream.read("data/atype.txt");
		back = ImageLoader.getImage("image/back.gif", false);
		chara = ImageLoader.getImage("image/chara.gif", true);
		waku = ImageLoader.getImage("image/waku.gif", true);
		anime = ImageLoader.getImage("image/anime.gif", true);
		text = ImageLoader.getImage("image/text.gif", true);
		card = ImageLoader.getImage("image/card.gif", true);
		status = ImageLoader.getImage("image/status.gif", true);
		java.awt.image.BufferedImage bufferedimage = ImageLoader.getImage(
				"image/num.gif", true);
		num = new Image[10];
		for (int i = 0; i < num.length; i++)
			num[i] = Mine.getClipImage(bufferedimage, i * 10, 0, 10, 12, true);

	}

	public static AnimeData getAnimeData(int i) {
		return (AnimeData) AnimeDatas.elementAt(i);
	}

	public static AttackData getAttackData(int i) {
		return (AttackData) AttackDatas.elementAt(i);
	}

	public static Body getWaza(int i) {
		AttackData attackdata = getAttackData(i);
		Body body = new Body();
		body.img = attackdata.AttackN1 + 169;
		body.name = attackdata.name;
		body.level = i;
		body.hpMax = 1;
		body.color = 2;
		body.atk[0] = i;
		body.type[0] = 52;
		body.setMax();
		body.newType(100);
		return body;
	}

	public static int getBukiType(int i) {
		switch (i) {
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
			return 1;

		case 4: // '\004'
		case 5: // '\005'
		case 6: // '\006'
			return 2;
		}
		return 3;
	}

	public static void setHelp(boolean flag) {
		helpFlag = flag;
	}

	public static boolean isHelp() {
		return helpFlag;
	}

	public static void debug() {
		debugFlag = true;
	}

	public static boolean isDebug() {
		return debugFlag;
	}

	static boolean debugFlag = false;
	static boolean helpFlag = false;
	static final int TYPE_MAX = 100;
	static Vector AttackDatas;
	static Vector AnimeDatas;
	static String idoType[];
	static String tokusei[];
	static String effect[];
	static String trtype[];
	static String atype[];
	static Image back;
	static Image chara;
	static Image waku;
	static Image anime;
	static Image card;
	static Image text;
	static Image status;
	static Image num[];

}
