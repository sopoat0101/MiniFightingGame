package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkillTornado {
	private TextureAtlas TAtornado;
	private TextureRegion TRtornado;
	private Sprite tornado;

	private Hitbox hitbox;

	private Actor user;
	private Actor target;

	private float POX;
	private float POY;

	private float count = 0;
	private int time = 1;

	private boolean mirror;

	public SkillTornado(Actor user, Actor target) {

		this.user = user;
		this.target = target;

		mirror = user.mirror;

		if (!mirror) {
			POX = user.POX + user.SActor.getWidth() / 2;
		}
		if (mirror) {
			POX = user.POX + user.SActor.getWidth() / 2 - 50;
		}
		POY = user.POY;
		hitbox = new Hitbox("Actor/Nox/hitbox/stpunch.png", POX, POY);
		hitbox.setSize(100, 130);
		TAtornado = new TextureAtlas(Gdx.files.internal("Actor/Mato/skill/tornado.pack"));
		TRtornado = TAtornado.findRegion("0");
		tornado = new Sprite(TRtornado);

		tornado.setPosition(POX, POY);

		hitbox.setAlpha(0f);

	}

	public void update() {

		if(PlayingState.STATE != 1) {
			count += Gdx.graphics.getDeltaTime();
		}

		if(count >= 0.5f) {
			TRtornado = TAtornado.findRegion("1");
			tornado.setRegion(TRtornado);
		}
		
		if(count >= 0.9f) {
			TRtornado = TAtornado.findRegion("2");
			tornado.setRegion(TRtornado);
		}
		
		if (count >= 1) {
			count = 0;
			time -= 1;
		}

		tornado.setPosition(POX, POY);
		hitbox.setPosition(POX + 50, POY);

		if ((user.skillNumber == 1) && InputManager.keyIsdown(target.BN_BACK)
				&& Math.abs(POX - target.POX) < target.SActor.getWidth() 
				&& target.STATUS != target.HITING
				&& (target.STATUS == target.STAND || target.STATUS == target.KNEEL) 
				&& target.STAMINA > 0 && !InputManager.keyIsdown(target.BN_FRONT)
				&& !InputManager.keyIsdown(target.BN_JUMP) && !InputManager.keyIsdown(target.BN_KICK)
				&& !InputManager.keyIsdown(target.BN_PUNCH)) {

			if (target.STATUS == target.STAND) {
				target.HITType = target.TOPGUARD;
			} else if (target.STATUS == target.KNEEL) {
				target.HITType = target.DOWNGUARD;
			}
			target.STATUS = target.GUARD;
			target.DELAY = 0.3f;
		}

		if (hitbox.hitwith(target.hitbox[1]) && target.STATUS != target.GUARD && time >= 0) {
			user.HitWithAnotherPlayer(target.KNOCKUPHIT, 0.3f, 10, target.hitbox[3].getPoY());
		} else {
			if (!mirror) {
				POX += 200 * Gdx.graphics.getDeltaTime();
			} else if (mirror) {
				POX -= 200 * Gdx.graphics.getDeltaTime();
			}
		}

	}

	public void draw(SpriteBatch batch) {
		if (time >= 0) {
			tornado.draw(batch);
			hitbox.draw(batch);
		} else {
			dispose();
		}
	}

	public void dispose() {
		TAtornado.dispose();
		hitbox.dispose();
	}

}
