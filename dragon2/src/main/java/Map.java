// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Map.java

import java.awt.Dimension;
import java.awt.Point;

interface Map {

	public abstract void repaint();

	public abstract void ppaint(int i, int j);

	public abstract void ppaint(int ai[]);

	public abstract Point getWaku();

	public abstract Dimension getMapSize();

	public abstract void wakuMove(Point point);

	public abstract void wakuPaint(boolean flag);

	public abstract void setWalkPaint(Body body);

	public abstract void setEndPaint(Body body);

	public abstract void setFightManager(Body body);

	public abstract void setWaitPaint();

	public abstract void setBasicPaint();

	public abstract void setKakuseiPaint(Body body);

	public abstract void setBerserkPaint(Body body);

	public abstract void setChangePaint(Body body, Body body1);

	public abstract void setButtonPaint(Point point,
			PaintListener paintlistener, int i);

	public abstract void setPaintListener(PaintListener paintlistener);
}
