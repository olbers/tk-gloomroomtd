package com.telekawaru.gloomroomtd.atlas;

import com.stickycoding.rokon.*;
import com.telekawaru.gloomroomtd.MainActivity;

public class PhysTextures {
	public static TextureAtlas atlas;
	public static Texture link, linkhurt, eyeball;

	public static void load() {
		if (MainActivity.mAct.isFinishing()) return;
		atlas = new TextureAtlas(3, 64, 128);
		atlas.insert(link = new Texture("phys_link_norm.png"));
		atlas.insert(linkhurt = new Texture("phys_link_yell.png"));
		atlas.insert(eyeball = new Texture("eyeball.png"));
		atlas.complete();
	}
}
