// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SolidPanel.java

package mine;

import java.awt.Dimension;

// Referenced classes of package mine:
//            JPanelBase

public class SolidPanel extends JPanelBase {

	public SolidPanel(int i, int j) {
		setSize(i, j);
	}

	public void setSize(int i, int j) {
		super.setSize(i, j);
		width = i;
		height = j;
	}

	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}

	public Dimension getMaximumSize() {
		return new Dimension(width, height);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	protected int width;
	protected int height;
}
