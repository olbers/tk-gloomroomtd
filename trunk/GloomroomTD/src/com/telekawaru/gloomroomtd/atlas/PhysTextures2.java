package com.telekawaru.gloomroomtd.atlas;

import com.stickycoding.rokon.*;
import com.telekawaru.gloomroomtd.MainActivity;

public class PhysTextures2 {
	public static TextureAtlas atlas;
	public static Texture dots, back;
	public static int RED = 8;
	public static int GREEN = 10;
	public static int BLUE = 12;

	public static void load() {
		if (MainActivity.mAct.isFinishing()) return;
		atlas = new TextureAtlas(3, 256, 256);
		atlas.insert(dots = new Texture("physdots.png", 7, 3));
		atlas.insert(back = new Texture("phys2wall.png"));
		atlas.complete();
	}
}
