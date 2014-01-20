// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnimeData.java

import java.io.Serializable;

public class AnimeData implements Serializable {

	public AnimeData() {
		name = "none";
		sleep = 50;
		anime = new int[1];
	}

	String name;
	int sleep;
	int anime[];
}
