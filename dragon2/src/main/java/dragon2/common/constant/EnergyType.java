package dragon2.common.constant;


public enum EnergyType {

	NONE("消費なし"),
	STR("攻撃消費"),
	DEF("防御消費"),
	MST("魔法消費"),
	MDF("抵抗消費"),
	HIT("命中消費"),
	MIS("回避消費"),
	;
	
	private String text;
	
	EnergyType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static EnergyType convert(int n) {
		switch (n) {
		case 0: return NONE;
		case 1: return STR;
		case 2: return DEF;
		case 3: return MST;
		case 4: return MDF;
		case 5: return HIT;
		case 6: return MIS;

		default:
			throw new IllegalArgumentException("FuelType unmatch: " + n);
		}
	}
}