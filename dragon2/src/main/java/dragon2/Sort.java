package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sort.java

import java.util.Vector;

import dragon2.attack.AttackData;
import dragon2.common.Body;

public class Sort {

	public Sort() {
	}

	public static Vector sort(Vector vector) {
		if (vector.size() <= 1)
			return vector;
		Vector vector1 = new Vector();
		Body body;
		for (; vector.size() > 1; vector.remove(body)) {
			body = (Body) vector.firstElement();
			int i = getScore(body);
			for (int j = 1; j < vector.size(); j++) {
				Body body1 = (Body) vector.elementAt(j);
				int k = getScore(body1);
				if (i < k) {
					i = k;
					body = body1;
				}
			}

			vector1.add(body);
		}

		vector1.add(vector.firstElement());
		return vector1;
	}

	private static int getScore(Body body) {
		int i = 0;
		switch (body.type[0]) {
		case 52: // '4'
			return -body.level - body.img * 100;

		case 2: // '\002'
			i = 0x2faf080;
			AttackData attackdata = Statics.getAttackData(body.atk[0]);
			i -= attackdata.AttackN1 * 0xf4240;
			break;

		case 3: // '\003'
			i = 0x2625a00;
			break;

		case 4: // '\004'
			i = 0x1c9c380;
			switch (body.img) {
			case 59: // ';'
				i -= 0xf4240;
				break;

			case 58: // ':'
				i -= 0x1e8480;
				break;

			case 60: // '<'
				i -= 0x2dc6c0;
				break;
			}
			break;

		case 1: // '\001'
			i = 0x3938700;
			AttackData attackdata1 = Statics.getAttackData(body.atk[0]);
			i -= attackdata1.AttackN1 * 0xf4240;
			break;

		case 39: // '\''
			i = 0x42c1d80;
			break;

		default:
			i = 0x4c4b400;
			break;
		}
		i += body.level * 10000;
		i += body.strMax;
		i += body.defMax;
		i += body.mstMax;
		i += body.mdfMax;
		i += body.hitMax;
		i += body.misMax;
		return i;
	}
}
