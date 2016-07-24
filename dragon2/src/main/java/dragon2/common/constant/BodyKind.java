package dragon2.common.constant;


public enum BodyKind {

	CHARA("キャラ"),
	CLASS("職業"), // 1
	WEPON("武器"), // 2
	ARMOR("防具"), // 3
	ITEM("小物"), // 4
	DOLL("ドール"), // 39
	WAZA("技"), // 52
	;
	
	private String text;
	
	BodyKind(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public BodyKind convert(int n) {
		switch (n) {
		case 0: return CHARA;
		case 1: return CLASS;
		case 2: return WEPON;
		case 3: return ARMOR;
		case 4: return ITEM;
		case 39: return DOLL;
		case 52: return WAZA;

		default:
			throw new IllegalArgumentException("Kinds unmatch: " + n);
		}
	}
}