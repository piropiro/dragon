package dragon2;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import dragon2.attack.AttackData;
import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.BodyAttribute;
import mine.DataStream;
import mine.UnitMap;
import mine.io.JsonIO;

public class Collection {

	public Collection(UnitWorks unitworks) {
//            Items = (Vector) BeanIO.read("data/body/E90.xml");
//            Charas = (Vector) BeanIO.read("data/body/E91.xml");
            Items = Arrays.asList(JsonIO.read("data/body/E90.json", Body[].class));
            Charas = Arrays.asList(JsonIO.read("data/body/E91.json", Body[].class));
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

	public void setList(List<Body> vector) {
		for (Body body : vector) {
		
			if (body.kind != BodyKind.WAZA) {
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

	}

	public void setWazaList(List<Body> vector) {
		for (Body body : vector) {
			if (GameColor.isPlayer(body)) {
				for (int i = 0; i < body.atk.length; i++)
					waza[body.atk[i]] = true;

			} else if (body.kind == BodyKind.WAZA)
				waza[body.level] = true;
		}

	}

	private void setItem(Body body) {
		int i = -1;
		for (Body body1 : Items) {
		
			i++;
			
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
				Body body = Charas.get(i);
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
				Body body = Items.get(i);
				unitmap.S(2, 0, body.x, body.y, body.img);
			}

	}

	public void deployWazas(UnitMap unitmap) {
		for (int i = 0; i < waza.length; i++)
			if (waza[i]) {
				AttackData attackdata = Statics.getAttackData(i);
				int j = attackdata.attackN1 != 0 ? attackdata.attackN1 + 169
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
		for (Body body : Charas) {
			if (body.x == i && body.y == j) {
				body.setMax();
				body.newType();
				setMaterial(body);
				return body;
			}
		}

		return null;
	}

	public Body searchItem(int i, int j) {
		for (Body body : Items) {
			if (body.x == i && body.y == j) {
				body.setMax();
				body.newType();
				return body;
			}
		}

		return null;
	}

	public void setMaterial(Body body) {
		int i = getCharaNum(body);
		if (i != -1) {
			body.setTypeState(BodyAttribute.M_RED, chara[i][0]);
			body.setTypeState(BodyAttribute.M_GREEN, chara[i][1]);
			body.setTypeState(BodyAttribute.M_BLUE, chara[i][2]);
		}
	}

	private int getCharaNum(Body body) {
		if (body.isType(BodyAttribute.HERO))
			return 0;
		if (body.isType(BodyAttribute.SISTER))
			return 1;
		int i = -1;
		for (Body body1 : Charas) {
			i++;
			
			if (body.name.equals(body1.name))
				return i;
		}

		return -1;
	}

	private Vector initData() {
		Vector vector = new Vector();
		vector.add(new boolean[Items.size()]);
		vector.add(new boolean[Charas.size()][4]);
		vector.add(new boolean[Statics.getAttackDataSize()]);
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

	private static List<Body> Items;
	private static List<Body> Charas;
	private boolean item[];
	private boolean chara[][];
	private boolean waza[];
}
