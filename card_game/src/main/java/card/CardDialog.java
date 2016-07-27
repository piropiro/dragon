package card;

import javax.swing.JComponent;
import javax.swing.JDialog;

import mine.MineException;
import mine.MineUtils;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MouseManagerAWT;
import mine.awt.PaintComponentAWT;
import mine.awt.SleepManagerAWT;
import mine.event.MouseManager;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.io.MatrixIO;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;

public class CardDialog extends JDialog implements CardListener {

	private static final long serialVersionUID = -7568381413859212716L;

	private MineImage[] chara;
	private int level = 1;
	private CardCanvas cc;
	private int[][] status;

	public static void main(String[] args) throws MineException {
		new CardDialog();
	}

	public CardDialog() throws MineException {
		super();
		MineImageLoader mil = new ImageLoaderAWT();
		
		PaintComponent cardPanel = new PaintComponentAWT(CardCanvas.WIDTH, CardCanvas.HEIGHT);
		
		setTitle("CardBattle");
		
		MouseManager mouseManager = new MouseManagerAWT((JComponent)cardPanel);
		SleepManager sleepManager = new SleepManagerAWT((JComponent)cardPanel);
		cc = new CardCanvas(cardPanel, mil, mouseManager, sleepManager);
		cardPanel.setPaintListener(cc);
		
		cc.setCardListener(this);

		chara = (MineImage[])MineUtils.linerize(
			mil.loadTile("card/image/chara.png", 32, 32), new MineImage[0]);
			
		status = MatrixIO.read("card/data/status.txt");


		getContentPane().add((JComponent)cardPanel);
		pack();
		MineAwtUtils.setCenter(this);
		setVisible(true);
		cc.setBlueChara(chara[status[0][0]], subarray(status[0], 1, 7));
		setRedChara();
		cc.start();
	}


	public void gameExit(int redWin, int blueWin) {
		cc.dispose();
		level = MineUtils.mid(1, level - redWin + blueWin, status.length - 1);
		setRedChara();
		cc.start();
	}
	
	private void setRedChara(){
		cc.setRedChara(chara[status[level][0]], subarray(status[level], 1, 7));
	}
	
	private int[] subarray(int[] src, int pos, int len){
		int[] dst = new int[len];
		System.arraycopy(src, pos, dst, 0, len);
		return dst;
	}
}
