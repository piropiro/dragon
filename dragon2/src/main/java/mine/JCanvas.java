// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCanvas.java

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
