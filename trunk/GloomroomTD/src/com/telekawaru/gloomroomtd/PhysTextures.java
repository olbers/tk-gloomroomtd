package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.*;

public class PhysTextures {
	public static TextureAtlas atlas;
	public static Texture link, linkhurt, eyeball;

	public static void load() {
		atlas = new TextureAtlas();
		atlas.insert(link = new Texture("phys_link_norm.png"));
		atlas.insert(linkhurt = new Texture("phys_link_yell.png"));
		atlas.insert(eyeball = new Texture("eyeball.png"));
		atlas.complete();
	}
}
