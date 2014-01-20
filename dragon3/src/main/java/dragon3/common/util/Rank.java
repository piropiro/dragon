package dragon3.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

import dragon3.bean.SaveData;
import dragon3.common.Body;
import dragon3.manage.SaveManager;

public class Rank {
	private SaveManager sm;
	private Equip equip;

	public Rank(Equip equip, SaveManager sm) {
		this.sm = sm;
		this.equip = equip;
	}

	public int getScore1() {
		SaveData sd = sm.getSaveData();
		if (sd.isAllClear()) {
			return sd.getScore();
		} else {
			return (int)
				(sd.getStage() * 5000
					+ sd.getItem() * 50
					+ sd.getKill() * 50
					- sd.getTurn() * 100
					- sd.getDead() * 100
					- sd.getEscape() * 500
					- sm.getPlayTime() / 60000);
		}
	}
	public int getScore2() {
		SaveData sd = sm.getSaveData();
		int score2 = sd.getStage() * 500 + sd.getItem() * 10 + sd.getKill() * 5;
		for (int x = 1; x <= 8; x += 7) {
			for (int y = 1; y <= 13; y += 3) {
				Body b = equip.search(x, y);
				if (b == null)
					continue;
				equip.equip(b);
				score2 += b.getHpMax()
					+ b.getStr()
					+ b.getDef()
					+ b.getMst()
					+ b.getMdf()
					+ b.getHit()
					+ b.getMis();
			}
		}
		return score2;
	}

	public int sendHayasaScore(String comment) {
		String url =
			"http://park.millto.net/~saitoo/cgi-bin/hayasa/regist_ranking.cgi";
		String poststr =
			"SCORE="
				+ getScore1()
				+ "&USER_NAME="
				+ sm.getSaveData().getPlayerName()
				+ "&COMMENT="
				+ comment;
		return doPost(url, poststr, comment);
	}
	public int sendTuyosaScore(String comment) {
		String url =
			"http://park.millto.net/~saitoo/cgi-bin/tuyosa/regist_ranking.cgi";
		String poststr =
			"SCORE="
				+ getScore2()
				+ "&USER_NAME="
				+ sm.getSaveData().getPlayerName()
				+ "&COMMENT="
				+ comment;

		return doPost(url, poststr, comment);
	}

	private int doPost(String urls, String poststr, String comment) {
		try {
			URL url = new URL(urls);

			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);

			PrintStream pout = new PrintStream(conn.getOutputStream());
			pout.print(poststr);
			pout.close();

			BufferedReader reader =
				new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = reader.readLine();
			if (line == null)
				line = "0";
			reader.close();
			return Integer.parseInt(line.trim());
		} catch (Exception e) {
			System.out.println("Can not connect cgi.");
			System.out.println("" + e);
			e.printStackTrace();
			return 0;
		}

	}
}
