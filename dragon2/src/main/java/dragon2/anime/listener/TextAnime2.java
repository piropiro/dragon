package dragon2.anime.listener;





import java.awt.Component;
import java.awt.Graphics;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.anime.item.Abc;
import mine.Mine;

public class TextAnime2 implements AnimeListener {

	public TextAnime2(UnitWorks unitworks, int i) {
		uw = unitworks;
		type = i;
		setText(i);
	}

	private void setText(int i) {
		switch (i) {
		case 3: // '\003'
			abc = new Abc[6];
			abc[0] = new Abc(6, 1, 10);
			abc[1] = new Abc(3, 7, 8);
			abc[2] = new Abc(6, 10, 6);
			abc[3] = new Abc(3, 16, 4);
			abc[4] = new Abc(5, 19, 2);
			abc[5] = new Abc(6, 24, 0);
			break;

		case 5: // '\005'
			abc = new Abc[5];
			abc[0] = new Abc(7, 1, 10);
			abc[1] = new Abc(6, 8, 8);
			abc[2] = new Abc(6, 14, 6);
			abc[3] = new Abc(5, 20, 2);
			abc[4] = new Abc(6, 25, 0);
			break;
		}
	}

	public void animation(Component component) {
		for (; !abc[0].end1(); uw.sleep(25L)) {
			for (int i = 0; i < abc.length; i++)
				abc[i].move1();

			component.repaint();
		}

		uw.sleep(500L);
		for (; !abc[0].end2(); uw.sleep(15L)) {
			for (int j = 0; j < abc.length; j++)
				abc[j].move2();

			component.repaint();
		}

		uw.sleep(100L);
	}

	public void paint(Graphics g) {
		if (abc == null)
			return;
		for (int i = 0; i < abc.length; i++) {
			int j = abc[i].x;
			int k = abc[i].y;
			int l = abc[i].w;
			int i1 = type * 32 + abc[i].xf;
			Mine.drawImage(Statics.text, j, k, i1, 0, l, 12, g);
		}

	}

	Abc abc[];
	UnitWorks uw;
	int type;
	static final int FINISH = 3;
	static final int DEATH = 5;
}
