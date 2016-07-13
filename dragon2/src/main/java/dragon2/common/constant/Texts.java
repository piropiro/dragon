package dragon2.common.constant;

public class Texts {
	public static final String[][] help;
	public static final String[][] tuto;
	public static final String[] sp;
	public static final String[] kigo;
	public static final int H_BASIC = 1;
	public static final int H_CAMP1 = 2;
	public static final int H_CAMP2 = 3;
	public static final int H_CAMP3 = 4;
	public static final int H_CAMP4 = 5;
	public static final int H_CAMP5 = 6;
	public static final int H_CAMP6 = 7;
	public static final int H_CAMP7 = 8;
	public static final int H_CARD = 9;
	public static final int H_COLLECTION = 10;
	public static final int H_END = 11;
	public static final int H_FIGHT1 = 12;
	public static final int H_FIGHT2 = 13;
	public static final int H_IMOGARI = 14;
	public static final int H_SCORE = 15;
	public static final int H_SETMENS = 16;
	public static final int H_TALK = 17;
	public static final int H_WAZA = 18;
	public static final int H_WALK = 19;
	public static final int H_CLEAR = 20;
	public static final int H_OVER = 21;

	public static final String heroname = "主人公の名前を入れて下さい。";
	public static final String sistername = "妹の名前を入れて下さい。";
	public static final String rankname = "ランキングに表示する名前を入れて下さい。";
	public static final String comment = "ランキングに表示するコメントを入れて下さい。";
	public static final String rankcancel = "送信を中止しました。";
	public static final String speed_rank = "あなたのスピードランクは";
	public static final String power_rank = "あなたのパワーランクは";
	public static final String idesu = "位です。";
	public static final String conn_fail = "接続に失敗しました。";
	public static final String help_off = "簡易ヘルプ表示 OFF";
	public static final String help_on = "簡易ヘルプ表示 ON";

	public static final String shokugyo = "職業";
	public static final String buki = "武器";
	public static final String bougu = "防具";
	public static final String komono = "小物";
	public static final String ningyo = "人形";
	public static final String wazasetumei = "技説明";
	public static final String nakama = "仲間";
	public static final String sokoni = "そこに";
	public static final String haokemasen = "は置けません";

	public static final String card_success = "説得に成功した。";
	public static final String card_success2 = "仲間になった。";
	public static final String card_fail = "説得に失敗した。";
	public static final String card_fail2 = "HPが回復した。";
	public static final String ga = "が";
	public static final String no = "の";
	public static final String item = "アイテム：";
	public static final String material = "　マテリアル：";
	public static final String waza = "技習得率：";

	public static final String ha = "は";
	public static final String equip1 = "レベルが上がった。";
	public static final String wo = "を";
	public static final String equip2 = "覚えた。";
	public static final String equip3 = " 上がった。";

	public static final String hp = "ＨＰ";
	public static final String kougekiryoku = "攻撃力";
	public static final String bougyoryoku = "防御力";
	public static final String mahouryoku = "魔法力";
	public static final String teikouryoku = "抵抗力";
	public static final String meichuritu = "命中率";
	public static final String kaihiritu = "回避率";

	public static final String imogari1 = "芋狩りに成功した。";
	public static final String expwo = "EXPを ";
	public static final String teniireta = " 手に入れた。";
	public static final String imogari2 = "芋狩りに失敗した。";

	public static final String material1 = "マテリアルになった。";
	public static final String material2 = "青マテリアルを";
	public static final String material3 = "赤マテリアルを";
	public static final String material4 = "緑マテリアルを";
	public static final String material5 = "手に入れた。";

	public static final String warning1 = "武器を外して下さい";
	public static final String warning2 = "キャラがいません";
	public static final String warning3 = "武器系統が違うので転職できません";
	public static final String warning4 = "装備できません";
	public static final String warning5 = "レベルが足りません";

	public static final String treasure1 = "宝箱を持っていた。";
	public static final String treasure2 = "手に入れた。";
	public static final String treasure3 = "宝箱を発見した。";

	public static final String goburin = "ゴブリン";
	public static final String pikusi = "ピクシー";
	public static final String gaikotu = "ガイコツ剣士";

