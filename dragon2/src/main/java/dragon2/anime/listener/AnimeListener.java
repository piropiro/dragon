package dragon2.anime.listener;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnimeListener.java

import java.awt.Component;
import java.awt.Graphics;

public interface AnimeListener {

	public abstract void paint(Graphics g);

	public abstract void animation(Component component);
}
