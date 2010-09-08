package com.telekawaru.gloomroomtd;

import android.content.Intent;
import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;
import com.telekawaru.gloomroomtd.scene.DockScene;

public class DockActivity extends RokonActivity {
	public static final float GAME_WIDTH = 854f;
	public static final float GAME_HEIGHT = 480f;
	
	public Intent callingIntent;
	public Integer iDockMode;
	
	public static DockActivity dAct;
	
	public void changeScene(Scene scene) {
		this.setScene(scene);
	}
	
	@Override
	public void onCreate() {
		debugMode();
		callingIntent = super.getIntent();
		iDockMode = callingIntent.getIntExtra("EXTRA_DOCK_STATE", 9);
		this.forceFullscreen();
		this.forceLandscape();
		this.setGameSize(GAME_WIDTH, GAME_HEIGHT);
		this.forceGameSize(GAME_WIDTH, GAME_HEIGHT);
		setDrawPriority(DrawPriority.PRIORITY_VBO);
		this.setGraphicsPath("textures/");
		dAct = this;
		//this.disableBack();
		this.createEngine();
	}

	@Override
	public void onDestroy() {
		System.exit(0);
	}
/*	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (this.isFinishing()) return super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP) || (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN))
			getScene().onKeyDown(keyCode);
		else
			return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (this.isFinishing()) return super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_VOLUME_UP))
			return getScene().onKeyUp(keyCode);
		else
			return super.onKeyUp(keyCode, event);
	}
*/

	@Override
	public void onLoadComplete() {
		if (!this.isFinishing()) this.changeScene(new DockScene());
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
