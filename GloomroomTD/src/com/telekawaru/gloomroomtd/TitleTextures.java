package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.TextureAtlas;
import com.stickycoding.rokon.Texture;

public class TitleTextures {
	public static TextureAtlas atlas;
	public static Texture title, menubutton;

	public static void load() {
		atlas = new TextureAtlas();
		atlas.insert(title = new Texture("title.png"));
		atlas.insert(menubutton = new Texture("menuitem.png"));
		atlas.complete();
	}
}
