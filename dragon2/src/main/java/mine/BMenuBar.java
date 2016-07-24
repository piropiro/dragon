




package mine;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BMenuBar extends JMenuBar {

	public BMenuBar() {
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setAlignment(0);
		flowlayout.setVgap(2);
		setLayout(flowlayout);
	}

	public void reset(ActionListener actionlistener) {
		al = actionlistener;
		removeAll();
	}

	public void add(String s, String s1, int i) {
		JMenuItem jmenuitem = new JMenuItem(s);
		jmenuitem.setActionCommand(s1);
		jmenuitem.addActionListener(al);
		jmenuitem.setAccelerator(KeyStroke.getKeyStroke(i, 0));
		add(((java.awt.Component) (jmenuitem)));
	}

	public void add(String s, int i) {
		add(s, s, i);
	}

	public void addMenu(String s, char c) {
		menu = new JMenu(s);
		menu.setMnemonic(c);
		add(menu);
	}

	public void addItem(String s, String s1, int i) {
		JMenuItem jmenuitem = new JMenuItem(s);
		jmenuitem.setActionCommand(s1);
		jmenuitem.addActionListener(al);
		jmenuitem.setAccelerator(KeyStroke.getKeyStroke(i, 0));
		menu.add(jmenuitem);
	}

	public void addItem(String s, int i) {
		addItem(s, s, i);
	}

	private ActionListener al;
	private JMenu menu;
}
