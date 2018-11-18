package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialState extends State{
	private Sprite bg;
	private SpriteBatch batch;

	public TutorialState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg = new Sprite(new Texture(Gdx.files.internal("../core/assets/background/tutorialBackground.png")));
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		batch.end();
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handle();
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		if (InputManager.keyIspressed(InputManager.KEY_ESC)){
			gsm.setState(GameStateManager.MENU);
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
