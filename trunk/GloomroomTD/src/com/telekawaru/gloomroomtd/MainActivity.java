package com.telekawaru.gloomroomtd;

import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Accelerometer;
import com.telekawaru.gloomroomtd.scene.TitleScreen;

public class MainActivity extends RokonActivity {
	public static final float GAME_WIDTH = 854f;
	public static final float GAME_HEIGHT = 480f;
//	public static final float GAME_WIDTH = 171f;
//	public static final float GAME_HEIGHT = 96f;

	public static MainActivity mAct;

	public void changeScene(Scene scene) {
		this.setScene(scene);
	}

	@Override
	public void onCreate() {
		debugMode();
		this.forceFullscreen();
		this.forceLandscape();
		this.setGameSize(GAME_WIDTH, GAME_HEIGHT);
		this.forceGameSize(GAME_WIDTH, GAME_HEIGHT);
		setDrawPriority(DrawPriority.PRIORITY_NORMAL);
		this.setGraphicsPath("textures/");
		mAct = this;
//		this.disableBack();
		this.createEngine();
	}

	@Override
	public void onDestroy() {
		Accelerometer.stopListening();
		super.onDestroy();
		System.exit(0);
	}
/*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (this.isFinishing()) return super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP) || (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN))
			return getScene().onKeyDown(keyCode, event);
		else
			return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (this.isFinishing()) return super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_VOLUME_UP))
			return getScene().onKeyUp(keyCode, event);
		else
			return super.onKeyUp(keyCode, event);
	}
*/
	@Override
	public void onLoadComplete() {
		//if (!this.isFinishing()) ;
		this.changeScene(new TitleScreen());
		//changeScene(new PhysScene());
	}

	public void onReady() {

	}

	@Override
	public void onResume() {
		super.onResume();
	}
}