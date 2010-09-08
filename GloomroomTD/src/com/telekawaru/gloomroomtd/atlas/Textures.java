package com.telekawaru.gloomroomtd.atlas;

import com.stickycoding.rokon.*;
import com.telekawaru.gloomroomtd.MainActivity;

public class Textures {
	public static TextureAtlas atlas;
	public static Texture guy, dpad, topTransUI, link, map01;

	public static void load() {
		if (MainActivity.mAct.isFinishing()) return;
		atlas = new TextureAtlas(5, 1024, 256);
		atlas.insert(link = new Texture("link.png", 9, 1));
		atlas.insert(dpad = new Texture("dpad.png"));
		atlas.insert(topTransUI = new Texture("topTransUI.png"));
		atlas.insert(map01 = new Texture("map01_tiles.png", 4, 1));
		atlas.complete();
	}
}
