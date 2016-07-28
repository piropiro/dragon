package dragon3.panel;

import java.util.Arrays;

import dragon3.Statics;
import dragon3.common.Body;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Page;
import dragon3.common.util.MoveUtils;
import dragon3.image.ImageManager;
import dragon3.manage.SummonManagerMock;
import dragon3.manage.TreasureManagerMock;
import dragon3.manage.TurnManagerMock;
import dragon3.panel.paint.CampDataPaint;
import dragon3.view.DragonFrame;
import junit.framework.TestCase;
import mine.MineException;
import mine.awt.ImageLoaderAWT;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;
import mine.util.Point;

public class PanelManagerTest extends TestCase {

	private static PanelManager pm;
	private static UnitMap map;

	static {
		try {
			MineImageLoader mil = new ImageLoaderAWT();
			ImageManager im = new ImageManager(mil);

			map = new UnitMap(14, 20, 15, mil);
			map.setVisible(Page.P00, true);
			map.setTile(Page.P00, im.getBack(), -1);
			map.setVisible(Page.P20, true);
			map.setTile(Page.P20, im.getBodyList().getImageList(), 0);
			map.setVisible(Page.P50, true);
			map.setTile(Page.P50, im.getStatus(), 0);
			map.setPage(Page.P00, Statics.getMapData("D01"));
			map.setData(Page.P00, 11, 10, MoveUtils.OPEN_MAGIC);
			map.setData(Page.P20, 10, 10, 1);
			map.fillDia(Page.P41, 10, 10, 2, 1);
			map.clear(Page.P02, 1);
			map.paintStep(Page.P02, Page.P03, 2, 2, 20);

			DragonFrame fw = new DragonFrame();
			
			fw.getMapPanel().setPaintListener((g) -> map.draw(g));

			PanelManagerImpl pmi = new PanelManagerImpl(fw, null, map, null, im, mil);
			pmi.setTurnManager(new TurnManagerMock());
			pmi.setTreasure(new TreasureManagerMock());
			pmi.setSummon(new SummonManagerMock());
			pm = pmi;

			fw.launch();
			
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}


	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		Thread.sleep(2000);
		pm.setHelpVisible(false);
		pm.closeSmall();

		super.tearDown();
	}

	public void testHelp1() {
		pm.setHelpVisible(true);
		pm.displayHelp(new Point(10, 10), new String[]{"Hello, World!", "こんにちわ世界！"}, GameColor.BLUE);
	}

	public void testHelp2() {
		pm.setHelpVisible(true);
		pm.displayHelp(new Point(1, 1), new String[]{"Hello, World!", "こんにちわ世界！"}, GameColor.RED);
	}

	public void testLarge() {
		pm.displayLarge("Largeでかい", GameColor.BLUE, 1500);
	}

	public void testSmall1() {
		Body body = new Body();
		body.setX(5);
		body.setY(5);
		pm.displaySmall("攻撃", GameColor.WHITE, body);
	}

	public void testSmall2() {
		Body body = new Body();
		body.setX(10);
		body.setY(10);
		pm.displaySmall("火炎輪", GameColor.RED, body);
	}

	public void testHp1() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);

		Body bb = new Body();
		bb.setColor(GameColor.RED);
		bb.setX(11);
		bb.setY(11);
		bb.setHp(20);
		bb.setHpMax(30);


		pm.displayHp(true, ba, bb, 5, true);

		pm.displayHp(false, bb, ba, 5, false);

	}

	public void testMessage() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);
		ba.setImageNum(1);

		pm.addMessage("てすと");
		pm.addMessage("これはテストです。");
		pm.addMessage("dada!");
		pm.addMessage("hoge!");
		pm.addMessage("hoo!");
		pm.startMessage(ba);
	}

	public void testDataData() {
		pm.displayData(1, 1);
	}

	public void testDataPlace() {
		pm.displayPlace(1, 1);
	}

	public void testDataWazaList() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);
		ba.setImageNum(1);

		String[] wazaList = { "slash", "fire" };
		ba.setWazaList(Arrays.asList(wazaList));


		pm.displayWazaList(ba);
	}

	public void testDataAnalyze() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);
		ba.setImageNum(1);

		pm.displayAnalyze(ba);
	}

	public void testDataStatus() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);
		ba.setImageNum(1);

		ba.setStr(1);
		ba.setDef(2);
		ba.setMst(3);
		ba.setMdf(4);
		ba.setHit(5);
		ba.setMis(6);

		pm.displayStatus(ba);
	}

	public void testDataCamp() {
		pm.displayCampData(5, 5, CampDataPaint.C_CHARA1, GameColor.BLUE);
	}

	public void testDataWaza() {
		Body ba = new Body();
		ba.setColor(GameColor.BLUE);
		ba.setX(10);
		ba.setY(10);
		ba.setHp(10);
		ba.setHpMax(20);
		ba.setImageNum(1);

		String[] wazaList = { "slash", "fire" };
		ba.setWazaList(Arrays.asList(wazaList));

		pm.displayWaza(ba, 0);
	}
}
