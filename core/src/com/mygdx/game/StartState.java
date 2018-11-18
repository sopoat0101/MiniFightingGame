package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartState extends State{
	private SpriteBatch batch;
	private Sprite bg, logo, startButton;

	public StartState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg = new Sprite(new Texture(Gdx.files.internal("../core/assets/background/StartBackground.png")));
		logo = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/logo/logo.png")));
		startButton = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/logo/startButton.png")));
	}

	@Override
	public void draw() {
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		logo.draw(batch);
		startButton.draw(batch);
		
		batch.end();
		
		
		
	}

	@Override
	public void update(float dt) {
		
		handle();
		
	}

	@Override
	public void handle() {
		
		if(InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			
			gsm.setState(GameStateManager.MENU);
			
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
