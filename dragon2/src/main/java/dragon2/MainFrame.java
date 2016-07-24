package dragon2;





import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JFrame;

import dragon2.common.util.Luck;
import dragon2.panel.VPanel;
import mine.BMenuBar;
import mine.DataStream;
import mine.ImageLoader;
import mine.JFrameBase;

public class MainFrame implements MainWorks {

	public MainFrame() {
		DataStream.setup(this);
		ImageLoader.setup(this);
		Statics.setup();
		Luck.setup(0);
		mb = new BMenuBar();
		vp = new VPanel(this);
	}

	public void setup(JFrameBase jframebase) {
		frame = jframebase;
		fflag = true;
		jframebase.setResizable(false);
		jframebase.setBackground(new Color(0, 0, 150));
		jframebase.setJMenuBar(mb);
		jframebase.setContentPane(vp);
		vp.title();
		jframebase.display();
		vp.requestFocus();
	}

	public void setup(JApplet japplet) {
		applet = japplet;
		fflag = false;
		japplet.setBackground(new Color(0, 0, 150));
		japplet.setJMenuBar(mb);
		japplet.setContentPane(vp);
		vp.title();
		vp.requestFocus();
	}

	public static void main(String args[]) {
		MainFrame mainframe = new MainFrame();
		mainframe.setup(new JFrameBase("RyuTaiji 2"));
	}

	public JFrame getFrame() {
		return frame;
	}

	public boolean isFrame() {
		return fflag;
	}

	public synchronized void setMenu(int i) {
		mb.reset(vp);
		switch (i) {
		case 10: // '\n'
			mb.add("BACK", "back", 66);
			mb.add("S_RANK", "hayasa", 83);
			mb.add("P_RANK", "tuyosa", 80);
			mb.add("HELP", "help", 72);
			break;

		case 9: // '\t'
			mb.add("NONE", "none", 78);
			break;

		case 0: // '\0'
			if (vp.isDivided()) {
				mb.add("L_STAGE", "left", 65);
				mb.add("R_STAGE", "right", 66);
			} else {
				mb.add("STAGE", "stage", 65);
			}
			mb.add("SAVE", "save", 83);
			mb.add("LOAD", "campload", 81);
			mb.addMenu("OPTION    x", 'x');
			mb.addItem("SORT", "sort", 84);
			mb.addItem("WAZA", "waza", 87);
			mb.addItem("WAZA_LIST", "wazalist", 69);
			mb.addItem("REMOVE", "remove", 82);
			mb.addItem("COLLECTION", "collect", 70);
			mb.addItem("IMO_GARI", "imogari", 86);
			mb.addItem("HELP", "help", 72);
			mb.addItem("SCORE", "score", 71);
			break;

		case 11: // '\013'
			mb.add("BACK", "back", 66);
			mb.add("HELP", "help", 72);
			break;

		case 2: // '\002'
			mb.add("TURN END", "turnend", 84);
			mb.add("ESCAPE", "escape", 69);
			mb.add("LOAD", "mapload", 81);
			
			mb.add("HELP", "help", 72);
			break;

		case 3: // '\003'
			mb.add("START", "start", 83);
			mb.add("CAMP", "camp", 65);
			mb.add("HELP", "help", 72);
			break;

		case 4: // '\004'
			mb.add("NONE", "none", 78);
			break;

		case 5: // '\005'
			mb.add("CAMP", "camp", 65);
			mb.add("HELP", "help", 72);
			break;

		case 6: // '\006'
			mb.add("LOAD", "mapload", 81);
			mb.add("HELP", "help", 72);
			break;

		case 7: // '\007'
			mb.add("START", "start", 83);
			mb.add("CANCEL", "cancel", 81);
			break;

		case 8: // '\b'
			mb.add("CANCEL", "cancel", 81);
			break;
		}
		if (isFrame())
			frame.setJMenuBar(mb);
		else
			applet.setJMenuBar(mb);
		mb.repaint();
	}

	VPanel vp;
	volatile BMenuBar mb;
	JFrameBase frame;
	JApplet applet;
	boolean fflag;
	static final int T_CAMP = 0;
	static final int T_PLAYER = 2;
	static final int T_SETMENS = 3;
	static final int T_ENEMY = 4;
	static final int T_CLEAR = 5;
	static final int T_GAMEOVER = 6;
	static final int T_REMOTES = 7;
	static final int T_REMOTEE = 8;
	static final int T_TITLE = 9;
	static final int T_SCORE = 10;
	static final int T_COLLECT = 11;
	static final int T_WAZALIST = 12;
}
