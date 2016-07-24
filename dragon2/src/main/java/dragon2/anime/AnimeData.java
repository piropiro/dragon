package dragon2.anime;

import java.io.Serializable;

import dragon2.common.constant.AnimeType;
import lombok.Data;

@Data
public class AnimeData implements Serializable {

    public static final long serialVersionUID = 8673672209445549702L;

    // dragon3
	private String id = "none";
	private AnimeType type = AnimeType.NONE;
	private String image = "none.png";

	// dragon2
	private String name = "none";
	private int sleep = 50;
	int[] anime = new int[1];  
}
