package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialState extends State{
	private Sprite bg;
	private SpriteBatch batch;

	private Sound bgsound;
	
	public TutorialState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		
		bgsound = Gdx.audio.newSound(Gdx.files.internal("sound/Generations_Away.mp3"));
		bgsound.loop(0.5f, 1.0f, 0.0f);
		
		bg = new Sprite(new Texture(Gdx.files.internal("background/tutorialBackground.png")));
		
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
		
		if (InputManager.keyIspressed(InputManager.KEY_ESC)){
			gsm.setState(GameStateManager.MENU);
		}
		
	}

	@Override
	public void dispose() {
		
		
		bgsound.dispose();
		
	}

}
