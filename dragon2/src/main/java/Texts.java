// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Texts.java

class Texts {

	Texts() {
	}

	public static void setup() {
		help = new String[30][2];
		tuto = new String[20][2];
		sp = new String[100];
		String as[] = { "\u2605", "\u2606", "\u25CF", "\u25CB", "\u25A0",
				"\u25A1", "\u25C6", "\u25C7", "\uFF0D" };
		kigo = as;
		help[1][0] = "\u884C\u52D5\u3055\u305B\u308B\u30AD\u30E3\u30E9\u3092\u6307\u5B9A\u3057\u3066\u4E0B\u3055\u3044\u3002";
		help[1][1] = "\u30E1\u30CB\u30E5\u30FC\u306E TURN END \u3067\u30BF\u30FC\u30F3\u7D42\u4E86\u3002";
		help[2][0] = "\u8077\u696D\u3092\u524A\u9664\u3057\u307E\u3059\u3002\u524A\u9664\u3055\u308C\u305F\u8077\u696D\u306F\u3001";
		help[2][1] = "\uFF25\uFF38\uFF30\u304C\uFF10\u306B\u306A\u3063\u3066\u5009\u5EAB\u306B\u623B\u308A\u307E\u3059\u3002";
		help[3][0] = "\u5DE6\u30AF\u30EA\u30C3\u30AF\uFF1A\u6280\u306E\u8AAC\u660E\u66F8\u3092\u524A\u9664\u3057\u307E\u3059\u3002";
		help[3][1] = "\u53F3\u30AF\u30EA\u30C3\u30AF\uFF1A\u30AD\u30E3\u30F3\u30BB\u30EB";
		help[4][0] = "\u5DE6\u30AF\u30EA\u30C3\u30AF\uFF1A\u30A2\u30A4\u30C6\u30E0\u3092\u63B4\u3080\u3002";
		help[4][1] = "\u30E1\u30CB\u30E5\u30FC\u306E STAGE \u3067\u6B21\u306E\u30B9\u30C6\u30FC\u30B8\u3078\u3002";
		help[5][0] = "\u5DE6\u30AF\u30EA\u30C3\u30AF\uFF1A\u30A2\u30A4\u30C6\u30E0\u3092\u7F6E\u304F\u3002";
		help[5][1] = "\u53F3\u30AF\u30EA\u30C3\u30AF\uFF1A\u5009\u5EAB\u306B\u9001\u308B\u3002";
		help[6][0] = "\u5DE6\u4E0B\u304C\u30C9\u30FC\u30EB\u306E\u914D\u7F6E\u5834\u6240\u3067\u3059\u3002";
		help[6][1] = "\u914D\u7F6E\u3057\u305F\u30C9\u30FC\u30EB\u306F\u6226\u95D8\u306B\u53C2\u52A0\u3067\u304D\u307E\u3059\u3002";
		help[7][0] = "\u914D\u7F6E\u5834\u6240\u3092\u6307\u5B9A\u3057\u3066\u4E0B\u3055\u3044\u3002";
		help[7][1] = "\u5DE6\u5217\uFF1A\u30EC\u30AE\u30E5\u30E9\u30FC\u67A0\u3000\u3000\u3000\u53F3\u5217\uFF1A\u88DC\u6B20\u67A0";
		help[9][0] = "\u30AB\u30FC\u30C9\u306F\u4E00\u5EA6\u306B\uFF13\u679A\u307E\u3067\u30AA\u30FC\u30D7\u30F3\u3067\u304D\u307E\u3059\u3002";
		help[9][1] = "\u30AB\u30FC\u30C9\u3092\u51FA\u3057\u5408\u3063\u3066\u5927\u304D\u3044\u307B\u3046\u306E\u52DD\u3061\u3067\u3059\u3002";
		help[10][0] = "\u4ECA\u307E\u3067\u306B\u96C6\u3081\u305F\u30A2\u30A4\u30C6\u30E0\u306E\u30EA\u30B9\u30C8\u3067\u3059\u3002";
		help[11][0] = "\u5DE6\u30AF\u30EA\u30C3\u30AF\uFF1A\u884C\u52D5\u7D42\u4E86";
		help[11][1] = "\u53F3\u30AF\u30EA\u30C3\u30AF\uFF1A\u30AD\u30E3\u30F3\u30BB\u30EB";
		help[12][0] = "\u547D\u4E2D\uFF0B\u84C4\u7A4D\u304C\uFF11\uFF16\u4EE5\u4E0A\u306A\u3089\u547D\u4E2D\u3057\u307E\u3059\u3002";
		help[12][1] = "\uFF28\uFF30\u30D0\u30FC\u304C\u9752\u3051\u308C\u3070\u78BA\u5B9F\u306B\u5012\u305B\u307E\u3059\u3002";
		help[13][0] = "\u653B\u6483\u76EE\u6A19\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044\u3002";
		help[13][1] = "\u81EA\u5206\u3092\u30AF\u30EA\u30C3\u30AF\u3059\u308B\u3068\u6280\u3092\u9078\u629E\u3067\u304D\u307E\u3059\u3002";
		help[14][0] = "\u828B\u72E9\u308A\u3067\u7D4C\u9A13\u5024\u304C\u3082\u3089\u3048\u307E\u3059\u3002";
		help[14][1] = "\u7121\u50B7\u3067\u5012\u3057\u305F\u3089\u59B9\u306E\u6240\u3078\u884C\u304D\u307E\u3057\u3087\u3046\u3002";
		help[15][0] = "S_RANK\uFF1A\u30B9\u30D4\u30FC\u30C9\u30E9\u30F3\u30AD\u30F3\u30B0\u306B\u9001\u4FE1\u3059\u308B\u3002";
		help[15][1] = "P_RANK\uFF1A\u30D1\u30EF\u30FC\u30E9\u30F3\u30AD\u30F3\u30B0\u306B\u9001\u4FE1\u3059\u308B\u3002";
		help[16][0] = "\u30E1\u30CB\u30E5\u30FC\u306E START \u3092\u62BC\u3057\u3066\u6226\u95D8\u958B\u59CB\u3067\u3059\u3002";
		help[16][1] = "\u914D\u7F6E\u3092\u63DB\u3048\u308B\u3068\u304D\u306F\u30AD\u30E3\u30E9\u3092\u30AF\u30EA\u30C3\u30AF\u3002";
		help[17][0] = "\u6575\u306B\u30AB\u30FC\u30C9\u30D0\u30C8\u30EB\u3092\u6311\u307F\u307E\u3059\u3002";
		help[17][1] = "\u52DD\u3066\u3070\u6575\u3092\u4EF2\u9593\u306B\u3059\u308B\u3053\u3068\u304C\u3067\u304D\u307E\u3059\u3002";
		help[18][0] = "\u4ECA\u307E\u3067\u306B\u899A\u3048\u305F\u6280\u306E\u30EA\u30B9\u30C8\u3067\u3059\u3002";
		help[19][0] = "\u5DE6\u30AF\u30EA\u30C3\u30AF\uFF1A\u79FB\u52D5\u5148\u6307\u5B9A";
		help[19][1] = "\u53F3\u30AF\u30EA\u30C3\u30AF\uFF1A\u30AD\u30E3\u30F3\u30BB\u30EB";
		help[20][0] = "\u30B9\u30C6\u30FC\u30B8\u30AF\u30EA\u30A2\u3067\u3059\u3002";
		help[20][1] = "\u30E1\u30CB\u30E5\u30FC\u306E CAMP \u3092\u62BC\u3057\u3066\u4E0B\u3055\u3044\u3002";
		help[21][0] = "\u30B2\u30FC\u30E0\u30AA\u30FC\u30D0\u30FC\u3067\u3059\u3002";
		help[21][1] = "\u30E1\u30CB\u30E5\u30FC\u306E LOAD \u3092\u62BC\u3057\u3066\u4E0B\u3055\u3044\u3002";
		tuto[0][0] = "\u653B\u6483\u76EE\u6A19\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044\u3002";
		tuto[0][1] = "\u81EA\u5206\u3092\u30AF\u30EA\u30C3\u30AF\u3059\u308B\u3068\u6280\u3092\u9078\u629E\u3067\u304D\u307E\u3059\u3002";
		tuto[1][0] = "STOP!! \u30B4\u30D6\u30EA\u30F3\u306F\u9632\u5FA1\u529B\u304C\u9AD8\u3044\u306E\u3067\u3001";
		tuto[1][1] = "\u5263\u306E\u653B\u6483\u306F\u3042\u307E\u308A\u52B9\u304D\u307E\u305B\u3093\u3002";
		tuto[2][0] = "GOOD!! \u30D4\u30AF\u30B7\u30FC\u306F\u9632\u5FA1\u529B\u304C\u4F4E\u3044\u306E\u3067\u3001";
		tuto[2][1] = "\u5263\u306E\u653B\u6483\u304C\u3088\u304F\u52B9\u304D\u307E\u3059\u3002";
		tuto[3][0] = "O.K. \u3053\u306E\u4E00\u6483\u3067\u5012\u3059\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093\u304C";
		tuto[3][1] = "\u59B9\u3068\u306E\u9023\u643A\u306A\u3089\u3053\u3061\u3089\u304C\u30AA\u30B9\u30B9\u30E1\u3067\u3059\u3002";
		tuto[4][0] = "GOOD!! \u6575\u306F\u3059\u3067\u306B\u7015\u6B7B\u3067\u3059\u3002";
		tuto[4][1] = "\u901A\u5E38\u653B\u6483\u3067\u78BA\u5B9F\u306B\u4ED5\u7559\u3081\u307E\u3057\u3087\u3046\u3002";
		tuto[5][0] = "STOP!! \u3082\u3057\u30D4\u30AF\u30B7\u30FC\u304C\u6B8B\u3063\u3066\u3044\u308B\u306A\u3089\u3001";
		tuto[5][1] = "\u5148\u306B\u305D\u3063\u3061\u3092\u5012\u3059\u307B\u3046\u304C\u5F97\u7B56\u3067\u3059\u3002";
		tuto[6][0] = "STOP!! \u30D4\u30AF\u30B7\u30FC\u306F\u56DE\u907F\u529B\u304C\u9AD8\u3044\u306E\u3067\u3001";
		tuto[6][1] = "\u828B\u65AC\u308A\u306F\u5F53\u3089\u306A\u3044\u53EF\u80FD\u6027\u304C\u3042\u308A\u307E\u3059\u3002";
		tuto[7][0] = "GOOD!! \u30DC\u30B9\u306B\u5BFE\u3057\u3066\u9060\u616E\u306F\u8981\u308A\u307E\u305B\u3093\u3002";
		tuto[7][1] = "\u5FC5\u6BBA\u6280\u3067\u4E00\u6C17\u306B\u5012\u3057\u307E\u3057\u3087\u3046\u3002";
		tuto[8][0] = "STOP!! \u828B\u65AC\u308A\u306F\u547D\u4E2D\u7387\u304C\u4F4E\u3044\u306E\u3067\u3001";
		tuto[8][1] = "\u5916\u308C\u308B\u53EF\u80FD\u6027\u304C\u3042\u308A\u307E\u3059\u3002";
		tuto[9][0] = "GOOD!! \u30B4\u30D6\u30EA\u30F3\u306F\u62B5\u6297\u529B\u304C\u4F4E\u3044\u306E\u3067\u3001";
		tuto[9][1] = "\u9B54\u6CD5\u306E\u653B\u6483\u304C\u3088\u304F\u52B9\u304D\u307E\u3059\u3002";
		tuto[10][0] = "STOP!! \u30D4\u30AF\u30B7\u30FC\u306F\u62B5\u6297\u529B\u304C\u9AD8\u3044\u306E\u3067\u3001";
		tuto[10][1] = "\u9B54\u6CD5\u306E\u653B\u6483\u306F\u3042\u307E\u308A\u52B9\u304D\u307E\u305B\u3093\u3002";
		tuto[11][0] = "GOOD!! \u30AC\u30A4\u30B3\u30C4\u306F\u706B\u708E\u5C5E\u6027\u306B\u5F31\u3044\u306E\u3067\u3001";
		tuto[11][1] = "\u30D5\u30A1\u30A4\u30E4\u30FC\u30DC\u30FC\u30EB\u3067\u30C0\u30E1\u30FC\u30B8\uFF12\u500D\u3067\u3059\u3002";
		tuto[12][0] = "STOP!! \u3053\u3053\u306F\u547D\u4E2D\u7387\u306E\u9AD8\u3044";
		tuto[12][1] = "\u8A98\u5C0E\u5F3E\u3067\u78BA\u5B9F\u306B\u4ED5\u7559\u3081\u307E\u3057\u3087\u3046\u3002";
		tuto[13][0] = "GOOD!! \u30B4\u30D6\u30EA\u30F3\u306F\u62B5\u6297\u529B\u304C\u4F4E\u3044\u306E\u3067\u3001";
		tuto[13][1] = "\u9B54\u6CD5\u306E\u653B\u6483\u304C\u3088\u304F\u52B9\u304D\u307E\u3059\u3002";
		tuto[14][0] = "STOP!! \u3082\u3057\u30B4\u30D6\u30EA\u30F3\u304C\u6B8B\u3063\u3066\u3044\u308B\u306A\u3089\u3001";
		tuto[14][1] = "\u5148\u306B\u305D\u3063\u3061\u3092\u5012\u3059\u307B\u3046\u304C\u5F97\u7B56\u3067\u3059\u3002";
		tuto[15][0] = "O.K. \u3053\u306E\u4E00\u6483\u3067\u5012\u3059\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093\u304C";
		tuto[15][1] = "\u4E3B\u4EBA\u516C\u3068\u306E\u9023\u643A\u306A\u3089\u3053\u3061\u3089\u304C\u30AA\u30B9\u30B9\u30E1\u3067\u3059\u3002";
		tuto[16][0] = "GOOD!! \u6575\u306F\u3059\u3067\u306B\u7015\u6B7B\u3067\u3059\u3002";
		tuto[16][1] = "\u8A98\u5C0E\u5F3E\u3067\u78BA\u5B9F\u306B\u4ED5\u7559\u3081\u307E\u3057\u3087\u3046\u3002";
		sp[0] = "\u30EC\u30AE\u30E5\u30E9\u30FC\u67A0&\u30EC\u30AE\u30E5\u30E9\u30FC\u306F\u6700\u521D\u304B\u3089&\u6226\u95D8\u306B\u53C2\u52A0\u3067\u304D\u307E\u3059\u3002&\u307E\u305F\u9014\u4E2D\u3067\u88DC\u6B20\u3068\u4EA4\u4EE3&\u3059\u308B\u3053\u3068\u304C\u3067\u304D\u307E\u3059\u3002";
		sp[1] = "\u88DC\u6B20\u67A0&\u88DC\u6B20\u306F\u30B9\u30BF\u30FC\u30C8\u6642\u306B\u306F&\u914D\u7F6E\u3067\u304D\u307E\u305B\u3093\u3002&\u9014\u4E2D\u3067\u30EC\u30AE\u30E5\u30E9\u30FC\u3068\u4EA4&\u4EE3\u3057\u3066\u51FA\u5834\u3057\u307E\u3059\u3002";
		sp[2] = "\u8077\u696D\u67A0&\u8077\u696D\u306B\u306F\u8D64\u9752\u7DD1\u306E\uFF13\u7CFB&\u7D71\u304C\u3042\u308A\u3001\u540C\u3058\u7CFB\u7D71\u306B&\u3057\u304B\u8EE2\u8077\u3067\u304D\u307E\u305B\u3093\u3002";
		sp[3] = "\u6B66\u5668\u67A0&\u5263\u65A7\u30CA\u30A4\u30D5\u5F13\u69CD\u9B54\u6CD5\u306E&\uFF16\u7A2E\u985E\u306B\u5206\u304B\u308C\u3001\u8077\u696D&\u306B\u3088\u3063\u3066\u88C5\u5099\u3067\u304D\u308B\u7A2E&\u985E\u304C\u9055\u3044\u307E\u3059\u3002";
		sp[4] = "\u9632\u5177\u67A0&\u30ED\u30FC\u30D6\u3001\u76FE\u3001\u30DE\u30F3\u30C8\u306E&\uFF13\u7A2E\u985E\u304C\u3042\u308A\u307E\u3059\u304C\u3001&\u8077\u696D\u306B\u95A2\u4FC2\u306A\u304F\u3001\u306A\u3093&\u3067\u3082\u88C5\u5099\u3067\u304D\u307E\u3059\u3002";
		sp[5] = "\u30A2\u30A4\u30C6\u30E0\u67A0&\u3053\u3053\u306B\u306F\u30A2\u30A4\u30C6\u30E0\u3084\u30DE&\u30C6\u30EA\u30A2\u30EB\u3092\u7F6E\u304D\u307E\u3059\u3002&\u8077\u696D\u306B\u95A2\u4FC2\u306A\u304F\u3001\u306A\u3093&\u3067\u3082\u88C5\u5099\u3067\u304D\u307E\u3059\u3002";
		sp[6] = "\u3054\u307F\u7BB1&\u3053\u3053\u306B\u7F6E\u3044\u305F\u30A2\u30A4\u30C6\u30E0&\u306F\u3001\u30AD\u30E3\u30F3\u30D7\u3092\u51FA\u308B\u304B&\u30E1\u30CB\u30E5\u30FC\u306EREMOVE\u3092&\u62BC\u3059\u3068\u524A\u9664\u3055\u308C\u307E\u3059\u3002";
		sp[7] = "\u30C9\u30FC\u30EB\u67A0&\u3053\u3053\u306B\u7F6E\u3044\u305F\u30C9\u30FC\u30EB\u306F&\u6226\u95D8\u306B\u53C2\u52A0\u3055\u305B\u308B\u3053\u3068&\u304C\u3067\u304D\u307E\u3059\u3002";
		sp[8] = "\u30A2\u30A4\u30C6\u30E0\u67A0\uFF11&\u3053\u3053\u306B\u7F6E\u3044\u305F\u30A2\u30A4\u30C6\u30E0&\u3084\u30DE\u30C6\u30EA\u30A2\u30EB\u306E\u56FA\u6709\u6280&\u306F\u81EA\u7531\u306B\u4F7F\u3046\u3053\u3068\u304C\u3067&\u304D\u307E\u3059\u3002";
		sp[9] = "\u30A2\u30A4\u30C6\u30E0\u67A0\uFF12&\u3053\u3053\u306B\u30DE\u30C6\u30EA\u30A2\u30EB\u3092\uFF14&\u3064\u7F6E\u304F\u3068\u3001\u305D\u306E\u7D44\u307F\u5408&\u305B\u306B\u3088\u3063\u3066\u69D8\u3005\u306A\u7279\u6B8A&\u6280\u304C\u4F7F\u3048\u307E\u3059\u3002";
		sp[10] = "\u6B66\u5668\u67A0&\u30C9\u30FC\u30EB\u306F\u3069\u3093\u306A\u6B66\u5668\u3067&\u3082\u88C5\u5099\u3067\u304D\u307E\u3059\u304C\u3001&\u6B66\u5668\u306E\u56FA\u6709\u6280\u306F\u4F7F\u7528\u3067&\u304D\u307E\u305B\u3093\u3002";
		sp[11] = "\u9632\u5177\u67A0&\u30C9\u30FC\u30EB\u306F\u3069\u3093\u306A\u9632\u5177\u3067&\u3082\u88C5\u5099\u3067\u304D\u307E\u3059\u304C\u3001&\u9632\u5177\u306E\u56FA\u6709\u6280\u306F\u4F7F\u7528\u3067&\u304D\u307E\u305B\u3093\u3002";
		sp[12] = "\u9752\u30AF\u30EA\u30B9\u30BF\u30EB&\u3053\u308C\u3092\u7834\u58CA\u3055\u308C\u308B\u3068&\u3042\u306A\u305F\u306E\u8CA0\u3051\u3067\u3059\u3002&\u3053\u306E\u4E0A\u306B\u3044\u308B\u3068&\uFF28\uFF30\u304C\u56DE\u5FA9\u3057\u307E\u3059\u3002";
		sp[13] = "\u8D64\u30AF\u30EA\u30B9\u30BF\u30EB&\u3053\u308C\u3092\u7834\u58CA\u3059\u308C\u3070&\u3042\u306A\u305F\u306E\u52DD\u3061\u3067\u3059\u3002";
		sp[14] = "\uFF33\u30D1\u30CD\u30EB&\u3053\u306E\u5834\u6240\u306B\u30AD\u30E3\u30E9\u3092&\u914D\u7F6E\u3057\u3066\u304F\u3060\u3055\u3044\u3002";
		sp[15] = "\u7802\u4E18&\u91CD\u6B69\uFF1A\uFF13\u3000\u8EFD\u6B69\uFF1A\uFF11";
		sp[16] = "\u7DD1\u6728&\u91CD\u6B69\uFF1A\uFF19\u3000\u8EFD\u6B69\uFF1A\uFF19& &\u98DB\u884C\u30E6\u30CB\u30C3\u30C8\u306E\u307F&\u79FB\u52D5\u53EF\u80FD\u3002";
		sp[17] = "\u6D45\u702C&\u91CD\u6B69\uFF1A\uFF16\u3000\u8EFD\u6B69\uFF1A\uFF13& &\u706B\u708E\u534A\u6E1B\u3000\u96FB\u6483\u7279\u52B9&\u6C34\u68F2\u30E6\u30CB\u30C3\u30C8\u306B\u6709\u5229\u3002";
		sp[18] = "\u6DF1\u6D77&\u91CD\u6B69\uFF1A\uFF19\u3000\u8EFD\u6B69\uFF1A\uFF19& &\u706B\u708E\u534A\u6E1B\u3000\u96FB\u6483\u7279\u52B9&\u6C34\u68F2\u30E6\u30CB\u30C3\u30C8\u306B\u6709\u5229\u3002";
		sp[19] = "\u9ED2\u58C1&\u91CD\u6B69\uFF1A\uFF19\u3000\u8EFD\u6B69\uFF1A\uFF19";
		sp[20] = "\u6C37\u96EA&\u91CD\u6B69\uFF1A\uFF11\u3000\u8EFD\u6B69\uFF1A\uFF11& &\u706B\u708E\u534A\u6E1B\u3000\u6C37\u7D50\u7279\u52B9";
		sp[21] = "\u6BD2\u6CBC&\u91CD\u6B69\uFF1A\uFF12\u3000\u8EFD\u6B69\uFF1A\uFF12& &\u5165\u308B\u3068\u6BD2\u72B6\u614B\u306B\u306A\u308B\u3002";
		sp[22] = "\u6CB9\u6CBC&\u91CD\u6B69\uFF1A\uFF12\u3000\u8EFD\u6B69\uFF1A\uFF12& &\u5165\u308B\u3068\u6CB9\u5857\u308C\u306B\u306A\u308B\u3002";
		sp[23] = "\u6EB6\u5CA9&\u91CD\u6B69\uFF1A\uFF12\u3000\u8EFD\u6B69\uFF1A\uFF12& &\u5165\u308B\u3068\u706B\u708E\u30C0\u30E1\u30FC\u30B8\u3002";
		sp[24] = "\u30B9\u30C6\u30FC\u30B8\u6570\u3000";
		sp[25] = "\u30A2\u30A4\u30C6\u30E0\u6570\u3000";
		sp[26] = "\u5012\u3057\u305F\u6575\u6570\u3000";
		sp[27] = "\u30D7\u30EC\u30A4\u6642\u9593\u3000";
		sp[28] = "\u7DCF\u30BF\u30FC\u30F3\u6570\u3000";
		sp[29] = "\u9003\u3052\u305F\u56DE\u6570\u3000";
		sp[30] = "\u6B7B\u3093\u3060\u56DE\u6570\u3000";
		sp[31] = "\u901F\u3055\u30B9\u30B3\u30A2\u3000";
		sp[32] = "\u5F37\u3055\u30B9\u30B3\u30A2\u3000";
		sp[33] = "\u88DC\u52A9\u9B54\u6CD5";
		sp[34] = "\u547D\u4E2D\u4F4E\u3081";
		sp[35] = "\u547D\u4E2D\u9AD8\u3081";
		sp[36] = "\u9AD8\u901F\u653B\u6483";
		sp[37] = "\u9023\u6483\u78BA\u5B9A";
		sp[38] = "\u5FC5\u4E2D\u653B\u6483";
		sp[39] = "\u653B\u6483\u6D88\u8CBB";
		sp[40] = "\u9632\u5FA1\u6D88\u8CBB";
		sp[41] = "\u9B54\u6CD5\u6D88\u8CBB";
		sp[42] = "\u62B5\u6297\u6D88\u8CBB";
		sp[43] = "\u547D\u4E2D\u6D88\u8CBB";
		sp[44] = "\u56DE\u907F\u6D88\u8CBB";
		sp[45] = "\u84C4\u7A4D";
		sp[46] = "\u9593\u5408";
		sp[47] = "\u5C04\u7A0B";
		sp[48] = "\u6642\u9650";
		sp[49] = "\u653B\u6483";
		sp[50] = "\u9632\u5FA1";
		sp[51] = "\u9B54\u6CD5";
		sp[52] = "\u62B5\u6297";
		sp[53] = "\u547D\u4E2D";
		sp[54] = "\u56DE\u907F";
		sp[55] = "\u5A01\u529B";
		sp[56] = "\u500D\u7387\u3000";
		sp[57] = "\u547D\u4E2D\u3000";
		sp[58] = "\u84C4\u7A4D\u3000";
		sp[59] = "\u52B9\u679C\u3000";
		sp[60] = "\u6226\u6CC1\u30C7\u30FC\u30BF";
		sp[61] = "\u30BF\u30FC\u30F3\u6570\u3000";
		sp[62] = "\u5012\u3057\u305F\u6570\u3000";
		sp[63] = "\u5B9D\u7BB1\u306E\u6570\u3000";
		sp[64] = "\u6B8B\u308B\u80FD\u529B\u3000\u9632\u5FA1";
		sp[65] = "\u6B8B\u308B\u80FD\u529B\u3000\u62B5\u6297";
		sp[66] = "\u6B8B\u308B\u80FD\u529B\u3000\u56DE\u907F";
		sp[67] = "\u5B9D\u7BB1\uFF08\u7A7A\u3063\u307D\uFF09";
		sp[68] = "\u58CA\u308C\u305F\u5B9D\u7BB1";
		sp[69] = "\u6642\u9593\u5236\u9650\u3000";
		sp[70] = "\u3082\u3046\u624B\u9045\u308C\u3067\u3059\u3002";
		sp[71] = "\u53D6\u308C\u307E\u305B\u3093\u3002";
		sp[72] = "\u5B9D\u7BB1";
		sp[73] = "\u6642\u9593\u5236\u9650\u3000";
		sp[74] = "\u5236\u9650\u30BF\u30FC\u30F3\u3092\u904E\u304E\u308B\u3068";
		sp[75] = "\u81EA\u52D5\u7684\u306B\u58CA\u308C\u307E\u3059\u3002";
		sp[76] = "\u53EC\u559A\u30D1\u30CD\u30EB\uFF08\u6E08\uFF09& &\u53EC\u559A\u6E08\u307F\u3067\u3059\u3002&\u3053\u308C\u4EE5\u4E0A\u306F\u51FA\u307E\u305B\u3093\u3002";
		sp[77] = "\u53EC\u559A\u30D1\u30CD\u30EB";
		sp[78] = "\u6642\u9593\u5236\u9650\u3000";
		sp[79] = "\u53EC\u559A\u5F85\u3061\u3067\u3059\u3002";
		sp[80] = "\u53EC\u559A\u30D1\u30CD\u30EB";
		sp[81] = "\u6642\u9593\u5236\u9650\u3000";
		sp[82] = "\u5236\u9650\u30BF\u30FC\u30F3\u3092\u904E\u304E\u308B\u3068";
		sp[83] = "\u6575\u304C\u53EC\u559A\u3055\u308C\u307E\u3059\u3002";
		sp[84] = "\u7A7A&\u91CD\u6B69\uFF1A\uFF19\u3000\u8EFD\u6B69\uFF1A\uFF19& &\u98DB\u884C\u30E6\u30CB\u30C3\u30C8\u306E\u307F&\u79FB\u52D5\u53EF\u80FD\u3002";
	}

