package dragon3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import card.CardCanvas;
import dragon3.panel.DataPanel;
import dragon3.panel.HPanel;
import dragon3.panel.HelpPanel;
import dragon3.panel.LargePanel;
import dragon3.panel.MessagePanel;
import dragon3.panel.SmallPanel;
import lombok.Getter;
import mine.awt.BMenuBar;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MouseManagerAWT;
import mine.awt.PaintComponentAWT;
import mine.awt.SleepManagerAWT;
import mine.event.PaintComponent;
import mine.paint.MineImageLoader;

public class DragonBuster implements FrameWorks, ActionListener, KeyListener {
	
	private UnitWorks uw;
	private volatile BMenuBar mb;
	private JFrame frame;
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
	
	private MineImageLoader imageLoader;
	private SleepManagerAWT sleepManager;
	private MouseManagerAWT mouseManager;
	
	/*** Constructer *****************************************************/

	public DragonBuster() {
		this.frame = new JFrame("RyuTaiji 3");
		frame.setResizable(false);
		frame.setBackground(new Color(0, 0, 150));
		
		// Menu
		mb = new BMenuBar();
		frame.setJMenuBar(mb);
		
		// MapPanel
		mapPanel = new PaintComponentAWT(640, 480);
		//MapKeyManager mkm = new MapKeyManager(mapPanel);
		//addKeyListener(mkm);
		
		// AnimePanel
		animePanel = new PaintComponentAWT(32, 32);
		
		// HPanel
		hPanel1 = new PaintComponentAWT(HPanel.WIDTH, HPanel.HEIGHT);
		hPanel2 = new PaintComponentAWT(HPanel.WIDTH, HPanel.HEIGHT);
		
		// HelpPanel
		helpPanel = new PaintComponentAWT(HelpPanel.WIDTH, HelpPanel.HEIGHT);
		
		// SmallPanel
		smallPanel = new PaintComponentAWT(SmallPanel.WIDTH, SmallPanel.HEIGHT);
		
		// LargePanel
		largePanel = new PaintComponentAWT(LargePanel.WIDTH, LargePanel.HEIGHT);
		
		// CardPanel
		cardPanel = new PaintComponentAWT(CardCanvas.WIDTH, CardCanvas.HEIGHT);
		
		// DataPanel
		dataPanel1 = new PaintComponentAWT(DataPanel.WIDTH, DataPanel.HEIGHT);
		dataPanel2 = new PaintComponentAWT(DataPanel.WIDTH, DataPanel.HEIGHT);
		
		// MessagePanel
		messagePanel = new PaintComponentAWT(MessagePanel.WIDTH, MessagePanel.HEIGHT);
		
		imageLoader = new ImageLoaderAWT();
		mouseManager = new MouseManagerAWT();
		sleepManager = new SleepManagerAWT();
		
		JLayeredPane parent = new JLayeredPane();
		MineAwtUtils.setSize(parent, 640, 480);
		parent.setBackground(new Color(0, 0, 150));
		parent.addMouseListener(mouseManager);
		parent.addMouseMotionListener(mouseManager);
		parent.addKeyListener(sleepManager);
		parent.addMouseListener(sleepManager);
		parent.addKeyListener(this);

		parent.setLayout(null);
		parent.add((JComponent)mapPanel, new Integer(1));
		parent.add((JComponent)cardPanel, new Integer(2));
		parent.add((JComponent)helpPanel, new Integer(3));
		parent.add((JComponent)dataPanel1, new Integer(4));
		parent.add((JComponent)dataPanel2, new Integer(5));
		parent.add((JComponent)messagePanel, new Integer(6));
		parent.add((JComponent)largePanel, new Integer(7));
		parent.add((JComponent)hPanel2, new Integer(8));
		parent.add((JComponent)hPanel1, new Integer(9));
		parent.add((JComponent)animePanel, new Integer(10));
		parent.add((JComponent)smallPanel, new Integer(11));
		
		// VPanel
		uw = new VPanel(this, imageLoader, mouseManager, sleepManager);
		uw.title();
		
		frame.setContentPane(parent);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		MineAwtUtils.setCenter(frame);
		parent.requestFocus();
	}

	public static void main(String[] args) {
		new DragonBuster();
	}

	/*** Data *************************************/

	public JFrame getFrame() {
		return frame;
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
				if (uw.getSaveManager().isDivided()) {
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.requestFocus();
		if (mouseManager.isAlive())
			return;
		AbstractButton b = (AbstractButton) e.getSource();
		String command = b.getActionCommand();
		uw.executeMenuCommand(command);
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
		uw.executeFKeyCommand(n, e.isShiftDown());
	}
		
}
