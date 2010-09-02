package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.*;

public class GameSprite extends Sprite {
	public boolean isMoving = false;
	public float xVel, yVel;

	public GameSprite(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public GameSprite(float x, float y, float width, float height, boolean isTouchable) {
		super(x, y, width, height);
		if (isTouchable == true) this.setTouchable();
	}

	public GameSprite(float x, float y, float width, float height, boolean isTouchable, String name) {
		super(x, y, width, height);
		if (isTouchable == true) this.setTouchable();
		this.setName(name);
	}

	public GameSprite(float x, float y, float width, float height, String name) {
		super(x, y, width, height);
		this.setName(name);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height);
		this.setTexture(texture);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, boolean isTouchable) {
		super(x, y, width, height);
		this.setTexture(texture);
		if (isTouchable == true) this.setTouchable();
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, boolean isTouchable, String name) {
		super(x, y, width, height);
		this.setTexture(texture);
		if (isTouchable == true) this.setTouchable();
		this.setName(name);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, int tileIndex) {
		super(x, y, width, height);
		this.setTextureTile(texture, tileIndex);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, int tileIndex, boolean isTouchable) {
		super(x, y, width, height);
		this.setTextureTile(texture, tileIndex);
		if (isTouchable == true) this.setTouchable();
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, int tileIndex, boolean isTouchable, String name) {
		super(x, y, width, height);
		this.setTextureTile(texture, tileIndex);
		if (isTouchable == true) this.setTouchable();
		this.setName(name);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, int tileIndex, String name) {
		super(x, y, width, height);
		this.setTextureTile(texture, tileIndex);
		this.setName(name);
	}

	public GameSprite(float x, float y, float width, float height, Texture texture, String name) {
		super(x, y, width, height);
		this.setTexture(texture);
		this.setName(name);
	}

	public GameSprite(float x, float y, Texture texture) {
		super(x, y, texture.getWidth(), texture.getHeight());
		this.setTexture(texture);
	}

	public GameSprite(float x, float y, Texture texture, boolean isTouchable) {
		super(x, y, texture.getWidth(), texture.getHeight());
		this.setTexture(texture);
		if (isTouchable == true) this.setTouchable();
	}

	public GameSprite(float x, float y, Texture texture, boolean isTouchable, String name) {
		super(x, y, texture.getWidth(), texture.getHeight());
		this.setTexture(texture);
		if (isTouchable == true) this.setTouchable();
		this.setName(name);
	}

	public GameSprite(float x, float y, Texture texture, int tileIndex) {
		super(x, y, texture.getTileWidth(), texture.getTileHeight());
		this.setTextureTile(texture, tileIndex);
	}

	public GameSprite(float x, float y, Texture texture, int tileIndex, boolean isTouchable) {
		super(x, y, texture.getTileWidth(), texture.getTileHeight());
		this.setTextureTile(texture, tileIndex);
		if (isTouchable == true) this.setTouchable();
	}

	public GameSprite(float x, float y, Texture texture, int tileIndex, boolean isTouchable, String name) {
		super(x, y, texture.getTileWidth(), texture.getTileHeight());
		this.setTextureTile(texture, tileIndex);
		if (isTouchable == true) this.setTouchable();
		this.setName(name);
	}

	public GameSprite(float x, float y, Texture texture, int tileIndex, String name) {
		super(x, y, texture.getTileWidth(), texture.getTileHeight());
		this.setTextureTile(texture, tileIndex);
		this.setName(name);
	}

	@Override
	public void move(float x, float y) {
		super.move(x, y);
		float nLeft = this.getX();
		float nTop = this.getY();
		float nRight = nLeft + this.getWidth();
		float nBottom = nTop + this.getHeight();
		if ((nRight > MainActivity.GAME_WIDTH) && (nLeft > 0)) nLeft -= nRight - MainActivity.GAME_WIDTH;
		if ((nBottom > MainActivity.GAME_HEIGHT) && (nTop > 0)) nTop -= nBottom - MainActivity.GAME_HEIGHT;
		if (nLeft < 0) nLeft = 0;
		if (nTop < 0) nTop = 0;
		if (this.getWidth() < 0) {
			if (nLeft < Math.abs(this.getWidth())) nLeft = Math.abs(this.getWidth());
			if (nLeft > MainActivity.GAME_WIDTH) nLeft = MainActivity.GAME_WIDTH;
		}
		this.setXY(nLeft, nTop);
	}
}
