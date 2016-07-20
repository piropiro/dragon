/*
 * 作成日: 2004/03/07
 */
package dragon3.bean;

import dragon3.common.constant.AnimeType;

/**
 * @author k-saito
 */
@lombok.Data
public class AnimeData implements Data {

	private String id = "none";
	private String name = "none";
	private AnimeType type = AnimeType.SINGLE;
	private String image = "0.png";
	private int sleep;

	@Override
	public String toString(){
		return id + " - " + name;
	}
}
