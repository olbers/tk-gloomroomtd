package com.telekawaru.gloomroomtd.scene;

import android.view.KeyEvent;
import com.stickycoding.rokon.Drawable;
import com.stickycoding.rokon.Scene;
import com.telekawaru.gloomroomtd.DockActivity;
import com.telekawaru.gloomroomtd.atlas.DockTextures;
import com.telekawaru.gloomroomtd.classes.GameSprite;
import com.telekawaru.gloomroomtd.classes.GameWindow;
import com.telekawaru.gloomroomtd.classes.TextOut;

public class DockScene extends Scene {
	public DockActivity dAct = DockActivity.dAct;
//	private static final int EXTRA_DOCK_STATE_UNDOCKED = 0;
	private static final int EXTRA_DOCK_STATE_CAR = 1;
	private static final int EXTRA_DOCK_STATE_DESK = 2;
	private static final int EXTRA_DOCK_STATE_UNKNOWN = 9;

	public DockScene() {
		super();
		if (!DockActivity.dAct.isFinishing()) DockTextures.load();
	}

	public void exitGame() {
		this.clear();
		System.gc();
		DockActivity.dAct.finish();
	}

	@Override
	public void onGameLoop() {}

	@Override
	public void onKeyDown(int keyCode) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitGame();
			return;
			//return true;
		}
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			return;
			//return true;
		}
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			return;
			//return true;
		}
		return;
		//return false;
	}

	@Override
	public void onKeyUp(int keyCode) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//			return true;
		}
//		return false;
	}
	
	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		if (DockActivity.dAct.isFinishing()) return;
		this.setWindow(window = new GameWindow(0, 0, DockActivity.GAME_WIDTH, DockActivity.GAME_HEIGHT));
		//this.setBackground(new FixedBackground(DockTextures.background));
		GameSprite tSprite = new GameSprite(0,0, DockTextures.background);
		add(tSprite);
		try {
			if (dAct.iDockMode == EXTRA_DOCK_STATE_DESK) {
				toastLong("I was started from desk dock!");
			} else if (dAct.iDockMode == EXTRA_DOCK_STATE_CAR) {
				toastLong("I was started from car dock!");
			} else if (dAct.iDockMode == EXTRA_DOCK_STATE_UNKNOWN) {
				toastLong("I was unable to get the dock mode.");
			}
		} catch (Exception e) {
			toastShort("Error getting dock mode");
		}
		TextOut.write("12:00pm", 240, 120, getLayer(0));
		//ClockTextOut.write("12:00pm", 240, 120, getLayer(0));
		//remove(tSprite);
	}

	@Override
	public void onResume() {}

	@Override
	public void onTouchDown(Drawable object, float x, float y, int action, int pointerCount, int pointerId) {
		String tName = new String();
		try {
			tName = object.getName();
		} catch (Exception e) {}
		if (tName == "GameScene") {
			this.clear();
			//this.dAct.changeScene(new PhysScene2());
		} else if (tName == "ExitGame") this.exitGame();
	}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {}
}
