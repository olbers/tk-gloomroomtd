package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.*;

public class Textures {
	public static TextureAtlas atlas;
	public static Texture guy, dpad, topTransUI, link, map01;

	public static void load() {
		atlas = new TextureAtlas(500);
		atlas.insert(link = new Texture("link.png", 9, 1));
		atlas.insert(dpad = new Texture("dpad.png"));
		atlas.insert(topTransUI = new Texture("topTransUI.png"));
		atlas.insert(map01 = new Texture("map01_tiles.png", 4, 1));
		atlas.complete();
	}
}
