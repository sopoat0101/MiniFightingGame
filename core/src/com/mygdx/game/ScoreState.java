package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreState extends State {

	private SpriteBatch batch;
	private Sprite bg;
	
	private Sprite menubg;
	private Sprite[] menu;
	
	private Texture[] Asubmenu;
	
	private Texture[] Bsubmenu;
	
	private Sprite status;
	private Texture[] picstatus;
	
	private int nowselect = 0;
	private static int winner;
	
	private NNumber[] Number;
	
	public ScoreState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		bg = new Sprite(new Texture(Gdx.files.internal("gui/score/scorestate.png")));
		
		menubg = new Sprite(new Texture(Gdx.files.internal("gui/score/bgmenu.png")));
		
		menu = new Sprite[3];
		Asubmenu = new Texture[3];
		Bsubmenu = new Texture[3];
		picstatus = new Texture[3];
		
		Asubmenu[0] = new Texture(Gdx.files.internal("gui/score/playagain.png"));
		Asubmenu[1] = new Texture(Gdx.files.internal("gui/score/select.png"));
		Asubmenu[2] = new Texture(Gdx.files.internal("gui/score/main.png"));
		
		Bsubmenu[0] = new Texture(Gdx.files.internal("gui/score/playagained.png"));
		Bsubmenu[1] = new Texture(Gdx.files.internal("gui/score/selected.png"));
		Bsubmenu[2] = new Texture(Gdx.files.internal("gui/score/mained.png"));
		
		picstatus[0] = new Texture(Gdx.files.internal("gui/score/P1win.png"));
		picstatus[1] = new Texture(Gdx.files.internal("gui/score/P2win.png"));
		picstatus[2] = new Texture(Gdx.files.internal("gui/score/draw.png"));
		
		status = new Sprite(picstatus[winner]);
		
		for(int i = 0; i < 3;i++) {
			menu[i] = new Sprite(Asubmenu[i]);
			menu[i].setPosition(PlayingState.WIDTH - menubg.getWidth() - 20,menubg.getHeight() - 90*(i+1));
			
		}
		
		batch = new SpriteBatch();
		
		Number = new NNumber[6];
		
		for(int i = 0 ; i< 6;i++) {
			Number[i] = new NNumber();
		}
		
		Number[0].setNumber(PlayingState.getP1Punchcount());
		Number[1].setNumber(PlayingState.getP1Kickcount());
		Number[2].setNumber(PlayingState.getP1Hitcount());
		Number[3].setNumber(PlayingState.getP2Punchcount());
		Number[4].setNumber(PlayingState.getP2Kickcount());
		Number[5].setNumber(PlayingState.getP2Hitcount());
		
		for(int i = 0 ;i< 6;i++) {
			Number[i].setPosition(600, PlayingState.HEIGHT - 130 - (i* 100));
		}
	}

	@Override
	public void draw() {
		
		batch.begin();
		
		bg.draw(batch);
		menubg.draw(batch);
		
		for(Sprite item: menu) {
			item.draw(batch);
		}
		
		status.draw(batch);
		status.setPosition(PlayingState.WIDTH - status.getWidth() - 100, PlayingState.HEIGHT - status.getHeight() - 50);
		
		for(NNumber item: Number) {
			item.draw(batch);
		}
		
		batch.end();
		
		
	}

	@Override
	public void update(float dt) {
		
		handle();
		
		status.setTexture(picstatus[winner]);
		
		menubg.setPosition(PlayingState.WIDTH - menubg.getWidth() - 50, 30);
		
		for(int i = 0;i < 3;i++) {
			if(nowselect == i) {
				menu[i].setTexture(Bsubmenu[i]);
			}else {
				menu[i].setTexture(Asubmenu[i]);
			}
		}
		
	}

	@Override
	public void handle() {
		
		if(InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			
			PlayingState.P1Hitcount = 0;
			PlayingState.P1Punchcount = 0;
			PlayingState.P1Kickcount = 0;
			
			PlayingState.P2Hitcount = 0;
			PlayingState.P2Punchcount = 0;
			PlayingState.P2Kickcount = 0;
			
			if(nowselect == 0) {
				
				gsm.setState(GameStateManager.PLAYING);
			}
			
			if(nowselect == 1) {
				gsm.setState(GameStateManager.SELECT);
			}
			
			if(nowselect == 2) {
				gsm.setState(GameStateManager.MENU);
			}
		}
		
		if(nowselect > 2) {
			nowselect = 0;
		}
		if(nowselect < 0) {
			nowselect = 2;
		}
		
		if(InputManager.keyIspressed(InputManager.KEY_UP)) {
			nowselect -= 1;
		}
		if(InputManager.keyIspressed(InputManager.KEY_DOWN)) {
			nowselect += 1;
		}
		
		
	}

	@Override
	public void dispose() {
		GameStateManager.bgMusic.dispose();
	}
	
	public static void setWinner(int winner) {
		ScoreState.winner = winner;
	}
}
