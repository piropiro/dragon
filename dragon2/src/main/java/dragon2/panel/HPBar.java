package dragon2.panel;





import java.awt.Color;
import java.awt.Graphics;
import mine.Mine;

public class HPBar {

	public HPBar() {
		hpMax = 10;
	}

	public void setup(boolean flag, int i, int j) {
		hit = flag;
		hp = i;
		hpMax = j;
		setMin(i, false);
	}

	public void paint(int i, int j, Graphics g) {
		g.setColor(Color.white);
		g.drawString("" + hp, i, j);
		drawBar(g, we, w, 60, i + 30, j - 8);
	}

	private void drawBar(Graphics g, int i, int j, int k, int l, int i1) {
		byte byte0 = 60;
		byte byte1 = 6;
		g.setColor(Color.white);
		g.drawRect(l - 2, i1 - 2, byte0 + 3, byte1 + 3);
		g.setColor(Color.black);
		g.drawRect(l - 1, i1 - 1, byte0 + 1, byte1 + 1);
		if (hpMin <= hp) {
			if (dpaint)
				g.setColor(new Color(128, 128, 0));
			else if (hit && dead)
				g.setColor(new Color(150, 200, 255));
			else
				g.setColor(Color.orange);
			g.fillRect(l, i1, (byte0 * j) / k, byte1);
			g.setColor(Color.yellow);
			g.fillRect(l, i1, (byte0 * i) / k, byte1);
			if (j != 0 && !dpaint && !hit)
				g.fillRect((l + (byte0 * j) / k) - 2, i1, 1, byte1);
		} else {
			if (dpaint)
				g.setColor(new Color(150, 200, 255));
			else
				g.setColor(new Color(100, 150, 200));
			g.fillRect(l, i1, (byte0 * i) / k, byte1);
			g.setColor(Color.yellow);
			g.fillRect(l, i1, (byte0 * j) / k, byte1);
		}
		g.setColor(Color.white);
	}

	public void setMin(int i, boolean flag) {
		hpMin = Mine.mid(0, i, hpMax);
		dpaint = flag;
		dead = i < 0;
		w = (60 * hp) / hpMax;
		we = (60 * hpMin) / hpMax;
		ws = (60 * hp) / hpMax;
	}

	public boolean henka() {
		if (w == we)
			return false;
		if (w > we) {
			w--;
			hp = Mine.mid(hpMin, (w * hpMax) / 60, hp);
		} else {
			w++;
			hp = Mine.mid(hp, (w * hpMax) / 60, hpMin);
		}
		return true;
	}

	public int getSleepTime() {
		return 400 / Math.max(1, Math.abs(ws - we));
	}

	private boolean hit;
	private boolean dead;
	private boolean dpaint;
	private int w;
	private int ws;
	private int we;
	private int hp;
	private int hpMax;
	private int hpMin;
	private Color bgc;
	static final int bar_width = 60;
}
