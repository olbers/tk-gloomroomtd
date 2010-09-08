package com.telekawaru.gloomroomtd.classes;

import com.stickycoding.rokon.TextureAtlas;

public class TexAtlas extends TextureAtlas {

	public boolean isComplete = false;

	public TexAtlas() {
		super();
	}

	public TexAtlas(int textureCount) {
		super(textureCount);
	}

	public TexAtlas(int textureCount, int width, int height) {
		super(textureCount, width, height);
	}

	@Override
	public void complete() {
		super.complete();
		this.isComplete = true;
	}
}
