package dragon2.anime;

import java.io.Serializable;

public class AnimeData implements Serializable {

    public static final long serialVersionUID = 8673672209445549702L;
    
	public AnimeData() {
		name = "none";
		sleep = 50;
		anime = new int[1];
	}

	String name;
	int sleep;
	int anime[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int[] getAnime() {
        return anime;
    }

    public void setAnime(int[] anime) {
        this.anime = anime;
    }
        
        
}
