




package mine;

import java.awt.Component;
import java.awt.Dimension;

public class JCanvas extends Component {

	public JCanvas(int i, int j) {
		setSize(i, j);
	}

	public void setSize(int i, int j) {
		super.setSize(i, j);
		size = new Dimension(i, j);
	}

	public Dimension getMinimumSize() {
		return size;
	}

	public Dimension getMaximumSize() {
		return size;
	}

	public Dimension getPreferredSize() {
		return size;
	}

	protected Dimension size;
}
