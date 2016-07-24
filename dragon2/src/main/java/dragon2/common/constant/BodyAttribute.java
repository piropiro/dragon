package dragon2.common.constant;


public enum BodyAttribute {

	//CLASS("職業"), // 1
	//WEPON("武器"), // 2
	//ARMOR("防具"), // 3
	//ITEM("小物"), // 4
	DRAGON("竜族"), // 5
	FIRE_200("火炎倍打"), // 6
	FIRE_50("火炎半減"), // 7
	FIRE_0("火炎無効"), // 8
	ICE_200("氷結倍打"), // 9
	ICE_50("氷結半減"), // 10
	ICE_0("氷結無効"), // 11
	THUNDER_200("電撃倍打"), // 12
	THUNDER_50("電撃半減"), // 13
	THUNDER_0("電撃無効"), // 14
	TALKABLE("会話可能"), // 15
	ASK("説得可能"), // 16
	ANTI_ALL("全部無効"), // 17
	ANTI_CRITICAL("必殺無効"), // 18
	ANTI_DEATH("デス無効"), // 19
	NKILL("即死無効"), // 20
	SLEEP("睡眠"), // 21
	ANTI_SLEEP("睡眠無効"), // 22
	POISON("毒"), // 23s
	ANTI_POISON("毒無効"), // 24
	CLOSE("封印"), // 25
	OIL("油まみれ"), // 26
	CHARM("魅了"), // 27
	ANTI_CHARM("魅了無効"), // 28
	HEAL("自然治癒"), // 29
	ATTACK_UP("攻撃態勢"), // 30
	GUARD_UP("防御態勢"), // 31
	S_WAIT("ウエイト"), // 32
	POWERUP("強化"), // 33
	UNDEAD("死霊"), // 34
	S_LOCK("ロック"), // 35
	SORA("飛行"), // 36
	RIKU("歩行"), // 37
	SUMMON("召喚"), // 38
	//DOLL("ドール"), // 39
	DRAGON_KILLER("竜退治"), // 40
	UNDEAD_KILLER("死霊退治"), // 41
	MASTER("魔物使い"), // 42
	SWORD_50("剣斧半減"), // 43
	BADITEM("劣化品"), // 44
	MAGIC_50("魔法半減"), // 45
	FLY_ABLE("飛行可能"), // 46
	SWIM_ABLE("水中可能"), // 47
	MOVE_UP_1("移動＋１"), // 48
	MOVE_UP_2("移動＋２"), // 49
	SISTER("妹"), // 50
	HERO("主人公"), // 51
	//WAZA("技"), // 52
	M_RED("赤玉"), // 53
	M_GREEN("緑玉"), // 54
	M_BLUE("青玉"), // 55
	MOVE_DOWN_1("移動－１"), // 56
	LITE_WALK("軽歩可能"), // 57
	BERSERK("暴走"), // 58

	REGENE("自然治癒"), // doragon3
	;
	
	private String text;
	
	BodyAttribute(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	public static BodyAttribute convert(int n) {
		switch (n) {
		//case 1: return CLASS;
		//case 2: return WEPON;
		//case 3: return ARMOR;
		//case 4: return ITEM;
		case 5: return DRAGON;
		case 6: return FIRE_200;
		case 7: return FIRE_50;
		case 8: return FIRE_0;
		case 9: return ICE_200;
		case 10: return ICE_50;
		case 11: return ICE_0;
		case 12: return THUNDER_200;
		case 13: return THUNDER_50;
		case 14: return THUNDER_0;
		case 15: return TALKABLE;
		case 16: return ASK;
		case 17: return ANTI_ALL;
		case 18: return ANTI_CRITICAL;
		case 19: return ANTI_DEATH;
		case 20: return NKILL;
		case 21: return SLEEP;
		case 22: return ANTI_SLEEP;
		case 23: return POISON;
		case 24: return ANTI_POISON;
		case 25: return CLOSE;
		case 26: return OIL;
		case 27: return CHARM;
		case 28: return ANTI_CHARM;
		case 29: return HEAL;
		case 30: return ATTACK_UP;
		case 31: return GUARD_UP;
		case 32: return S_WAIT;
		case 33: return POWERUP;
		case 34: return UNDEAD;
		case 35: return S_LOCK;
		case 36: return SORA;
		case 37: return RIKU;
		case 38: return SUMMON;
		//case 39: return DOLL;
		case 40: return DRAGON_KILLER;
		case 41: return UNDEAD_KILLER;
		case 42: return MASTER;
		case 43: return SWORD_50;
		case 44: return BADITEM;
		case 45: return MAGIC_50;
		case 46: return FLY_ABLE;
		case 47: return SWIM_ABLE;
		case 48: return MOVE_UP_1;
		case 49: return MOVE_UP_2;
		case 50: return SISTER;
		case 51: return HERO;
		//case 52: return WAZA;
		case 53: return M_RED;
		case 54: return M_GREEN;
		case 55: return M_BLUE;
		case 56: return MOVE_DOWN_1;
		case 57: return LITE_WALK;
		case 58: return BERSERK;

		default:
			throw new IllegalArgumentException("Types unmatch: " + n);
		}
	}
//public static final int CLASS = 1;
//public static final int WEPON = 2;
//public static final int ARMOR = 3;
//public static final int ITEM = 4;
//public static final int DRAGON = 5;
//public static final int FIRE2 = 6;
//public static final int FIREH = 7;
//public static final int FIRE0 = 8;
//public static final int ICE2 = 9;
//public static final int ICEH = 10;
//public static final int ICE0 = 11;
//public static final int THU2 = 12;
//public static final int THUH = 13;
//public static final int THU0 = 14;
//public static final int TALK = 15;
//public static final int ASK = 16;
//public static final int NSTATUS = 17;
//public static final int NFINISH = 18;
//public static final int NDEATH = 19;
//public static final int NKILL = 20;
//public static final int SLEEP = 21;
//public static final int NSLEEP = 22;
//public static final int POISON = 23;
//public static final int NPOISON = 24;
//public static final int CLOSE = 25;
//public static final int OIL = 26;
//public static final int CHARM = 27;
//public static final int NCHARM = 28;
//public static final int HEAL = 29;
//public static final int OFFENCE = 30;
//public static final int DEFENCE = 31;
//public static final int S_WAIT = 32;
//public static final int POWERUP = 33;
//public static final int UNDEAD = 34;
//public static final int S_LOCK = 35;
//public static final int FLY = 36;
//public static final int WALK = 37;
//public static final int SUMMON = 38;
//public static final int DOLL = 39;
//public static final int DRAGONK = 40;
//public static final int UNDEADK = 41;
//public static final int MASTER = 42;
//public static final int SWORDH = 43;
//public static final int BADITEM = 44;
//public static final int MAGICH = 45;
//public static final int FLYABLE = 46;
//public static final int SWIMABL = 47;
//public static final int DASH = 48;
//public static final int DASH2 = 49;
//public static final int SISTER = 50;
//public static final int HERO = 51;
//public static final int WAZA = 52;
//public static final int M_RED = 53;
//public static final int M_GREEN = 54;
//public static final int M_BLUE = 55;
//public static final int SLOW = 56;
//public static final int BOOTS = 57;
//public static final int BERSERK = 58;



}
