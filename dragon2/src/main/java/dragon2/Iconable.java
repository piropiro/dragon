package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Iconable.java

import dragon2.common.Body;

public interface Iconable {

	public abstract String getSubName();

	public abstract String getName();

	public abstract int getColor();

	public abstract Body getBody(boolean flag);

	public abstract int getStore();

	public abstract int getDamage();

	public abstract int getMeichu();

	public abstract int getRate();

	public abstract boolean isHit();

	public abstract boolean isPossible(int i);

	public abstract boolean isEffect(int i);
}
