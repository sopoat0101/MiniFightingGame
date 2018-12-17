package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditState extends State{
	private float time = 0;
	private float alpha = 0;
	private Sprite credit;
	private SpriteBatch batch;
	
	public CreditState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		credit = new Sprite(new Texture(Gdx.files.internal("credit/credit.png")));
		credit.setAlpha(0);
		credit.setPosition(0, -1350);
	}

	@Override
	public void draw() {
		batch = new SpriteBatch();
		batch.begin();
		credit.draw(batch);
		
		batch.end();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handle();
		credit.setPosition(0, credit.getY() + 1);
		
		time += dt;
		
		alpha += dt/4;
		if(alpha >= 1) {
			alpha = 1;
		}
		credit.setAlpha((float)alpha);
		if(time >= 33) {
			gsm.setState(GameStateManager.MENU);
		}
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		if (InputManager.keyIspressed(InputManager.KEY_ESC) || InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			gsm.setState(GameStateManager.MENU);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
