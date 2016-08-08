package dragon3.edit;

import javax.inject.Inject;

import dagger.ObjectGraph;
import dragon3.data.StageData;
import dragon3.stage.StageBack;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;

@SuppressWarnings("serial")
public class StageEditor extends EditPanel<StageData> implements EditListener<StageData> {


	public static void main(String[] args) {
		ObjectGraph objectGraph = ObjectGraph.create(new EditorModule());

		new BeanEditor<>("StageEditor", "stages.txt", "data.json", objectGraph.get(StageEditor.class));
	}

	@Inject
	StageEditor() {
		super(StageData.class);

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setSlider(CENTER, "level", "レベル", 10);
		setEnumCombo(LEFT, "back", "背景色", StageBack.class);
		initCombo("back", StageBack.createMap());
	}
}
