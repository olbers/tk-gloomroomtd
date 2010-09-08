package com.telekawaru.gloomroomtd.scene;

import android.view.KeyEvent;
import com.stickycoding.rokon.*;
import com.stickycoding.rokon.background.FixedBackground;
import com.telekawaru.gloomroomtd.MainActivity;
import com.telekawaru.gloomroomtd.atlas.TitleTextures;
import com.telekawaru.gloomroomtd.classes.GameSprite;
import com.telekawaru.gloomroomtd.classes.GameWindow;
import com.telekawaru.gloomroomtd.classes.TextOut;

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
	public void onKeyDown(int keyCode) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitGame();
			return;// true;
		}
		return;// false;
	}

	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		float scalePercent = 1;
		if (MainActivity.mAct.isFinishing()) return;
		GameWindow window;
		this.setWindow(window = new GameWindow(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT));
		scalePercent = window.getWidth() / TitleTextures.title.getWidth();
		float butStartY = window.getHeight() * 0.42f;
		float butSpacing = 30f * scalePercent;
		float texStartY = butStartY + 10;
		float butStartX = (window.getWidth() - TitleTextures.menubutton.getWidth() * scalePercent) / 2;
		this.setBackground(new FixedBackground(TitleTextures.title));
		this.add(new GameSprite(butStartX, butStartY, TitleTextures.menubutton.getWidth() * scalePercent, TitleTextures.menubutton.getHeight() * scalePercent, TitleTextures.menubutton, true, "GameScene"));
		TextOut.write("Window Engine Test", window.halfWidth, texStartY, this.getLayer(0), true);
		this.add(new GameSprite(butStartX, butStartY + (TitleTextures.menubutton.getHeight() * scalePercent + butSpacing), TitleTextures.menubutton.getWidth() * scalePercent, TitleTextures.menubutton.getHeight() * scalePercent, TitleTextures.menubutton, true, "PhysScene"));
		TextOut.write("Phys Engine Test", window.halfWidth, texStartY + (TitleTextures.menubutton.getHeight() + butSpacing), this.getLayer(0), true);
		this.add(new GameSprite(butStartX, butStartY + ((TitleTextures.menubutton.getHeight() * scalePercent + butSpacing) * 2), TitleTextures.menubutton.getWidth() * scalePercent, TitleTextures.menubutton.getHeight() * scalePercent, TitleTextures.menubutton, true, "PhysScene2"));
		TextOut.write("Phys Engine Test 2", window.halfWidth, texStartY + ((TitleTextures.menubutton.getHeight() + butSpacing) * 2), this.getLayer(0), true);
		this.add(new GameSprite(butStartX, butStartY + ((TitleTextures.menubutton.getHeight() * scalePercent + butSpacing) * 3), TitleTextures.menubutton.getWidth() * scalePercent, TitleTextures.menubutton.getHeight() * scalePercent, TitleTextures.menubutton, true, "ExitGame"));
		TextOut.write("Exit", window.halfWidth, texStartY + ((TitleTextures.menubutton.getHeight() + butSpacing) * 3), this.getLayer(0), true);
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
			this.mAct.changeScene(new GameScene());
		} else if (tName == "PhysScene") {
			this.clear();
			this.mAct.changeScene(new PhysScene());
		} else if (tName == "PhysScene2") {
			this.clear();
			this.mAct.changeScene(new PhysScene2());
		} else if (tName == "ExitGame") this.exitGame();
	}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {}

	@Override
	public void onKeyUp(int keyCode) {}
}
