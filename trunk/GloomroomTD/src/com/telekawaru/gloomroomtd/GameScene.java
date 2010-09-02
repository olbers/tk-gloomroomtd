package com.telekawaru.gloomroomtd;

import android.view.KeyEvent;
import com.stickycoding.rokon.*;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.OnAccelerometerChange;
import com.stickycoding.rokon.tileengine.*;

public class GameScene extends Scene {

	public Sprite touchedObj;
	private static GameSprite link;
	private DPad dpad;
	private static GameWindow window;
	private boolean isDragging;
	private float dragStartX, dragStartY;
	private static float BOB_VELX = 2;
	private static float BOB_VELY = 2;
	public static boolean isMoving;
	public static Layer statusLayer;
	public static boolean useAccelerometer = false;
	private int updateStatus = 0;
	public static TileMap tm;

	public static void moveBob(float xPct, float yPct) {
		link.move(xPct * BOB_VELX, yPct * BOB_VELY);
		if (tm.getTileIndex(link.getX(), link.getY()) == 1) link.move(-(xPct * BOB_VELX), -(yPct * BOB_VELY));
		if (tm.getTileIndex(link.getX() + link.getWidth(), link.getY()) == 1) link.move(-(xPct * BOB_VELX), -(yPct * BOB_VELY));
		if (tm.getTileIndex(link.getX() + link.getWidth(), link.getY() + link.getHeight()) == 1) link.move(-(xPct * BOB_VELX), -(yPct * BOB_VELY));
		if (tm.getTileIndex(link.getX(), link.getY() + link.getHeight()) == 1) link.move(-(xPct * BOB_VELX), -(yPct * BOB_VELY));
		window.centerOnSprite(link);
	}

	public OnAccelerometerChange onAccChange = new OnAccelerometerChange() {
		@Override
		public void onAccelerometerChange(float arg0, float arg1, float arg2) {}

		@Override
		public void onShake(float arg0) {}
	};

	public GameScene() {
		super(4);
		if (!MainActivity.mAct.isFinishing()) Textures.load();
	}

	@Override
	public void onGameLoop() {
		if (useAccelerometer == true) {
			float acX = Accelerometer.getY();
			float acY = Accelerometer.getX();
			if (Math.abs(acX) < 2) acX = 0;
			if (Math.abs(acY) < 2) acY = 0;
			if (acX < 0)
				acX += 2;
			else if (acX > 0) acX -= 2;
			if (acY < 0)
				acY += 2;
			else if (acY > 0) acY -= 2;
			moveBob(acX / 8, acY / 8);
			if (++this.updateStatus == 10) {
				statusLayer.clear();
				acX = Math.round((acX / 8) * 1000) / 10;
				acY = Math.round((acY / 8) * 1000) / 10;
				TextOut.write("X: " + Float.toString(acX) + "% Y: " + Float.toString(acY) + "%", 14, 4, statusLayer);
				this.updateStatus = 0;
			}
		}
		if (isMoving) moveBob(DPad.X_PCT, DPad.Y_PCT);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.clear();
			System.gc();
			MainActivity.mAct.changeScene(new TitleScreen());
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_CAMERA) if (useAccelerometer = !useAccelerometer) {
			Accelerometer.startListening(this.onAccChange);
			toastShort("Accelerometer On");
		} else {
			Accelerometer.stopListening();
			toastShort("Accelerometer Off");
		}
		/*
				link.setWidth(-link.getWidth());
				link.setX(link.getX() - link.getWidth());
		*/
		if (keyCode == KeyEvent.KEYCODE_MENU) if (window.getWidth() == MainActivity.GAME_WIDTH)
			window.zoomTo(.5f);
		else
			window.zoomTo(1);
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			window.zoomIn(.01f);
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			window.zoomOut(.01f);
			return true;
		}
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_VOLUME_UP))
			return true;
		else
			return false;
	}

	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		if (MainActivity.mAct.isFinishing()) return;

		window = new GameWindow(0, 0, 427, 240);
		this.setWindow(window);
		Layer uiLayer = new Layer(this, 128);
		statusLayer = new Layer(this, 500);
		statusLayer.ignoreWindow();
		uiLayer.ignoreWindow();
		this.dpad = new DPad();
		uiLayer.add(this.dpad);
		tm = new TileMap(18, 15);
		RectangularLayer tLayer = new RectangularLayer(this, (15 * 18), Textures.map01.getTileWidth(), Textures.map01.getTileHeight());
		tm.generateGraphics(tLayer);
		uiLayer.add(new GameSprite(0, 0, Textures.topTransUI));
		TextOut.write("Status - Accelerometer: Off", 14, 4, statusLayer);
		this.setLayer(0, tLayer);
		this.setLayer(2, uiLayer);
		this.setLayer(3, statusLayer);
		this.isDragging = false;

		this.add(1, link = new GameSprite(this.getWindow().getWidth() / 2, this.getWindow().getHeight() / 2, Textures.link, 3, true, "link"));
		if (useAccelerometer == true) Accelerometer.startListening(this.onAccChange);
	}

	@Override
	public void onResume() {}

	@Override
	public void onTouchDown(Drawable object, float x, float y, int action, int pointerCount, int pointerId) {
		if (object.getName() == "link") {
			link.animate(new int[] { 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 3 }, 250, 1, false);
			this.isDragging = false;
		} else if (object.getName() == "dpad")
			link.animate(new int[] { 4, 5, 6, 5, 4, 2, 1, 0, 1, 2 }, 50);
		else
			Debug.print("Touched " + object.getName());
	}

	@Override
	public void onTouchDownReal(float x, float y, int action, int pointerCount, int pointerId) {
		if (!isMoving) {
			this.dragStartX = x;
			this.dragStartY = y;
			this.isDragging = true;
		}
	}

	@Override
	public void onTouchMoveReal(float x, float y, int action, int pointerCount, int pointerId) {
		if (this.isDragging) {
			window.shift(x - this.dragStartX, y - this.dragStartY);
			this.dragStartX = x;
			this.dragStartY = y;
		}
	}

	@Override
	public void onTouchUp(Drawable object, float x, float y, int action, int pointerCount, int pointerId) {
		this.isDragging = false;
		if (isMoving == true) link.stopAnimation(3);
		isMoving = false;
	}

	@Override
	public void onTouchUp(float x, float y, int action, int pointerCount, int pointerId) {
		this.isDragging = false;
		if (isMoving == true) link.stopAnimation(3);
		isMoving = false;
	}
}
