// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumAnime.java

import java.awt.Component;
import java.awt.Graphics;

class NumAnime implements AnimeListener {

	NumAnime(UnitWorks unitworks, int i) {
		uw = unitworks;
		damage = Math.min(Math.abs(i), 999);
		nums = new Number[3];
	}

	private boolean end() {
		for (int i = 0; i < nums.length; i++)
			if (nums[i] != null && !nums[i].end())
				return false;

		return true;
	}

	public void animation(Component component) {
		if (damage > 99) {
			nums[2] = new Number(damage / 100, 1, -4, 0);
			nums[1] = new Number((damage % 100) / 10, 11, -2, 0);
			nums[0] = new Number(damage % 10, 21, 0, 0);
		} else if (damage > 9) {
			nums[2] = null;
			nums[1] = new Number((damage % 100) / 10, 6, -2, 0);
			nums[0] = new Number(damage % 10, 16, 0, 0);
		} else {
			nums[2] = null;
			nums[1] = null;
			nums[0] = new Number(damage % 10, 12, 0, 0);
		}
		for (; !end(); uw.sleep(25L))
			component.repaint();

	}

	public void paint(Graphics g) {
		for (int i = 0; i < nums.length; i++)
			if (nums[i] != null) {
				nums[i].move();
				nums[i].display(g);
			}

	}

	Number nums[];
	UnitWorks uw;
	int damage;
}
