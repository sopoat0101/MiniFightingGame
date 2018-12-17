package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartState extends State{
	private SpriteBatch batch;
	private Sprite bg, logo, startButton;
	private float alpha = .0f;
	
	public StartState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		bg = new Sprite(new Texture(Gdx.files.internal("background/StartBackground.png")));
		logo = new Sprite(new Texture(Gdx.files.internal("gui/logo/logo.png")));
		startButton = new Sprite(new Texture(Gdx.files.internal("gui/logo/startButton.png")));
		
	}

	@Override
	public void draw() {
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		logo.draw(batch);
		logo.setPosition(0, 50);
		startButton.draw(batch);
		startButton.setPosition(0, -250);
		
		
		batch.end();
		
		
	}

	@Override
	public void update(float dt) {
		
		handle();
		
		alpha += dt;
		startButton.setAlpha((float)Math.abs(Math.sin(alpha)));
		
	}

	@Override
	public void handle() {
		
		if(InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			
			dispose();
			gsm.setState(GameStateManager.MENU);
			
		}
		
		if(InputManager.keyIspressed(InputManager.KEY_ESC)) {
			Gdx.app.exit();
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
