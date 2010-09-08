package com.telekawaru.gloomroomtd.atlas;

import com.stickycoding.rokon.TextureAtlas;
import com.stickycoding.rokon.Texture;
import com.telekawaru.gloomroomtd.MainActivity;

public class TitleTextures {
	public static TextureAtlas atlas;
	public static Texture title, menubutton;

	public static void load() {
		if (MainActivity.mAct.isFinishing()) return;
		atlas = new TextureAtlas();
		atlas.insert(title = new Texture("title.png"));
		atlas.insert(menubutton = new Texture("menuitem.png"));
		atlas.complete();
	}
}
