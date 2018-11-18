package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
	private SpriteBatch batch;
	private Sprite bg;

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg = new Sprite(new Texture(Gdx.files.internal("../core/assets/background/playingmap.png")));
	}

	@Override
	public void draw() {
		
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		batch.end();
		

	}

	@Override
	public void update(float dt) {

		handle();

	}

	@Override
	public void handle() {

		if (InputManager.keyIspressed(InputManager.KEY_SPACE)) {

			gsm.setState(GameStateManager.SELECT);

		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
