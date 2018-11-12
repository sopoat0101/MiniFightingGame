package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoS extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameStateManager gsm;
	
	@Override
	public void create () {
		
		gsm = new GameStateManager();
		
		batch = new SpriteBatch();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		gsm.darw();
		gsm.update(Gdx.graphics.getDeltaTime());
		
		Gdx.input.setInputProcessor(new InputManager());
		
		batch.end();
		
		InputManager.keysUpdate();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
