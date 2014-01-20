package shot;

import java.awt.Color;

import javax.swing.JApplet;

import mine.awt.GameCanvas;

/* <APPLET codebase="../" code="ShotApplet" width=120 height=120></APPLET> */

public class ShotApplet extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427606229836053624L;

	/*** コンストラクタ **********************************/

	private GameCanvas gc;
	private ShotCanvas sc;

	public ShotApplet() {
		super();
		gc = new GameCanvas(ShotCanvas.SCREEN_WIDTH, ShotCanvas.SCREEN_HEIGHT, 40);
		sc = new ShotCanvas(gc);
		gc.setGameListener(sc);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getContentPane().add(gc);
	}



}