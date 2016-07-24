/*
 * 作成日: 2004/03/06
 */
package dragon3.bean;

import java.util.ArrayList;
import java.util.List;

import dragon2.common.constant.BodyAttribute;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.MoveType;
import dragon3.common.constant.ArmorType;
import dragon3.common.constant.SoulType;
import dragon3.common.constant.WeponType;


/**
 * @author k-saito
 */
@lombok.Data
public class BodyData implements Data {

	private String id = "none";
	private String name = "none";
	private String image = "none.png";
	private BodyKind kind = BodyKind.CHARA;

	private int hp;
	private int str;
	private int def;
	private int mst;
	private int mdf;
	private int hit;
	private int mis;
	private int moveStep;
	private MoveType moveType = MoveType.NONE;
	private SoulType soulType = SoulType.NONE;

	private WeponType weponType = WeponType.NONE;
	private ArmorType armorType = ArmorType.NONE;

	private List<String> wazaList = new ArrayList<>();
	private List<BodyAttribute> attrList = new ArrayList<>();

	@Override
	public String toString(){
		return id + " - " + name;
	}

}
