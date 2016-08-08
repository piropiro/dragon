package dragon3.anime;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dagger.ObjectGraph;
import dragon3.Statics;
import dragon3.common.DataList;
import dragon3.common.constant.Page;
import dragon3.common.util.MoveUtils;
import dragon3.data.AnimeData;
import dragon3.data.load.AnimeDataLoader;
import dragon3.image.ImageManager;
import dragon3.map.MapPanel;
import dragon3.map.MapWorks;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MineCanvasAWT;
import mine.awt.SleepManagerAWT;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;

public class AnimeManagerTest {

	private static Statics statics;
	
	private static UnitMap map;
	private static MapWorks mw;
	private static AnimeManager am;
	private static JFrame frame;

	@BeforeClass
	public static void setUpClass() throws Exception {
		ObjectGraph objectGraph = ObjectGraph.create();
		statics = objectGraph.get(Statics.class);
		
		MineImageLoader mil = new ImageLoaderAWT();
		ImageManager imageManager = new ImageManager(mil);

		MineCanvasAWT mc = new MineCanvasAWT(mil);
		MineAwtUtils.setSize(mc, 640, 480);	

		map = new UnitMap(14, 20, 15, mil);
		map.setVisible(Page.P01, true);
		map.setTile(Page.P01, imageManager.getStageObj(), -1);
		map.setVisible(Page.P20, true);
		map.setTile(Page.P20, imageManager.getBodyImageList().getImageList(), 0);
		map.setVisible(Page.P50, true);
		map.setTile(Page.P50, imageManager.getStatus(), 0);

	
		SleepManager sm = new SleepManagerAWT(mc);
		
		PaintComponent mapC = mc.newLayer(0, 0, 640, 480);
		MapPanel mapP = new MapPanel(mapC, null, map);
		mw = mapP;
		mapC.setVisible(true);


		PaintComponent animeC = mc.newLayer(0, 0, 32, 32);
		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		AnimePanel ap = new AnimePanel(animeC, mw, sm, map, animeList, imageManager);
		am = ap;

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mc);
		frame.pack();
		frame.setLocation(100, 100);
		frame.setVisible(true);
	}

	@Before
	public void setUp() throws Exception {
		map.setPage(Page.P01, statics.getMapData("D01"));
		map.setData(Page.P01, 11, 10, MoveUtils.OPEN_MAGIC);
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
