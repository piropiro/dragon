package shot;
import java.awt.Color;

import javax.swing.JFrame;

import mine.awt.GameCanvas;
import mine.awt.MineAwtUtils;

public class ShotFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3629196055253291262L;

	public ShotFrame() {
		super("RectangleShooter");
		GameCanvas gc = new GameCanvas(ShotCanvas.SCREEN_WIDTH, ShotCanvas.SCREEN_HEIGHT, ShotCanvas.SLEEP_TIME);
		ShotCanvas mc = new ShotCanvas(gc);
		gc.setGameListener(mc);
		setBackground(Color.black);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getContentPane().add(gc);
	}

	public static void main(String args[]) {
		ShotFrame f = new ShotFrame();
		f.pack();
		MineAwtUtils.setCenter(f);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}