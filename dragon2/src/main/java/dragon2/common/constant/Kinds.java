package dragon2.common.constant;


public enum Kinds {

	CHARA,
	CLASS, // 1
	WEPON, // 2
	ARMOR, // 3
	ITEM, // 4
	DOLL, // 39
	WAZA, // 52
	;
	
	public Kinds convert(int n) {
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