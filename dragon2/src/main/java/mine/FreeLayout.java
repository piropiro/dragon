




package mine;

import java.awt.*;
import java.util.Vector;

public class FreeLayout implements LayoutManager {

	public FreeLayout(int i, int j) {
		width = i;
		height = j;
		Components = new Vector();
	}

	public void addLayoutComponent(String s, Component component) {
		Components.add(component);
	}

	public void removeLayoutComponent(Component component) {
		Components.remove(component);
	}

	public Dimension preferredLayoutSize(Container container) {
		return new Dimension(width, height);
	}

	public Dimension minimumLayoutSize(Container container) {
		return new Dimension(width, height);
	}

	public void layoutContainer(Container container) {
	}

	private Vector Components;
	private int width;
	private int height;
}
