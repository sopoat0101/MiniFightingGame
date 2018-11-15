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

		mpack = new TextureAtlas("../core/assets/Actor/Nox/mirror/Nox.pack");
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
		
		hitbox = new Hitbox[2];
		hitbox[0] = new Hitbox("../core/assets/Actor/Nox/hitbox/outlinebody.png", POX, POY);
		hitbox[1] = new Hitbox("../core/assets/Actor/Nox/hitbox/hitbody.png", POX, POY);
		

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
		
		hitbox[0].draw(batch);
		hitbox[1].draw(batch);

	}

	@Override
	protected void update(float dt) {

		handle();

		SActor.setPosition(POX, POY);
		MSActor.setPosition(POX, POY);
		//set Hit box Position
		
		if(!mirror) {
			hitbox[0].setPosition(POX+SActor.getWidth()/4 + 20, POY);
			hitbox[1].setPosition(POX+SActor.getWidth()/4 + 20, POY);
			
		}else if(mirror) {
			hitbox[0].setPosition(POX+SActor.getWidth()/4 + 20, POY);
			hitbox[1].setPosition(POX+SActor.getWidth()/4 + 50, POY);
		}
		
		DELAY -= dt;
		if (DELAY <= 0) {
			DELAY = 0;
		}

		if (STATUS == STAND) {
			stand();
		}
		if (STATUS == KNEEL) {
			kneel();
		}
		if (STATUS == JUMP) {
			jump();
		}

		if (DELAY <= 0) {
			action(cmdlog);
		}

		//Update hitbox Status
		
		if(hitbox[1].hitwith(Anotherplayer.hitbox[1]) && STATUS == STAND) {
			if (mirror) {
				if (POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.WORLD_WIDTH
						&& POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.camera.position.x
								+ PlayingState.WIDTH / 2) {
					POX += (movespeed) * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 4 > 0
						&& POX + SActor.getWidth() / 4 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
					POX -= (movespeed) * Gdx.graphics.getDeltaTime();
				}
			} else {
				POX += 0;
			}
		}else if(hitbox[0].hitwith(Anotherplayer.hitbox[0]) && STATUS == JUMP && Anotherplayer.STATUS == JUMP) {
			if (mirror) {
				if (POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.WORLD_WIDTH
						&& POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.camera.position.x
								+ PlayingState.WIDTH / 2) {
					POX += (450) * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 4 > 0
						&& POX + SActor.getWidth() / 4 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
					POX -= (450) * Gdx.graphics.getDeltaTime();
				}
			} else {
				POX += 0;
			}
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
			DELAY = 0.1f;
		}
		if (InputManager.keyIspressed(BN_FRONT)) {
			cmdlog += "F";
			DELAY = 0.1f;
		}
		if (InputManager.keyIspressed(BN_BACK)) {
			cmdlog += "B";
			DELAY = 0.1f;
		}
		if (InputManager.keyIspressed(BN_PUNCH)) {
			cmdlog += "P";
			DELAY = 0.1f;
		}
		if (InputManager.keyIspressed(BN_KICK)) {
			cmdlog += "K";
			DELAY = 0.1f;
		}

	}

	@Override
	protected void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void action(String combo) {

		if (combo.equals("C") && STATUS == STAND) {
			STATUS = KNEEL;
			cmdlog = "";
			animationtime = 0;
		}
		if (combo.equals("J") && STATUS != JUMP) {
			STATUS = JUMP;
			cmdlog = "";
			animationtime = 0.5f;
			AIRDELAY = 0.5f;
		}
		if (combo.equals("FJ") && STATUS != JUMP) {
			STATUS = JUMP;
			cmdlog = "";
			animationtime = 0.5f;
			AIRDELAY = 0.5f;
			JUMPTYPE = 1;
		}
		if (combo.equals("BJ") && STATUS != JUMP) {
			STATUS = JUMP;
			cmdlog = "";
			animationtime = 0.5f;
			AIRDELAY = 0.5f;
			JUMPTYPE = 2;
		}

//		System.out.println(cmdlog);
		cmdlog = "";

	}

	@Override
	protected void stand() {

//		System.out.println(animationtime);
		// Punch
		if (InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick)) {

			isPunch = true;
			// Kick
		} else if (InputManager.keyIspressed(BN_KICK) && (!isKick && !isPunch)) {

			isKick = true;
			// Move Front
		} else if (InputManager.keyIsdown(BN_FRONT) && (!isPunch && !isKick)) {
			if (animationtime <= 0) {
				animationtime = 0.1f;
			}
			animationType = LOOP;
			runframe(2, 5);
			if (mirror) {
				if (POX > 0) {
					POX -= movespeed * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() < PlayingState.WORLD_WIDTH) {
					POX += movespeed * Gdx.graphics.getDeltaTime();
				}
			}
			// Move Back
		} else if (InputManager.keyIsdown(BN_BACK) && (!isPunch && !isKick)) {
			if (animationtime <= 0) {
				animationtime = 0.1f;
			}
			animationType = LOOP;
			runframe(5, 8);
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
		} else {
			if (isPunch || isKick) {
				if (animationtime <= 0) {
					animationtime = 0.05f;
				}
				animationType = ONEWAY;
				if (isPunch) {
					runframe(14, 16);
				} else if (isKick) {
					runframe(17, 19);
				}
			} else {
				if (animationtime <= 0) {
					animationtime = 0.3f;
				}
				animationType = LOOP;
				runframe(0, 1);
			}
		}

	}

	@Override
	protected void jump() {

		AIRDELAY -= Gdx.graphics.getDeltaTime();

		if (AIRDELAY <= 0) {
			AIRDELAY = 0;
		}

		if (InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick)) {

			isPunch = true;
			animationtime = 0.5f;

		} else if (InputManager.keyIspressed(BN_KICK) && (!isPunch && !isKick)) {

			isKick = true;
			animationtime = 0.5f;

		} else {
			if (isPunch || isKick) {
				animationType = ONEWAY;
				if (isPunch) {
					runframe(24, 24);
				} else if (isKick) {
					runframe(25, 25);
				}
			} else {
				animationType = ONEWAY;
				runframe(12, 13);
			}
		}

		if (AIRDELAY > 0) {
			POY += 1000 * Gdx.graphics.getDeltaTime();
		}
		if (AIRDELAY <= 0) {
			POY -= 850 * Gdx.graphics.getDeltaTime();
		}
		if (JUMPTYPE == 1) {
			if (mirror) {
				if (POX > 0) {
					POX -= 450 * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() < PlayingState.WORLD_WIDTH) {
					POX += 450 * Gdx.graphics.getDeltaTime();
				}
			}
		}
		if (JUMPTYPE == 2) {
			if (mirror) {
				if (POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.WORLD_WIDTH
						&& POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.camera.position.x
								+ PlayingState.WIDTH / 2) {
					POX += 450 * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 4 > 0
						&& POX + SActor.getWidth() / 4 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
					POX -= 450 * Gdx.graphics.getDeltaTime();
				}
			} else {
				POX += 0;
			}
		}
		if (POY <= PlayingState.GROUND) {
			POY = PlayingState.GROUND;
			isPunch = false;
			isKick = false;
			STATUS = STAND;
			JUMPTYPE = 0;
			cmdlog = "";
		}

	}

	@Override
	protected void kneel() {

		if(InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick)) {
			
			isPunch = true;
			
		}
		else if(InputManager.keyIspressed(BN_KICK) && (!isPunch && !isKick)) {
			
			isKick = true;
			
		}
		else {
			if (isPunch || isKick) {
				if (animationtime <= 0) {
					animationtime = 0.1f;
				}
				animationType = ONEWAY;
				if (isPunch) {
					runframe(20, 22);
				} else if (isKick) {
					runframe(23, 23);
				}
			} else {
				if (animationtime <= 0) {
					animationtime = 0.05f;
				}
				animationType = ONEWAY;
				runframe(9, 11);
			}
		}

		if (!InputManager.keyIsdown(BN_KNEEL)) {
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

		if (animationtime <= 0) {
			NOWframe++;
		}
		if (NOWframe < st) {
			NOWframe = st;
		}
		if (NOWframe > en && animationType == LOOP) {
			NOWframe = st;
		}
		if (NOWframe > en && (animationType == ONEWAY || animationType == AUTO)) {
			NOWframe = en;
			isPunch = false;
			isKick = false;
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
