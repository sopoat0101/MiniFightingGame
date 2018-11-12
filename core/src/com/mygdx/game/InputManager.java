package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputManager extends InputAdapter {

	public static final int NUMKEYS = 14;

	public static final int KEY_SPACE = 0;
	public static final int KEY_ESC = 1;
	public static final int KEY_A = 2;
	public static final int KEY_W = 3;
	public static final int KEY_S = 4;
	public static final int KEY_D = 5;
	public static final int KEY_V = 6;
	public static final int KEY_B = 7;
	public static final int KEY_LEFT = 8;
	public static final int KEY_UP = 9;
	public static final int KEY_DOWN = 10;
	public static final int KEY_RIGHT = 11;
	public static final int KEY_O = 12;
	public static final int KEY_P = 13;

	private static boolean[] SKEY;
	private static boolean[] PSKEY;

	static {
		SKEY = new boolean[NUMKEYS];
		PSKEY = new boolean[NUMKEYS];
	}

	public static boolean keyIsdown(int keycode) {
		return SKEY[keycode];
	}

	public static boolean keyIspressed(int keycode) {
		return SKEY[keycode] && !PSKEY[keycode];
	}

	public static boolean keyIsup(int keycode) {
		return !SKEY[keycode];
	}

	public static void keysUpdate() {
		for (int i = 0; i < SKEY.length; i++) {
			PSKEY[i] = SKEY[i];
		}
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE) {
			SKEY[KEY_SPACE] = true;
		}
		if (keycode == Keys.ESCAPE) {
			SKEY[KEY_ESC] = true;
		}
		if (keycode == Keys.A) {
			SKEY[KEY_A] = true;
		}
		if (keycode == Keys.W) {
			SKEY[KEY_W] = true;
		}
		if (keycode == Keys.S) {
			SKEY[KEY_S] = true;
		}
		if (keycode == Keys.D) {
			SKEY[KEY_D] = true;
		}
		if (keycode == Keys.V) {
			SKEY[KEY_V] = true;
		}
		if (keycode == Keys.B) {
			SKEY[KEY_B] = true;
		}
		if (keycode == Keys.LEFT) {
			SKEY[KEY_LEFT] = true;
		}
		if (keycode == Keys.UP) {
			SKEY[KEY_UP] = true;
		}
		if (keycode == Keys.DOWN) {
			SKEY[KEY_DOWN] = true;
		}
		if (keycode == Keys.RIGHT) {
			SKEY[KEY_RIGHT] = true;
		}
		if (keycode == Keys.O) {
			SKEY[KEY_O] = true;
		}
		if (keycode == Keys.P) {
			SKEY[KEY_P] = true;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {

		if (keycode == Keys.SPACE) {
			SKEY[KEY_SPACE] = false;
		}
		if (keycode == Keys.ESCAPE) {
			SKEY[KEY_ESC] = false;
		}
		if (keycode == Keys.A) {
			SKEY[KEY_A] = false;
		}
		if (keycode == Keys.W) {
			SKEY[KEY_W] = false;
		}
		if (keycode == Keys.S) {
			SKEY[KEY_S] = false;
		}
		if (keycode == Keys.D) {
			SKEY[KEY_D] = false;
		}
		if (keycode == Keys.V) {
			SKEY[KEY_V] = false;
		}
		if (keycode == Keys.B) {
			SKEY[KEY_B] = false;
		}
		if (keycode == Keys.LEFT) {
			SKEY[KEY_LEFT] = false;
		}
		if (keycode == Keys.UP) {
			SKEY[KEY_UP] = false;
		}
		if (keycode == Keys.DOWN) {
			SKEY[KEY_DOWN] = false;
		}
		if (keycode == Keys.RIGHT) {
			SKEY[KEY_RIGHT] = false;
		}
		if (keycode == Keys.O) {
			SKEY[KEY_O] = false;
		}
		if (keycode == Keys.P) {
			SKEY[KEY_P] = false;
		}
		return true;
	}
}
