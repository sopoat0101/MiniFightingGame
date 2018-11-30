package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectBackgroundState extends State{
	public static int bgSelected = 0;
	private Sprite bg;
	private Texture bg0, bg1;
	private SpriteBatch batch;

	public SelectBackgroundState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg0 = new Texture("background/MAP.png");
		bg1 = new Texture("background/background1.png");
		bg = new Sprite(bg0);
		bg.setScale(0.9f);
		bg.setPosition(-550, -100);
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
		if (InputManager.keyIspressed(InputManager.KEY_D) || InputManager.keyIspressed(InputManager.KEY_RIGHT) && bgSelected == 0) {
			bg.setTexture(bg1);
			bgSelected = 1;
		}
		
		if (InputManager.keyIspressed(InputManager.KEY_D) || InputManager.keyIspressed(InputManager.KEY_RIGHT) && bgSelected == 0) {
			bg.setScale(1);
			gsm.setState(gsm.PLAYING);
		}
		
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
