package shot.body.enemy;

import mine.paint.MineColor;
import mine.paint.MineGraphics;
import mine.util.Randomer;
import shot.body.Body;

public abstract class Enemy extends Body {

	private int xv;
	private int yv;
	private MineColor color;

	private int time;
	private int shootTime;

	public void init(int x, int y, int w, int h, int xv_, int yv_, int life, MineColor color) {
		super.init(x, y, w, h, life);
		this.xv = xv_;
		this.yv = yv_;
		this.color = color;
		this.time = 0;
	}
	
	public void setShootTime(int shootTime) {
		this.shootTime = shootTime;
		if (shootTime > 0) {
			this.time = Randomer.getInstance().nextInt(shootTime);
		}
	}

	public void move(Body ore){
		time++;
		if (getLife() <= 0) {
			setY(getY() + 2);
		} else {
			moves(ore);
		}
		setX(getX() + xv);
		setY(getY() + yv);
	}
	protected abstract void moves(Body ore);

	public void paint(MineGraphics g) {
		if (getLife() > 0) {
			g.setColor(color);
			g.fillRect(getX(), getY(), getW(), getH());
		} else if (incLife(-1) > -7) {
			drawDead(getX() + getW() / 2, getY() + getH() / 2, getLife(), g);
		}
	}
	
	public static void drawDead(int x, int y, int count, MineGraphics g) {
		g.setColor(MineColor.SILVER);
		g.drawRect(
			x + count * 2,
			y + count * 2,
			-count * 4,
			-count * 4);
		g.setColor(MineColor.GRAY);
		g.drawRect(
			x + count * 4,
			y + count * 4,
			-count * 8,
			-count * 8);
		g.setColor(MineColor.GRAY);
		g.drawRect(
			x + count * 8,
			y + count * 8,
			-count * 16,
			-count * 16);
	}

	public void shoot(Body[] tama) {
		if (getLife() > 0 && shootTime != 0 && time % shootTime == 0) {
			Body[] newtama = shoots();
			if (newtama != null) {
				int j = 0;
				for (int i = 0; i < tama.length; i++) {
					if (tama[i] != null)
						continue;
					tama[i] = newtama[j++];
					if (j >= newtama.length)
						break;
				}
			}
		}
	}
	protected abstract Body[] shoots();

	public void setXv(int xv) {
		this.xv = xv;
	}

	public int getXv() {
		return xv;
	}

	public void setYv(int yv) {
		this.yv = yv;
	}

	public int getYv() {
		return yv;
	}

	public MineColor getColor() {
		return color;
	}

	public void setColor(MineColor i) {
		color = i;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int i) {
		time = i;
	}

}
