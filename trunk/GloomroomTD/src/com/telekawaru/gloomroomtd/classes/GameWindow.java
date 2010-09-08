package com.telekawaru.gloomroomtd.classes;

import com.stickycoding.rokon.*;
import com.telekawaru.gloomroomtd.MainActivity;

public class GameWindow extends Window {
	public float halfWidth;
	public float halfHeight;

	public GameWindow(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.halfWidth = width / 2;
		this.halfHeight = height / 2;
	}

	public void centerOnSprite(Sprite sprite) {
		this.safeCentre(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}

	public float centerX() {
		return this.getX() + this.getWidth() / 2;
	}

	public float centerY() {
		return this.getY() + this.getHeight() / 2;
	}

	public float getZoom() {
		return this.getWidth() / MainActivity.GAME_WIDTH;
	}

	public void moveTo(float x, float y) {
		this.safeCentre(x + this.getWidth() / 2, y + this.getHeight() / 2);
	}

	@Override
	public void onUpdate() {}

	public void safeCentre(float x, float y) {
		this.halfWidth = this.getWidth() / 2;
		this.halfHeight = this.getHeight() / 2;
		float nLeft = x - this.halfWidth;
		float nTop = y - this.halfHeight;
		float nRight = x + this.halfWidth;
		float nBottom = y + this.halfHeight;
		if ((nRight > MainActivity.GAME_WIDTH) && (nLeft > 0)) nLeft -= nRight - MainActivity.GAME_WIDTH;
		if ((nBottom > MainActivity.GAME_HEIGHT) && (nTop > 0)) nTop -= nBottom - MainActivity.GAME_HEIGHT;
		if (nLeft < 0) nLeft = 0;
		if (nTop < 0) nTop = 0;
		super.centre(nLeft + this.halfWidth, nTop + this.halfHeight);
	}

	public void safeMove(float xMove, float yMove) {

	}

	public void safeResize(float width) {
		if (width > MainActivity.GAME_WIDTH) width = MainActivity.GAME_WIDTH;
		super.resize(width);
		this.halfWidth = this.getWidth() / 2;
		this.halfHeight = this.getHeight() / 2;
	}

	public void shift(float x, float y) {
		this.safeCentre((super.x + this.getWidth() / 2) - x, (super.y + this.getHeight() / 2) - y);
	}

	public void zoomIn(float zoomAmount) {
		this.zoomTo(this.getZoom() - zoomAmount);
	}

	public void zoomOut(float zoomAmount) {
		this.zoomTo(this.getZoom() + zoomAmount);
	}

	public void zoomTo(float zoomPct) {
		float oX = this.centerX();
		float oY = this.centerY();
		this.safeResize(MainActivity.GAME_WIDTH * zoomPct);
		this.safeCentre(oX, oY);
	}
}
