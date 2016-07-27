package shot.body;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mine.paint.MineColor;
import mine.paint.MineGraphics;
import shot.ShotCanvas;
import shot.body.wepon.BeamWepon;
import shot.body.wepon.BlueWepon;
import shot.body.wepon.RedWepon;
import shot.body.wepon.Wepon;
import shot.body.wepon.YellowWepon;

@Data
@EqualsAndHashCode(callSuper=false)
public class Jiki extends Body {

	public static final int ENERGY_MAX = 80; // エネルギー最大値

	private int energy; // レーザーエネルギー

	private int bomb; // ボム個数

	private boolean blue;
	private boolean red;
	private boolean yellow;

	/**
	 * コンストラクタ<p>
	 * 
	 * @param x
	 * @param y
	 */
	public Jiki(int x, int y) {
		super(x, y, 1, 1, 1);
		bomb = 3;
	}

	/**
	 * 移動<p>
	 * 
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 */
	public void move(boolean up, boolean down, boolean left, boolean right) {
		int v = (isEnergyMax()) ? 4 : 3;

		if (up) {
			setY(Math.max(5, getY() - v));
		}
		if (down) {
			setY(Math.min(ShotCanvas.SCREEN_HEIGHT - 5, getY() + v));
		}

		if (left) {
			setX(Math.max(5, getX() - v));
		}
		if (right) {
			setX(Math.min(ShotCanvas.SCREEN_WIDTH - 5, getX() + v));
		}
	}

	/**
	 * 弾撃ち<p>
	 * 
	 * @param time
	 * @param beam
	 */
	public List<Wepon> shoot(int time) {
		int x = getX();
		int y = getY();

		List<Wepon> beams = new ArrayList<>();
		if (time % 4 == 0) {
			beams.add(new BeamWepon(x - 2, y));
		}

		if (blue && (time % 8 == 0)) {
			beams.add(new BlueWepon(x - 7, y));
			beams.add(new BlueWepon(x + 3, y));
		}

		if (red && (time % 8 == 0)) {
			int t = time / 8 % 2;
			beams.add(new RedWepon(x - 7, y + 5, -2 + t, -1 - t));
			beams.add(new RedWepon(x + 5, y + 5, 2 - t, -1 - t));
		}

		if (yellow && (time % 16 == 0)) {
			beams.add(new YellowWepon(x - 4, y, -4, 3));
			beams.add(new YellowWepon(x, y, 4, 3));
		}
		
		return beams;
	}

	/**
	 * 描画<p>
	 * 
	 * @param g
	 * @param time
	 */
	public void paint(MineGraphics g, int time) {

		if (red && yellow && blue) {
			g.setColor(MineColor.GRAY);
		} else if (red && yellow) {
			g.setColor(MineColor.ORANGE);
		} else if (red && blue) {
			g.setColor(MineColor.PINK);
		} else if (yellow && blue) {
			g.setColor(MineColor.LIME);
		} else if (red) {
			g.setColor(MineColor.RED);
		} else if (blue) {
			g.setColor(MineColor.BLUE);
		} else if (yellow) {
			g.setColor(MineColor.YELLOW);
		} else {
			g.setColor(MineColor.WHITE);
		}

		int x = getX() - 2;
		int y = getY() - 2;

		if (blue) {
			g.fillRect(x - 2, y - 3, 1, 12);
			g.fillRect(x - 3, y, 1, 8);
			g.fillRect(x - 4, y + 3, 1, 3);
			g.fillRect(x + 6, y - 3, 1, 12);
			g.fillRect(x + 7, y, 1, 8);
			g.fillRect(x + 8, y + 3, 1, 3);
		}
		if (yellow) {
			g.fillRect(x - 2, y + 2, 1, 6);
			g.fillRect(x - 3, y + 3, 1, 7);
			g.fillRect(x - 4, y + 4, 1, 8);
			g.fillRect(x + 6, y + 2, 1, 6);
			g.fillRect(x + 7, y + 3, 1, 7);
			g.fillRect(x + 8, y + 4, 1, 8);
		}
		if (red) {
			g.fillRect(x - 4, y + 3, 3, 1);
			g.fillRect(x - 6, y + 4, 5, 1);
			g.fillRect(x - 8, y + 5, 7, 1);
			g.fillRect(x + 6, y + 3, 3, 1);
			g.fillRect(x + 6, y + 4, 5, 1);
			g.fillRect(x + 6, y + 5, 7, 1);
		}

		g.fillRect(x, y, 5, 7);

		g.fillRect(x - 2, y + 5, 9, 1);
		g.fillRect(x - 2, y + 3, 1, 4);
		g.fillRect(x + 6, y + 3, 1, 4);
		g.setColor(MineColor.AQUA);
		g.fillRect(x + 1, y - 1, 3, 4);
		g.setColor(MineColor.RED);
		g.fillRect(x, y + 8, 5, 1);

		if (time % 2 == 0) {
			g.fillRect(x + 1, y + 9, 3, 1);
			if (isEnergyMax()) {
				g.setColor(MineColor.YELLOW);
				g.drawRect(x - 3, y, 2, 2);
				g.drawRect(x + 5, y, 2, 2);
			}
		}
	}

	public void incEnergy() {
		energy = Math.min(energy + 1, ENERGY_MAX);
	}

	public boolean isEnergyMax() {
		return energy >= ENERGY_MAX;
	}

	public boolean flash() {
		if (blue || red || yellow) {
			blue = false;
			red = false;
			yellow = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean useBomb(){
		if (bomb > 0) {
			bomb--;
			return true;
		} else {
			return false;
		}
	}
	public boolean addBomb(){
		if (bomb < 10) {
			bomb++;
			return false;
		} else {
			return true;
		}
	}

}
