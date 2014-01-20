// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SlgClient.java

import java.awt.Frame;
import java.io.PrintStream;
import java.util.Vector;
import mine.DataStream;
import mine.TextDialog;

public class SlgClient {

	public SlgClient() {
	}

	public static int[][] setup(Frame frame, UnitWorks unitworks) {
		uw = unitworks;
		String s = getHost(frame);
		if (s == null)
			return null;
		hostname = s;
		String s1;
		try {
			s1 = "//" + hostname + "/SlgManager";
		} catch (Exception exception) {
			uw.setLPanel("Can't Connect to Server", 3, 1500);
			return null;
		}
		try {
			if (!setColor())
				return null;
			int ai[][] = manager.getMapData();
			if (ai != null)
				return ai;
			ai = getMap(frame);
			if (ai != null && manager.setMapData(ai))
				return ai;
			else
				return manager.getMapData();
		} catch (Exception exception1) {
			System.out.println(exception1);
		}
		return null;
	}

	private static String getHost(Frame frame) {
		TextDialog textdialog = new TextDialog(frame);
		textdialog.setup("Input HostName", hostname);
		textdialog.show();
		return textdialog.getText();
	}

	private static int[][] getMap(Frame frame) {
		TextDialog textdialog = new TextDialog(frame);
		textdialog.setup("Input MapFile", mapname);
		textdialog.show();
		String s = textdialog.getText();
		if (s == null)
			return null;
		mapname = s;
		int ai[][] = (int[][]) DataStream.read(mapname);
		if (ai != null)
			return ai;
		else
			return getMap(frame);
	}

	private static boolean setColor() throws Exception {
		num = manager.getNumber();
		switch (num) {
		case 1: // '\001'
			Colors.setup(1, 3);
			return true;

		case 2: // '\002'
			Colors.setup(3, 1);
			return true;
		}
		return false;
	}

	public static void setChara(Vector vector) {
		try {
			manager.setChara(Colors.getPC(), vector);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static Vector getChara() {
		try {
			Luck.setup(3);
			while (remoteFlag) {
				Vector vector = manager.getChara(Colors.getEC());
				if (vector != null)
					return vector;
				Thread.currentThread();
				Thread.sleep(1000L);
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
	}

	public static void setActionWalk(int i, int j, int k, int l) {
		act = new Action();
		act.x = i;
		act.y = j;
		act.wx = k;
		act.wy = l;
	}

	public static void setActionAtk(int i, int j, int k) {
		act.atk = i;
		act.tx = j;
		act.ty = k;
		setAction(act);
	}

	public static void setActionEnd() {
		act.atk = -1;
		setAction(act);
	}

	private static void setAction(Action action) {
		if (manager == null)
			return;
		try {
			manager.setAction(action);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static void setTurnEnd() {
		if (manager == null)
			return;
		try {
			manager.setTurnEnd(Colors.getPC());
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static Action getAction() {
		try {
			do {
				Action action = manager.getAction();
				if (action == null) {
					if (manager.isMyTurn(Colors.getPC()))
						return null;
					Thread.currentThread();
					Thread.sleep(500L);
				} else {
					return action;
				}
			} while (true);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
	}

	public static void cancel() {
		if (manager == null)
			return;
		try {
			remoteFlag = false;
			Luck.setup(0);
			manager.reset();
			manager = null;
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public static boolean remoteFlag;
	private static String hostname = "localhost";
	private static String mapname = "data/D1.txt";
	private static int num;
	private static UnitWorks uw;
	private static SlgManager manager;
	private static Action act;

}
