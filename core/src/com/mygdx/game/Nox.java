package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Nox extends Actor {
	
	protected Nox(int bn_front, int bn_back, int bn_jump, int bn_kneel, int bn_punch, int bn_kick, boolean mirror,	int player) {
		super(bn_front, bn_back, bn_jump, bn_kneel, bn_punch, bn_kick, mirror, player);
	}

	@Override
	protected void init() {
		
		pack = new TextureAtlas("../core/assets/Actor/Nox/notmirror/Nox.pack");
		frame = pack.findRegion("00");
		SActor = new Sprite(frame);
		SActor.setPosition(PlayingState.WORLD_WIDTH / 4, PlayingState.GROUND);
		
		
		mpack = new TextureAtlas("../core/assets/Actor/Nox/notmirror/Nox.pack");
		mframe = mpack.findRegion("00");
		MSActor = new Sprite(mframe);
		MSActor.setPosition(PlayingState.WORLD_WIDTH - PlayingState.WORLD_WIDTH / 4 - MSActor.getWidth(),
				PlayingState.GROUND);
		
		if (mirror == false) {
			SActor.setAlpha(1f);
			MSActor.setAlpha(0f);

			POX = PlayingState.WORLD_WIDTH / 5;

		} else if (mirror) {
			MSActor.setAlpha(1f);
			SActor.setAlpha(0f);

			POX = PlayingState.WORLD_WIDTH - PlayingState.WORLD_WIDTH / 5 - MSActor.getWidth();

		}
		
		POY = PlayingState.GROUND;
		
	}

	@Override
	protected void draw(SpriteBatch batch) {
		
		SActor.draw(batch);
		MSActor.draw(batch);
	
	}

	@Override
	protected void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void action(String combo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kneel() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setBN_FRONT(int bn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setBN_BACK(int bn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setMIRROR(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAnotherPlayer(Actor Player) {
		// TODO Auto-generated method stub
		
	}

}
