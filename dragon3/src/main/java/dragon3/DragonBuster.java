package dragon3;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import dragon3.common.util.Luck;
import mine.awt.BMenuBar;

public class DragonBuster implements FrameWorks {
	
	private VPanel vp;
	private volatile BMenuBar mb;
	private JFrame frame;

	/*** Constructer *****************************************************/

	public DragonBuster() {
		Luck.setup(Luck.FairLuck);
		mb = new BMenuBar();
		vp = new VPanel(this);
	}

	public void setup(JFrame frame) {
		this.frame = frame;
		frame.setResizable(false);
		frame.setBackground(new Color(0, 0, 150));
		frame.setJMenuBar(mb);
		frame.setContentPane(vp);
		vp.title();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//MineAwtUtils.setCenter(frame);
		frame.setVisible(true);
		vp.requestFocus();
	}

	public static void main(String[] args) {
		DragonBuster mf = new DragonBuster();
		mf.setup(new JFrame("RyuTaiji 3"));
	}

	/*** Data *************************************/

	public JFrame getFrame() {
		return frame;
	}

	/*** MenuBar ****************************************/

	public synchronized void setMenu(int type) {
		mb.reset(vp);
		switch (type) {
			case T_SCORE :
				mb.add("BACK", "back", KeyEvent.VK_B);
				mb.add("S_RANK", "hayasa", KeyEvent.VK_S);
				mb.add("P_RANK", "tuyosa", KeyEvent.VK_P);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_TITLE :
				mb.add("NONE", "none", KeyEvent.VK_N);
				break;
			case T_CAMP :
				if (vp.getSaveManager().isDivided()) {
					mb.add("L_STAGE", "left", KeyEvent.VK_A);
					mb.add("R_STAGE", "right", KeyEvent.VK_B);
				} else {
					mb.add("STAGE", "stage", KeyEvent.VK_A);
				}
				mb.add("SAVE", "save", KeyEvent.VK_S);
				mb.add("LOAD", "campload", KeyEvent.VK_Q);
				mb.addMenu("OPTION    x", 'x');
				mb.addItem("SORT", "sort", KeyEvent.VK_T);
				mb.addItem("WAZA", "waza", KeyEvent.VK_W);
				mb.addItem("WAZA_LIST", "wazalist", KeyEvent.VK_E);
				mb.addItem("REMOVE", "remove", KeyEvent.VK_R);
				mb.addItem("COLLECTION", "collect", KeyEvent.VK_F);
				mb.addItem("IMO_GARI", "imogari", KeyEvent.VK_V);
				mb.addItem("HELP", "help", KeyEvent.VK_H);
				mb.addItem("SCORE", "score", KeyEvent.VK_G);
				break;
			case T_COLLECT :
				mb.add("BACK", "back", KeyEvent.VK_B);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_PLAYER :
				mb.add("TURN END", "turnend", KeyEvent.VK_T);
				mb.add("ESCAPE", "escape", KeyEvent.VK_E);
				mb.add("LOAD", "mapload", KeyEvent.VK_Q);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_SETMENS :
				mb.add("START", "start", KeyEvent.VK_S);
				mb.add("CAMP", "camp", KeyEvent.VK_A);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_ENEMY :
				mb.add("NONE", "none", KeyEvent.VK_N);
				break;
			case T_CLEAR :
				mb.add("CAMP", "camp", KeyEvent.VK_A);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_GAMEOVER :
				mb.add("LOAD", "mapload", KeyEvent.VK_Q);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
		}
		frame.setJMenuBar(mb);
		mb.repaint();
	}
}
