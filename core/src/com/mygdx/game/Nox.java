package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Nox extends Actor {

	protected Nox(int bn_front, int bn_back, int bn_jump, int bn_kneel, int bn_punch, int bn_kick, boolean mirror,
			int player) {
		super(bn_front, bn_back, bn_jump, bn_kneel, bn_punch, bn_kick, mirror, player);
	}

	@Override
	protected void init() {

		pack = new TextureAtlas("../core/assets/Actor/Nox/notmirror/Nox.pack");
		frame = pack.findRegion("00");
		SActor = new Sprite(frame);
		SActor.setPosition(PlayingState.WORLD_WIDTH / 5, PlayingState.GROUND);

		mpack = new TextureAtlas("../core/assets/Actor/Nox/notmirror/Nox.pack");
		mframe = mpack.findRegion("00");
		MSActor = new Sprite(mframe);
		MSActor.setPosition(PlayingState.WORLD_WIDTH - PlayingState.WORLD_WIDTH / 5 - MSActor.getWidth(),
				PlayingState.GROUND);

		if (mirror == false) {
			SActor.setAlpha(1f);
			MSActor.setAlpha(0f);

			POX = PlayingState.WORLD_WIDTH / 4;

		} else if (mirror) {
			MSActor.setAlpha(1f);
			SActor.setAlpha(0f);

			POX = PlayingState.WORLD_WIDTH - PlayingState.WORLD_WIDTH / 4 - MSActor.getWidth();

		}

		POY = PlayingState.GROUND;

	}

	@Override
	protected void draw(SpriteBatch batch) {

		if (mirror == false) {
			SActor.setAlpha(1f);
			MSActor.setAlpha(0f);

		} else if (mirror) {
			MSActor.setAlpha(1f);
			SActor.setAlpha(0f);

		}
		
		SActor.draw(batch);
		MSActor.draw(batch);

	}

	@Override
	protected void update(float dt) {

		handle();

		SActor.setPosition(POX, POY);
		MSActor.setPosition(POX, POY);
		
		DELAY -= dt;
		if(DELAY <= 0) {
			DELAY = 0;
		}
		
		if (STATUS == STAND) {
			stand();
		}
		if(STATUS == KNEEL) {
			kneel();
		}
		if(STATUS == JUMP) {
			jump();
		}
		
		if(DELAY <= 0) {
			action(cmdlog);
		}

	}

	@Override
	protected void handle() {

		if (InputManager.keyIspressed(BN_JUMP)) {
			cmdlog += "J";
			DELAY = 0.1f;
		}
		if (InputManager.keyIspressed(BN_KNEEL)) {
			cmdlog += "C";
			DELAY = 0.2f;
		}
		if (InputManager.keyIspressed(BN_FRONT)) {
			cmdlog += "F";
		}
		if (InputManager.keyIspressed(BN_BACK)) {
			cmdlog += "B";
		}
		if (InputManager.keyIspressed(BN_PUNCH)) {
			cmdlog += "P";
		}
		if (InputManager.keyIspressed(BN_KICK)) {
			cmdlog += "K";
		}

	}

	@Override
	protected void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void action(String combo) {
		
		if(combo.equals("C")) {
			STATUS = KNEEL;
			cmdlog = "";
			animationtime = 0;
		}
		if(combo.equals("J") && STATUS != JUMP) {
			STATUS = JUMP;
			cmdlog = "";
			animationtime = 0.5f;
		}
		
		cmdlog = "";

	}

	@Override
	protected void stand() {

		if(animationtime <= 0) {
			animationtime = 0.3f;
		}
		animationType = LOOP;
		
		if(InputManager.keyIsdown(BN_FRONT)) {
			if(animationtime <= 0) {
				animationtime = 0.1f;
			}
			runframe(2, 4);
			if (mirror) {
				if (POX > 0) {
					POX -= movespeed * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() < PlayingState.WORLD_WIDTH) {
					POX += movespeed * Gdx.graphics.getDeltaTime();
				}
			}
		}
		else if(InputManager.keyIsdown(BN_BACK)) {
			if(animationtime <= 0) {
				animationtime = 0.1f;
			}
			runframe(2, 4);
			if (mirror) {
				if (POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.WORLD_WIDTH
						&& POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.camera.position.x
								+ PlayingState.WIDTH / 2) {
					POX += movespeed * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 4 > 0
						&& POX + SActor.getWidth() / 4 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
					POX -= movespeed * Gdx.graphics.getDeltaTime();
				}
			} else {
				POX += 0;
			}
		}
		else {
			runframe(0, 1);
		}
		
	}

	@Override
	protected void jump() {
		
		animationType = ONEWAY;
		runframe(8, 9);
		
		if(animationtime > 0) {
			POY += 800 * Gdx.graphics.getDeltaTime();
		}
		if(animationtime <= 0) {
			POY -= 700 * Gdx.graphics.getDeltaTime();
		}
		if(POY <= PlayingState.GROUND) {
			POY = PlayingState.GROUND;
			STATUS = STAND;
		}

	}

	@Override
	protected void kneel() {
		
		if(animationtime <= 0) {
			animationtime = 0.05f;
		}
		animationType = ONEWAY;
		runframe(5, 7);
		
		if(!InputManager.keyIsdown(BN_KNEEL)) {
			STATUS = STAND;
		}

	}

	@Override
	protected void guard(int type) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void hit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void knockback(float movespeed, int type) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void runframe(int st, int en) {
		
		animationtime -= Gdx.graphics.getDeltaTime();
		
		if(animationtime <= 0) {
			NOWframe++;
		}
		if(NOWframe < st) {
			NOWframe = st;
		}
		if(NOWframe > en && animationType == LOOP) {
			NOWframe = st;
		}
		if(NOWframe > en && animationType == ONEWAY) {
			NOWframe = en;
		}
		if (mirror) {
			mframe = mpack.findRegion(String.format("%02d", NOWframe));
			MSActor.setRegion(mframe);
		}
		if (!mirror) {
			frame = pack.findRegion(String.format("%02d", NOWframe));
			SActor.setRegion(frame);
		}
		
	}

	@Override
	protected void setBN_FRONT(int bn) {

		this.BN_FRONT = bn;

	}

	@Override
	protected void setBN_BACK(int bn) {

		this.BN_BACK = bn;

	}

	@Override
	protected void setMIRROR(boolean b) {
		this.mirror = b;

	}

	@Override
	protected void setAnotherPlayer(Actor Player) {

		this.Anotherplayer = Player;

	}

}
