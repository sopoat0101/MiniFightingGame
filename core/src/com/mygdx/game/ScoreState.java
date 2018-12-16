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
	
	private int nowselect = 0;
	
	private static int punchwin;
	private static int kickwin;
	private static int hitsuccess;
	private static float accuracy;
	
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
		
		Asubmenu[0] = new Texture(Gdx.files.internal("gui/score/playagain.png"));
		Asubmenu[1] = new Texture(Gdx.files.internal("gui/score/select.png"));
		Asubmenu[2] = new Texture(Gdx.files.internal("gui/score/main.png"));
		
		Bsubmenu[0] = new Texture(Gdx.files.internal("gui/score/playagained.png"));
		Bsubmenu[1] = new Texture(Gdx.files.internal("gui/score/selected.png"));
		Bsubmenu[2] = new Texture(Gdx.files.internal("gui/score/mained.png"));
		
		for(int i = 0; i < 3;i++) {
			menu[i] = new Sprite(Asubmenu[i]);
			
			menu[i].setPosition(PlayingState.WIDTH - menubg.getWidth() - 20,menubg.getHeight() - 90*(i+1));
			
		}
		
		batch = new SpriteBatch();
		
	}

	@Override
	public void draw() {
		
		batch.begin();
		
		bg.draw(batch);
		menubg.draw(batch);
		
		for(Sprite item: menu) {
			item.draw(batch);
		}
		
		batch.end();
		
		
	}

	@Override
	public void update(float dt) {
		
		handle();
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
		
	}
	
	
	public int getPunchwin() {
		return punchwin;
	}

	public void setPunchwin(int punchwin) {
		this.punchwin = punchwin;
	}

	public int getKickwin() {
		return kickwin;
	}

	public void setKickwin(int kickwin) {
		this.kickwin = kickwin;
	}

	public int getHitsuccess() {
		return hitsuccess;
	}

	public void setHitsuccess(int hitsuccess) {
		this.hitsuccess = hitsuccess;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
}
