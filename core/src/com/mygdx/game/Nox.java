package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

		damage = new Sprite(new Texture(Gdx.files.internal("../core/assets/Actor/Nox/hitbox/NoxDam.png")));
		Mdamage = new Sprite(new Texture(Gdx.files.internal("../core/assets/Actor/Nox/hitbox/MNoxDam.png")));
		guard = new Sprite(new Texture(Gdx.files.internal("../core/assets/Actor/Nox/hitbox/NoxGu.png")));
		
		damage.setAlpha(0f);
		Mdamage.setAlpha(0f);
		guard.setAlpha(0f);
		
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
		
		hitbox = new Hitbox[6];
		hitbox[0] = new Hitbox("../core/assets/Actor/Nox/hitbox/outlinebody.png", POX, POY);// Outline Body
		hitbox[1] = new Hitbox("../core/assets/Actor/Nox/hitbox/hitbody.png", POX, POY);// Hit all Body
		hitbox[2] = new Hitbox("../core/assets/Actor/Nox/hitbox/topbody.png", POX, POY);// Hit Top Body
		hitbox[3] = new Hitbox("../core/assets/Actor/Nox/hitbox/downbody.png", POX, POY);// Hit Down BOdy
		hitbox[4] = new Hitbox("../core/assets/Actor/Nox/hitbox/stpunch.png", POX, POY);// St punch
		hitbox[5] = new Hitbox("../core/assets/Actor/Nox/hitbox/stpunch.png", POX, POY);// St kick

	}

	@Override
	protected void draw(SpriteBatch batch) {

		if (mirror == false) {
			SActor.setAlpha(1f);
			MSActor.setAlpha(0f);
//			Mdamage.setAlpha(0f);
//			damage.setAlpha(0f);

		} else if (mirror) {
			MSActor.setAlpha(1f);
			SActor.setAlpha(0f);
//			Mdamage.setAlpha(0f);
//			damage.setAlpha(0f);

		}

		SActor.draw(batch);
		MSActor.draw(batch);
		guard.draw(batch);
		
		damage.draw(batch);
		Mdamage.draw(batch);
		
		for(Hitbox item: hitbox) {
			item.draw(batch);
			item.setAlpha(0f);
		}
	}

	@Override
	protected void update(float dt) {

		handle();

		SActor.setPosition(POX, POY);
		MSActor.setPosition(POX, POY);
		// set Hit box Position
		
		damage.setPosition(hitbox[2].getPoX() + hitbox[2].getWidth(), POY);
		Mdamage.setPosition(hitbox[2].getPoX() - 50, POY);
		
		if (!mirror) {
			
			hitbox[0].setPosition(POX + SActor.getWidth() / 4 + 20, POY);
			hitbox[1].setPosition(POX + SActor.getWidth() / 4 + 20, POY);
			hitbox[2].setPosition(POX + SActor.getWidth() / 4 + 20, POY + hitbox[2].getHeight());
			hitbox[3].setPosition(POX + SActor.getWidth() / 4 + 20, POY);
			hitbox[4].setPosition(POX + SActor.getWidth() / 2 + 20, POY + hitbox[2].getHeight() + 20);
			hitbox[5].setPosition(POX + SActor.getWidth() / 2 + 20, POY + hitbox[2].getHeight() - 60);
			
			guard.setPosition(hitbox[0].getPoX() + hitbox[0].getWidth(), POY);
			
		} else if (mirror) {
			
			hitbox[0].setPosition(POX + SActor.getWidth() / 4 - 20, POY);
			hitbox[1].setPosition(POX + SActor.getWidth() / 4 + 50, POY);
			hitbox[2].setPosition(POX + SActor.getWidth() / 4 + 50, POY + hitbox[2].getHeight());
			hitbox[3].setPosition(POX + SActor.getWidth() / 4 + 50, POY);
			hitbox[4].setPosition(POX + SActor.getWidth() / 4 + 50 - hitbox[4].getWidth(),
					POY + hitbox[2].getHeight() + 20);
			hitbox[5].setPosition(POX + SActor.getWidth() / 4 + 50 - hitbox[4].getWidth(),
					POY + hitbox[2].getHeight() - 60);
			
			guard.setPosition(hitbox[0].getPoX() - 5, POY);
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
		if (STATUS == HIT) {
			hit();
		}
		if (STATUS == HITING) {
			hiting();
		}
		if (STATUS == GUARD) {
			guard();
		}

		if (DELAY <= 0) {
			action(cmdlog);
		}

		hitboxchecking();
		
		if(HP <= 0) {
			HP = 0;
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

		if (InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick)) {

			isPunch = true;

		} else if (InputManager.keyIspressed(BN_KICK) && (!isPunch && !isKick)) {

			isKick = true;

		} else {
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
	protected void hiting() {

		if (Anotherplayer.DELAY <= 0) {
			STATUS = beforeSTATUS;
			isPunch = false;
			isKick = false;
		}

	}

	@Override
	protected void hit() {
		
		if (HITType == TOPHIT) {
			runframe(26, 26);
			
			damage.setPosition(hitbox[2].getPoX() + hitbox[2].getWidth(), hitPOY);
			Mdamage.setPosition(hitbox[2].getPoX() - 50, hitPOY);
			
			if(!mirror) {
				damage.setAlpha(1f);
			}else if(mirror) {
				Mdamage.setAlpha(1f);
			}
			
		} else if (HITType == DOWNHIT) {
			runframe(27, 27);
			
			damage.setPosition(hitbox[2].getPoX() + hitbox[2].getWidth(), hitbox[5].getPoY());
			Mdamage.setPosition(hitbox[2].getPoX() - 50, hitbox[5].getPoY());
			
			if(!mirror) {
				damage.setAlpha(1f);
			}else if(mirror) {
				Mdamage.setAlpha(1f);
			}
			
		}

		if (DELAY >= 0.2f) {
			knockback(400);
		}

		if (DELAY <= 0) {
			HP -= Anotherplayer.ATK;
			damage.setAlpha(0f);
			Mdamage.setAlpha(0f);
			STATUS = beforeSTATUS;
			AIRDELAY = 0;
		}

	}

	@Override
	protected void guard() {

		guard.setAlpha(1f);
		
		if (HITType == TOPGUARD) {
			runframe(28, 28);
			
		} else if (HITType == DOWNGUARD) {
			runframe(29, 29);
			
		}

		if (hitbox[4].hitwith(Anotherplayer.hitbox[0]) && (Anotherplayer.isPunch || Anotherplayer.isKick)) {
			knockback(400);
		}

		if (DELAY <= 0) {
			
			guard.setAlpha(0f);
			
			if (HITType == TOPGUARD) {
				STATUS = STAND;
			} else if (HITType == DOWNGUARD) {
				STATUS = KNEEL;
			}
		}
	}

	@Override
	protected void knockback(float movespeed) {
		if (mirror) {
			if (POX + SActor.getWidth() - SActor.getWidth() / 4 < PlayingState.WORLD_WIDTH && POX + SActor.getWidth()
					- SActor.getWidth() / 4 < PlayingState.camera.position.x + PlayingState.WIDTH / 2) {
				POX += movespeed * Gdx.graphics.getDeltaTime();
			} else {
				Anotherplayer.POX -= (movespeed + 400) * Gdx.graphics.getDeltaTime();
			}
		} else if (!mirror) {
			if (POX + SActor.getWidth() / 4 > 0
					&& POX + SActor.getWidth() / 4 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
				POX -= movespeed * Gdx.graphics.getDeltaTime();
			} else {
				Anotherplayer.POX += (movespeed + 400) * Gdx.graphics.getDeltaTime();
			}
		} else {
			POX += 0;
		}
		System.out.println(POX);
	}

	@Override
	protected void hitboxchecking() {
		// Update hit box Status

		if (hitbox[1].hitwith(Anotherplayer.hitbox[1]) && STATUS == STAND) {
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
		} else if (hitbox[1].hitwith(Anotherplayer.hitbox[1]) && STATUS == JUMP && Anotherplayer.STATUS == JUMP) {
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
		////////////////////////
		if ((Anotherplayer.isPunch || Anotherplayer.isKick) && InputManager.keyIsdown(BN_BACK)
				&& Math.abs(POX - Anotherplayer.POX) < SActor.getWidth() && Anotherplayer.STATUS != HITING
				&& (STATUS == STAND || STATUS == KNEEL)
				) {

			if (STATUS == STAND) {
				HITType = TOPGUARD;
			} else if (STATUS == KNEEL) {
				HITType = DOWNGUARD;
			}
			STATUS = GUARD;
			DELAY = 0.3f;
		}

		else if (hitbox[4].hitwith(Anotherplayer.hitbox[2]) && isPunch && NOWframe == 16
				&& Anotherplayer.STATUS != KNEEL && Anotherplayer.STATUS != GUARD) {

			Anotherplayer.HITType = Anotherplayer.TOPHIT;

			if (STATUS != HITING) {
				beforeSTATUS = STATUS;
				DELAY = 0.3f;
			}
			ATK = 20;
			STATUS = HITING;

			if (Anotherplayer.STATUS != HIT) {
				Anotherplayer.beforeSTATUS = Anotherplayer.STATUS;
				Anotherplayer.DELAY = 0.3f;
			}
			Anotherplayer.STATUS = Anotherplayer.HIT;
			Anotherplayer.hitPOY = hitbox[4].getPoY();

		}else if (hitbox[5].hitwith(Anotherplayer.hitbox[3]) && isKick && NOWframe == 19
				&& Anotherplayer.STATUS != GUARD) {

			Anotherplayer.HITType = Anotherplayer.DOWNHIT;

			if (STATUS != HITING) {
				beforeSTATUS = STATUS;
				DELAY = 0.3f;
			}
			ATK = 20;
			STATUS = HITING;

			if (Anotherplayer.STATUS != HIT) {
				Anotherplayer.beforeSTATUS = Anotherplayer.STATUS;
				Anotherplayer.DELAY = 0.3f;
			}
			Anotherplayer.STATUS = Anotherplayer.HIT;
			Anotherplayer.hitPOY = hitbox[5].getPoY();

		}

		System.out.println(isPunch);
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