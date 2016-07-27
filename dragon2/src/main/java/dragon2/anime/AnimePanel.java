package dragon2.anime;


// Decompiler options: packimports(3)


import java.awt.Graphics;
import mine.util.Point;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.anime.listener.AllAnime;
import dragon2.anime.listener.AnimeListener;
import dragon2.anime.listener.ArrowAnime;
import dragon2.anime.listener.ArrowAnime2;
import dragon2.anime.listener.CloseAnime;
import dragon2.anime.listener.EraseAnime;
import dragon2.anime.listener.EraseAnime2;
import dragon2.anime.listener.NumAnime;
import dragon2.anime.listener.PictureAnime;
import dragon2.anime.listener.RotateAnime;
import dragon2.anime.listener.SingleAnime;
import dragon2.anime.listener.StatusAnime;
import dragon2.anime.listener.SummonAnime;
import dragon2.anime.listener.TextAnime;
import dragon2.anime.listener.TextAnime2;
import dragon2.anime.listener.TitleAnime;
import dragon2.anime.listener.WalkAnime;
import dragon2.map.Map;
import mine.JCanvas;
import mine.UnitMap;

public class AnimePanel extends JCanvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AnimePanel(UnitWorks unitworks, UnitMap unitmap, Map map1) {
		super(32, 32);
		uw = unitworks;
		map = map1;
		V = unitmap;
		np = null;
		al = null;
	}

	private void setLocate(Point point) {
		int i = point.x * 32;
		int j = point.y * 32;
		setLocation(i, j);
		setSize(32, 32);
	}

	public void dispose() {
		np = null;
		al = null;
		setVisible(false);
	}

	public void anime(int i, int j, Point point, Point point1) {
		Point point2 = null;
		Point point3 = null;
		if (point != null)
			point2 = new Point(point.x * 32, point.y * 32);
		if (point1 != null)
			point3 = new Point(point1.x * 32, point1.y * 32);
		switch (i) {
		case -12:
			al = new CloseAnime(uw, j, "/image/title.gif");
			al.animation(this);
			if (j == 1) {
				setVisible(false);
				al = null;
			}
			return;

		case -11:
			setLocation(0, 0);
			setSize(640, 480);
			al = new PictureAnime(uw, "/image/title.gif");
			al.animation(this);
			setVisible(true);
			return;

		case -10:
			setLocate(point1);
			setVisible(true);
			al = new EraseAnime(uw, V, point1);
			al.animation(this);
			al = null;
			return;

		case -9:
			np = null;
			al = new TitleAnime(uw, j);
			setVisible(true);
			al.animation(this);
			setVisible(false);
			al = null;
			return;

		case -4:
			setLocate(point1);
			np = null;
			al = new WalkAnime(uw, V, point1);
			setVisible(true);
			al.animation(this);
			setVisible(false);
			uw.sleep(15L);
			al = null;
			return;

		case -1:
			setLocate(point1);
			setVisible(true);
			np = new NumAnime(uw, j);
			np.animation(this);
			np = null;
			return;

		case -2:
			setLocate(point1);
			al = new EraseAnime(uw, V, point1);
			al.animation(this);
			al = null;
			return;

		case -3:
			np = null;
			al = new EraseAnime2(uw, V, point1);
			al.animation(this);
			al = null;
			return;

		case -5:
			setLocate(point1);
			setVisible(true);
			al = new TextAnime(uw, j);
			al.animation(this);
			al = null;
			return;

		case -6:
			setLocate(point1);
			setVisible(true);
			al = new TextAnime2(uw, j);
			al.animation(this);
			al = null;
			return;

		case -7:
			setVisible(true);
			al = new StatusAnime(uw, V, point1, j);
			al.animation(this);
			al = null;
			setVisible(false);
			return;

		case -8:
			setVisible(true);
			al = new SummonAnime(uw, V, point1, j);
			al.animation(this);
			al = null;
			setVisible(false);
			return;

		case 1: // '\001'
			setLocate(point1);
			setVisible(true);
			al = new SingleAnime(uw, point1, getImages(j), sleep);
			al.animation(this);
			al = null;
			return;

		case 2: // '\002'
			setVisible(true);
			al = new AllAnime(uw, V, getImages(j), sleep);
			al.animation(this);
			al = null;
			return;

		case 6: // '\006'
			np = null;
			setVisible(true);
			al = new RotateAnime(uw, point2, point3, getImages(j), sleep);
			al.animation(this);
			al = null;
			return;

		case 3: // '\003'
		case 4: // '\004'
			np = null;
			setLocate(point);
			setVisible(true);
			al = new ArrowAnime(uw, point2, point3, 16, getImages(j), sleep);
			al.animation(this);
			al = null;
			return;

		case 5: // '\005'
			np = null;
			setVisible(true);
			Point point4 = new Point(point.x * 32, point.y * 32);
			al = new ArrowAnime2(uw, V, point4, 16, getImages(j), sleep);
			al.animation(this);
			al = null;
			return;

		case 0: // '\0'
		default:
			return;
		}
	}

	private int[] getImages(int i) {
		switch (i) {
		case -1:
			int ai[] = { 105, 106, 107, 108, 0 };
			sleep = 80;
			return ai;

		case -9:
			int ai1[] = { 1, 2, 3, 4 };
			sleep = 15;
			return ai1;

		case -11:
			int ai2[] = { 120, 121, 122, 123, 124, 125, 126, 127, 0, 135, 136,
					137, 138, 139, 140, 141, 142, 0, 68, 69, 70, 71, 0, 105,
					106, 107, 108, 0 };
			sleep = 50;
			return ai2;

		case -5:
			int ai3[] = { 21, 20, 19, 18, 17, 16, 15 };
			sleep = 40;
			return ai3;

		case -4:
			int ai4[] = { 15, 16, 17, 18, 19, 20, 21 };
			sleep = 40;
			return ai4;

		case -3:
			int ai5[] = { 28, 27, 26, 25, 24, 23, 22 };
			sleep = 40;
			return ai5;

		case -2:
			int ai6[] = { 22, 23, 24, 25, 26, 27, 28 };
			sleep = 40;
			return ai6;

		case -6:
			int ai7[] = { 5, 6, 7, 8, 9, 0 };
			sleep = 40;
			return ai7;

		case -7:
			int ai8[] = { 10, 11, 12, 13, 14, 0 };
			return ai8;

		case -8:
			int ai9[] = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 0 };
			sleep = 100;
			return ai9;

		case -10:
			int ai10[] = { 49, 50, 51, 52, 53, 0 };
			sleep = 70;
			return ai10;
		}
		AnimeData animedata = Statics.getAnimeData(i);
		sleep = animedata.getSleep();
		return animedata.getAnime();
	}

	public void paint(Graphics g) {
		if (np != null)
			np.paint(g);
		if (al != null)
			al.paint(g);
	}

	static final int T_CLOSE = -12;
	static final int T_PICTURE = -11;
	static final int T_CHANGE = -10;
	static final int T_CLEAR = -9;
	static final int T_SUMMON = -8;
	static final int T_STATUS = -7;
	static final int T_TEXT2 = -6;
	static final int T_TEXT = -5;
	static final int T_WALK = -4;
	static final int T_ERASE2 = -3;
	static final int T_ERASE = -2;
	static final int T_NUM = -1;
	static final int T_ONE = 1;
	static final int T_ALL = 2;
	static final int T_ARROW = 3;
	static final int T_ALLAR = 4;
	static final int T_ANYAR = 5;
	static final int T_ROTATE = 6;
	static final int KAKUSEI2 = -11;
	static final int FIRE = -10;
	static final int KAKUSEI = -9;
	static final int SUMMON = -8;
	static final int DEATH = -7;
	static final int FINISH = -6;
	static final int B_OPEN = -5;
	static final int B_CLOSE = -4;
	static final int R_OPEN = -3;
	static final int R_CLOSE = -2;
	static final int DANCE = -1;
	UnitWorks uw;
	Map map;
	UnitMap V;
	AnimeListener np;
	AnimeListener al;
	int sleep;
}
