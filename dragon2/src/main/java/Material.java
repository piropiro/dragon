// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Material.java

class Material {

	Material(UnitWorks unitworks) {
		uw = unitworks;
		count = 0;
		material = null;
	}

	public int getCount() {
		return count;
	}

	public Body getMaterial(AttackBase attackbase) {
		count++;
		if (count % 6 != 0)
			return null;
		Body body = attackbase.bb;
		if (Colors.isPlayer(body))
			return null;
		material = body.copy();
		material.atk[0] = body.atk[3];
		material.type[0] = 4;
		material.setTypeState(53, false);
		material.setTypeState(54, false);
		material.setTypeState(55, false);
		for (int i = 1; i < material.atk.length; i++)
			material.atk[i] = 0;

		material.hpMax = Math.max(1, body.hpMax / 6);
		material.strMax = 0;
		material.defMax = 0;
		material.mstMax = 0;
		material.mdfMax = 0;
		material.hitMax = 0;
		material.misMax = 0;
		switch (Statics.getBukiType(attackbase.getBuki())) {
		case 1: // '\001'
			material.img = 59;
			material.strMax = body.strMax / 6;
			break;

		case 2: // '\002'
			material.img = 60;
			material.hitMax = body.hitMax / 6;
			break;

		case 3: // '\003'
			material.img = 58;
			material.mstMax = body.mstMax / 6;
			break;
		}
		switch (uw.getTurn() % 3) {
		case 0: // '\0'
			material.defMax = body.defMax / 6;
			break;

		case 1: // '\001'
			material.mdfMax = body.mdfMax / 6;
			break;

		case 2: // '\002'
			material.misMax = body.misMax / 6;
			break;
		}
		if (uw.have(material))
			material = null;
		return material;
	}

	public void message() {
		if (material == null)
			return;
		uw.setMPanel(material.name + Texts.ha);
		uw.setMPanel(Texts.material1);
		switch (material.img) {
		case 58: // ':'
			uw.setMPanel(Texts.material2);
			break;

		case 59: // ';'
			uw.setMPanel(Texts.material3);
			break;

		case 60: // '<'
			uw.setMPanel(Texts.material4);
			break;
		}
		uw.setMPanel(Texts.material5);
		uw.startMPanel(material);
		material = null;
	}

	private UnitWorks uw;
	private int count;
	private Body material;
	static final int RED = 59;
	static final int BLUE = 58;
	static final int GREEN = 60;
}
