package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
	
	protected GameStateManager gsm;
	protected SpriteBatch batch;
	
	public State(GameStateManager gsm){
		this.gsm = gsm;
	}

	public abstract void init();
	
	public abstract void draw();
	
	public abstract void update(float dt);
	
	public abstract void handle();
	
	public abstract void dispose();
	
}
