package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayingState extends State {

	public static final float WORLD_WIDTH = 2500;
	public static final float WORLD_HEIGHT = 1080;

	public static float WIDTH = Gdx.graphics.getWidth();
	public static float HEIGHT = Gdx.graphics.getHeight();

	public static OrthographicCamera camera;

	public static float GROUND = 150;

	private int TIME;
	private float countDOWN = 0;

	private Number spriteTime;
	private Number P1Hit;
	private Number P2Hit;

	private Actor PLAYER1;
	private Actor PLAYER2;

	private int P1Hitcount;
	private int P2Hitcount;
	private int P1Punchcount;
	private int P2Punchcount;
	private int P1Kickcount;
	private int P2Kickcount;

	private float centerPX = 0;
	private float centerPY = 0;
	public static float cpox = 0;
	public static float cpoy = 0;

	private SpriteBatch batch;
	private Sprite bg;

	private Sprite hpbar1;
	private Sprite hpbar2;
	private Sprite hp1;
	private Sprite hp2;
	private Sprite hpback1;
	private Sprite hpback2;

	private Sprite stmbar1;
	private Sprite stmbar2;

	private float DELAY;

	float hpP1;
	float hpP2;
	float hpbackP1;
	float hpbackP2;
	float stmP1;
	float stmP2;

	private int STATE = 0;
	private int PLAY = 0;
	private int PAUSE = 1;
	private int REGAME = 2;
	private int WAIT = 3;
	private int GAMESTART = 4;

	private boolean menu = false;
	private int round = 1;

	private Sprite[] TEXT;

	private int lable;
	//

	private int pointP1 = 0;
	private int pointP2 = 0;

	private Sprite tagP1;
	private Sprite tagP2;

	private Sprite[] wintag;

	private Sprite textHit1;

	private Sprite textHit2;

	public PlayingState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {

		batch = new SpriteBatch();

		bg = new Sprite(new Texture(Gdx.files.internal("background/MAP.png")));

		hpbar1 = new Sprite(new Texture(Gdx.files.internal("gui/playing/hpbar.png")));
		hpbar2 = new Sprite(new Texture(Gdx.files.internal("gui/playing/hpbar.png")));
		hp1 = new Sprite(new Texture(Gdx.files.internal("gui/playing/hp.png")));
		hp2 = new Sprite(new Texture(Gdx.files.internal("gui/playing/hp.png")));
		hpback1 = new Sprite(new Texture(Gdx.files.internal("gui/playing/backhp.png")));
		hpback2 = new Sprite(new Texture(Gdx.files.internal("gui/playing/backhp.png")));

		stmbar1 = new Sprite(new Texture(Gdx.files.internal("gui/playing/stm.png")));
		stmbar2 = new Sprite(new Texture(Gdx.files.internal("gui/playing/stm.png")));

		tagP1 = new Sprite(new Texture(Gdx.files.internal("gui/tag/tagP1.png")));
		tagP2 = new Sprite(new Texture(Gdx.files.internal("gui/tag/tagP2.png")));

		wintag = new Sprite[4];

		for (int i = 0; i < 4; i++) {
			wintag[i] = new Sprite(new Texture(Gdx.files.internal("gui/tag/win.png")));
		}

		TEXT = new Sprite[9];
		for (int i = 0; i < 9; i++) {
			TEXT[i] = new Sprite(new Texture(Gdx.files.internal("gui/status/" + (i) + ".png")));
		}

		camera = new OrthographicCamera(WIDTH, HEIGHT);

		camera.position.set(WORLD_WIDTH / 2, HEIGHT / 2, 0);

		if (SelectState.select_P1 == 0) {
			PLAYER1 = new Nox(InputManager.KEY_D, InputManager.KEY_A, InputManager.KEY_W, InputManager.KEY_S,
					InputManager.KEY_V, InputManager.KEY_B, false, 1);
		} else if (SelectState.select_P1 == 1) {
			PLAYER1 = new Mato(InputManager.KEY_D, InputManager.KEY_A, InputManager.KEY_W, InputManager.KEY_S,
					InputManager.KEY_V, InputManager.KEY_B, false, 1);
		}

		if (SelectState.select_P2 == 0) {
			PLAYER2 = new Nox(InputManager.KEY_LEFT, InputManager.KEY_RIGHT, InputManager.KEY_UP, InputManager.KEY_DOWN,
					InputManager.KEY_I, InputManager.KEY_O, true, 2);
		} else if (SelectState.select_P2 == 1) {
			PLAYER2 = new Mato(InputManager.KEY_LEFT, InputManager.KEY_RIGHT, InputManager.KEY_UP,
					InputManager.KEY_DOWN, InputManager.KEY_I, InputManager.KEY_O, true, 2);
		}

		PLAYER1.init();
		PLAYER2.init();

		PLAYER1.setAnotherPlayer(PLAYER2);
		PLAYER2.setAnotherPlayer(PLAYER1);

		centerPX = Math.abs(PLAYER1.POX - PLAYER2.POX);
		centerPY = Math.abs(PLAYER1.POY - PLAYER2.POY);
		cpox = PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 + centerPX / 2;
		cpoy = centerPY / 2 + HEIGHT / 2;

		hpP1 = PLAYER1.HP;
		hpP2 = PLAYER2.HP;

		hpbackP1 = PLAYER1.HP;
		hpbackP2 = PLAYER2.HP;

		stmP1 = PLAYER1.STAMINA;
		stmP2 = PLAYER2.STAMINA;

		TIME = 99;

		spriteTime = new Number();

		// hit
		textHit1 = new Sprite(new Texture(Gdx.files.internal("gui/tag/hit.png")));
		textHit1.setAlpha(0);
		P1Hit = new Number();
		P1Hit.setAlpha(0);

		textHit2 = new Sprite(new Texture(Gdx.files.internal("gui/tag/hit.png")));
		textHit2.setAlpha(0);
		P2Hit = new Number();
		P2Hit.setAlpha(0);

		STATE = GAMESTART;
		DELAY = 3;

	}

	@Override
	public void draw() {

		batch.begin();

		bg.draw(batch);

		batch.setProjectionMatrix(camera.combined);

		spriteTime.draw(batch);

		hpbar1.draw(batch);
		hpbar2.draw(batch);

		hpback1.draw(batch);
		hpback1.setAlpha(0.7f);
		hpback2.draw(batch);
		hpback2.setAlpha(0.7f);

		hp1.draw(batch);
		hp2.draw(batch);

		stmbar1.draw(batch);
		stmbar2.draw(batch);

		PLAYER2.draw(batch);
		PLAYER1.draw(batch);

		tagP1.draw(batch);
		tagP2.draw(batch);

		P1Hit.draw(batch);
		P2Hit.draw(batch);

		textHit1.draw(batch);
		textHit2.draw(batch);

		for (Sprite item : wintag) {
			item.draw(batch);
			item.setSize(30, 30);
			item.setAlpha(0f);
		}

		for (Sprite item : TEXT) {
			item.draw(batch);
			item.setAlpha(0f);
		}

		batch.end();

	}

	@Override
	public void update(float dt) {
		handle();
		// Update Camera

		centerPX = Math.abs(PLAYER1.POX - PLAYER2.POX);
		centerPY = Math.abs(PLAYER1.POY - PLAYER2.POY);
		// mirror
		if (PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 > PLAYER2.POX + PLAYER2.SActor.getWidth() / 2
				&& (PLAYER1.POY <= GROUND)) {

			PLAYER1.setBN_BACK(InputManager.KEY_D);
			PLAYER1.setBN_FRONT(InputManager.KEY_A);

			PLAYER1.setMIRROR(true);

		}
		if (PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 < PLAYER2.POX + PLAYER2.SActor.getWidth() / 2
				&& (PLAYER1.POY <= GROUND)) {

			PLAYER1.setBN_BACK(InputManager.KEY_A);
			PLAYER1.setBN_FRONT(InputManager.KEY_D);

			PLAYER1.setMIRROR(false);

		}
		if (PLAYER2.POX + PLAYER2.SActor.getWidth() / 2 > PLAYER1.POX + PLAYER1.SActor.getWidth() / 2
				&& (PLAYER2.POY <= GROUND)) {

			PLAYER2.setBN_BACK(InputManager.KEY_RIGHT);
			PLAYER2.setBN_FRONT(InputManager.KEY_LEFT);

			PLAYER2.setMIRROR(true);

		}
		if (PLAYER2.POX + PLAYER2.SActor.getWidth() / 2 < PLAYER1.POX + PLAYER1.SActor.getWidth() / 2
				&& (PLAYER2.POY <= GROUND)) {

			PLAYER2.setBN_BACK(InputManager.KEY_LEFT);
			PLAYER2.setBN_FRONT(InputManager.KEY_RIGHT);

			PLAYER2.setMIRROR(false);

		}

		// camera control
		if (!PLAYER1.mirror) {
			cpox = PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 + centerPX / 2;
		} else if (PLAYER1.mirror) {
			cpox = PLAYER2.POX + PLAYER2.SActor.getWidth() / 2 + centerPX / 2;
		}

		cpoy = centerPY / 2 + HEIGHT / 2;

		// camera

		if (camera.position.x < cpox && Math.abs(camera.position.x - cpox) < 10
				&& camera.position.x + WIDTH / 2 < WORLD_WIDTH) {
			camera.translate(1, 0f);
		} else if (camera.position.x > cpox && Math.abs(camera.position.x - cpox) < 10
				&& camera.position.x - WIDTH / 2 > 0) {
			camera.translate(-1, 0f);
		} else if (camera.position.x < cpox && camera.position.x + WIDTH / 2 < WORLD_WIDTH) {
			camera.translate(300 * dt, 0f);
		} else if (camera.position.x > cpox && camera.position.x - WIDTH / 2 > 0) {
			camera.translate(-300 * dt, 0f);
		} else {
			camera.translate(0f, 0f);
		}

		if (camera.position.y < cpoy && Math.abs(camera.position.y - cpoy) < 10
				&& camera.position.y + HEIGHT / 2 < WORLD_HEIGHT) {
			camera.translate(0, 1f);
		} else if (camera.position.y > cpoy && Math.abs(camera.position.y - cpoy) < 10
				&& camera.position.y - HEIGHT / 2 > 0) {
			camera.translate(0, -1f);
		} else if (camera.position.y < cpoy && camera.position.y + HEIGHT / 2 < WORLD_HEIGHT
				&& camera.position.y + HEIGHT / 2 < HEIGHT + 300) {
			camera.translate(0, +150 * dt);
		} else if (camera.position.y > cpoy && camera.position.y - HEIGHT / 2 > 0) {
			camera.translate(0, -150 * dt);
		}

		// GUI update
		DELAY -= dt;
		if (DELAY <= 0) {
			DELAY = 0;
		}

		hpbar1.setPosition(camera.position.x - WIDTH / 2 + 20,
				camera.position.y + HEIGHT / 2 - hpbar1.getHeight() - 20);
		hpbar2.setPosition(camera.position.x + WIDTH / 2 - 20,
				camera.position.y + HEIGHT / 2 - hpbar2.getHeight() - 20);

		hp1.setPosition(camera.position.x - 80, camera.position.y + HEIGHT / 2 - hpbar1.getHeight() + 20);
		hp2.setPosition(camera.position.x + WIDTH / 2 - 500 - 58,
				camera.position.y + HEIGHT / 2 - hpbar1.getHeight() + 20);

		hpback1.setPosition(camera.position.x - 80, camera.position.y + HEIGHT / 2 - hpbar1.getHeight() + 20);
		hpback2.setPosition(camera.position.x + WIDTH / 2 - 500 - 58,
				camera.position.y + HEIGHT / 2 - hpbar1.getHeight() + 20);

		stmbar1.setPosition(camera.position.x - WIDTH / 2 + 200 * 3 - 40,
				camera.position.y + HEIGHT / 2 - hpbar1.getHeight() - 10);
		stmbar2.setPosition(camera.position.x - WIDTH / 2 + 200 * 3 + 122,
				camera.position.y + HEIGHT / 2 - hpbar1.getHeight() - 10);

		// update HP
		if (hpP1 <= 0 && STATE != REGAME && STATE != WAIT) {
			hpP1 = 0;
			lable = 7;
			pointP2 += 1;
			DELAY = 2;
			STATE = WAIT;
		}
		if (hpP2 <= 0 && STATE != REGAME && STATE != WAIT) {
			hpP2 = 0;
			lable = 7;
			pointP1 += 1;
			DELAY = 2;
			STATE = WAIT;
		}
		if (hpbackP1 <= 0) {
			hpbackP1 = 0;
		}
		if (hpbackP2 <= 0) {
			hpbackP2 = 0;
		}
		if (hpP1 > PLAYER1.HP && hpP1 >= 0) {
			hpP1 -= 100 * dt;
			DELAY = 0.5f;
		}
		if (hpP2 > PLAYER2.HP && hpP2 >= 0) {
			hpP2 -= 100 * dt;
			DELAY = 0.5f;
		}

		if (PLAYER1.STATUS == PLAYER1.HIT) {
			textHit2.setAlpha(1);
			P2Hit.setAlpha(1);
		}
		if (PLAYER2.STATUS == PLAYER2.HIT) {
			textHit1.setAlpha(1);
			P1Hit.setAlpha(1);
		}

		if (DELAY <= 0) {
			if (hpbackP1 > PLAYER1.HP && hpbackP1 >= 0) {

				hpbackP1 -= 100 * dt;
				
				P2Hitcount += PLAYER2.hitCount;
				
				PLAYER2.hitCount = 0;

				for (int i = 100; i > 0; i--) {
					textHit2.setAlpha(i * dt);
					P2Hit.setAlpha(i * dt);
				}
			}
			if (hpbackP2 > PLAYER2.HP && hpbackP2 >= 0) {
				hpbackP2 -= 100 * dt;

				P1Hitcount += PLAYER1.hitCount;
				
				PLAYER1.hitCount = 0;

				for (int i = 100; i > 0; i--) {
					textHit1.setAlpha(i * dt);
					P1Hit.setAlpha(i * dt);
				}
			}

		}
		// STAMINA
		if (stmP1 <= 0) {
			stmP1 = 0;
		}
		if (stmP1 > 200) {
			stmP1 = 200;
		}
		if (stmP1 > PLAYER1.STAMINA) {
			stmP1 -= 100 * dt;
		}
		if (stmP1 < PLAYER1.STAMINA && PLAYER1.STMDELAY <= 0) {
			stmP1 += 100 * dt;
		}

		if (stmP2 <= 0) {
			stmP2 = 0;
		}
		if (stmP2 >= 200) {
			stmP2 = 200;
		}
		if (stmP2 > PLAYER2.STAMINA) {
			stmP2 -= 100 * dt;
		}
		if (stmP2 < PLAYER2.STAMINA && PLAYER2.STMDELAY <= 0) {
			stmP2 += 100 * dt;
		}
		// SetSize
		hp1.setSize(-1 * hpP1, 20);
		hp2.setSize(hpP2, 20);

		hpback1.setSize(-1 * hpbackP1, 20);
		hpback2.setSize(hpbackP2, 20);

		hpbar2.setSize(-hpbar1.getWidth(), hpbar1.getHeight());

		stmbar1.setSize(-1 * stmP1, 15);
		stmbar2.setSize(stmP2, 15);

		for (Sprite item : TEXT) {
			item.setPosition(camera.position.x - WIDTH / 2, camera.position.y - HEIGHT / 2);
		}
		if (STATE != GAMESTART && STATE != WAIT) {
			PLAYER2.update(dt);
			PLAYER1.update(dt);
		}

		camera.update();

		// GAMEUPDATE
		if (STATE == PAUSE) {
			if (PLAYER1.STATUS != PLAYER1.STOP) {

				PLAYER1.STATUS_PAUSE_GAME = PLAYER1.STATUS;
			}
			PLAYER1.STATUS = PLAYER1.STOP;

			if (PLAYER2.STATUS != PLAYER2.STOP) {

				PLAYER2.STATUS_PAUSE_GAME = PLAYER2.STATUS;
			}
			PLAYER2.STATUS = PLAYER2.STOP;
		}
		if (STATE == GAMESTART) {
			if (DELAY >= 1) {
				TEXT[round].setAlpha(1f);
			}
			if (DELAY <= 0.7) {
				TEXT[0].setAlpha(1f);
			}
			if (DELAY <= 0) {
				STATE = PLAY;
			}
		}
		if (STATE == REGAME) {
			init();
		}
		if (STATE == WAIT) {

			TEXT[lable].setAlpha(1f);

			if (DELAY <= 1) {
				if (pointP1 >= 2) {
					lable = 4;
				} else if (pointP2 >= 2) {
					lable = 5;
				} else if (pointP1 == pointP2 && round == 3) {
					lable = 6;
				}
			}

			if (DELAY <= 0) {
				round += 1;

				if (pointP1 >= 2 || pointP2 >= 2) {
					System.out.println("===========================");
					System.out.println("PLAYER 1");
					System.out.println("Pressed Punch Button : "+ P1Punchcount + " time");
					System.out.println("Pressed Kick  Button : "+ P1Kickcount + " time");
					System.out.println("Hit Success : "+ P1Hitcount + " time");
					
					System.out.println("===========================");
					System.out.println("PLAYER 2");
					System.out.println("Pressed Punch Button : "+ P2Punchcount + " time");
					System.out.println("Pressed Kick  Button : "+ P2Kickcount + " time");
					System.out.println("Hit Success : "+ P2Hitcount + " time");
					
					gsm.setState(GameStateManager.MENU);
				} else if (pointP1 >= 2) {
					gsm.setState(GameStateManager.MENU);
				} else if (pointP2 >= 2) {
					gsm.setState(GameStateManager.MENU);
				} else {
					STATE = REGAME;
				}
			}

		}
		// Timer

		spriteTime.setPosition(camera.position.x - 50, camera.position.y + HEIGHT / 2 - 100);

		if (STATE != PAUSE && STATE != GAMESTART && STATE != WAIT) {
			countDOWN += dt;
			if (countDOWN >= 1) {
				countDOWN = 0;
				TIME -= 1;
			}
			if (TIME <= 0) {
				TIME = 0;
			}

			spriteTime.setNumber(TIME);

			if (TIME == 0 && STATE != WAIT & STATE != REGAME) {
				lable = 8;

				if (PLAYER1.HP > PLAYER2.HP) {
					pointP1 += 1;
				} else if (PLAYER2.HP > PLAYER1.HP) {
					pointP2 += 1;
				} else if (PLAYER2.HP == PLAYER1.HP) {
					pointP1 += 1;
					pointP2 += 1;
				}
				DELAY = 3;
				STATE = WAIT;
			}
		}

		tagP1.setPosition(PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 - tagP1.getWidth() / 2, GROUND - 90);
		tagP2.setPosition(PLAYER2.POX + PLAYER2.SActor.getWidth() / 2 - tagP2.getWidth() / 2, GROUND - 90);

		wintag[0].setPosition(camera.position.x - WIDTH / 2 + 20, camera.position.y + HEIGHT / 2 - 100);
		wintag[1].setPosition(camera.position.x - WIDTH / 2 + 20 + wintag[0].getWidth() + 20,
				camera.position.y + HEIGHT / 2 - 100);

		wintag[2].setPosition(camera.position.x + WIDTH / 2 - 20 - wintag[0].getWidth(),
				camera.position.y + HEIGHT / 2 - 100);
		wintag[3].setPosition(camera.position.x + WIDTH / 2 - 20 - wintag[0].getWidth() * 2 - 20,
				camera.position.y + HEIGHT / 2 - 100);

		P1Hit.setNumber(PLAYER1.hitCount);

		P2Hit.setNumber(PLAYER2.hitCount);

		textHit1.setPosition(camera.position.x - WIDTH / 2 + 40 + P1Hit.getWidth(), camera.position.y);
		P1Hit.setPosition(camera.position.x - WIDTH / 2 + 40, camera.position.y);

		textHit2.setPosition(camera.position.x + WIDTH / 2 - 40 - P2Hit.getWidth() - textHit2.getWidth(),
				camera.position.y);
		P2Hit.setPosition(camera.position.x + WIDTH / 2 - 40 - P2Hit.getWidth(), camera.position.y);

		for (int i = 0; i < pointP1; i++) {
			wintag[i].setAlpha(1);
		}

		for (int i = 0; i < pointP2; i++) {
			wintag[i + 2].setAlpha(1);
		}

		if (menu == true) {

		}

	}

	@Override
	public void handle() {
		if (InputManager.keyIspressed(InputManager.KEY_ESC)) {

			if (STATE == PLAY) {
				STATE = PAUSE;
				menu = true;

			} else if (STATE == PAUSE) {
				STATE = PLAY;
				PLAYER1.STATUS = PLAYER1.STATUS_PAUSE_GAME;
				PLAYER2.STATUS = PLAYER2.STATUS_PAUSE_GAME;
				menu = false;

			}

		}

		if (InputManager.keyIspressed(PLAYER1.BN_PUNCH)) {
			P1Punchcount += 1;
		}
		if (InputManager.keyIspressed(PLAYER1.BN_KICK)) {
			P1Kickcount += 1;
		}

		if (InputManager.keyIspressed(PLAYER2.BN_PUNCH)) {
			P2Punchcount += 1;
		}
		if (InputManager.keyIspressed(PLAYER2.BN_KICK)) {
			P2Kickcount += 1;
		}

	}

	@Override
	public void dispose() {
		PLAYER1.dispose();
		PLAYER2.dispose();

	}

}
