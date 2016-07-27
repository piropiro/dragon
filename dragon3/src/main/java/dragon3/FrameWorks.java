package dragon3;


import javax.swing.JFrame;

import mine.event.PaintComponent;

public interface FrameWorks {

	public static final int T_CAMP = 0;
	public static final int T_PLAYER = 2;
	public static final int T_SETMENS = 3;
	public static final int T_ENEMY = 4;
	public static final int T_CLEAR = 5;
	public static final int T_GAMEOVER = 6;
	public static final int T_REMOTES = 7;
	public static final int T_REMOTEE = 8;
	public static final int T_TITLE = 9;
	public static final int T_SCORE = 10;
	public static final int T_COLLECT = 11;
	public static final int T_WAZALIST = 12;

    public JFrame getFrame();

    public void setMenu( int n );
    
    public PaintComponent getMapPanel();
    public PaintComponent getAnimePanel();
    public PaintComponent getHPanel1();
    public PaintComponent getHPanel2();
    public PaintComponent getHelpPanel();
    public PaintComponent getSmallPanel();
    public PaintComponent getLargePanel();
    public PaintComponent getCardPanel();
    public PaintComponent getDataPanel1();
    public PaintComponent getDataPanel2();
    public PaintComponent getMessagePanel();
}
