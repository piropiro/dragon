




package imo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import mine.KeyManager;
import mine.Mine;

// Referenced classes of package imo:
//            Body, Jiki, Sister, Imo, 
//            Sword, Turu, PaintListener, Images, 
//            MainWorks

public class GamePaint implements PaintListener {

	public GamePaint(MainWorks mainworks) {
		mw = mainworks;
		screen = new Body(0.0D, 0.0D, 300, 300, 0.0D, 0.0D);
		km = new KeyManager();
	}

	public void resetData(String s, int i) {
		Love = 0;
		clearFlag = false;
		ore = new Jiki(120D, 120D, s, screen, Images.chara);
		sister = new Sister(0.0D, 0.0D, Images.chara);
		imo = new Imo(280D, 280D, i, screen, Images.chara);
		sword = new Sword(Images.chara);
		turu = new Turu(Images.chara);
		km.reset();
	}

	public void paint(Graphics g) {
		g.setFont(Mine.getFont(14));
		sword.display(g);
		ore.display(g);
		sister.display(g);
		imo.display(g);
		turu.display(g);
		ore.displayHP(g);
		imo.displayHP(g);
	}

	private void hit() {
		if (Body.Hit(ore, sister)) {
			ore.moveS();
			Love++;
		}
		if (Body.Hit(ore, imo))
			if (imo.isAlive())
				ore.damage(imo, 1);
			else
				ore.moveS();
		if (turu.isAlive() && Body.Hit(ore, turu))
			ore.damage(imo, 3);
		if (sword.isAlive() && Body.Hit(sword, sister))
			sister.damage();
		if (sword.isAlive() && Body.Hit(sword, imo))
			imo.damage(ore, 1);
		sword.move(ore);
	}

	private void endjudge() {
		if (ore.hp <= 0) {
			mw.gameEnd(1);
			return;
		}
		if (sister.hp <= 0) {
			mw.gameEnd(4);
			return;
		}
		if (Body.Hit(imo, sister)) {
			mw.gameEnd(3);
			return;
		}
		if (turu.isAlive() && Body.Hit(turu, sister)) {
			mw.gameEnd(3);
			return;
		}
		if (Love >= 200) {
			mw.gameEnd(2);
			return;
		}
		if (imo.hp <= 0) {
			clearFlag = true;
			if (ore.hp == ore.hpMax) {
				if (Love >= 10) {
					mw.gameEnd(5);
					return;
				}
			} else {
				mw.gameEnd(0);
				return;
			}
		}
	}

	public int getEXP() {
		if (clearFlag)
			return ore.hp;
		else
			return 0;
	}

	public void keyReleased(KeyEvent keyevent) {
		km.keyReleased(keyevent);
	}

	public void keyPressed(KeyEvent keyevent) {
		km.keyPressed(keyevent);
	}

	public void run() {
		mw.sleep(20L);
		mw.repaint();
		if (km.isEscape() || km.isKey('x'))
			mw.gameStart();
		endjudge();
		ore.move(km.isUp(), km.isDown(), km.isLeft(), km.isRight());
		imo.move(ore);
		sister.move();
		if (km.isEnter() || km.isKey('z'))
			ore.shot(sword);
		imo.shot(turu);
		sword.move(ore);
		turu.move(imo);
		hit();
	}

	Jiki ore;
	Sister sister;
	Sword sword;
	Imo imo;
	Turu turu;
	Body screen;
	int Love;
	boolean clearFlag;
	MainWorks mw;
	KeyManager km;
}
