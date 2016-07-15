package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemList.java

import java.util.Vector;

import dragon2.Sort;
import dragon2.Statics;
import dragon2.common.Body;
import mine.DataStream;
import mine.io.BeanIO;

public class ItemList {

	public ItemList() {
	}

	public static void main(String args[]) {
		Statics.setup();
		Vector<Body> vector = new Vector<>();
		Vector<Body> vector1 = new Vector<>();
		for (int i = 0; i < 30; i++) {
                    Vector<Body> vector2 = (Vector<Body>) BeanIO.read("data/body/E" + i + ".xml");
			if (vector2 != null) {
				for (int j = 0; j < vector2.size(); j++) {
					Body body = (Body) vector2.elementAt(j);
					switch (body.kind) {
					case CLASS: // '\001'
					case WEPON: // '\002'
					case ARMOR: // '\003'
					case ITEM: // '\004'
					case DOLL: // '\''
						add(vector, body);
						break;

					default:
						add(vector1, body);
						break;
					}
				}

			}
		}

		Vector vector3 = Sort.sort(vector);
		for (int k = 0; k < vector3.size(); k++) {
			Body body1 = (Body) vector3.elementAt(k);
			body1.x = k % 20;
			body1.y = k / 20;
		}

		for (int l = 0; l < vector1.size(); l++) {
			Body body2 = (Body) vector1.elementAt(l);
			body2.x = l % 20;
			body2.y = l / 20 + 8;
		}

		DataStream.write("data/E90.txt", vector3);
		DataStream.write("data/E91.txt", vector1);
	}

	private static void add(Vector vector, Body body) {
		for (int i = 0; i < vector.size(); i++) {
			Body body1 = (Body) vector.elementAt(i);
			if (body.img == body1.img && body1.name.equals(body.name))
				return;
		}

		vector.add(body);
	}
}
