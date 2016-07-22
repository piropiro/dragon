package dragon2.common.util;





import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import dragon2.SaveManager;
import dragon2.common.Body;

public class Rank {

	public Rank() {
	}

	public static void setup(SaveManager savemanager, Equip equip1) {
		sm = savemanager;
		equip = equip1;
	}

	public static int getScore1() {
		if (sm.isAllClear())
			return sm.getScore();
		else
			return (int) ((long) ((sm.getStage() * 5000 + sm.getItem() * 50 + sm
					.getKill() * 50) - sm.getTurn() * 100 - sm.getDead() * 100 - sm
					.getEscape() * 500) - sm.getPlayTime() / 60000L);
	}

	public static int getScore2() {
		int i = sm.getStage() * 500 + sm.getItem() * 10 + sm.getKill() * 5;
		for (int j = 1; j <= 8; j += 7) {
			for (int k = 1; k <= 13; k += 3) {
				Body body = equip.search(j, k);
				if (body != null) {
					equip.equip(body);
					i += body.hpMax + body.str + body.def + body.mst + body.mdf
							+ body.hit + body.mis;
				}
			}

		}

		return i;
	}

	public static int sendHayasaScore(String s) {
		String s1 = "http://park.millto.net/~saitoo/cgi-bin/hayasa/regist_ranking.cgi";
		String s2 = "SCORE=" + getScore1() + "&USER_NAME=" + sm.getPlayerName()
				+ "&COMMENT=" + s;
		return doPost(s1, s2, s);
	}

	public static int sendTuyosaScore(String s) {
		String s1 = "http://park.millto.net/~saitoo/cgi-bin/tuyosa/regist_ranking.cgi";
		String s2 = "SCORE=" + getScore2() + "&USER_NAME=" + sm.getPlayerName()
				+ "&COMMENT=" + s;
		return doPost(s1, s2, s);
	}

	private static int doPost(String s, String s1, String s2) {
		try {
			URL url = new URL(s);
			URLConnection urlconnection = url.openConnection();
			urlconnection.setDoOutput(true);
			PrintStream printstream = new PrintStream(
					urlconnection.getOutputStream());
			printstream.print(s1);
			printstream.close();
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(urlconnection.getInputStream()));
			String s3 = bufferedreader.readLine();
			if (s3 == null)
				s3 = "0";
			bufferedreader.close();
			return Integer.parseInt(s3.trim());
		} catch (Exception exception) {
			System.out.println("Can not connect cgi.");
			System.out.println("" + exception);
			exception.printStackTrace();
			return 0;
		}
	}

	private static SaveManager sm;
	private static Equip equip;
}
