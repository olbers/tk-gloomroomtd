package com.telekawaru.gloomroomtd.scene;

import android.view.KeyEvent;
import com.stickycoding.rokon.*;
import com.stickycoding.rokon.background.FixedBackground;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.OnAccelerometerChange;
import com.telekawaru.gloomroomtd.MainActivity;
import com.telekawaru.gloomroomtd.atlas.PhysTextures2;
import com.telekawaru.gloomroomtd.classes.GameWindow;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class PhysScene2 extends Scene {

	public World pWorld;
	private PhysicalSprite[] wall;
	private FixtureDef[] dotFix;
	private static final float SCENE_WIDTH = 171f;
	private static final float SCENE_HEIGHT = 96f;

	private OnAccelerometerChange onAccCh = new OnAccelerometerChange() {

		@Override
		public void onAccelerometerChange(float arg0, float arg1, float arg2) {
			Vector2 newGrav = new Vector2(arg1 * 10, arg0 * 10);
			PhysScene2.this.world.setGravity(newGrav);
		}

		@Override
		public void onShake(float arg0) {}
	};

	public PhysScene2() {
		super(1, 500);
		if (!MainActivity.mAct.isFinishing()) {
			Debug.print("About to load textures");
			PhysTextures2.load();
			Debug.print("Loaded textures");
			this.setWindow(new GameWindow(0, 0, SCENE_WIDTH, SCENE_HEIGHT));
		}
	}

	public void createWalls() {
		this.wall = new PhysicalSprite[4];

		this.wall[0] = new PhysicalSprite(0, -1, SCENE_WIDTH, 1);
		this.wall[0].createStaticBox();
		this.add(0, this.wall[0]);

		this.wall[1] = new PhysicalSprite(SCENE_WIDTH, 0, 1, SCENE_HEIGHT);
		this.wall[1].createStaticBox();
		this.add(0, this.wall[1]);

		this.wall[2] = new PhysicalSprite(0, SCENE_HEIGHT, SCENE_WIDTH, 1);
		this.wall[2].createStaticBox();
		this.add(0, this.wall[2]);

		this.wall[3] = new PhysicalSprite(-1, 0, 1, SCENE_HEIGHT);
		this.wall[3].createStaticBox();
		this.add(0, this.wall[3]);
	}

	private void addDots(int dotCount, FixtureDef dFix, int dotColor) {
		for (int i = 0; i < dotCount; i++) {
			MassData dMD = new MassData();
			float sW = new Float(0);
			if (dotColor == PhysTextures2.RED) {
				dMD.mass = 500000f;
			} else if (dotColor == PhysTextures2.GREEN) {
				dMD.mass = 100f;
			} else if (dotColor == PhysTextures2.BLUE) {
				dMD.mass = 1f;
			}
			//sW = (float)Math.ceil(Math.random() * 8.0) + 4;
			sW = 6;
			float dX = new Float(Math.random() * (SCENE_WIDTH - (sW + 2)));
			float dY = new Float(Math.random() * (SCENE_HEIGHT - (sW + 2)));			
			PhysicalSprite dot = new PhysicalSprite(dX + 1, dY + 1, sW, sW);
			dot.setTextureTile(PhysTextures2.dots, dotColor);
			dot.createDynamicCircle(dFix);
			dot.getBody().setMassData(dMD);
			this.add(dot);
		}
	}

	@Override
	public void onGameLoop() {
	}

	@Override
	public void onKeyDown(int keyCode) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.clear();
			System.gc();
			MainActivity.mAct.changeScene(new TitleScreen());
			return;// true;
		}
		return;// false;
	}

	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		if (MainActivity.mAct.isFinishing()) return;

		this.setWorld(new World(new Vector2(0, 0), true));
		this.setBackground(new FixedBackground(PhysTextures2.back));
		this.createWalls();
		this.dotFix = new FixtureDef[3];
		this.dotFix[0] = new FixtureDef();
		this.dotFix[0].density = 500000.0f;
		this.dotFix[0].friction = .0f;
		this.dotFix[0].restitution = 0.0f;
		this.dotFix[1] = new FixtureDef();
		this.dotFix[1].density = 100.0f;
		this.dotFix[1].friction = .0f;
		this.dotFix[1].restitution = 0.0f;
		this.dotFix[2] = new FixtureDef();
		this.dotFix[2].density = 1.0f;
		this.dotFix[2].friction = .0f;
		this.dotFix[2].restitution = 0.0f;
		this.addDots((int)(Math.random() * 20) + 50, this.dotFix[0], PhysTextures2.RED);
		this.addDots((int)(Math.random() * 20) + 50, this.dotFix[1], PhysTextures2.GREEN);
		this.addDots((int)(Math.random() * 20) + 50, this.dotFix[2], PhysTextures2.BLUE);
		Accelerometer.startListening(this.onAccCh);
	}

	@Override
	public void onResume() {}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {
	}

	@Override
	public void onTouchMove(float x, float y, int action, int pointerCount, int pointerId) {
	}

	@Override
	public void onTouchUp(float x, float y, int action, int pointerCount, int pointerId) {
	}

	@Override
	public void onKeyUp(int keyCode) {}

}
