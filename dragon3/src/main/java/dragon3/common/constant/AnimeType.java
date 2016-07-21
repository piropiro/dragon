package dragon3.common.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

public enum AnimeType {

	NONE("無"),
	SINGLE("単体"),
	ALL("全体"),
	SINGLE_ARROW("一人矢"),
	LASER_ARROW_2("貫通矢２"),
	LASER_ARROW_3("貫通矢３"),
	SOME_ARROW("複数矢"),
	ROTATE("回転"),
	;
	
	@Getter private String text;
	
	AnimeType(String text) {
		this.text = text;
	}
	
	public static Map<String, String> createMap() {
		return Arrays.asList(values()).stream()
				.collect(Collectors.toMap(AnimeType::name, 
						d -> d.text, 
						(u, v) -> v, 
						LinkedHashMap::new));
	}
}