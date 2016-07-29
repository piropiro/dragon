package dragon3.anime;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import mine.awt.GraphicsAWT;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.PaintComponentAWT;
import mine.awt.SleepManagerAWT;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.paint.MineGraphics;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dragon3.Statics;
import dragon3.bean.AnimeData;
import dragon3.bean.load.AnimeDataLoader;
import dragon3.common.DataList;
import dragon3.common.constant.Page;
import dragon3.common.util.MoveUtils;
import dragon3.image.ImageManager;

public class AnimeManagerTest {

	private static UnitMap map;
	private static AnimeManager am;
	private static JFrame frame;

	@BeforeClass
	public static void setUpClass() throws Exception {
		MineImageLoader mil = new ImageLoaderAWT();
		ImageManager imageManager = new ImageManager(mil);

		map = new UnitMap(14, 20, 15, mil);
		map.setVisible(Page.P00, true);
		map.setTile(Page.P00, imageManager.getBack(), -1);
		map.setVisible(Page.P20, true);
		map.setTile(Page.P20, imageManager.getBodyImageList().getImageList(), 0);
		map.setVisible(Page.P50, true);
		map.setTile(Page.P50, imageManager.getStatus(), 0);

		JLayeredPane panel = new JLayeredPane(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				MineGraphics mg = new GraphicsAWT(g);
				map.draw(mg);
			}
		};

		MineAwtUtils.setSize(panel, 640, 480);

		SleepManager sm = new SleepManagerAWT(panel);

		PaintComponent pc = new PaintComponentAWT(32, 32);
		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		AnimePanel ap = new AnimePanel(pc, sm, map, animeList, imageManager);
		am = ap;
		panel.add((JComponent)pc);

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocation(100, 100);
		frame.setVisible(true);
	}

	@Before
	public void setUp() throws Exception {
		map.setPage(Page.P00, Statics.getMapData("D01"));
		map.setData(Page.P00, 11, 10, MoveUtils.OPEN_MAGIC);
		map.setData(Page.P20, 10, 10, 1);
		map.fillDia(Page.P41, 10, 10, 2, 1);
		map.clear(Page.P02, 1);
		map.paintStep(Page.P02, Page.P03, 2, 2, 20);
		frame.repaint();
	}

	@Test
	public void testOpenCloseTitle(){
		am.openTitle();
		am.closeTitleOut();
		am.closeTitleIn();
	}

	@Test
	public void testEraseAnime(){
		am.eraseAnime(10, 10);
	}
	@Test
	public void testNumberAnime(){
		am.numberAnime(278, 10, 10);
	}
	@Test
	public void testStatusAnime(){
		am.statusAnime(AnimeManager.STATUS_SLEEP, 10, 10);
	}
	@Test
	public void testSingleSystemFire(){
		am.singleAnime((AnimeData) am.getData("system.fire"), 10, 10);
	}
	@Test
	public void testDropTextFinish(){
		am.dropText(AnimeManager.TEXT_FINISH, 10, 10);
	}
	@Test
	public void testSingleSystemSummon(){
		am.singleAnime((AnimeData) am.getData("system.summon"), 11, 10);
	}
	@Test
	public void testSummonAnime(){
		am.summonAnime(1, 11, 10);
	}
	@Test
	public void testWalkAnime(){
		am.walkAnime(10, 10);
	}
	@Test
	public void testRotateAnime(){
		am.rotateAnime(am.getData("ROTATE.roll_blue"), 10, 10, 11, 11);
	}
	@Test
	public void testSomeArrowAnime(){
		am.someArrowAnime(am.getData("SINGLE_ARROW.arrow_blue"), 0, 0);
	}
	@Test
	public void testSingleArrowAnime(){
		am.singleArrowAnime(am.getData("SINGLE_ARROW.arrow"), 10, 10, 12, 12);
	}
	@Test
	public void testAllSystemFire(){
		am.allAnime(am.getData("system.fire"));
	}
	@Test
	public void testSingleSystemDeath(){
		am.singleAnime(am.getData("system.death"), 10, 10);
	}
	@Test
	public void testCriticalAnime(){
		am.criticalAnime(10, 10);
	}
	@Test
	public void testSlideTextDeath(){
		am.slideText(AnimeManager.TEXT_DEATH, 10, 10);
	}
}
