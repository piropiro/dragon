// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttackData.java

import java.io.Serializable;

public class AttackData implements Serializable {

	public AttackData() {
		name = "none";
		sname = "none";
		effect = new int[5];
	}

	String name;
	String sname;
	int color;
	int effect[];
	int AttackType;
	int AttackN1;
	int TRType;
	int TargetType;
	int TargetN1;
	int TargetN2;
	int RangeType;
	int RangeN1;
	int RangeN2;
	int AnimeType;
	int AnimeN1;
	int FuelType;
	int FuelN1;
}
