// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaintListener.java

package imo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

interface PaintListener {

	public abstract void keyPressed(KeyEvent keyevent);

	public abstract void keyReleased(KeyEvent keyevent);

	public abstract void paint(Graphics g);

	public abstract void run();
}
