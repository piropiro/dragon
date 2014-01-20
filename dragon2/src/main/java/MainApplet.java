// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainApplet.java

import javax.swing.JApplet;

public class MainApplet extends JApplet {

	public MainApplet() {
	}

	public void init() {
		MainFrame mainframe = new MainFrame();
		mainframe.setup(this);
	}
}
