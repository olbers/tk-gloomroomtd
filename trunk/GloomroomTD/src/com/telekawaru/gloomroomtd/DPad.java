package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.Sprite;

public class DPad extends Sprite {

	private float halfHeight, halfWidth, centerX, centerY;
	public static float X_PCT;
	public static float Y_PCT;

	public DPad() {
		super(10, MainActivity.GAME_HEIGHT - Textures.dpad.getHeight() - 10, Textures.dpad.getWidth(), Textures.dpad.getHeight());
		this.halfHeight = this.getHeight() / 2;
		this.halfWidth = this.getWidth() / 2;
		this.centerX = this.x + this.halfWidth;
		this.centerY = this.y + this.halfHeight;
		this.setTexture(Textures.dpad);
		this.setName("dpad");
		this.setTouchable();
	}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {
		X_PCT = (x - this.centerX) / this.halfWidth;
		Y_PCT = (y - this.centerY) / this.halfHeight;
		GameScene.isMoving = true;
	}

	@Override
	public void onTouchMove(float x, float y, int action, int pointerCount, int pointerId) {
		X_PCT = (x - this.centerX) / this.halfWidth;
		Y_PCT = (y - this.centerY) / this.halfHeight;
		GameScene.isMoving = true;
	}

	@Override
	public void onTouchUp(float x, float y, int action, int pointerCount, int pointerId) {
		X_PCT = 0;
		Y_PCT = 0;
		GameScene.isMoving = false;
	}
}
