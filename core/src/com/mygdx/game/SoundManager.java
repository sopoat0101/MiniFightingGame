package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	public static final int ROUND1 = 0;
	public static final int ROUND2 = 1;
	public static final int ROUND3 = 2;
	public static final int FIGHT = 3;
	public static final int KO = 4;
	
	private static boolean canplay = false;
	
	private Sound Sound[];
	
	public SoundManager() {
		
		Sound = new Sound[5];
		
		Sound[0] = Gdx.audio.newSound(Gdx.files.internal("sound/fx/round1.wav"));
		Sound[1] = Gdx.audio.newSound(Gdx.files.internal("sound/fx/round2.wav"));
		Sound[2] = Gdx.audio.newSound(Gdx.files.internal("sound/fx/finalround.wav"));
		Sound[3] = Gdx.audio.newSound(Gdx.files.internal("sound/fx/fig.wav"));
		Sound[4] = Gdx.audio.newSound(Gdx.files.internal("sound/fx/KO.wav"));
		
	}
	
	public void play(int select) {
		
		if(canplay == true) {
			Sound[select].play(0.5f);
			canplay = false;
		}
		
	}
	
	public void setCanPlay(boolean set) {
		canplay = set;
	}
	
}
