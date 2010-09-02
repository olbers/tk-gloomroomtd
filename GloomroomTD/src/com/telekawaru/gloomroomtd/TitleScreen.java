package com.telekawaru.gloomroomtd;

import android.view.KeyEvent;
import com.stickycoding.rokon.*;
import com.stickycoding.rokon.background.FixedBackground;

public class TitleScreen extends Scene {
	public MainActivity mAct = MainActivity.mAct;

	public TitleScreen() {
		super(1, 250);
		if (!MainActivity.mAct.isFinishing()) TitleTextures.load();
	}

	public void exitGame() {
		this.clear();
		System.gc();
		MainActivity.mAct.finish();
	}

	@Override
	public void onGameLoop() {}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitGame();
			return true;
		}
		return false;
	}

	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		if (MainActivity.mAct.isFinishing()) return;
		GameWindow window;
		this.setWindow(window = new GameWindow(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT));
		float butStartY = 217f;
		float butSpacing = 30f;
		float texStartY = 227f;
		float butStartX = (this.getWindow().getWidth() - TitleTextures.menubutton.getWidth()) / 2;
		this.setBackground(new FixedBackground(TitleTextures.title));
		this.add(new GameSprite(butStartX, butStartY, TitleTextures.menubutton, true, "button1"));
		TextOut.write("Window Engine Test", window.halfWidth, texStartY, this.getLayer(0), true);
		this.add(new GameSprite(butStartX, butStartY + (TitleTextures.menubutton.getHeight() + butSpacing), TitleTextures.menubutton, true, "button2"));
		TextOut.write("Physics Engine Test", window.halfWidth, texStartY + (TitleTextures.menubutton.getHeight() + butSpacing), this.getLayer(0), true);
		this.add(new GameSprite(butStartX, butStartY + ((TitleTextures.menubutton.getHeight() + butSpacing) * 2), TitleTextures.menubutton, true, "button3"));
		TextOut.write("Exit", window.halfWidth, texStartY + ((TitleTextures.menubutton.getHeight() + butSpacing) * 2), this.getLayer(0), true);
	}

	@Override
	public void onResume() {}

	@Override
	public void onTouchDown(Drawable object, float x, float y, int action, int pointerCount, int pointerId) {
		String tName = new String();
		try {
			tName = object.getName();
		} catch (Exception e) {}
		if (tName == "button1") {
			this.clear();
			this.mAct.changeScene(new GameScene());
		} else if (tName == "button2") {
			this.clear();
			this.mAct.changeScene(new PhysScene());
		} else if (tName == "button3") this.exitGame();
	}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {}
}
