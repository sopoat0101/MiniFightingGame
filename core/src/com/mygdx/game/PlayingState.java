package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayingState extends State {

	private SpriteBatch batch;
	private Sprite sp;
	
	public PlayingState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		batch = new SpriteBatch();
		sp = new Sprite(new Texture(Gdx.files.internal("../core/assets/NoxStand.png")));
		
	}

	@Override
	public void draw() {
		
		System.out.println("Playing");
		
		batch.begin();
		
		sp.draw(batch);
		
		batch.end();
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
