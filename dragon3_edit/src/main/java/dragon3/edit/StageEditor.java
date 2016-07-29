package dragon3.edit;

import dragon3.data.StageData;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;

public class StageEditor extends EditPanel<StageData> implements EditListener<StageData> {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new BeanEditor<>("StageEditor", "stages.txt", "data.json", new StageEditor());
	}

	StageEditor() {
		super(StageData.class);

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setSlider(CENTER, "level", "レベル", 10);
	}
}
