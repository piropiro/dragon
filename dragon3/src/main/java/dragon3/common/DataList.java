/*
 * 作成日: 2004/03/20
 */
package dragon3.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mine.MineException;
import mine.MineUtils;
import mine.io.BeanIO;
import dragon3.bean.Data;

/**
 * @author k-saito
 */
public class DataList<T extends Data> {

	private List<T> list;

	private Map<String, T> map;

	@SuppressWarnings("unchecked")
	public DataList(String baseDir, String[] fileList) {
		super();

		list = new ArrayList<T>();
		for (int i=0; i<fileList.length; i++) {
			String file = baseDir + fileList[i];
			try {
				MineUtils.addToList(list, (T[])BeanIO.read(file));
			} catch (MineException e) {
				throw new RuntimeException("Dataファイルのロードに失敗しました。fileName:[" + file + "]");
			}
		}

		map = new HashMap<String, T>();
		for (T data: list) {
			map.put(data.getId(), data);
		}
	}

	public List<T> getList() {
		return list;
	}

	public T getData(String id) {
		return map.get(id);
	}

	public Map<String, String> getIdAndName() {
		Map<String, String> idAndName = new LinkedHashMap<String, String>();

		for (int i=0; i<list.size(); i++) {
			Data data = (Data)list.get(i);
			idAndName.put(data.getId(), data.getName());
		}
		return idAndName;
	}
}
