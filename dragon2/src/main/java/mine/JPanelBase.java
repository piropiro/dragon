




package mine;

import java.awt.*;
import javax.swing.JPanel;

public class JPanelBase extends JPanel {

	public JPanelBase() {
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		setFill(1);
		setLayout(layout);
	}

	public void add(Component component, int i, int j, int k, int l) {
		add(component, i, j, k, l, 0, 0);
	}

	public void add(Component component, int i, int j, int k, int l, int i1,
			int j1) {
		c.gridx = i;
		c.gridy = j;
		c.gridwidth = k;
		c.gridheight = l;
		c.weightx = i1;
		c.weighty = j1;
		layout.setConstraints(component, c);
		add(component);
	}

	public void setFill(int i) {
		c.fill = i;
	}

	public static final int NONE = 0;
	public static final int BOTH = 1;
	public static final int HORIZONTAL = 2;
	public static final int VERTICAL = 3;
	GridBagLayout layout;
	GridBagConstraints c;
}