	static {
		help = new String[30][2];
		tuto = new String[20][2];

		sp = new String[100];
		String[] kigos = { "★", "☆", "●", "○", "■", "□", "◆", "◇", "－" };
		kigo = kigos;
		help[H_BASIC][0] = "行動させるキャラを指定して下さい。";
		help[H_BASIC][1] = "メニューの TURN END でターン終了。";
		help[H_CAMP1][0] = "職業を削除します。削除された職業は、";
		help[H_CAMP1][1] = "ＥＸＰが０になって倉庫に戻ります。";
		help[H_CAMP2][0] = "左クリック：技の説明書を削除します。";
		help[H_CAMP2][1] = "右クリック：キャンセル";
		help[H_CAMP3][0] = "左クリック：アイテムを掴む。";
		help[H_CAMP3][1] = "メニューの STAGE で次のステージへ。";
		help[H_CAMP4][0] = "左クリック：アイテムを置く。";
		help[H_CAMP4][1] = "右クリック：倉庫に送る。";
		help[H_CAMP5][0] = "左下がドールの配置場所です。";
		help[H_CAMP5][1] = "配置したドールは戦闘に参加できます。";
		help[H_CAMP6][0] = "配置場所を指定して下さい。";
		help[H_CAMP6][1] = "左列：レギュラー枠　　　右列：補欠枠";
		help[H_CARD][0] = "カードは一度に３枚までオープンできます。";
		help[H_CARD][1] = "カードを出し合って大きいほうの勝ちです。";
		help[H_COLLECTION][0] = "今までに集めたアイテムのリストです。";
		help[H_END][0] = "左クリック：行動終了";
		help[H_END][1] = "右クリック：キャンセル";
		help[H_FIGHT1][0] = "命中＋蓄積が１６以上なら命中します。";
		help[H_FIGHT1][1] = "ＨＰバーが青ければ確実に倒せます。";
		help[H_FIGHT2][0] = "攻撃目標を指定してください。";
		help[H_FIGHT2][1] = "自分をクリックすると技を選択できます。";
		help[H_IMOGARI][0] = "芋狩りで経験値がもらえます。";
		help[H_IMOGARI][1] = "無傷で倒したら妹の所へ行きましょう。";
		help[H_SCORE][0] = "S_RANK：スピードランキングに送信する。";
		help[H_SCORE][1] = "P_RANK：パワーランキングに送信する。";
		help[H_SETMENS][0] = "メニューの START を押して戦闘開始です。";
		help[H_SETMENS][1] = "配置を換えるときはキャラをクリック。";
		help[H_TALK][0] = "敵にカードバトルを挑みます。";
		help[H_TALK][1] = "勝てば敵を仲間にすることができます。";
		help[H_WAZA][0] = "今までに覚えた技のリストです。";
		help[H_WALK][0] = "左クリック：移動先指定";
		help[H_WALK][1] = "右クリック：キャンセル";
		help[H_CLEAR][0] = "ステージクリアです。";
		help[H_CLEAR][1] = "メニューの CAMP を押して下さい。";
		help[H_OVER][0] = "ゲームオーバーです。";
		help[H_OVER][1] = "メニューの LOAD を押して下さい。";

		tuto[0][0] = "攻撃目標を指定してください。";
		tuto[0][1] = "自分をクリックすると技を選択できます。";
		tuto[1][0] = "STOP!! ゴブリンは防御力が高いので、";
		tuto[1][1] = "剣の攻撃はあまり効きません。";
		tuto[2][0] = "GOOD!! ピクシーは防御力が低いので、";
		tuto[2][1] = "剣の攻撃がよく効きます。";
		tuto[3][0] = "O.K. この一撃で倒すことはできませんが";
		tuto[3][1] = "妹との連携ならこちらがオススメです。";
		tuto[4][0] = "GOOD!! 敵はすでに瀕死です。";
		tuto[4][1] = "通常攻撃で確実に仕留めましょう。";
		tuto[5][0] = "STOP!! もしピクシーが残っているなら、";
		tuto[5][1] = "先にそっちを倒すほうが得策です。";
		tuto[6][0] = "STOP!! ピクシーは回避力が高いので、";
		tuto[6][1] = "芋斬りは当らない可能性があります。";
		tuto[7][0] = "GOOD!! ボスに対して遠慮は要りません。";
		tuto[7][1] = "必殺技で一気に倒しましょう。";
		tuto[8][0] = "STOP!! 芋斬りは命中率が低いので、";
		tuto[8][1] = "外れる可能性があります。";
		tuto[9][0] = "GOOD!! ゴブリンは抵抗力が低いので、";
		tuto[9][1] = "魔法の攻撃がよく効きます。";
		tuto[10][0] = "STOP!! ピクシーは抵抗力が高いので、";
		tuto[10][1] = "魔法の攻撃はあまり効きません。";
		tuto[11][0] = "GOOD!! ガイコツは火炎属性に弱いので、";
		tuto[11][1] = "ファイヤーボールでダメージ２倍です。";
		tuto[12][0] = "STOP!! ここは命中率の高い";
		tuto[12][1] = "誘導弾で確実に仕留めましょう。";
		tuto[13][0] = "GOOD!! ゴブリンは抵抗力が低いので、";
		tuto[13][1] = "魔法の攻撃がよく効きます。";
		tuto[14][0] = "STOP!! もしゴブリンが残っているなら、";
		tuto[14][1] = "先にそっちを倒すほうが得策です。";
		tuto[15][0] = "O.K. この一撃で倒すことはできませんが";
		tuto[15][1] = "主人公との連携ならこちらがオススメです。";
		tuto[16][0] = "GOOD!! 敵はすでに瀕死です。";
		tuto[16][1] = "誘導弾で確実に仕留めましょう。";

		sp[0] = "レギュラー枠&レギュラーは最初から&戦闘に参加できます。" + "&また途中で補欠と交代&することができます。";
		sp[1] = "補欠枠&補欠はスタート時には&配置できません。" + "&途中でレギュラーと交&代して出場します。";
		sp[2] = "職業枠&職業には赤青緑の３系&統があり、同じ系統に" + "&しか転職できません。";
		sp[3] = "武器枠&剣斧ナイフ弓槍魔法の&６種類に分かれ、職業" + "&によって装備できる種&類が違います。";
		sp[4] = "防具枠&ローブ、盾、マントの&３種類がありますが、" + "&職業に関係なく、なん&でも装備できます。";
		sp[5] = "アイテム枠&ここにはアイテムやマ&テリアルを置きます。" + "&職業に関係なく、なん&でも装備できます。";
		sp[6] = "ごみ箱&ここに置いたアイテム&は、キャンプを出るか" + "&メニューのREMOVEを&押すと削除されます。";
		sp[7] = "ドール枠&ここに置いたドールは&戦闘に参加させること" + "&ができます。";
		sp[8] = "アイテム枠１&ここに置いたアイテム&やマテリアルの固有技" + "&は自由に使うことがで&きます。";
		sp[9] = "アイテム枠２&ここにマテリアルを４&つ置くと、その組み合" + "&せによって様々な特殊&技が使えます。";
		sp[10] = "武器枠&ドールはどんな武器で&も装備できますが、" + "&武器の固有技は使用で&きません。";
		sp[11] = "防具枠&ドールはどんな防具で&も装備できますが、" + "&防具の固有技は使用で&きません。";
		sp[12] = "青クリスタル&これを破壊されると&あなたの負けです。" + "&この上にいると&ＨＰが回復します。";
		sp[13] = "赤クリスタル&これを破壊すれば&あなたの勝ちです。";
		sp[14] = "Ｓパネル&この場所にキャラを&配置してください。";
		sp[15] = "砂丘&重歩：３　軽歩：１";
		sp[16] = "緑木&重歩：９　軽歩：９& &飛行ユニットのみ&移動可能。";
		sp[17] = "浅瀬&重歩：６　軽歩：３& &火炎半減　電撃特効" + "&水棲ユニットに有利。";
		sp[18] = "深海&重歩：９　軽歩：９& &火炎半減　電撃特効" + "&水棲ユニットに有利。";
		sp[19] = "黒壁&重歩：９　軽歩：９";
		sp[20] = "氷雪&重歩：１　軽歩：１& &火炎半減　氷結特効";
		sp[21] = "毒沼&重歩：２　軽歩：２& &入ると毒状態になる。";
		sp[22] = "油沼&重歩：２　軽歩：２& &入ると油塗れになる。";
		sp[23] = "溶岩&重歩：２　軽歩：２& &入ると火炎ダメージ。";
		sp[24] = "ステージ数　";
		sp[25] = "アイテム数　";
		sp[26] = "倒した敵数　";
		sp[27] = "プレイ時間　";
		sp[28] = "総ターン数　";
		sp[29] = "逃げた回数　";
		sp[30] = "死んだ回数　";
		sp[31] = "速さスコア　";
		sp[32] = "強さスコア　";
		sp[33] = "補助魔法";
		sp[34] = "命中低め";
		sp[35] = "命中高め";
		sp[36] = "高速攻撃";
		sp[37] = "連撃確定";
		sp[38] = "必中攻撃";
		sp[39] = "攻撃消費";
		sp[40] = "防御消費";
		sp[41] = "魔法消費";
		sp[42] = "抵抗消費";
		sp[43] = "命中消費";
		sp[44] = "回避消費";
		sp[45] = "蓄積";
		sp[46] = "間合";
		sp[47] = "射程";
		sp[48] = "時限";
		sp[49] = "攻撃";
		sp[50] = "防御";
		sp[51] = "魔法";
		sp[52] = "抵抗";
		sp[53] = "命中";
		sp[54] = "回避";
		sp[55] = "威力";
		sp[56] = "倍率　";
		sp[57] = "命中　";
		sp[58] = "蓄積　";
		sp[59] = "効果　";
		sp[60] = "戦況データ";
		sp[61] = "ターン数　";
		sp[62] = "倒した数　";
		sp[63] = "宝箱の数　";
		sp[64] = "残る能力　防御";
		sp[65] = "残る能力　抵抗";
		sp[66] = "残る能力　回避";
		sp[67] = "宝箱（空っぽ）";
		sp[68] = "壊れた宝箱";
		sp[69] = "時間制限　";
		sp[70] = "もう手遅れです。";
		sp[71] = "取れません。";
		sp[72] = "宝箱";
		sp[73] = "時間制限　";
		sp[74] = "制限ターンを過ぎると";
		sp[75] = "自動的に壊れます。";
		sp[76] = "召喚パネル（済）& &召喚済みです。&これ以上は出ません。";
		sp[77] = "召喚パネル";
		sp[78] = "時間制限　";
		sp[79] = "召喚待ちです。";
		sp[80] = "召喚パネル";
		sp[81] = "時間制限　";
		sp[82] = "制限ターンを過ぎると";
		sp[83] = "敵が召喚されます。";
		sp[84] = "空&重歩：９　軽歩：９& &飛行ユニットのみ&移動可能。";

	}
}
