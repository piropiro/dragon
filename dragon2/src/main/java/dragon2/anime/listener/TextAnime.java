package dragon2.anime.listener;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextAnime.java

import java.awt.Component;
import java.awt.Graphics;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.anime.item.Number;
import mine.Mine;

public class TextAnime implements AnimeListener {

	public TextAnime(UnitWorks unitworks, int i) {
		uw = unitworks;
		type = i;
		setText(i);
	}

	private void setText(int i) {
		switch (i) {
		case 0: // '\0'
			nums = new Number[4];
			nums[3] = new Number(9, 4, -6, 0);
			nums[2] = new Number(3, 13, -4, 0);
			nums[1] = new Number(6, 16, -2, 0);
			nums[0] = new Number(6, 22, 0, 0);
			break;

		case 1: // '\001'
			nums = new Number[6];
			nums[5] = new Number(6, 1, -10, 0);
			nums[4] = new Number(5, 7, -8, 0);
			nums[3] = new Number(3, 12, -6, 0);
			nums[2] = new Number(5, 15, -4, 0);
			nums[1] = new Number(5, 20, -2, 0);
			nums[0] = new Number(6, 25, 0, 0);
			break;

		case 2: // '\002'
			nums = new Number[5];
			nums[4] = new Number(6, 2, -8, 0);
			nums[3] = new Number(5, 8, -6, 0);
			nums[2] = new Number(6, 13, -4, 0);
			nums[1] = new Number(6, 19, -2, 0);
			nums[0] = new Number(6, 25, 0, 0);
			break;

		case 3: // '\003'
			nums = new Number[6];
			nums[5] = new Number(6, 1, -10, 0);
			nums[4] = new Number(3, 7, -8, 0);
			nums[3] = new Number(6, 10, -6, 0);
			nums[2] = new Number(3, 16, -4, 0);
			nums[1] = new Number(5, 19, -2, 0);
			nums[0] = new Number(6, 24, 0, 0);
			break;

		case 4: // '\004'
			nums = new Number[3];
			nums[2] = new Number(12, 2, -4, 0);
			nums[1] = new Number(4, 15, -2, 0);
			nums[0] = new Number(9, 20, 0, 0);
			break;

		case 5: // '\005'
			nums = new Number[5];
			nums[4] = new Number(7, 1, -8, 0);
			nums[3] = new Number(6, 8, -6, 0);
			nums[2] = new Number(6, 14, -4, 0);
			nums[1] = new Number(5, 20, -2, 0);
			nums[0] = new Number(6, 25, 0, 0);
			break;

		case 6: // '\006'
			nums = new Number[5];
			nums[4] = new Number(6, 3, -8, 0);
			nums[3] = new Number(5, 9, -6, 0);
			nums[2] = new Number(5, 14, -4, 0);
			nums[1] = new Number(5, 19, -2, 0);
			nums[0] = new Number(5, 24, 0, 0);
			break;
		}
	}

	private boolean end() {
		for (int i = 0; i < nums.length; i++)
			if (nums[i] != null && !nums[i].end())
				return false;

		return true;
	}

	public void animation(Component component) {
		for (; !end(); uw.sleep(25L))
			component.repaint();

		uw.sleep(100L);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < nums.length; i++) {
			nums[i].move();
			int j = nums[i].x;
			int k = nums[i].y;
			int l = nums[i].n;
			int i1 = type * 32 + nums[i].x;
			Mine.drawImage(Statics.text, j, k, i1, 0, l, 12, g);
		}

	}

	Number nums[];
	UnitWorks uw;
	int type;
	static final int MISS = 0;
	static final int POISON = 1;
	static final int SLEEP = 2;
	static final int FINISH = 3;
	static final int WIN = 4;
	static final int DEATH = 5;
	static final int CLOSE = 6;
}
