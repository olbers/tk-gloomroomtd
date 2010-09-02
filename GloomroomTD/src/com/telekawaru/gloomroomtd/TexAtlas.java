package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.TextureAtlas;

public class TexAtlas extends TextureAtlas {

	public boolean isComplete = false;

	public TexAtlas() {
		super();
	}

	@Override
	public void complete() {
		super.complete();
		this.isComplete = true;
	}
}