	static String help[][];
	static String tuto[][];
	static String sp[];
	static String kigo[];
	static final int H_BASIC = 1;
	static final int H_CAMP1 = 2;
	static final int H_CAMP2 = 3;
	static final int H_CAMP3 = 4;
	static final int H_CAMP4 = 5;
	static final int H_CAMP5 = 6;
	static final int H_CAMP6 = 7;
	static final int H_CAMP7 = 8;
	static final int H_CARD = 9;
	static final int H_COLLECTION = 10;
	static final int H_END = 11;
	static final int H_FIGHT1 = 12;
	static final int H_FIGHT2 = 13;
	static final int H_IMOGARI = 14;
	static final int H_SCORE = 15;
	static final int H_SETMENS = 16;
	static final int H_TALK = 17;
	static final int H_WAZA = 18;
	static final int H_WALK = 19;
	static final int H_CLEAR = 20;
	static final int H_OVER = 21;
	static String heroname = "\u4E3B\u4EBA\u516C\u306E\u540D\u524D\u3092\u5165\u308C\u3066\u4E0B\u3055\u3044\u3002";
	static String sistername = "\u59B9\u306E\u540D\u524D\u3092\u5165\u308C\u3066\u4E0B\u3055\u3044\u3002";
	static String rankname = "\u30E9\u30F3\u30AD\u30F3\u30B0\u306B\u8868\u793A\u3059\u308B\u540D\u524D\u3092\u5165\u308C\u3066\u4E0B\u3055\u3044\u3002";
	static String comment = "\u30E9\u30F3\u30AD\u30F3\u30B0\u306B\u8868\u793A\u3059\u308B\u30B3\u30E1\u30F3\u30C8\u3092\u5165\u308C\u3066\u4E0B\u3055\u3044\u3002";
	static String rankcancel = "\u9001\u4FE1\u3092\u4E2D\u6B62\u3057\u307E\u3057\u305F\u3002";
	static String speed_rank = "\u3042\u306A\u305F\u306E\u30B9\u30D4\u30FC\u30C9\u30E9\u30F3\u30AF\u306F";
	static String power_rank = "\u3042\u306A\u305F\u306E\u30D1\u30EF\u30FC\u30E9\u30F3\u30AF\u306F";
	static String idesu = "\u4F4D\u3067\u3059\u3002";
	static String conn_fail = "\u63A5\u7D9A\u306B\u5931\u6557\u3057\u307E\u3057\u305F\u3002";
	static String help_off = "\u7C21\u6613\u30D8\u30EB\u30D7\u8868\u793A OFF";
	static String help_on = "\u7C21\u6613\u30D8\u30EB\u30D7\u8868\u793A ON";
	static String shokugyo = "\u8077\u696D";
	static String buki = "\u6B66\u5668";
	static String bougu = "\u9632\u5177";
	static String komono = "\u5C0F\u7269";
	static String ningyo = "\u4EBA\u5F62";
	static String wazasetumei = "\u6280\u8AAC\u660E";
	static String nakama = "\u4EF2\u9593";
	static String sokoni = "\u305D\u3053\u306B";
	static String haokemasen = "\u306F\u7F6E\u3051\u307E\u305B\u3093";
	static String card_success = "\u8AAC\u5F97\u306B\u6210\u529F\u3057\u305F\u3002";
	static String card_success2 = "\u4EF2\u9593\u306B\u306A\u3063\u305F\u3002";
	static String card_fail = "\u8AAC\u5F97\u306B\u5931\u6557\u3057\u305F\u3002";
	static String card_fail2 = "HP\u304C\u56DE\u5FA9\u3057\u305F\u3002";
	static String ga = "\u304C";
	static String no = "\u306E";
	static String item = "\u30A2\u30A4\u30C6\u30E0\uFF1A";
	static String material = "\u3000\u30DE\u30C6\u30EA\u30A2\u30EB\uFF1A";
	static String waza = "\u6280\u7FD2\u5F97\u7387\uFF1A";
	static String ha = "\u306F";
	static String equip1 = "\u30EC\u30D9\u30EB\u304C\u4E0A\u304C\u3063\u305F\u3002";
	static String wo = "\u3092";
	static String equip2 = "\u899A\u3048\u305F\u3002";
	static String equip3 = " \u4E0A\u304C\u3063\u305F\u3002";
	static String hp = "\uFF28\uFF30";
	static String kougekiryoku = "\u653B\u6483\u529B";
	static String bougyoryoku = "\u9632\u5FA1\u529B";
	static String mahouryoku = "\u9B54\u6CD5\u529B";
	static String teikouryoku = "\u62B5\u6297\u529B";
	static String meichuritu = "\u547D\u4E2D\u7387";
	static String kaihiritu = "\u56DE\u907F\u7387";
	static String imogari1 = "\u828B\u72E9\u308A\u306B\u6210\u529F\u3057\u305F\u3002";
	static String expwo = "EXP\u3092 ";
	static String teniireta = " \u624B\u306B\u5165\u308C\u305F\u3002";
	static String imogari2 = "\u828B\u72E9\u308A\u306B\u5931\u6557\u3057\u305F\u3002";
	static String material1 = "\u30DE\u30C6\u30EA\u30A2\u30EB\u306B\u306A\u3063\u305F\u3002";
	static String material2 = "\u9752\u30DE\u30C6\u30EA\u30A2\u30EB\u3092";
	static String material3 = "\u8D64\u30DE\u30C6\u30EA\u30A2\u30EB\u3092";
	static String material4 = "\u7DD1\u30DE\u30C6\u30EA\u30A2\u30EB\u3092";
	static String material5 = "\u624B\u306B\u5165\u308C\u305F\u3002";
	static String warning1 = "\u6B66\u5668\u3092\u5916\u3057\u3066\u4E0B\u3055\u3044";
	static String warning2 = "\u30AD\u30E3\u30E9\u304C\u3044\u307E\u305B\u3093";
	static String warning3 = "\u6B66\u5668\u7CFB\u7D71\u304C\u9055\u3046\u306E\u3067\u8EE2\u8077\u3067\u304D\u307E\u305B\u3093";
	static String warning4 = "\u88C5\u5099\u3067\u304D\u307E\u305B\u3093";
	static String warning5 = "\u30EC\u30D9\u30EB\u304C\u8DB3\u308A\u307E\u305B\u3093";
	static String treasure1 = "\u5B9D\u7BB1\u3092\u6301\u3063\u3066\u3044\u305F\u3002";
	static String treasure2 = "\u624B\u306B\u5165\u308C\u305F\u3002";
	static String treasure3 = "\u5B9D\u7BB1\u3092\u767A\u898B\u3057\u305F\u3002";
	static String goburin = "\u30B4\u30D6\u30EA\u30F3";
	static String pikusi = "\u30D4\u30AF\u30B7\u30FC";
	static String gaikotu = "\u30AC\u30A4\u30B3\u30C4\u5263\u58EB";

}
