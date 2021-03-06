// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Collection.java

import java.util.Iterator;
import java.util.Vector;
import mine.DataStream;
import mine.UnitMap;
import mine.io.BeanIO;

class Collection {

	public Collection(UnitWorks unitworks) {
            Items = (Vector) BeanIO.read("data/body/E90.xml");
            Charas = (Vector) BeanIO.read("data/body/E91.xml");
		loadData();
	}

	public int countItem() {
		int i = 0;
		for (int j = 0; j < item.length; j++)
			if (item[j])
				i++;

		return i;
	}

	public int itemMax() {
		return item.length;
	}

	public int countChara() {
		int i = 0;
		for (int j = 0; j < chara.length; j++) {
			for (int k = 0; k < 3; k++)
				if (chara[j][k])
					i++;

		}

		return i;
	}

	public int charaMax() {
		return (chara.length - 2) * 3;
	}

	public int countWaza() {
		int i = 0;
		for (int j = 1; j < waza.length; j++)
			if (waza[j])
				i++;

		return i;
	}

	public int wazaMax() {
		return waza.length - 1;
	}

	public void setList(Vector vector) {
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.type[0] != 52)
				switch (body.img) {
				case 59: // ';'
					setChara(body, 0);
					break;

				case 60: // '<'
					setChara(body, 1);
					break;

				case 58: // ':'
					setChara(body, 2);
					break;

				default:
					setItem(body);
					break;
				}
		}

	}

	public void setWazaList(Vector vector) {
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (Colors.isPlayer(body)) {
				for (int i = 0; i < body.atk.length; i++)
					waza[body.atk[i]] = true;

			} else if (body.type[0] == 52)
				waza[body.level] = true;
		}

	}

	private void setItem(Body body) {
		int i = -1;
		for (Iterator iterator = Items.iterator(); iterator.hasNext();) {
			i++;
			Body body1 = (Body) iterator.next();
			if (body.img == body1.img && body.name.equals(body1.name)) {
				item[i] = true;
				return;
			}
		}

	}

	private void setChara(Body body, int i) {
		int j = getCharaNum(body);
		if (j != -1)
			chara[j][i] = true;
	}

	public void deployCharas(UnitMap unitmap) {
		for (int i = 0; i < chara.length; i++)
			if (chara[i][0] || chara[i][1] || chara[i][2]) {
				Body body = (Body) Charas.elementAt(i);
				unitmap.S(2, 0, body.x, body.y, body.img);
				int j = 0;
				byte byte0 = 0;
				if (chara[i][0])
					j++;
				if (chara[i][1])
					j++;
				if (chara[i][2])
					j++;
				switch (j) {
				case 1: // '\001'
					if (chara[i][0])
						byte0 = 3;
					if (chara[i][1])
						byte0 = 2;
					if (chara[i][2])
						byte0 = 1;
					break;

				case 2: // '\002'
					byte0 = 4;
					break;
				}
				unitmap.S(1, 0, body.x, body.y, byte0);
			}

	}

	public void deployItems(UnitMap unitmap) {
		for (int i = 0; i < item.length; i++)
			if (item[i]) {
				Body body = (Body) Items.elementAt(i);
				unitmap.S(2, 0, body.x, body.y, body.img);
			}

	}

	public void deployWazas(UnitMap unitmap) {
		for (int i = 0; i < waza.length; i++)
			if (waza[i]) {
				AttackData attackdata = Statics.getAttackData(i);
				int j = attackdata.AttackN1 != 0 ? attackdata.AttackN1 + 169
						: 0;
				unitmap.S(2, 0, 2 + i % 16, 2 + i / 16, j);
			}

	}

	public Body searchWaza(int i, int j) {
		int k = (i - 2) + (j - 2) * 16;
		Body body = Statics.getWaza(k);
		body.x = i;
		body.y = j;
		return body;
	}

	public Body searchChara(int i, int j) {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.x == i && body.y == j) {
				body.setMax();
				body.newType(100);
				setMaterial(body);
				return body;
			}
		}

		return null;
	}

	public Body searchItem(int i, int j) {
		for (Iterator iterator = Items.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.x == i && body.y == j) {
				body.setMax();
				body.newType(100);
				return body;
			}
		}

		return null;
	}

	public void setMaterial(Body body) {
		int i = getCharaNum(body);
		if (i != -1) {
			body.setTypeState(53, chara[i][0]);
			body.setTypeState(54, chara[i][1]);
			body.setTypeState(55, chara[i][2]);
		}
	}

	private int getCharaNum(Body body) {
		if (body.isType(51))
			return 0;
		if (body.isType(50))
			return 1;
		int i = -1;
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			i++;
			Body body1 = (Body) iterator.next();
			if (body.name.equals(body1.name))
				return i;
		}

		return -1;
	}

	private Vector initData() {
		Vector vector = new Vector();
		vector.add(new boolean[Items.size()]);
		vector.add(new boolean[Charas.size()][4]);
		vector.add(new boolean[Statics.AttackDatas.size()]);
		return vector;
	}

	public void loadData() {
		Vector vector = (Vector) DataStream.read("item.dat");
		if (vector == null)
			vector = initData();
		item = (boolean[]) vector.elementAt(0);
		chara = (boolean[][]) vector.elementAt(1);
		waza = (boolean[]) vector.elementAt(2);
	}

	public void saveData() {
		Vector vector = new Vector();
		vector.add(item);
		vector.add(chara);
		vector.add(waza);
		DataStream.write("item.dat", vector);
	}

	static Vector Items;
	static Vector Charas;
	private boolean item[];
	private boolean chara[][];
	private boolean waza[];
}
