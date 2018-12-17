package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameStateManager {

	private static State gameState;

	public static int state;

	public static final int START = 0;
	public static final int MENU = 1;
	public static final int SELECT = 2;
	public static final int TUTORIAL = 3;
	public static final int PLAYING = 4;
	public static final int CREDIT = 5;
	public static final int BACKGROUND = 6;
	public static final int SCORE = 7;

	private static Music bgMusic;

	private static int select_P1;
	private static int select_P2;

	public GameStateManager() {
		
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.5f);
		
		bgMusic.play();
		
		setState(START);
	}

	public void setState(int state) {
		
		bgMusic.dispose();
		
		if (gameState != null)
			gameState.dispose();

		if (state == START) {
			
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new StartState(this);

		} else if (state == MENU) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Operatic_3.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new MenuState(this);
		} else if (state == SELECT) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Stranger_Danger.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new SelectState(this);
		} else if (state == TUTORIAL) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Generations_Away.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new TutorialState(this);
		} else if (state == CREDIT) {
			gameState = new CreditState(this);
		} else if (state == PLAYING) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/1-ThisisWar.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new PlayingState(this);
		} else if (state == BACKGROUND) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Generations_Away.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new SelectBackgroundState(this);
		} else if (state == SCORE) {
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Inevitable.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);

			gameState = new ScoreState(this);
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
		bgMusic.dispose();
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

	public static void setVolum(float vol) {
		
		bgMusic.setVolume(vol);

	}

	public static void setSoundState(boolean state) {

		if(state) {
			bgMusic.play();
		}
		if(!state) {
			bgMusic.pause();
		}

	}
}
