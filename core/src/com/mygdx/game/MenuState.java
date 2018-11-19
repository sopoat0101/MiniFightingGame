package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
	private SpriteBatch batch;
	private Sprite bg, playOption, tutorialOption, exitOption;
	private Texture playTexture1, playTexture12, tutorialTexture1, tutorialTexture12, exitTexture1, exitTexture12;
	private boolean playSelected = true, tutorialSelected = false, exitSelected = false;

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bg = new Sprite(new Texture(Gdx.files.internal("../core/assets/background/MenuBackground.png")));
		playOption = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/menu/battlemenu1-2.png")));
		tutorialOption = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/menu/tutorialmenu1.png")));
		exitOption = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/menu/exitmenu1.png")));
		playTexture1 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/battlemenu1.png")));
		playTexture12 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/battlemenu1-2.png")));
		tutorialTexture1 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/tutorialmenu1.png")));
		tutorialTexture12 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/tutorialmenu1-2.png")));
		exitTexture1 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/exitmenu1.png")));
		exitTexture12 = (new Texture(Gdx.files.internal("../core/assets/gui/menu/exitmenu1-2.png")));
	}

	@Override
	public void draw() {
		
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		playOption.setPosition(730f, 550f);
		playOption.draw(batch);
		tutorialOption.setPosition(730f, 350f);
		tutorialOption.draw(batch);
		exitOption.setPosition(730f, 150f);
		exitOption.draw(batch);
		batch.end();
		

	}

	@Override
	public void update(float dt) {

		handle();

	}

	@Override
	public void handle() {
		
		//SELECT MENU ALGORITHMS
		if (InputManager.keyIspressed(InputManager.KEY_DOWN) && playSelected == true) {
			tutorialSelected = true;
			playSelected = false;
			tutorialOption.setTexture(tutorialTexture12);
			playOption.setTexture(playTexture1);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_UP) && playSelected == true) {
			exitSelected = true;
			playSelected = false;
			exitOption.setTexture(exitTexture12);
			playOption.setTexture(playTexture1);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_DOWN) && tutorialSelected == true) {
			exitSelected = true;
			tutorialSelected = false;
			exitOption.setTexture(exitTexture12);
			tutorialOption.setTexture(tutorialTexture1);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_UP) && tutorialSelected == true) {
			playSelected = true;
			tutorialSelected = false;
			playOption.setTexture(playTexture12);
			tutorialOption.setTexture(tutorialTexture1);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_DOWN) && exitSelected == true) {
			exitSelected = false;
			playSelected = true;
			exitOption.setTexture(exitTexture1);
			playOption.setTexture(playTexture12);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_UP) && exitSelected == true) {
			exitSelected = false;
			tutorialSelected = true;
			exitOption.setTexture(exitTexture1);
			tutorialOption.setTexture(tutorialTexture12);
		}
		

		else if (InputManager.keyIspressed(InputManager.KEY_SPACE) && playSelected == true) {

			gsm.setState(GameStateManager.SELECT);

		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_SPACE) && tutorialSelected == true) {

			gsm.setState(GameStateManager.TUTORIAL);

		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_SPACE) && exitSelected == true) {

			Gdx.app.exit();

		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
