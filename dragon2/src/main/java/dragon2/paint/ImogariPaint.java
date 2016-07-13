package dragon2.paint;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImogariPaint.java

import imo.ImoDialog;
import javax.swing.JFrame;

import dragon2.ActionBase;
import dragon2.common.Body;
import dragon2.common.constant.Texts;
import dragon2.common.constant.Types;
import dragon2.common.util.Equip;

public class ImogariPaint extends ActionBase {

	public ImogariPaint(JFrame jframe, Equip equip1) {
		frame = jframe;
		equip = equip1;
		setHelp();
		action();
	}

	private void setHelp() {
		PaintBase.uw.setHelp(Texts.help[14], 1);
	}

	public void actionMain() {
		Body body = null;
		for (int i = 1; i <= 8; i += 7) {
			for (int j = 1; j <= 10; j += 3) {
				Body body1 = equip.search(i, j);
				if (body1 == null)
					continue;
				body1.newType();
				if (!body1.isType(Types.HERO))
					continue;
				body = body1;
				break;
			}

		}

		if (body != null) {
			ImoDialog imodialog = new ImoDialog(frame, body.name, body.moveturn);
			imodialog.show();
			int k = imodialog.getEXP();
			if (k != 0) {
				k += 10;
				body.moveturn++;
				PaintBase.uw.setMPanel(Texts.imogari1);
				PaintBase.uw.setMPanel(Texts.expwo + k + Texts.teniireta);
				PaintBase.uw.startMPanel(body);
				for (int l = 1; l <= 8; l += 7) {
					for (int i1 = 1; i1 <= 13; i1 += 3) {
						Body body2 = equip.search(l, i1);
						if (body2 != null) {
							body2.exp += k;
							equip.levelup(body2);
						}
					}

				}

			} else {
				PaintBase.uw.setMPanel(Texts.imogari2);
				PaintBase.uw.startMPanel(body);
			}
		}
		PaintBase.uw.backFromImogari();
	}

	public void leftPressed() {
	}

	public void rightPressed() {
	}

	Equip equip;
	JFrame frame;
}
