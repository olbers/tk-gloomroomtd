package com.telekawaru.gloomroomtd;

import android.view.KeyEvent;
import com.stickycoding.rokon.*;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.OnAccelerometerChange;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class PhysScene extends Scene {

	public World pWorld;
	private PhysicalSprite[] wall, link;
	private FixtureDef linkFix;
	private static final float SCENE_WIDTH = 427f;
	private static final float SCENE_HEIGHT = 240f;
	private ContactListener contListen;
	private PhysicalSprite[] finger;

	private OnAccelerometerChange onAccCh = new OnAccelerometerChange() {

		@Override
		public void onAccelerometerChange(float arg0, float arg1, float arg2) {
			Vector2 newGrav = new Vector2(arg1 * 2, arg0 * 2);
			PhysScene.this.world.setGravity(newGrav);
		}

		@Override
		public void onShake(float arg0) {}
	};

	public PhysScene() {
		super(1, 100);
		if (!MainActivity.mAct.isFinishing()) {
			PhysTextures.load();
			this.setWindow(new GameWindow(0, 0, SCENE_WIDTH, SCENE_HEIGHT));
		}
	}

	public void addEye(float x, float y, boolean isDynamic) {
		FixtureDef circleDef = new FixtureDef();
		circleDef.density = 6.0f;
		circleDef.friction = 0.1f;
		circleDef.restitution = 3.0f;
		PhysicalSprite eyeBall = new PhysicalSprite(x - (PhysTextures.eyeball.getWidth() / 2), y - (PhysTextures.eyeball.getHeight() / 2), PhysTextures.eyeball.getWidth(), PhysTextures.eyeball.getHeight());
		eyeBall.setTexture(PhysTextures.eyeball);
		if (isDynamic == true) {
			eyeBall.createDynamicCircle(circleDef);
			eyeBall.getBody().setSleepingAllowed(false);
		} else
			eyeBall.createStaticCircle(circleDef);
		this.add(eyeBall);
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

	private void makeLinks(int linkCount) {
		this.link = new PhysicalSprite[linkCount];
		for (int i = 0; i < linkCount; i++) {
			float newLinkX = (10 + (i * (PhysTextures.link.getWidth() * 2)));
			float newLinkY = 10;
			if (newLinkX + PhysTextures.link.getWidth() > SCENE_WIDTH) {
				newLinkY += Math.floor(newLinkX / SCENE_WIDTH) * PhysTextures.link.getHeight() + 10;
				newLinkX = newLinkX % SCENE_WIDTH;
			}
			this.link[i] = new PhysicalSprite(newLinkX, newLinkY, PhysTextures.link.getWidth(), PhysTextures.link.getHeight());
			this.link[i].setTexture(PhysTextures.link);
			this.link[i].createDynamicBox(this.linkFix);
			this.link[i].getBody().setSleepingAllowed(false);
			this.add(this.link[i]);
		}
	}

	@Override
	public void onGameLoop() {
	//		getWorld().setGravity(new Vector2(Accelerometer.getY() * 2, Accelerometer.getX() * 2));
	//Accelerometer.startListening(onAccCh);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.clear();
			System.gc();
			MainActivity.mAct.changeScene(new TitleScreen());
			return true;
		}
		return false;
	}

	@Override
	public void onPause() {}

	@Override
	public void onReady() {
		if (MainActivity.mAct.isFinishing()) return;

		this.setWorld(new World(new Vector2(0, 0), true));
		this.finger = new PhysicalSprite[10];
		this.contListen = new ContactListener() {
			@Override
			public void beginContact(Contact arg0) {
				for (PhysicalSprite element : PhysScene.this.link)
					if ((arg0.getFixtureA().getBody() == element.body) || (arg0.getFixtureB().getBody() == element.body)) element.setTexture(PhysTextures.linkhurt);
			}

			@Override
			public void endContact(Contact arg0) {
				for (PhysicalSprite element : PhysScene.this.link)
					if ((arg0.getFixtureA().getBody() == element.body) || (arg0.getFixtureB().getBody() == element.body)) element.setTexture(PhysTextures.link);
			}
		};
		this.world.setContactListener(this.contListen);
		this.createWalls();
		this.linkFix = new FixtureDef();
		this.linkFix.density = 2.0f;
		this.linkFix.friction = .3f;
		this.linkFix.restitution = 0.25f;
		this.makeLinks(50);
		Accelerometer.startListening(this.onAccCh);
	}

	@Override
	public void onResume() {}

	@Override
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) {
		try {
			this.remove(this.finger[pointerId]);
		} catch (Exception e) {
			Debug.print("onTouchDown: " + e.getMessage());
		}
		try {
			FixtureDef circleDef = new FixtureDef();
			circleDef.density = 6.0f;
			circleDef.friction = 0.1f;
			circleDef.restitution = 3.0f;
			this.finger[pointerId] = new PhysicalSprite(x - (PhysTextures.eyeball.getWidth() / 2), y - (PhysTextures.eyeball.getHeight() / 2), PhysTextures.eyeball.getWidth(), PhysTextures.eyeball.getHeight());
			this.finger[pointerId].setTexture(PhysTextures.eyeball);
			this.finger[pointerId].createStaticCircle(circleDef);
			this.add(this.finger[pointerId]);
		} catch (Exception e) {
			Debug.print("onTouchDown: " + e.getMessage());
		}
	}

	@Override
	public void onTouchMove(float x, float y, int action, int pointerCount, int pointerId) {
		try {
			this.remove(this.finger[pointerId]);
		} catch (Exception exc) {
			Debug.print("onTouchMove: when trying to remove " + exc.getMessage());

		}
		try {
			FixtureDef circleDef = new FixtureDef();
			circleDef.density = 6.0f;
			circleDef.friction = 0.1f;
			circleDef.restitution = 3.0f;
			this.finger[pointerId] = new PhysicalSprite(x - (PhysTextures.eyeball.getWidth() / 2), y - (PhysTextures.eyeball.getHeight() / 2), PhysTextures.eyeball.getWidth(), PhysTextures.eyeball.getHeight());
			this.finger[pointerId].setTexture(PhysTextures.eyeball);
			this.finger[pointerId].createStaticCircle(circleDef);
			this.add(this.finger[pointerId]);
		} catch (Exception exc) {
			Debug.print("onTouchMove: when trying to add " + exc.getMessage());
		}
	}

	@Override
	public void onTouchUp(float x, float y, int action, int pointerCount, int pointerId) {
		try {
			if (this.finger[pointerId].isOnScreen()) {
				this.remove(this.finger[pointerId]);
				this.addEye(x, y, true);
			}
		} catch (Exception exc) {
			Debug.print("onTouchUp: when trying to remove " + exc.getMessage());
		}
	}

}
