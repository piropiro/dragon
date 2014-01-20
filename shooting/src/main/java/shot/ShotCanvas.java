package shot;

import mine.game.GameListener;
import mine.game.GameManager;
import mine.paint.Colors;
import mine.paint.MineGraphics;
import mine.util.Randomer;
import shot.body.Body;
import shot.body.Item;
import shot.body.Jiki;
import shot.body.Laser;
import shot.body.boss.AquaBoss;
import shot.body.boss.BlueBoss;
import shot.body.boss.GreenBoss;
import shot.body.boss.LimeBoss;
import shot.body.boss.OrangeBoss;
import shot.body.boss.PinkBoss;
import shot.body.boss.PurpleBoss;
import shot.body.boss.RedBoss;
import shot.body.boss.YellowBoss;
import shot.body.enemy.AquaEnemy;
import shot.body.enemy.BlueEnemy;
import shot.body.enemy.Enemy;
import shot.body.enemy.GreenEnemyH;
import shot.body.enemy.GreenEnemyV;
import shot.body.enemy.LimeEnemyH;
import shot.body.enemy.LimeEnemyV;
import shot.body.enemy.OrangeEnemy;
import shot.body.enemy.PinkEnemy;
import shot.body.enemy.PurpleEnemy;
import shot.body.enemy.RedEnemy;
import shot.body.enemy.WhiteEnemy;
import shot.body.enemy.YellowEnemy;
import shot.body.wepon.Wepon;

public class ShotCanvas implements GameListener {

	public static final int SCREEN_WIDTH = 240; // 画面の横幅
	public static final int SCREEN_HEIGHT = 240; // 画面の縦幅
	public static final int SLEEP_TIME = 40; // スリープ時間
	public static final int BOSS_TURN = 600; // ボスを出現させるターン

	private Body screen; // スクリーン
	private Laser laser; // レーザー
	private Jiki jiki; // 自機
	private Item[] item; // アイテム配列
	private Enemy[] enemy; // 敵配列
	private Wepon[] beam; // 弾配列
	private Wepon[] tama; // 敵弾配列

	private int turn; // 経過時間

	private int enemy_type1; // ザコ敵タイプ１
	private int enemy_type2; // ザコ敵タイプ２

	private boolean bombf; // ボムフラグ

	private int score; // 得点数
	private int high_score; // ハイスコア
	private int item_score; // アイテムが出現するスコア

	private GameManager gm;

	/**
	 * コンストラクタ
	 */
	public ShotCanvas(GameManager gm) {
		this.gm = gm;
	}

