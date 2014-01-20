// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RmiStarter.java

class RmiStarter extends ActionBase {

	RmiStarter() {
		action();
	}

	public void actionMain() {
		java.util.Vector vector = SlgClient.getChara();
		if (vector != null)
			PaintBase.uw.rmiStarterEnd(vector);
	}

	public void leftPressed() {
		PaintBase.uw.setLPanel("Waiting", 3, 1500);
	}

	public void rightPressed() {
	}
}
