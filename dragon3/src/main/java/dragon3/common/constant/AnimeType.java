package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum AnimeType {

	SINGLE("単体"),
	ALL("全体"),
	SINGLE_ARROW("一人矢"),
	LASER_ARROW_2("貫通矢２"),
	LASER_ARROW_3("貫通矢３"),
	SOME_ARROW("複数矢"),
	ROTATE("回転"),
	;
	
	private String text;
	
	AnimeType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (AnimeType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}
}