	/**
	 * 初期化
	 */
	public void init() {
		jiki = new Jiki(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		screen = new Body(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 1);
		laser = new Laser();
		beam = new Wepon[100];
		enemy = new Enemy[50];
		tama = new Wepon[100];
		item = new Item[10];

		bombf = false;

		turn = 0;
		score = 0;
		item_score = 50;
	}

	/**
	 * Zキーが押されても何もしない。
	 */
	public void z_key() {
	}

	/**
	 * Xキーが押されたらボム発動。
	 */
	public void x_key() {
		if (jiki.useBomb()) {
			bombf = true;
		}
	}

	/**
	 * Cキーが押されたらレーザー発動。
	 */
	public void c_key() {
		if (jiki.isEnergyMax()) {
			jiki.setEnergy(0);
			laser.setLife(1);
		}
	}

	/**
	 * スタート画面を表示する。
	 */
	public void paintStart(MineGraphics g) {
		g.setColor(Colors.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Colors.WHITE);
		g.drawString("[ Z ] START", SCREEN_WIDTH / 2 - 55, SCREEN_HEIGHT / 2 - 30);
		g.drawString("[ X ] BOMB", SCREEN_WIDTH / 2 - 55, SCREEN_HEIGHT / 2 - 10);
		g.drawString("[ C ] LASER", SCREEN_WIDTH / 2 - 55, SCREEN_HEIGHT / 2 + 10);
		g.drawString("[NEUTRAL] CHARGE", SCREEN_WIDTH / 2 - 55, SCREEN_HEIGHT / 2 + 30);
	}

	/**
	 * ゲームオーバー画面を表示する。
	 */
	public void paintEnd(MineGraphics g) {
		g.setColor(Colors.WHITE);
		g.drawString("Game Over", SCREEN_WIDTH / 2 - 30, SCREEN_HEIGHT / 2 - 10);
	}

	/**
	 * プレイ画面を表示する。
	 */
	public void paintGame(MineGraphics g) {
		turn++;

		// 一度に出現する敵の最大数
		int emax = Math.min(enemy.length, 2 + turn / BOSS_TURN);

		// ビーム出し
		if (!laser.isAlive()) {
			jiki.shoot(turn, beam);
		}

		// 敵出し
		createEnemy(emax);

		// アイテム出し
		createItem();

		// 自機移動
		moveJiki();

		// レーザー移動
		laser.move();

		// 背景表示
		paintBackground(g);

		// レーザー表示
		laser.paint(g, jiki);

		// 敵移動＆弾撃ち＆表示
		displayEnemy(enemy, g, emax);

		// ビーム移動＆表示
		displayWepon(beam, g);

		// 自機表示
		jiki.paint(g, turn);

		// 敵ビーム移動＆表示
		displayWepon(tama, g);

		// アイテム移動＆表示
		displayItem(item, g);

		// 敵当たり判定
		hitEnemy(g, emax);

		// 敵弾当たり判定
		hitTama(g);

		// アイテム当たり判定
		hitItem();

		// フラッシュ発動
		if (bombf) {
			flash(g, true);
			bombf = false;
		}

		// スコア表示
		displayScore(g);
	}

	/**
	 * スコアを表示する。
	 * 
	 * @param g
	 */
	private void displayScore(MineGraphics g) {

		// スコア表示
		high_score = Math.max(high_score, score);
		g.setColor(Colors.WHITE);
		g.drawString("Sc " + score, 5, 11);
		g.drawString("Hi " + high_score, 58, 11);
		g.drawString("Bo", 5, 22);
		g.drawString("En", 58, 22);

		// エネルギーバー表示
		if (jiki.isEnergyMax()) {
			g.setColor(Colors.RED);
		} else {
			g.setColor(Colors.YELLOW);
		}
		g.fillRect(75, 16, jiki.getEnergy() / 2, 3);
		g.setColor(Colors.WHITE);
		g.drawRect(75 - 1, 16 - 1, Jiki.ENERGY_MAX / 2 + 1, 4);

		// ボム個数表示
		g.setColor(Colors.WHITE);
		for (int i = 0; i < jiki.getBomb(); i++) {
			g.fillRect(22 + i * 3, 15, 2, 6);
		}
	}

	/**
	 * アイテムの当たり判定
	 */
	private void hitItem() {
		for (int i = 0; i < item.length; i++) {
			if (item[i] != null) {
				if (Body.hit(item[i], jiki)) {
					switch (item[i].getColor()) {
						case Colors.BLUE :
							jiki.setBlue(true);
							break;
						case Colors.YELLOW :
							jiki.setYellow(true);
							break;
						case Colors.RED :
							jiki.setRed(true);
							break;
					}
					item[i] = null;

					if (jiki.addBomb()) {
						bombf = true;
					}
				}
			}
		}
	}

	/**
	 * 敵弾の当たり判定
	 * 
	 * @param g
	 */
	private void hitTama(MineGraphics g) {
		for (int i = 0; i < tama.length; i++) {
			if (tama[i] != null) {

				// 敵弾と自機の当たり判定
				if (Body.hit(tama[i], jiki)) {
					if (jiki.flash()) {
						flash(g, false);
					} else {
						paintEnd(g);
						gm.end();
					}
					break;
				}

				// 敵弾とレーザーの当たり判定
				if (laser.isAlive()) {
					if (Body.hit(tama[i], laser)) {
						tama[i] = null;
						score++;
					}
				}
			}
		}
	}

	/**
	 * 敵の当たり判定
	 * 
	 * @param g
	 * @param emax
	 */
	private void hitEnemy(MineGraphics g, int emax) {
		for (int i = 0; i < emax; i++) {
			if (enemy[i] != null && enemy[i].isAlive()) {

				// 敵と自機弾の当たり判定
				for (int j = 0; j < beam.length; j++) {
					if (beam[j] == null)
						continue;
					if (Body.hit(enemy[i], beam[j])) {
						enemy[i].setLife(enemy[i].getLife() - 1);
						beam[j] = null;
						score++;
					}
				}

				// 敵とレーザーの当たり判定
				if (laser.isAlive()) {
					if (Body.hit(enemy[i], laser)) {
						enemy[i].setLife(enemy[i].getLife() - 1);
						score++;
					}
				}

				// 敵と自機の当たり判定
				if (Body.hit(enemy[i], jiki)) {
					enemy[i].setLife(enemy[i].getLife() - 5);
					if (jiki.flash()) {
						flash(g, false);
					} else {
						paintEnd(g);
						gm.end();
					}
				}
			}
		}
	}

	/**
	 * 自機の移動
	 */
	private void moveJiki() {
		boolean up = gm.isUp();
		boolean down = gm.isDown();
		boolean left = gm.isLeft();
		boolean right = gm.isRight();

		jiki.move(up, down, left, right);

		if (!up && !down && !left && !right && !laser.isAlive()) {
			jiki.incEnergy();
		}
	}

	/**
	 * 背景を描画する。
	 * 
	 * @param g
	 */
	private void paintBackground(MineGraphics g) {
		g.setColor(Colors.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Colors.GRAY);
		for (int i = 0; i <= SCREEN_HEIGHT / 32; i++) {
			int y = i * 32 + turn % 16 * 2;
			g.drawLine(0, y, SCREEN_WIDTH, y);
		}
	}

	/**
	 * アイテムを出現させる。
	 */
	private void createItem() {
		if (score >= item_score) {
			for (int i = 0; i < item.length; i++) {
				if (item[i] == null) {
					switch (turn % 3) {
						case 0 :
							item[i] = new Item(SCREEN_WIDTH / 2, Colors.BLUE);
							break;
						case 1 :
							item[i] = new Item(SCREEN_WIDTH / 2, Colors.RED);
							break;
						case 2 :
							item[i] = new Item(SCREEN_WIDTH / 2, Colors.YELLOW);
							break;
					}
					break;
				}
			}
			item_score += 100 + turn / BOSS_TURN * 10;
		}
	}

	/**
	 * 敵を出現させる。
	 * 
	 * @param emax
	 */
	private void createEnemy(int emax) {
		if (turn % 5 == 0) {
			for (int i = 0; i < emax; i++) {
				if (enemy[i] == null) {
					enemy[i] = getEnemy();
					break;
				}
			}
		}
	}

	/**
	 * フラッシュボム
	 * 
	 * @param g
	 * @param flag ボタンによる発動ならtrue。ダメージによる発動ならfalse。
	 */
	private void flash(MineGraphics g, boolean flag) {
		g.setColor(Colors.WHITE);

		// 敵弾を全部消す。
		for (int i = 0; i < tama.length; i++) {
			if (tama[i] != null) {
				g.drawLine(jiki.getX() + jiki.getW() / 2, jiki.getY(), tama[i].getX(), tama[i].getY());
				tama[i] = null;
			}
		}

		// すべての敵にダメージを与える。
		if (flag) {
			g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			for (int i = 0; i < enemy.length; i++) {
				if (enemy[i] != null) {
					enemy[i].setLife(enemy[i].getLife() - 5);
					score++;
				}
			}
		}
	}

	/**
	 * 敵移動＆表示＆弾撃ち
	 * 
	 * @param body
	 * @param g
	 * @param max
	 */
	private void displayEnemy(Enemy[] body, MineGraphics g, int max) {
		for (int i = 0; i < max; i++) {
			Enemy b = body[i];
			if (b != null) {
				if (Body.hit(b, screen)) {
					b.shoot(tama);
					b.move(jiki);
					b.paint(g);
				} else {
					body[i] = null;
				}
			}
		}
	}

	/**
	 * アイテム移動＆表示
	 * 
	 * @param body
	 * @param g
	 */
	private void displayItem(Item[] body, MineGraphics g) {
		for (Item b: body) {
			if (b != null) {
				b.move();
				b.paint(g);
			}
		}
	}

	/**
	 * 弾移動＆表示
	 */
	private void displayWepon(Wepon[] body, MineGraphics g) {
		for (int i = 0; i < body.length; i++) {
			Wepon b = body[i];
			if (b != null) {
				if (Body.hit(b, screen)) {
					b.move();
					b.paint(g);
				} else {
					body[i] = null;
				}
			}
		}
	}

	/**
	 * 敵作成
	 */
	private Enemy getEnemy() {
		
		Randomer randomer = Randomer.getInstance();

		// 300ターン過ぎたら出現させる敵のタイプを変える。
		if (turn % 300 == 0) {
			enemy_type1 = randomer.nextInt(6);
			enemy_type2 = randomer.nextInt(6);
		}

		int type = 0; // 出現させる敵のタイプ

		if (turn % BOSS_TURN == 0) {
			// ボスターンになったらボスを出現させる。
			type = 12 + (turn / BOSS_TURN - 1) % 9;
		} else if (randomer.nextInt(20) == 0) {
			// 1/20の確率で中ボスを出現させる。
			type = 6 + randomer.nextInt(6);
		} else {
			// タイプ１とタイプ２からランダムで選択する。
			type = (randomer.nextBoolean()) ? enemy_type1 : enemy_type2;
		}

		// 出現させる座標をランダムに決定する。
		int x = SCREEN_WIDTH / 10 + randomer.nextInt(SCREEN_WIDTH * 8 / 10);
		int y = randomer.nextInt(SCREEN_HEIGHT * 2 / 3);

		// タイプに応じた敵を生成する。
		switch (type) {
			case 0 :
				return new YellowEnemy(y);
			case 1 :
				return new BlueEnemy(x);
			case 2 :
				return new GreenEnemyV(x);
			case 3 :
				return new WhiteEnemy(x);
			case 4 :
				return new GreenEnemyH(y);
			case 5 :
				return new PinkEnemy(x);
			case 6 :
				return new OrangeEnemy(y);
			case 7 :
				return new AquaEnemy(x);
			case 8 :
				return new LimeEnemyV(x);
			case 9 :
				return new RedEnemy(x);
			case 10 :
				return new LimeEnemyH(y);
			case 11 :
				return new PurpleEnemy(x);
			case 12 :
				return new RedBoss(turn / BOSS_TURN);
			case 13 :
				return new OrangeBoss(turn / BOSS_TURN);
			case 14 :
				return new YellowBoss(turn / BOSS_TURN);
			case 15 :
				return new LimeBoss(turn / BOSS_TURN);
			case 16 :
				return new GreenBoss(turn / BOSS_TURN);
			case 17 :
				return new AquaBoss(turn / BOSS_TURN);
			case 18 :
				return new BlueBoss(turn / BOSS_TURN);
			case 19 :
				return new PurpleBoss(turn / BOSS_TURN);
			case 20 :
				return new PinkBoss(turn / BOSS_TURN);
			default :
				return null;
		}
	}
}
