package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectState extends State {

	public static int select_P1 = 0;
	public static int select_P2 = 0;
	public static final int NOX = 0;
	public static final int MATO = 1;
	private Sprite bg, nox1, mato1, nox2, mato2, blank1, blank2, blank3, blank4, frame1, frame2, hero1, hero2, ready1, ready2, tutorial, exit, select;
	private SpriteBatch batch;
	private Texture _nox, _mato, locked, noxMain, matoMain, ready;
	private boolean P1_onready = false, P2_onready = false;
	private Color P1, P2;
	private float alpha = .0f;

	private Sound bgsound;
	
	public SelectState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		bgsound = Gdx.audio.newSound(Gdx.files.internal("sound/Stranger_Danger.mp3"));
		bgsound.loop(0.5f, 1.0f, 0.0f);
		
		_nox = new Texture(Gdx.files.internal("gui/select/NoxFrame.png"));
		_mato = new Texture(Gdx.files.internal("gui/select/MatoFrame.png"));
		locked = new Texture(Gdx.files.internal("gui/select/dlc.png"));
		noxMain = new Texture(Gdx.files.internal("Actor/Nox/mirror/01.png"));
		matoMain = new Texture(Gdx.files.internal("Actor/Mato/mirror/01.png"));
		ready = new Texture("gui/select/readyGUI.png");
		hero1 = new Sprite(noxMain);
		hero2 = new Sprite(noxMain);
		select = new Sprite((new Texture(Gdx.files.internal("gui/select/selectGUI.png"))));
		bg = new Sprite(new Texture(Gdx.files.internal("background/MenuBackground.png")));
		nox1 = new Sprite(_nox);
		mato1 = new Sprite(_mato);
		blank1 = new Sprite(locked);
		blank2 = new Sprite(locked);
		nox2 = new Sprite(_nox);
		mato2 = new Sprite(_mato);
		blank3 = new Sprite(locked);
		blank4 = new Sprite(locked);
		ready1 = new Sprite(ready);
		ready2 = new Sprite(ready);
		tutorial = new Sprite(new Texture(Gdx.files.internal("gui/select/tutorialGUI.png")));
		exit = new Sprite(new Texture(Gdx.files.internal("gui/select/exitGUI.png")));
		frame1 = new Sprite(new Texture(Gdx.files.internal("gui/select/frameP1.png")));
		frame2 = new Sprite(new Texture(Gdx.files.internal("gui/select/frameP2.png")));
		P1 = new Color(hero1.getColor());
		P2 = new Color(hero2.getColor());
		
		select.setPosition(0, 525);
		
		nox1.setPosition(25, 100);
		nox1.setScale(0.5f, 0.5f);
		mato1.setPosition(25+125, 100);
		mato1.setScale(0.5f, 0.5f);
		blank1.setPosition(25+125+125, 100);
		blank1.setScale(0.5f, 0.5f);
		blank2.setPosition(25+(125*3), 100);
		blank2.setScale(0.5f, 0.5f);
		
		nox2.setPosition(1050, 100);
		nox2.setScale(0.5f, 0.5f);
		nox2.setFlip(true, false);
		mato2.setPosition(1050-125, 100);
		mato2.setScale(0.5f, 0.5f);
		mato2.setFlip(true, false);
		blank3.setPosition(1050-250, 100);
		blank3.setScale(0.5f, 0.5f);
		blank4.setPosition(1050-375, 100);
		blank4.setScale(0.5f, 0.5f);
		
		hero1.setPosition(162.5f, 300);
		hero2.setPosition(800, 300);
		hero1.setFlip(true, false);
		ready1.setRotation(360-30f);
		ready2.setRotation(360-30f);
		ready1.setPosition(-320, 100);
		ready2.setPosition(320, 100);
		ready1.setAlpha(0);
		ready2.setAlpha(0);
		
		frame1.setPosition(nox1.getX()-5, nox1.getY());
		frame1.setScale(0.5f);
		frame2.setPosition(1045, 100);
		frame2.setScale(0.5f);
		
		tutorial.setPosition(0, 0f);
		
		exit.setPosition(0, 720-exit.getHeight()-10);
		
	}

	@Override
	public void draw() {
		batch = new SpriteBatch();
		batch.begin();
		bg.draw(batch);
		select.draw(batch);
		nox1.draw(batch);
		mato1.draw(batch);
		blank1.draw(batch);
		blank2.draw(batch);
		nox2.draw(batch);
		mato2.draw(batch);
		blank3.draw(batch);
		blank4.draw(batch);
		frame1.draw(batch);
		frame2.draw(batch);
		hero1.draw(batch);
		hero2.draw(batch);
		ready1.draw(batch);
		ready2.draw(batch);
		tutorial.draw(batch);
		exit.draw(batch);
		
		batch.end();

	}

	@Override
	public void update(float dt) {
		
		handle();
		
		alpha += dt;
		select.setAlpha((float)Math.abs(Math.sin(alpha)));
		if(P1_onready == true) {
			ready1.setAlpha((float)Math.abs(Math.sin(alpha)));
		}
		else {
			ready1.setAlpha(0);
		}
		
		if(P2_onready == true) {
			ready2.setAlpha((float)Math.abs(Math.sin(alpha)));
		}
		else {
			ready2.setAlpha(0);
		}

	}

	@Override
	public void handle() {
		
		//PLAYER1
		if (InputManager.keyIspressed(InputManager.KEY_D) && select_P1 == NOX && P1_onready == false) {
			frame1.setPosition(mato1.getX()-5, mato1.getY());
			select_P1 = MATO;
			hero1.setTexture(matoMain);
		}
		
		else if(InputManager.keyIspressed(InputManager.KEY_A) && select_P1 == NOX && P1_onready == false) {
			frame1.setPosition(mato1.getX()-5, mato1.getY());
			select_P1 = MATO;
			hero1.setTexture(matoMain);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_D) && select_P1 == MATO && P1_onready == false) {
			frame1.setPosition(nox1.getX()-5, 100);
			select_P1 = NOX;
			hero1.setTexture(noxMain);
		}
		
		else if(InputManager.keyIspressed(InputManager.KEY_A) && select_P1 == MATO && P1_onready == false) {
			frame1.setPosition(nox1.getX()-5, 100);
			select_P1 = NOX;
			hero1.setTexture(noxMain);
		}
		
		//PLAYER2
		else if (InputManager.keyIspressed(InputManager.KEY_LEFT) && select_P2 == NOX && P2_onready == false) {
			frame2.setPosition(mato2.getX()-5, mato2.getY());
			select_P2 = MATO;
			hero2.setTexture(matoMain);
		}
		
		else if(InputManager.keyIspressed(InputManager.KEY_RIGHT) && select_P2 == NOX && P2_onready == false) {
			frame2.setPosition(mato2.getX()-5, mato2.getY());
			select_P2 = MATO;
			hero2.setTexture(matoMain);
		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_LEFT) && select_P2 == MATO && P2_onready == false) {
			frame2.setPosition(nox2.getX()-5, 100);
			select_P2 = NOX;
			hero2.setTexture(noxMain);
		}
		
		else if(InputManager.keyIspressed(InputManager.KEY_RIGHT) && select_P2 == MATO && P2_onready == false) {
			frame2.setPosition(nox2.getX()-5, 100);
			select_P2 = NOX;
			hero2.setTexture(noxMain);
		}
		
		else if((InputManager.keyIspressed(InputManager.KEY_V)) && P1_onready == false) {
			P1_onready = true;
			P1 = hero1.getColor();
			hero1.setColor(Color.BLACK);
			ready1.setAlpha(1);
		}
		
		else if((InputManager.keyIspressed(InputManager.KEY_I)) && P2_onready == false) {
			P2_onready = true;
			P2 = hero2.getColor();
			hero2.setColor(Color.BLACK);
			ready2.setAlpha(1);
		}
		
		else if((InputManager.keyIspressed(InputManager.KEY_B)) && P1_onready == true) {
			P1_onready = false;
			hero1.setColor(P1);
			ready1.setAlpha(0);
		}
		
		else if((InputManager.keyIspressed(InputManager.KEY_O)) && P2_onready == true) {
			P2_onready = false;
			hero2.setColor(P2);
			ready2.setAlpha(0);
		}
		
		

		else if (InputManager.keyIspressed(InputManager.KEY_SPACE) && P1_onready == true && P2_onready == true) {

			
			gsm.setState(GameStateManager.BACKGROUND);

		}
		
		else if (InputManager.keyIspressed(InputManager.KEY_ESC)){

			
			gsm.setState(GameStateManager.MENU);

		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bgsound.dispose();
		
	}

}
