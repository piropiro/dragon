package dragon3.edit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dragon3.edit.deploy.DeployEditor;
import mine.awt.ImageLoaderAWT;
import mine.paint.MineImageLoader;

@Module(injects={ BodyEditor.class, AnimeEditor.class, StageEditor.class, WazaEditor.class, DeployEditor.class} )
public class EditorModule {
	
	@Provides @Singleton
	MineImageLoader provideMineImageLoader() {
		return new ImageLoaderAWT();
	}
}