package shot.body;

import mine.paint.Colors;
import mine.paint.MineGraphics;

public class Laser extends Body {

	/**
	 * 横幅最大値
	 */
	private static final int WIDTH_MAX = 80;

	/**
	 * 溜め時間
	 */
	private static final int CHARGE_TIME = 15;

	/**
	 * 拡大時間
	 */
	private static final int UP_TIME = 6;

	/**
	 * 最大時間
	 */
	private static final int FULL_TIME = 60;

	/**
	 * 収縮時間
	 */
	private static final int DOWN_TIME = 40;

	/**
	 * コンストラクタ<p>
	 * 
	 */
	public Laser() {
		super(0, 0, 0, 0, 0);
	}

	/**
	 * 移動<p>
	 */
	public void move() {
		if (isAlive()) {
			setLife(getLife() + 1);

			int time = getLife();
			if (time < CHARGE_TIME) { // 溜め時間

			} else if ((time -= CHARGE_TIME) < UP_TIME) { // 拡大時間

				setW(WIDTH_MAX * time * time / (UP_TIME * UP_TIME));

			} else if ((time -= UP_TIME) < FULL_TIME) { // 最大時間

				setW(WIDTH_MAX);

			} else if ((time -= FULL_TIME) < DOWN_TIME) { // 収縮時間

				int n = DOWN_TIME - time;
				setW(WIDTH_MAX * n * n / (DOWN_TIME * DOWN_TIME));

			} else { // 終了
				setX(0);
				setY(0);
				setW(0);
				setH(0);
				setLife(0);
			}
		}
	}

	/**
	 * 描画<p>
	 * 
	 * @param g
	 * @param ore
	 */
	public void paint(MineGraphics g, Body ore) {
		if (isAlive()) {
			if (getLife() < CHARGE_TIME) {
				int x = ore.getX() + ore.getW() / 2;
				int y = ore.getY();
				int n = CHARGE_TIME - getLife();
				int r = WIDTH_MAX * n * n / (CHARGE_TIME * CHARGE_TIME);

				g.setColor(Colors.WHITE);
				g.drawRect(x - r, y - r, r * 2, r * 2);
				g.drawRect(x - r * 2, y - r * 2, r * 4, r * 4);
				g.drawRect(x - r * 4, y - r * 4, r * 8, r * 8);
			} else {
				setH(ore.getY());
				setX(ore.getX() + ore.getW() / 2 - getW() / 2);
				setY(0);

				g.setColor(Colors.YELLOW);
				g.fillRect(getX(), getY(), getW() + 1, getH());
			}
		}
	}

}
