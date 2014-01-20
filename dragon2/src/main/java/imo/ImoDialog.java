// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImoDialog.java

package imo;

import java.awt.Frame;
import javax.swing.JDialog;
import mine.Mine;

// Referenced classes of package imo:
//            MainPanel, ImoListener

public class ImoDialog extends JDialog implements ImoListener {

	public ImoDialog(Frame frame, String s, int i) {
		super(frame, true);
		setTitle("ImoGari");
		MainPanel mainpanel = new MainPanel(this, s, i);
		setContentPane(mainpanel);
	}

	public void show() {
		life = false;
		exp = 0;
		pack();
		Mine.setCenter(this);
		super.show();
	}

	public int getEXP() {
		if (life)
			return exp;
		else
			return 0;
	}

	public void gameExit(int i) {
		life = true;
		exp = i;
		dispose();
	}

	private boolean life;
	private int exp;
}
