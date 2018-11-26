package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Mato extends Actor {

	protected Mato(int bn_front, int bn_back, int bn_jump, int bn_kneel, int bn_punch, int bn_kick, boolean mirror,
			int player) {
		super(bn_front, bn_back, bn_jump, bn_kneel, bn_punch, bn_kick, mirror, player);
	}

	@Override
	protected void init() {

		pack = new TextureAtlas("Actor/Mato/notmirror/Mato.pack");
		frame = pack.findRegion("00");
		SActor = new Sprite(frame);

		damage = new Sprite(new Texture(Gdx.files.internal("Actor/Mato/hitbox/NoxDam.png")));
		Mdamage = new Sprite(new Texture(Gdx.files.internal("Actor/Mato/hitbox/MNoxDam.png")));
		guard = new Sprite(new Texture(Gdx.files.internal("Actor/Mato/hitbox/NoxGu.png")));

		damage.setAlpha(0f);
		Mdamage.setAlpha(0f);
		guard.setAlpha(0f);

		if (mirror == false) {

			POX = PlayingState.WORLD_WIDTH / 2 - PlayingState.WIDTH / 2 - 100;

		} else if (mirror) {

			POX = PlayingState.WORLD_WIDTH / 2 + PlayingState.WIDTH / 2 - 400;

		}

		POY = PlayingState.GROUND;

		hitbox = new Hitbox[10];
		hitbox[0] = new Hitbox("Actor/Mato/hitbox/outlinebody.png", POX, POY);// Outline Body
		hitbox[1] = new Hitbox("Actor/Mato/hitbox/hitbody.png", POX, POY);// Hit all Body
		hitbox[2] = new Hitbox("Actor/Mato/hitbox/topbody.png", POX, POY);// Hit Top Body
		hitbox[3] = new Hitbox("Actor/Mato/hitbox/downbody.png", POX, POY);// Hit Down BOdy
		hitbox[4] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// ST punch
		hitbox[5] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// ST kick
		hitbox[6] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// C punch
		hitbox[7] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// C kick
		hitbox[8] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// J Punch
		hitbox[9] = new Hitbox("Actor/Mato/hitbox/stpunch.png", POX, POY);// J Kick

		hitbox[4].setSize(150, 60);
		hitbox[5].setSize(190, 60);
		hitbox[8].setSize(170, 60);

		SActor.setPosition(POX, POY);

	}

	@Override
	protected void draw(SpriteBatch batch) {

		SActor.setAlpha(1f);
		SActor.setFlip(mirror, false);

		SActor.draw(batch);
		guard.draw(batch);

		damage.draw(batch);
		Mdamage.draw(batch);

		for (Hitbox item : hitbox) {
			item.draw(batch);
			item.setAlpha(0f);
		}

		hitbox[1].setAlpha(0f);

	}

	@Override
	protected void update(float dt) {
		if (STATUS != KNOCKOUT && STATUS != STOP) {
			handle();
		}

		SActor.setPosition(POX, POY);

		// set Hit box Position

		damage.setAlpha(0f);
		Mdamage.setAlpha(0f);

		if (!mirror) {

			hitbox[0].setPosition(POX + SActor.getWidth() / 4 + 35, POY);
			hitbox[1].setPosition(POX + SActor.getWidth() / 4 + 60, POY);
			hitbox[2].setPosition(POX + SActor.getWidth() / 4 + 60, POY + hitbox[2].getHeight());
			hitbox[3].setPosition(POX + SActor.getWidth() / 4 + 60, POY);
			hitbox[4].setPosition(POX + SActor.getWidth() / 2 + 30, POY + hitbox[2].getHeight() + 20);
			hitbox[5].setPosition(POX + SActor.getWidth() / 2 + 20, POY + hitbox[2].getHeight() - 100);
			hitbox[6].setPosition(POX + SActor.getWidth() / 2 + 40, POY + hitbox[2].getHeight() - 60);
			hitbox[7].setPosition(POX + SActor.getWidth() / 2 + 40, POY);
			hitbox[8].setPosition(POX + SActor.getWidth() / 2 + 30, POY + hitbox[2].getHeight());
			hitbox[9].setPosition(POX + SActor.getWidth() / 2 - 20, POY);

			guard.setPosition(hitbox[0].getPoX() + hitbox[0].getWidth(), POY);

		} else if (mirror) {

			hitbox[0].setPosition(POX + SActor.getWidth() / 4 + 35, POY);
			hitbox[1].setPosition(POX + SActor.getWidth() / 4 + hitbox[1].getWidth() + 40, POY);
			hitbox[2].setPosition(POX + SActor.getWidth() / 4 + hitbox[1].getWidth() + 40, POY + hitbox[2].getHeight());
			hitbox[3].setPosition(POX + SActor.getWidth() / 4 + hitbox[1].getWidth() + 40, POY);
			hitbox[4].setPosition(POX + SActor.getWidth() / 4 + 90 - hitbox[4].getWidth(),
					POY + hitbox[2].getHeight() + 20);
			hitbox[5].setPosition(POX + SActor.getWidth() / 4 + 70 - hitbox[4].getWidth(),
					POY + hitbox[2].getHeight() - 100);
			hitbox[6].setPosition(POX + SActor.getWidth() / 4 + 140 - hitbox[4].getWidth(),
					POY + hitbox[2].getHeight() - 60);
			hitbox[7].setPosition(POX + SActor.getWidth() / 4 + 140 - hitbox[4].getWidth(), POY);
			hitbox[8].setPosition(POX + SActor.getWidth() / 4 + 80 - hitbox[4].getWidth(), POY + hitbox[2].getHeight());
			hitbox[9].setPosition(POX + SActor.getWidth() / 4 + 90 - hitbox[4].getWidth(), POY);

			guard.setPosition(hitbox[0].getPoX() - 5, POY);
		}

		if (STATUS != STOP) {
			DELAY -= dt;
		}
		if (DELAY <= 0) {
			DELAY = 0;
		}

		if (STATUS != STOP) {
			STMDELAY -= dt;
		}
		if (STMDELAY <= 0) {
			STMDELAY = 0;
			STAMINA += 70 * dt;
		}
		// Status
		if (STATUS == STAND) {
			stand();
			beforeSTATUS = STAND;
		}
		if (STATUS == KNEEL) {
			kneel();
			beforeSTATUS = KNEEL;
		}
		if (STATUS == JUMP) {
			jump();
			beforeSTATUS = JUMP;
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
		if (STATUS == STOP) {

		}

		if (DELAY <= 0) {
			action(cmdlog);
		}

		if (STATUS != STOP) {
			hitboxchecking();
		}

		if (HP <= 0) {
			HP = 0;
			STATUS = KNOCKOUT;

			setAnimationTimeLoop(0.1f);

			runframe(19, 21, 1);

			damage.setAlpha(0f);
			Mdamage.setAlpha(0f);

			Anotherplayer.STATUS = STOP;
		}

		if (STAMINA <= 0) {
			STAMINA = 0;
		} else if (STAMINA > 200) {
			STAMINA = 200;
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

		for (Hitbox item : hitbox) {
			item.dispose();
		}

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
		if (combo.equals("CFP") && STATUS == STAND) {
			STATUS = SKILL;
			System.out.println("WIND");
			cmdlog = "";
		}

		cmdlog = "";

	}

	@Override
	protected void stand() {
		// Punch
		if (InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick) && STAMINA >= 20 && canCounter) {

			STAMINA -= 25;
			STMDELAY = 1.5f;
			isPunch = true;
			animationtime = 0.3f;

			// Kick
		} else if (InputManager.keyIspressed(BN_KICK) && (!isKick && !isPunch) && STAMINA >= 20 && canCounter) {

			STAMINA -= 25;
			STMDELAY = 1.5f;
			isKick = true;
			animationtime = 0.3f;

		} else if ((InputManager.keyIsdown(BN_FRONT) && InputManager.keyIspressed(BN_JUMP))
				|| (InputManager.keyIspressed(BN_FRONT) && InputManager.keyIspressed(BN_JUMP))) {

			JUMPTYPE = 1;

		} else if ((InputManager.keyIsdown(BN_BACK) && InputManager.keyIspressed(BN_JUMP))
				|| (InputManager.keyIspressed(BN_BACK) && InputManager.keyIspressed(BN_JUMP))) {

			JUMPTYPE = 2;
			// Move Front
		} else if (InputManager.keyIsdown(BN_FRONT) && (!isPunch && !isKick)) {

			setAnimationTimeLoop(0.2f);

			runframe(2, 4, 0);

			movement(movespeed, 0);

			// Move Back
		} else if (InputManager.keyIsdown(BN_BACK) && (!isPunch && !isKick)) {

			setAnimationTimeLoop(0.2f);

			runframe(2, 4, 0);

			movement(movespeed, 1);

		} else {

			if (isPunch || isKick) {

				setAnimationTimeLoop(0.1f);

				if (isPunch) {
					runframe(05, 05, 1);
				} else if (isKick) {
					runframe(06, 06, 1);
				}
			} else {

				setAnimationTimeLoop(0.3f);

				runframe(0, 1, 0);
			}
		}

	}

	@Override
	protected void jump() {

		AIRDELAY -= Gdx.graphics.getDeltaTime();

		if (AIRDELAY <= 0) {
			AIRDELAY = 0;
		}

		if (InputManager.keyIspressed(BN_PUNCH) && canCounter && (!isPunch && !isKick) && STAMINA >= 30
				&& AIRDELAY > 0) {

			STAMINA -= 30;
			STMDELAY = 1.5f;
			isPunch = true;
			animationtime = 0.1f;

		} else if (InputManager.keyIspressed(BN_KICK) && canCounter && (!isPunch && !isKick) && STAMINA >= 30
				&& AIRDELAY > 0) {

			STAMINA -= 30;
			STMDELAY = 1.5f;
			isKick = true;
			animationtime = 0.1f;

		} else {
			if (isPunch || isKick) {

				setAnimationTimeLoop(0.1f);
				if (isPunch) {
					runframe(12, 14, 1);
				}

			} else {
				runframe(10, 11, 1);
			}
		}

		if (AIRDELAY > 0) {
			POY += 1000 * Gdx.graphics.getDeltaTime();
		}
		if (AIRDELAY <= 0) {
			POY -= 850 * Gdx.graphics.getDeltaTime();
		}
		if (JUMPTYPE == 1) {

			movement(movespeed + 150, 0);

		}
		if (JUMPTYPE == 2) {

			movement(movespeed + 150, 1);

		}
		if (POY <= PlayingState.GROUND) {
			POY = PlayingState.GROUND;
			isPunch = false;
			isKick = false;
			STATUS = STAND;
			JUMPTYPE = 0;
			cmdlog = "";
			canCounter = true;
		}

	}

	@Override
	protected void kneel() {

		if (InputManager.keyIspressed(BN_PUNCH) && (!isPunch && !isKick) && STAMINA >= 10 && canCounter) {

			STAMINA -= 10;
			STMDELAY = 1.5f;
			isPunch = true;

		} else if (InputManager.keyIspressed(BN_KICK) && (!isPunch && !isKick) && STAMINA >= 10 && canCounter) {

			STAMINA -= 10;
			STMDELAY = 1.5f;
			isKick = true;

		} else {
			if (isPunch || isKick) {

				setAnimationTimeLoop(0.3f);

				if (isPunch) {
					runframe(8, 8, 1);
				} else if (isKick) {
					runframe(9, 9, 1);
				}
			} else {

				setAnimationTimeLoop(0.05f);

				runframe(7, 7, 1);
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

		if (beforeSTATUS == JUMP) {
			canCounter = false;
		} else {
			canCounter = true;
		}

		if (HITType == TOPHIT) {
			runframe(18, 18, 1);

			damage.setPosition(hitbox[2].getPoX() + hitbox[2].getWidth(), hitPOY);
			Mdamage.setPosition(hitbox[2].getPoX() - 50, hitPOY);

			if (!mirror) {
				damage.setAlpha(1f);
			} else if (mirror) {
				Mdamage.setAlpha(1f);
			}

		} else if (HITType == DOWNHIT) {
			runframe(17, 17, 1);

			damage.setPosition(hitbox[2].getPoX() + hitbox[2].getWidth(), hitPOY);
			Mdamage.setPosition(hitbox[2].getPoX() - 50, hitPOY);

			if (!mirror) {
				damage.setAlpha(1f);
			} else if (mirror) {
				Mdamage.setAlpha(1f);
			}

		}

		if (DELAY >= 0.28f) {
			HP -= Anotherplayer.ATK;
			Anotherplayer.hitCount += 1;
		}

		if (DELAY >= 0.2f) {
			knockback(400);
		}

		if (DELAY <= 0) {
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
			runframe(15, 15, 1);

		} else if (HITType == DOWNGUARD) {
			runframe(16, 16, 1);

		}

		if (hitbox[4].hitwith(Anotherplayer.hitbox[0]) && (Anotherplayer.isPunch || Anotherplayer.isKick)) {
			knockback(400);
			STAMINA -= 3;
			STMDELAY = 1f;
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
	protected void movement(float movespeed, int type) {

		if (type == 0) {
			// moveFront
			if (mirror) {
				if (POX + SActor.getWidth() / 3 > 0) {
					POX -= movespeed * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 2 + 80 < PlayingState.WORLD_WIDTH) {
					POX += movespeed * Gdx.graphics.getDeltaTime();
				}
			}
		} else if (type == 1) {
			// moveBack
			if (mirror) {
				if (POX + SActor.getWidth() - SActor.getWidth() / 3 < PlayingState.WORLD_WIDTH
						&& POX + SActor.getWidth() - SActor.getWidth() / 3 < PlayingState.camera.position.x
								+ PlayingState.WIDTH / 2) {
					POX += movespeed * Gdx.graphics.getDeltaTime();
				}
			} else if (!mirror) {
				if (POX + SActor.getWidth() / 3 > 0
						&& POX + SActor.getWidth() / 3 > PlayingState.camera.position.x - PlayingState.WIDTH / 2) {
					POX -= movespeed * Gdx.graphics.getDeltaTime();
				}
			} else {
				POX += 0;
			}
		}

	}

	@Override
	protected void knockback(float movespeed) {

		movement(movespeed, 1);

		if (!mirror) {
			if (POX + SActor.getWidth() / 3 <= 0 + 200) {
				Anotherplayer.movement(movespeed + 200, 1);
			}
		} else if (mirror) {
			if (POX + SActor.getWidth() - SActor.getWidth() / 3 >= PlayingState.WORLD_WIDTH - 200) {
				Anotherplayer.movement(movespeed + 200, 1);
			}
		}

	}

	@Override
	protected void hitboxchecking() {
		// Update hit box Status

		if (hitbox[1].hitwith(Anotherplayer.hitbox[1]) && STATUS == STAND) {

			movement(movespeed, 1);

		} else if (hitbox[1].hitwith(Anotherplayer.hitbox[1]) && STATUS == JUMP) {

			movement(movespeed + 150, 1);

		}
		////////////////////////
		if ((Anotherplayer.isPunch || Anotherplayer.isKick) && InputManager.keyIsdown(BN_BACK)
				&& Math.abs(POX - Anotherplayer.POX) < SActor.getWidth() && Anotherplayer.STATUS != HITING
				&& (STATUS == STAND || STATUS == KNEEL) && STAMINA > 0) {

			if (STATUS == STAND) {
				HITType = TOPGUARD;
			} else if (STATUS == KNEEL) {
				HITType = DOWNGUARD;
			}
			STATUS = GUARD;
			DELAY = 0.3f;
		}
		// STAND PUNCH
		else if (hitbox[4].hitwith(Anotherplayer.hitbox[2]) && isPunch && NOWframe == 5 && Anotherplayer.STATUS != KNEEL
				&& Anotherplayer.STATUS != GUARD) {

			HitWithAnotherPlayer(Anotherplayer.TOPHIT, 0.3f, 20, hitbox[4].getPoY());

			// STAND KICK
		} else if (hitbox[5].hitwith(Anotherplayer.hitbox[3]) && isKick && NOWframe == 6
				&& Anotherplayer.STATUS != GUARD) {

			HitWithAnotherPlayer(Anotherplayer.DOWNHIT, 0.3f, 20, hitbox[5].getPoY());

			// KNEEL PUNCH
		} else if (hitbox[6].hitwith(Anotherplayer.hitbox[3]) && isPunch && NOWframe == 8
				&& Anotherplayer.STATUS != GUARD) {

			HitWithAnotherPlayer(Anotherplayer.DOWNHIT, 0.3f, 10, hitbox[6].getPoY());

			// KNEEL KICK
		} else if (hitbox[7].hitwith(Anotherplayer.hitbox[3]) && isKick && NOWframe == 9
				&& Anotherplayer.STATUS != GUARD) {

			HitWithAnotherPlayer(Anotherplayer.DOWNHIT, 0.3f, 10, hitbox[7].getPoY());

			// JUMP PUNCH
		} else if ((hitbox[8].hitwith(Anotherplayer.hitbox[1]) || (hitbox[8].hitwith(Anotherplayer.hitbox[2])))
				&& isPunch && NOWframe == 14 && Anotherplayer.STATUS != GUARD) {

			AIRDELAY = 0;

			Anotherplayer.AIRDELAY = 0;

			if ((hitbox[8].hitwith(Anotherplayer.hitbox[1]))) {

				HitWithAnotherPlayer(Anotherplayer.TOPHIT, 0.3f, 25, hitbox[8].getPoY());

			} else if ((hitbox[8].hitwith(Anotherplayer.hitbox[3]))) {

				HitWithAnotherPlayer(Anotherplayer.DOWNHIT, 0.3f, 25, hitbox[8].getPoY());

			}

			// JUMP KICK
		} // else if ((hitbox[9].hitwith(Anotherplayer.hitbox[3]) ||
			// (hitbox[9].hitwith(Anotherplayer.hitbox[2])))
//				&& isKick && NOWframe == 25 && Anotherplayer.STATUS != GUARD) {
//
//			Anotherplayer.AIRDELAY = 0;
//
//			if ((hitbox[9].hitwith(Anotherplayer.hitbox[2]))) {
//
//				HitWithAnotherPlayer(Anotherplayer.TOPHIT, 0.3f, 25, hitbox[9].getPoY());
//
//			} else if ((hitbox[9].hitwith(Anotherplayer.hitbox[3]))) {
//
//				HitWithAnotherPlayer(Anotherplayer.DOWNHIT, 0.3f, 25, hitbox[9].getPoY());
//
//			}
//
//		}

	}

	@Override
	protected void runframe(int st, int en, int animationType) {

		animationtime -= Gdx.graphics.getDeltaTime();

		if (animationtime <= 0) {
			NOWframe++;
		}
		if (NOWframe < st) {
			NOWframe = st;
		}
		if (NOWframe > en && animationType == 0) {
			NOWframe = st;
		}
		if (NOWframe > en && (animationType == 1)) {
			NOWframe = en;
			isPunch = false;
			isKick = false;
		}

		frame = pack.findRegion(String.format("%02d", NOWframe));
		SActor.setRegion(frame);

	}

	@Override
	protected void setAnimationTimeLoop(float animationtime) {

		if (this.animationtime <= 0) {
			this.animationtime = animationtime;
		}

	}

	@Override
	protected void HitWithAnotherPlayer(int HitType, float DELAY, int ATK, float hitPOY) {

		Anotherplayer.HITType = HitType;

		if (STATUS != HITING && STATUS != STOP) {

			this.DELAY = DELAY;
		}
		this.ATK = ATK;
		STATUS = HITING;

		if (Anotherplayer.STATUS != HIT && Anotherplayer.STATUS != Anotherplayer.STOP) {

			Anotherplayer.DELAY = DELAY;

			Anotherplayer.STATUS = Anotherplayer.HIT;
			Anotherplayer.hitPOY = hitPOY;
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
