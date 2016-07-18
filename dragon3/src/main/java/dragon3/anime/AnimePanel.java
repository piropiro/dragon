package dragon3.anime;

import java.awt.Graphics;

import javax.swing.JComponent;

import mine.awt.GraphicsAWT;
import mine.awt.MineAwtUtils;
import mine.event.SleepManager;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.UnitMap;
import dragon3.anime.listener.AllAnime;
import dragon3.anime.listener.AnimeListener;
import dragon3.anime.listener.ArrowAnime;
import dragon3.anime.listener.CloseAnime;
import dragon3.anime.listener.CriticalAnime;
import dragon3.anime.listener.DropTextAnime;
import dragon3.anime.listener.EraseAnime;
import dragon3.anime.listener.NumberAnime;
import dragon3.anime.listener.PictureAnime;
import dragon3.anime.listener.RotateAnime;
import dragon3.anime.listener.SingleAnime;
import dragon3.anime.listener.SlideTextAnime;
import dragon3.anime.listener.SomeArrowAnime;
import dragon3.anime.listener.StatusAnime;
import dragon3.anime.listener.SummonAnime;
import dragon3.anime.listener.WalkAnime;
import dragon3.bean.AnimeData;
import dragon3.common.DataList;
import dragon3.common.constant.AnimeType;
import dragon3.image.ImageManager;

public class AnimePanel extends JComponent implements AnimeManager, AnimeWorks {

	private static final long serialVersionUID = 3067832274274205427L;

	private SleepManager sm;

	private UnitMap map;

	private ImageManager imageManager;

	private DataList<AnimeData> animeList;

	private AnimeListener np;
	private AnimeListener al;

	/**
	 * @param uw
	 * @param map
	 */
	public AnimePanel(SleepManager sm, UnitMap map, DataList<AnimeData> animeList, ImageManager imageManager) {
		super();
		this.sm = sm;
		this.map = map;
		this.animeList = animeList;
		this.imageManager = imageManager;

		MineAwtUtils.setSize(this, 32, 32);
		np = null;
		al = null;
	}

	public AnimeData getData(String id) {
		return animeList.getData(id);
	}

	/**
	 * Dispose
	 */
	public void dispose() {
		np = null;
		al = null;
		setVisible(false);
	}

	/**
	 * タイトル表示アニメーション
	 */
	public void openTitle() {
		setBounds(0, 0, 640, 480);
		al = new PictureAnime(imageManager.getImage("title.png"));
		al.animation(this);
		setVisible(true);
	}

	/**
	 * タイトル消去アニメーション１
	 */
	public void closeTitleOut() {
		al = new CloseAnime(CloseAnime.OUT, imageManager.getImage("title.png"));
		al.animation(this);
	}

	/**
	 * タイトル消去アニメーション２
	 */
	public void closeTitleIn() {
		al = new CloseAnime(CloseAnime.IN, imageManager.getImage("title.png"));
		al.animation(this);
		setVisible(false);
		al = null;
	}

	/**
	 * キャラ消去アニメーション
	 *
	 * @param x
	 * @param y
	 */
	public void eraseAnime(int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		setVisible(true);
		al = new EraseAnime(map, x, y);
		al.animation(this);
		al = null;
	}

	/**
	 * 移動アニメーション
	 *
	 * @param x
	 * @param y
	 */
	public void walkAnime(int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		np = null;
		al = new WalkAnime(map, x, y);
		setVisible(true);
		al.animation(this);
		setVisible(false);
		sm.sleep(15);
		al = null;
	}

	/**
	 * ダメージアニメーション
	 *
	 * @param n
	 * @param x
	 * @param y
	 */
	public void numberAnime(int n, int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		setVisible(true);
		np = new NumberAnime(n, imageManager.getNum());
		np.animation(this);
		np = null;
	}

	/**
	 * 即死アニメーション
	 *
	 * @param x
	 * @param y
	 */
	public void criticalAnime(int x, int y) {
		np = null;
		al = new CriticalAnime(map, x, y);
		setBounds(x * 32 - 32, y * 32, 96, 32);
		setVisible(true);
		al.animation(this);
		al = null;
	}

