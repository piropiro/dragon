// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaintListener.java

import java.awt.Point;

interface PaintListener {

	public abstract void setSelectBody(Body body);

	public abstract void setSelectPlace(Point point);

	public abstract boolean isNextPoint(Point point);

	public abstract void leftPressed();

	public abstract void rightPressed();

	public abstract void mouseMoved(Point point);

	public abstract void leftReleased();

	public abstract void rightReleased();
}
