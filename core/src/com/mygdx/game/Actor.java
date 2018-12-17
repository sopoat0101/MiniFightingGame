package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Actor {
	
	protected Sound hitFX;
	
	protected int BN_FRONT;
	protected int BN_BACK;
	protected int BN_JUMP;
	protected int BN_KNEEL;
	protected int BN_PUNCH;
	protected int BN_KICK;

	protected boolean mirror;

	protected float HP = 500;
	protected float ATK = 20;
	protected float STAMINA = 200;
	protected float movespeed = 300;
	protected float POX = 0;
	protected float POY = 0;

	protected float animationtime = 0;
	protected String cmdlog = "";

	protected Sprite SActor;
	protected TextureAtlas pack;
	protected TextureRegion frame;

	protected int STATUS = 0;
	protected final int STAND = 0;
	protected final int JUMP = 1;
	protected final int KNEEL = 2;
	protected final int HITING = 3;
	protected final int HIT = 4;
	protected final int GUARD = 5;
	protected final int STOP = 6;
	protected final int SKILL = 7;
	protected final int KNOCKOUT = 100;

	protected int STATUS_PAUSE_GAME = 0;

	protected boolean canCounter = true;
	protected boolean isPunch = false;
	protected boolean isKick = false;

	protected int JUMPTYPE = 0;

	protected float DELAY = 0;
	protected float AIRDELAY = 0;
	protected float STMDELAY = 0;
	// animation
	protected int NOWframe = 0;
	protected int STframe = 0;
	protected int ENDframe = 1;

	// Hit box
	protected int beforeSTATUS = 0;
	Hitbox hitbox[];
	protected int HITType = 0;
	protected final int TOPHIT = 0;
	protected final int DOWNHIT = 1;
	protected final int TOPGUARD = 2;
	protected final int DOWNGUARD = 3;
	
	protected final int KNOCKUPHIT = 4;
	
	protected int hitCount;
	
	protected int punchCount;
	protected int kickCount;
	
	protected Sprite damage;
	protected Sprite Mdamage;
	protected Sprite guard;
	protected float hitPOY = 0;

	protected Actor Anotherplayer;

	
	protected int skillNumber = 0;
	
	protected Actor(int bn_front, int bn_back, int bn_jump, int bn_kneel, int bn_punch, int bn_kick, boolean mirror,
			int player) {

		this.BN_FRONT = bn_front;
		this.BN_BACK = bn_back;
		this.BN_JUMP = bn_jump;
		this.BN_KNEEL = bn_kneel;
		this.BN_PUNCH = bn_punch;
		this.BN_KICK = bn_kick;

		this.mirror = mirror;

		POY = PlayingState.GROUND;

	}

	protected abstract void init();

	protected abstract void draw(SpriteBatch batch);

	protected abstract void update(float dt);

	protected abstract void handle();

	protected abstract void dispose();

	protected abstract void action(String combo);

	protected abstract void stand();

	protected abstract void jump();

	protected abstract void kneel();

	protected abstract void hiting();

	protected abstract void hit();

	protected abstract void guard();
	
	protected abstract void skill();

	protected abstract void movement(float movespeed, int type);
	//Move Front = 0
	//Move Back = 1
	
	protected abstract void knockback(float movespeed);

	protected abstract void hitboxchecking();

	protected abstract void runframe(int st, int en, int animationType);
	//Loop = 0
	//OneWay = 1
	
	protected abstract void setAnimationTimeLoop(float animationtime);

	protected abstract void HitWithAnotherPlayer(int HitType, float DELAY, int ATK, float hitPOY);

	protected abstract void setBN_FRONT(int bn);

	protected abstract void setBN_BACK(int bn);

	protected abstract void setMIRROR(boolean b);

	protected abstract void setAnotherPlayer(Actor Player);
	
	protected abstract int getPunchCount();
	protected abstract int getKickCount();
}
