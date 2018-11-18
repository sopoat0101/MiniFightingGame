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

	public static float GROUND = 100;

	private Actor PLAYER1;
	private Actor PLAYER2;

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
	
	private float DELAY = 0;

	float hpP1;
	float hpP2;
	float hpbackP1;
	float hpbackP2;
	float stmP1;
	float stmP2;
	
	private int STATE = 0;
	private int PLAY = 0;
	private int PAUSE = 1;

	public PlayingState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {

		batch = new SpriteBatch();

		bg = new Sprite(new Texture(Gdx.files.internal("../core/assets/background/playingmap.png")));

		hpbar1 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/hpbar.png")));
		hpbar2 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/hpbar.png")));
		hp1 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/hp.png")));
		hp2 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/hp.png")));
		hpback1 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/backhp.png")));
		hpback2 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/backhp.png")));

		stmbar1 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/stm.png")));
		stmbar2 = new Sprite(new Texture(Gdx.files.internal("../core/assets/gui/playing/stm.png")));
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);

		camera.position.set(WORLD_WIDTH / 2, HEIGHT / 2, 0);

		if (gsm.getSelect_P1() == 0) {

		}

		PLAYER1 = new Nox(InputManager.KEY_D, InputManager.KEY_A, InputManager.KEY_W, InputManager.KEY_S,
				InputManager.KEY_V, InputManager.KEY_B, false, 1);

		PLAYER2 = new Nox(InputManager.KEY_LEFT, InputManager.KEY_RIGHT, InputManager.KEY_UP, InputManager.KEY_DOWN,
				InputManager.KEY_O, InputManager.KEY_P, true, 2);

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

	}

	@Override
	public void draw() {

		batch.begin();

		bg.draw(batch);
		
		batch.setProjectionMatrix(camera.combined);

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

		batch.end();

	}

	@Override
	public void update(float dt) {
		handle();

		// Update Camera

		centerPX = Math.abs(PLAYER1.POX - PLAYER2.POX);
		centerPY = Math.abs(PLAYER1.POY - PLAYER2.POY);
		// mirror
		if (PLAYER1.POX > PLAYER2.POX && (PLAYER1.POY <= GROUND && PLAYER2.POY <= GROUND)) {

			PLAYER1.setBN_BACK(InputManager.KEY_D);
			PLAYER1.setBN_FRONT(InputManager.KEY_A);
			PLAYER2.setBN_BACK(InputManager.KEY_LEFT);
			PLAYER2.setBN_FRONT(InputManager.KEY_RIGHT);
			
			PLAYER1.setMIRROR(true);
			PLAYER2.setMIRROR(false);

		}
		if (PLAYER2.POX > PLAYER1.POX && (PLAYER1.POY <= GROUND && PLAYER2.POY <= GROUND)) {

			PLAYER1.setBN_BACK(InputManager.KEY_A);
			PLAYER1.setBN_FRONT(InputManager.KEY_D);
			PLAYER2.setBN_BACK(InputManager.KEY_RIGHT);
			PLAYER2.setBN_FRONT(InputManager.KEY_LEFT);
			
			PLAYER1.setMIRROR(false);
			PLAYER2.setMIRROR(true);

		}
		// camera control
		if (!PLAYER1.mirror) {
			cpox = PLAYER1.POX + PLAYER1.SActor.getWidth() / 2 + centerPX / 2;
		} else if (PLAYER1.mirror) {
			cpox = PLAYER2.POX + PLAYER2.MSActor.getWidth() / 2 + centerPX / 2;
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

		stmbar1.setPosition(camera.position.x - WIDTH/2 + 200*3 - 40, camera.position.y + HEIGHT / 2 - hpbar1.getHeight() - 10);
		stmbar2.setPosition(camera.position.x - WIDTH/2 + 200*3 + 122, camera.position.y + HEIGHT / 2 - hpbar1.getHeight() - 10);
		
		
		// update HP
		if (hpP1 <= 0) {
			hpP1 = 0;
		}
		if (hpP2 <= 0) {
			hpP2 = 0;
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
		if (DELAY <= 0) {
			if (hpbackP1 > PLAYER1.HP && hpbackP1 >= 0) {

				hpbackP1 -= 100 * dt;
			}
			if (hpbackP2 > PLAYER2.HP && hpbackP2 >= 0) {
				hpbackP2 -= 100 * dt;
			}
		}
		//STAMINA
		if(stmP1 <= 0) {
			stmP1 = 0;
		}
		if(stmP1 > 200) {
			stmP1 = 200;
		}
		if(stmP1 > PLAYER1.STAMINA) {
			stmP1 -= 100 * dt;
		}
		if(stmP1 < PLAYER1.STAMINA && PLAYER1.STMDELAY <= 0) {
			stmP1 += 100 * dt;
		}
		
		if(stmP2 <= 0) {
			stmP2 = 0;
		}
		if(stmP2 >= 200) {
			stmP2 = 200;
		}
		if(stmP2 > PLAYER2.STAMINA) {
			stmP2 -= 100 * dt;
		}
		if(stmP2 < PLAYER2.STAMINA && PLAYER2.STMDELAY <= 0) {
			stmP2 += 100 * dt;
		}
		//SetSize
		hp1.setSize(-1 * hpP1, 20);
		hp2.setSize(hpP2, 20);

		hpback1.setSize(-1 * hpbackP1, 20);
		hpback2.setSize(hpbackP2, 20);

		hpbar2.setSize(-hpbar1.getWidth(), hpbar1.getHeight());
		
		stmbar1.setSize(-1 * stmP1, 15);
		stmbar2.setSize(stmP2, 15);
		
		PLAYER1.update(dt);
		PLAYER2.update(dt);
		
		camera.update();

		
		//GAMEUPDATE
		if(STATE == PAUSE) {
			if(PLAYER1.STATUS != PLAYER1.STOP) {
				
				PLAYER1.STATUS_PAUSE_GAME = PLAYER1.STATUS;
			}
			PLAYER1.STATUS = PLAYER1.STOP;
			
			if(PLAYER2.STATUS != PLAYER2.STOP) {
				
				PLAYER2.STATUS_PAUSE_GAME = PLAYER2.STATUS;
			}
			PLAYER2.STATUS = PLAYER2.STOP;
		}
		
//		System.out.println(PLAYER1.DELAY);
		
	}

	@Override
	public void handle() {
		if (InputManager.keyIspressed(InputManager.KEY_ESC)) {
			
			if(STATE == PLAY) {
				STATE = PAUSE;
				
			}else if(STATE == PAUSE) {
				STATE = PLAY;
				PLAYER1.STATUS = PLAYER1.STATUS_PAUSE_GAME;
				PLAYER2.STATUS = PLAYER2.STATUS_PAUSE_GAME;

			}
			
		}
		if (InputManager.keyIsdown(InputManager.KEY_SPACE)) {
			camera.translate(0f, 5);
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		PLAYER1.dispose();
		PLAYER2.dispose();
		
	}

}