	/**
	 * 落下テキストアニメーション
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void dropText(int text, int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		setVisible(true);
		al = new DropTextAnime(text, imageManager.getText());
		al.animation(this);
		al = null;
	}

	/**
	 * スライドテキストアニメーション
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void slideText(int text, int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		setVisible(true);
		al = new SlideTextAnime(text, imageManager.getText());
		al.animation(this);
		al = null;
	}

	/**
	 * ステータスアニメーション
	 *
	 * @param status
	 * @param x
	 * @param y
	 */
	public void statusAnime(int status, int x, int y) {
		setBounds(x * 32, y * 32 - 16, 32, 48);
		setVisible(true);
		al = new StatusAnime(map, status, x, y, imageManager.getStatus());
		al.animation(this);
		al = null;
		setVisible(false);
	}

	/**
	 * 召喚アニメーション
	 *
	 * @param image
	 * @param x
	 * @param y
	 */
	public void summonAnime(int image, int x, int y) {
		setBounds(x * 32, y * 32 - 32, 32, 56);
		setVisible(true);
		al = new SummonAnime(map, image, x, y);
		al.animation(this);
		al = null;
		setVisible(false);
	}

	/**
	 * 単体アニメーション
	 *
	 * @param data
	 * @param x
	 * @param y
	 */
	public void singleAnime(AnimeData data, int x, int y) {
		setBounds(x * 32, y * 32, 32, 32);
		setVisible(true);
		MineImage[] image = imageManager.getAnimeList().getImage(data.getImage());
		al = new SingleAnime(image, data.getSleep());
		al.animation(this);
		al = null;
	}

	/**
	 * 全体アニメーション
	 *
	 * @param data
	 */
	public void allAnime(AnimeData data) {
		setVisible(true);
		MineImage[] image = imageManager.getAnimeList().getImage(data.getImage());
		al = new AllAnime(map, image, data.getSleep());
		al.animation(this);
		al = null;
	}

	/**
	 * 単体矢アニメーション
	 *
	 * @param data
	 * @param startX
	 * @param startY
	 * @param goalX
	 * @param goalY
	 */
	public void singleArrowAnime(AnimeData data, int startX, int startY, int goalX, int goalY) {
		np = null;
		setBounds(startX * 32, startY * 32, 32, 32);
		setVisible(true);
		MineImage[] image = imageManager.getAnimeList().getImage(data.getImage());
		al = new ArrowAnime(image, data.getSleep(), startX * 32, startY * 32, goalX * 32, goalY * 32);
		al.animation(this);
		al = null;
	}

	/**
	 * 複数矢アニメーション
	 *
	 * @param data
	 * @param x
	 * @param y
	 */
	public void someArrowAnime(AnimeData data, int x, int y) {
		np = null;
		setVisible(true);
		MineImage[] image = imageManager.getAnimeList().getImage(data.getImage());
		al = new SomeArrowAnime(map, image, data.getSleep(), x*32, y*32);
		al.animation(this);
		al = null;
	}

	/**
	 * 回転アニメーション
	 *
	 * @param data
	 * @param startX
	 * @param startY
	 * @param goalX
	 * @param goalY
	 */
	public void rotateAnime(AnimeData data, int startX, int startY, int goalX, int goalY) {
		np = null;
		setVisible(true);
		MineImage[] image = imageManager.getAnimeList().getImage(data.getImage());
		al = new RotateAnime(image, data.getSleep(), startX * 32, startY * 32, goalX * 32, goalY * 32);
		al.animation(this);
		al = null;
	}

	/**
	 * システムアニメーション
	 *
	 * @param id
	 * @param x
	 * @param y
	 */
	public void systemAnime(String id, int x, int y) {
		AnimeData animeData = animeList.getData(id);

		if (animeData.getType() == AnimeType.SINGLE) {
			singleAnime(animeData, x, y);
			return;
		}
	}

	/*** Paint *************************************************/

	public void paintComponent(Graphics g) {
		MineGraphics mg = new GraphicsAWT(g);
		if (np != null)
			np.paint(mg);
		if (al != null)
			al.paint(mg);
	}


	/* (non-Javadoc)
	 * @see dragon3.anime.AnimeComponent#sleep(long)
	 */
	public void sleep(long t) {
		sm.sleep(t);
	}
}
