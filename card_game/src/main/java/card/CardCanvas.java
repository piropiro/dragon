package card;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

import mine.awt.GraphicsAWT;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MouseManagerAWT;
import mine.awt.SleepManagerAWT;
import mine.event.MouseAllListener;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.PaintBox;
import mine.paint.UnitMap;
import mine.thread.Lock;
import card.anime.AnimeManager;
import card.body.Card;
import card.body.Enemy;
import card.body.Player;
import card.common.ImageList;
import card.common.Page;
import card.manage.BattleManager;
import card.manage.CardManager;
import card.manage.DoubleManager;
import card.paint.ResultPainter;
import card.paint.WakuPainter;



public class CardCanvas extends JComponent
	implements UnitWorks, MouseAllListener {

	private static final long serialVersionUID = -6614496241129845710L;

	private CardListener listener;

	private List<Card> cards;
	private CardManager cardManager;
	private AnimeManager animeManager;
	private SleepManagerAWT sleepManager;
	private BattleManager battleManager;
	private ResultPainter resultPainter;
	private DoubleManager doubleManager;
	private WakuPainter wakuMover;
	private Enemy enemy;
	private Player player;
	private UnitMap map;
	private Lock lock;
	private Random random;

	private MineImage blueChara;
	private MineImage redChara;

	private int[] blueNum;
	private int[] redNum;

	public CardCanvas(){
		super();
		ImageList il = new ImageList(new ImageLoaderAWT());
		cards = new ArrayList<Card>();
		MineAwtUtils.setSize(this, 32*11, 32*13);
		MouseManagerAWT manager = new MouseManagerAWT(this);
		manager.setMouseAllListener(this);

		initMap(il);
		wakuMover = new WakuPainter(this, map);
		sleepManager = new SleepManagerAWT(this);
		animeManager = new AnimeManager(this, map, il);
		cardManager = new CardManager(this, animeManager, il);
		battleManager = new BattleManager(animeManager);
		doubleManager = new DoubleManager(cardManager, animeManager);
		resultPainter = new ResultPainter();

		lock = new Lock();
		random = new Random();
	}

	public void setCardListener(CardListener listener) {
		this.listener = listener;
	}

	private void initMap(ImageList il){
		map = new UnitMap(3, 11, 13, il.getImageLoader());
		map.setTile(Page.BACK, il.getBack(), -1);
		map.setTile(Page.WAKU, il.getWaku(), 0);
		map.clear(Page.BACK, -1);
		map.clear(Page.CHARA, -1);
		map.clear(Page.WAKU, 0);
		map.setVisible(0, true);
		map.setVisible(1, true);
		map.setVisible(2, true);
	}

	private void initialize(){
		battleManager.initialize();
		doubleManager.initialize();
		enemy = new Enemy(this, battleManager, cardManager);
		player = new Player(cardManager);

		cards.removeAll(cards);
		cardManager.setBlueCards(blueNum);
		cardManager.setRedCards(redNum);
		map.setTile(Page.CHARA, new MineImage[]{blueChara, redChara}, -1);
	}

	public void setBlueChara(MineImage blueChara, int[] blueNum){
		this.blueChara = blueChara;
		this.blueNum = blueNum;
	}

	public void setRedChara(MineImage redChara, int[] redNum){
		this.redChara = redChara;
		this.redNum = redNum;
	}

	public void addCard(Card card){
		synchronized (cards) {
			cards.add(card);
		}
	}
	public void removeCard(Card card){
		synchronized (cards) {
			cards.remove(card);
		}
	}

	public void paintComponent(Graphics g){
		MineGraphics mg = new GraphicsAWT(g);
		requestFocus();
		map.draw(mg);
		synchronized (cards) {
			for (Card card : cards) {
				card.paint(mg);
			}
		}
		resultPainter.paint(mg);
	}

	public void repaint(PaintBox box){
		repaint(box.getX(), box.getY(), box.getW(), box.getH());
	}

	public void sleep(long msec){
		sleepManager.sleep(msec);
	}

	public int nextInt(int max) {
		return random.nextInt(max);
	}

	public void start(){
		lock.lock();
		initialize();
		animeManager.opening(cardManager.getRedCards(), cardManager.getBlueCards());
		enemy.openCard();
		enemy.openCard();
		enemy.openCard();
		lock.unlock();
	}

	public void dispose(){
		lock.lock();
		animeManager.closing((Card[])cards.toArray(new Card[0]));
		resultPainter.setResult(ResultPainter.NONE);
		repaint();
		lock.unlock();
	}


	private void startBattle(int n){
		Card red = enemy.selectCard();
		Card blue = player.selectCard(n);
		battleManager.startBattle(red, blue);
		if (battleManager.isEnd()) {
			gameset();
		} else if (!player.hasCard()){
			battleManager.retireBlue();
			gameset();
		} else {
			enemy.openCard();
		}
	}

	private void gameset(){
		int redWin = battleManager.getRedWin();
		int blueWin = battleManager.getBlueWin();
		if (redWin > blueWin) {
			resultPainter.setResult(ResultPainter.LOSE);
		} else if ( redWin < blueWin ) {
			resultPainter.setResult(ResultPainter.WIN);
		} else {
			resultPainter.setResult(ResultPainter.DRAW);
		}

		repaint();
		sleep(1000);
		listener.gameExit(redWin, blueWin);
	}

	public void leftPressed(int x, int y) {
		mouseMoved(x, y);
		if (lock.lock()) {
			x /= 32;
			y /= 32;
			if ( 2 <= x && x <= 8 && y == 8) {
				int n = x - 2;
				if (cardManager.isOpenedBlue(n)) {
					startBattle(n);
				} else {
					player.openCard(n);
				}
			} else if ( x == 3 && y == 10) {
				if (doubleManager.clickDoubleCard()) {
					player.doubleCard();
				}
			}
			doubleManager.checkDoubleCard();
			lock.unlock();
		}
	}

	public void mouseMoved(int x, int y) {
		if (wakuMover.isMoved(x/32, y/32)) {
			if (lock.lock()) {
				wakuMover.moveWaku(x/32, y/32);
				lock.unlock();
			}
		}
	}

	public void mouseExited(int x, int y) {
		if (lock.lock()) {
			wakuMover.moveWaku(-1, -1);
			lock.unlock();
		}
	}

	public void leftReleased(int x, int y) {}

	public void rightPressed(int x, int y) {}

	public void rightReleased(int x, int y) {}

	public void leftDragged(int x, int y) {}

	public void rightDragged(int x, int y) {}

	public void mouseEntered(int x, int y) {}

}
