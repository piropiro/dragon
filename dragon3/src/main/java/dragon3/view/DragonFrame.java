package dragon3.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import card.CardCanvas;
import dragon3.controller.CommandListener;
import dragon3.panel.DataPanel;
import dragon3.panel.HPanel;
import dragon3.panel.HelpPanel;
import dragon3.panel.LargePanel;
import dragon3.panel.MessagePanel;
import dragon3.panel.SmallPanel;
import lombok.Getter;
import lombok.Setter;
import mine.awt.BMenuBar;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MineCanvasAWT;
import mine.awt.SleepManagerAWT;
import mine.event.MouseAllListener;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;

public class DragonFrame implements FrameWorks, ActionListener, KeyListener {
	
	private volatile BMenuBar mb;
	private JFrame frame;
	private MineCanvasAWT mc;
	
	@Setter private CommandListener commandListener;

	@Getter private PaintComponent mapPanel;
	@Getter private PaintComponent animePanel;
	@Getter private PaintComponent hPanel1;
	@Getter private PaintComponent hPanel2;
	@Getter private PaintComponent helpPanel;
	@Getter private PaintComponent smallPanel;
	@Getter private PaintComponent largePanel;
	@Getter private PaintComponent cardPanel;
	@Getter private PaintComponent dataPanel1;
	@Getter private PaintComponent dataPanel2;
	@Getter private PaintComponent messagePanel;
	@Getter private PaintComponent stageSelectPanel;
	
	@Getter private MineImageLoader imageLoader;
	@Getter private SleepManager sleepManager;
	
	/*** Constructer *****************************************************/

	public DragonFrame() {
		this.frame = new JFrame("RyuTaiji 3");
		frame.setResizable(false);
		frame.setBackground(new Color(0, 0, 150));
		
		// Menu
		mb = new BMenuBar();
		mb.add("NONE", "none", KeyEvent.VK_N);
		frame.setJMenuBar(mb);
		
		imageLoader = new ImageLoaderAWT();
		
		mc = new MineCanvasAWT(imageLoader);

		mapPanel = mc.newLayer(0, 0, 640, 480);
		stageSelectPanel = mc.newLayer(0, 0, 640, 480);
		animePanel = mc.newLayer(0, 0, 640, 480);

		hPanel1 = mc.newLayer(0, 0, HPanel.WIDTH, HPanel.HEIGHT);
		hPanel2 = mc.newLayer(0, 0, HPanel.WIDTH, HPanel.HEIGHT);
		helpPanel = mc.newLayer(0, 0, HelpPanel.WIDTH, HelpPanel.HEIGHT);
		smallPanel = mc.newLayer(0, 0, SmallPanel.WIDTH, SmallPanel.HEIGHT);
		largePanel = mc.newLayer(0, 0, LargePanel.WIDTH, LargePanel.HEIGHT);
		cardPanel = mc.newLayer(0, 0, CardCanvas.WIDTH, CardCanvas.HEIGHT);
		dataPanel1 = mc.newLayer(0, 0, DataPanel.WIDTH, DataPanel.HEIGHT);
		dataPanel2 = mc.newLayer(0, 0, DataPanel.WIDTH, DataPanel.HEIGHT);
		messagePanel = mc.newLayer(0, 0, MessagePanel.WIDTH, MessagePanel.HEIGHT);

		mapPanel.setVisible(true);
		
		MineAwtUtils.setSize(mc, 640, 480);	
		mc.setBackground(new Color(0, 0, 150));
		mc.addKeyListener(this);
		
		frame.getContentPane().add(mc);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sleepManager = new SleepManagerAWT(mc);
	}
	
	public void launch() {
		frame.setVisible(true);
		MineAwtUtils.setCenter(frame);
		frame.requestFocus();
	}

	/*** MenuBar ****************************************/

	public synchronized void setMenu(int type) {
		mb.reset(this);
		switch (type) {
			case T_SCORE :
				mb.add("BACK", "back", KeyEvent.VK_B);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_TITLE :
				mb.add("NONE", "none", KeyEvent.VK_N);
				break;
			case T_CAMP :
				mb.add("STAGE", "stage", KeyEvent.VK_A);
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
			case T_STAGESELECT:
				mb.add("BACK", "camp", KeyEvent.VK_B);
				mb.add("HELP", "help", KeyEvent.VK_H);
				break;
			case T_SETMENS :
				mb.add("BACK", "stage", KeyEvent.VK_B);
				mb.add("START", "start", KeyEvent.VK_S);
				mb.add("HELP", "help", KeyEvent.VK_H);
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.requestFocus();
		if (mc.isRunning())
			return;
		AbstractButton b = (AbstractButton) e.getSource();
		String command = b.getActionCommand();
		
		new Thread(() -> commandListener.executeMenuCommand(command)).start();
	}
	
	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		int n = 0;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_F1 :
				n = 1;
				break;
			case KeyEvent.VK_F2 :
				n = 2;
				break;
			case KeyEvent.VK_F3 :
				n = 3;
				break;
			case KeyEvent.VK_F4 :
				n = 4;
				break;
			case KeyEvent.VK_F5 :
				n = 5;
				break;
			case KeyEvent.VK_F6 :
				n = 6;
				break;
			case KeyEvent.VK_F7 :
				n = 7;
				break;
			case KeyEvent.VK_F8 :
				n = 8;
				break;
			default :
				return;
		}
		commandListener.executeFKeyCommand(n, e.isShiftDown());
	}

	@Override
	public void setMouseListener(MouseAllListener mal) {
		mc.setMouseAllListener(mal);
	}
		
}
