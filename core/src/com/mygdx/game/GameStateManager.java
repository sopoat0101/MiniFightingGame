package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameStateManager {

	private static State gameState;

	public static int state;
	public static boolean isPlaying = false;

	public static final int START = 0;
	public static final int MENU = 1;
	public static final int SELECT = 2;
	public static final int TUTORIAL = 3;
	public static final int PLAYING = 4;
	public static final int CREDIT = 5;
	public static final int BACKGROUND = 6;
	public static final int SCORE = 7;

	public static Music bgMusic;

	private static int select_P1;
	private static int select_P2;

	public GameStateManager() {
		
		setState(START);
	}

	public void setState(int state) {
		
		
		if (gameState != null)
			gameState.dispose();

		if (state == START) {
			
			if(isPlaying == false) {
				isPlaying = true;
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
				bgMusic.setVolume(0.5f);
				bgMusic.play();
				bgMusic.setLooping(true);
			}

			gameState = new StartState(this);

		} else if (state == MENU) {
			
			if(isPlaying == false) {
				isPlaying = true;
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
				bgMusic.setVolume(0.5f);
				bgMusic.play();
				bgMusic.setLooping(true);
			}
			gameState = new MenuState(this);
		} else if (state == SELECT) {
			
			if(isPlaying == false) {
				isPlaying = true;
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
				bgMusic.setVolume(0.5f);
				bgMusic.play();
				bgMusic.setLooping(true);
			}
			
			gameState = new SelectState(this);
		} else if (state == TUTORIAL) {
			
			if(isPlaying == false) {
				isPlaying = true;
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
				bgMusic.setVolume(0.5f);
				bgMusic.play();
				bgMusic.setLooping(true);
			}
			
			gameState = new TutorialState(this);
		} else if (state == CREDIT) {
			
			if(isPlaying == false) {
				isPlaying = true;
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/2-NullField.mp3"));
				bgMusic.setVolume(0.5f);
				bgMusic.play();
				bgMusic.setLooping(true);
			}
			
			gameState = new CreditState(this);
			
		} else if (state == PLAYING) {
			bgMusic.dispose();
			isPlaying = false;
			bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/1-ThisisWar.mp3"));
			bgMusic.play();
			bgMusic.setLooping(true);
			bgMusic.setVolume(0.5f);
			gameState = new PlayingState(this);
		} else if (state == BACKGROUND) {
			gameState = new SelectBackgroundState(this);
		} else if (state == SCORE) {
			
			bgMusic.dispose();
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
