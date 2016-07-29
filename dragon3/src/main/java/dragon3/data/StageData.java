/*
 * 作成日: 2004/03/07
 */
package dragon3.data;

/**
 * @author k-saito
 */
@lombok.Data
public class StageData implements Data {

	private String id = "none";
	private String name = "none";
	private int level;

	@Override
	public String toString(){
		return id + " - " + name;
	}
}
