package com.telekawaru.gloomroomtd.atlas;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;
import com.telekawaru.gloomroomtd.DockActivity;

public class DockTextures {
	public static TextureAtlas atlas;
	public static Texture background;

	public static void load() {
		if (DockActivity.dAct.isFinishing()) return;
		atlas = new TextureAtlas(1, 1024, 1024);
		atlas.insert(background = new Texture("car_dock_back.png"));
		atlas.complete();
	}
}
