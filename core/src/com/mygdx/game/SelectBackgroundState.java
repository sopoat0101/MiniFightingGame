package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Select;

public class SelectBackgroundState extends State{
	public static int bgSelected = 0;
	private Sprite bg, gui1, guiName;
	public static Texture bg0, bg1, bg2, name0, name1, name2;
	private SpriteBatch batch;

	public SelectBackgroundState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg0 = new Texture(Gdx.files.internal("background/MAP.png"));
		bg1 = new Texture(Gdx.files.internal("background/background1.png"));
		bg2 = new Texture(Gdx.files.internal("background/background2.png"));
		name0 = new Texture(Gdx.files.internal("gui/select/stageName/name0.png"));
		name1 = new Texture(Gdx.files.internal("gui/select/stageName/name1.png"));
		name2 = new Texture(Gdx.files.internal("gui/select/stageName/name2.png"));
		bg = new Sprite(bg0);
		bg.setScale(0.9f);
		bg.setPosition(-550, -100);
		
		gui1 = new Sprite(new Texture(Gdx.files.internal("gui/select/selectStageGUI.png")));
		guiName = new Sprite(name0);
		gui1.setPosition(0, 475);
		guiName.setPosition(0, 0);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		
		batch.begin();
		
		bg.draw(batch);
		gui1.draw(batch);
		guiName.draw(batch);
		
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
		if ((InputManager.keyIspressed(InputManager.KEY_D) || InputManager.keyIspressed(InputManager.KEY_RIGHT)) && bgSelected == 0) {
			bg.setTexture(bg1);
			bgSelected = 1;
			guiName.setTexture(name1);
		}
		
		else if ((InputManager.keyIspressed(InputManager.KEY_A) || InputManager.keyIspressed(InputManager.KEY_LEFT)) && bgSelected == 0) {
			bg.setTexture(bg2);
			bgSelected = 2;
			guiName.setTexture(name2);
		}
		
		else if ((InputManager.keyIspressed(InputManager.KEY_D) || InputManager.keyIspressed(InputManager.KEY_RIGHT)) && bgSelected == 1) {
			bg.setTexture(bg2);
			bgSelected = 2;
			guiName.setTexture(name2);
		}
		
		else if ((InputManager.keyIspressed(InputManager.KEY_A) || InputManager.keyIspressed(InputManager.KEY_LEFT)) && bgSelected == 1) {
			bg.setTexture(bg0);
			bgSelected = 0;
			guiName.setTexture(name0);
		}
		
		else if ((InputManager.keyIspressed(InputManager.KEY_D) || InputManager.keyIspressed(InputManager.KEY_RIGHT)) && bgSelected == 2) {
			bg.setTexture(bg0);
			bgSelected = 0;
			guiName.setTexture(name0);
		}
		
		else if ((InputManager.keyIspressed(InputManager.KEY_A) || InputManager.keyIspressed(InputManager.KEY_LEFT)) && bgSelected == 2) {
			bg.setTexture(bg1);
			bgSelected = 1;
			guiName.setTexture(name1);
		}
		
		
		else if (InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			bg.setScale(1);
			gsm.setState(GameStateManager.PLAYING);
		}
		
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		GameStateManager.bgsound.dispose();
	}

}
