package dragon3;

import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Texts;
import dragon3.common.constant.Types;

public class Tutorial {
	public static void setHelp(Body ba, Body bb, int n, UnitWorks uw) {
		String[] line;
		line = Texts.tuto[0];

		GameColors color = GameColors.BLUE;
		if (bb != null) {
			if (ba.isType(Types.HERO)) {
				if (n == 0) {
					if (bb.getName().equals(Texts.goburin)) {
						line = Texts.tuto[1];
						color = GameColors.RED;
					} else if (bb.getName().equals(Texts.pikusi)) {
						line = Texts.tuto[2];
					} else if (bb.getName().equals(Texts.gaikotu)) {
						if (bb.getHp() == bb.getHpMax()) {
							line = Texts.tuto[3];
							color = GameColors.GREEN;
						} else {
							line = Texts.tuto[4];
						}
					}
				} else {
					if (bb.getName().equals(Texts.goburin)) {
						line = Texts.tuto[5];
						color = GameColors.RED;
					} else if (bb.getName().equals(Texts.pikusi)) {
						line = Texts.tuto[6];
						color = GameColors.RED;
					} else if (bb.getName().equals(Texts.gaikotu)) {
						if (bb.getHpMax() == bb.getHpMax()) {
							line = Texts.tuto[7];
						} else {
							line = Texts.tuto[8];
							color = GameColors.RED;
						}
					}
				}
			} else if (ba.isType(Types.SISTER)) {
				if (n == 0) {
					if (bb.getName().equals(Texts.goburin)) {
						line = Texts.tuto[9];
					} else if (bb.getName().equals(Texts.pikusi)) {
						line = Texts.tuto[10];
						color = GameColors.RED;
					} else if (bb.getName().equals(Texts.gaikotu)) {
						if (bb.getHpMax() == bb.getHpMax()) {
							line = Texts.tuto[11];
						} else {
							line = Texts.tuto[12];
							color = GameColors.RED;
						}
					}
				} else {
					if (bb.getName().equals(Texts.goburin)) {
						line = Texts.tuto[13];

					} else if (bb.getName().equals(Texts.pikusi)) {
						line = Texts.tuto[14];
						color = GameColors.RED;
					} else if (bb.getName().equals(Texts.gaikotu)) {
						if (bb.getHp() == bb.getHpMax()) {
							line = Texts.tuto[15];
							color = GameColors.GREEN;
						} else {
							line = Texts.tuto[16];
						}
					}
				}
			}
		}
		uw.getPanelManager().displayHelp(uw.getMapWorks().getWaku(), line, color);
	}
}
