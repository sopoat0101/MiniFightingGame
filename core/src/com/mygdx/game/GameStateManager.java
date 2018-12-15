package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameStateManager {

	private static State gameState;

	public static int state;

	public static final int START = 0;
	public static final int MENU = 10;
	public static final int SELECT = 100;
	public static final int TUTORIAL = 50;
	public static final int PLAYING = 1000;
	public static final int CREDIT = 99;
	public static final int BACKGROUND = 98;
	public static Music bgsound;

	private static int select_P1;
	private static int select_P2;

	public GameStateManager() {
		GameStateManager.bgsound = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
		GameStateManager.bgsound.setVolume(0.5f);
		GameStateManager.bgsound.play();
		GameStateManager.bgsound.setLooping(true);
		setState(START);
	}

	public void setState(int state) {

		if (gameState != null)
			gameState.dispose();

		if (state == START) {
			gameState = new StartState(this);
		} else if (state == MENU) {
			gameState = new MenuState(this);
		} else if (state == SELECT) {
			gameState = new SelectState(this);
		} else if (state == TUTORIAL) {
			gameState = new TutorialState(this);
		} else if (state == CREDIT) {
			gameState = new CreditState(this);
		} else if (state == PLAYING) {
			gameState = new PlayingState(this);
			bgsound = Gdx.audio.newMusic(Gdx.files.internal("sound/1-ThisisWar.mp3"));
			bgsound.play();
			bgsound.setLooping(true);
			bgsound.setVolume(0.5f);
		} else if (state == BACKGROUND) {
			gameState = new SelectBackgroundState(this);
		}
		gameState.init();
		
	}

	public void darw() {
		gameState.draw();
	}

	public void update(float dt) {
		gameState.update(dt);
	}

	public void dispose() {
		gameState.dispose();
	}

//
	public int getSelect_P1() {
		return select_P1;
	}

	public void setSelect_P1(int select_P1) {
		GameStateManager.select_P1 = select_P1;
	}

	public int getSelect_P2() {
		return select_P2;
	}

	public void setSelect_P2(int select_P2) {
		GameStateManager.select_P2 = select_P2;
	}

}
