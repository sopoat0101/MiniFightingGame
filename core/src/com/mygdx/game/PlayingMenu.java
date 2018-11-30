package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayingMenu {
	
	private int nowSelect = 0;
	private boolean enable = true;

	private Sprite background;
	private Sprite menu;
	private Sprite resume;
	private Sprite backtomainmenu;
	
	private Texture resume1;
	private Texture resume2;
	
	private Texture backtomainmenu1;
	private Texture backtomainmenu2;
	
	private Sprite howto[];
	
	private float POX;
	private float POY;
	
	public PlayingMenu(float pox, float poy) {
		this.POX = pox;
		this.POY = poy;
		
		background = new Sprite(new Texture(Gdx.files.internal("gui/playingmenu/background.png")));
		menu = new Sprite(new Texture(Gdx.files.internal("gui/playingmenu/menu.png")));
		
		resume1 = new Texture(Gdx.files.internal("gui/playingmenu/resume.png"));
		resume2 = new Texture(Gdx.files.internal("gui/playingmenu/resumeed.png"));
		
		backtomainmenu1 = new Texture(Gdx.files.internal("gui/playingmenu/backtomain.png"));
		backtomainmenu2 = new Texture(Gdx.files.internal("gui/playingmenu/backtomained.png"));
		
		resume = new Sprite(resume1);
		backtomainmenu = new Sprite(backtomainmenu1);
		
		howto = new Sprite[2];
		
		howto[0] = new Sprite(new Texture(Gdx.files.internal("gui/playingmenu/howtoESC.png")));
		howto[1] = new Sprite(new Texture(Gdx.files.internal("gui/playingmenu/howtoSPE.png")));
		
		
		
	}
	
	public void setAlpha(float a) {
		
		background.setAlpha(a);
		menu.setAlpha(a);
		resume.setAlpha(a);
		backtomainmenu.setAlpha(a);
		
		for(Sprite item : howto) {
			item.setAlpha(a);
		}
	
	}
	
	public void setPosition(float pox, float poy) {
		this.POX = pox;
		this.POY = poy;
		
		background.setPosition(pox - background.getWidth()/2, poy - background.getHeight()/2);
		menu.setPosition(pox - menu.getWidth()/2, poy - menu.getHeight()/2);
		resume.setPosition(pox - resume.getWidth()/2, poy - resume.getHeight()/2 + resume.getHeight()*2);
		backtomainmenu.setPosition(pox - backtomainmenu.getWidth()/2, poy - backtomainmenu.getHeight()/2 - backtomainmenu.getHeight());
		
		howto[0].setPosition(pox - PlayingState.WIDTH / 2 + 20, poy + PlayingState.HEIGHT /2  - howto[0].getHeight() - 20);
		howto[1].setPosition(pox - PlayingState.WIDTH / 2 + 20, poy - PlayingState.HEIGHT /2 + 20);
		
		
	}
	
	public void draw(SpriteBatch batch) {
		
		background.draw(batch);
		menu.draw(batch);
		resume.draw(batch);
		backtomainmenu.draw(batch);
		
		for(Sprite item : howto) {
			item.draw(batch);
		}
		
		handle();
		update();
		
	}
	
	public void update() {
		
		if(enable && nowSelect == 0) {
			resume.setTexture(resume2);
			backtomainmenu.setTexture(backtomainmenu1);
		}
		if(enable && nowSelect == 1) {
			backtomainmenu.setTexture(backtomainmenu2);
			resume.setTexture(resume1);
		}
		
	}
	
	public void handle() {
		
		if(nowSelect > 1) {
			nowSelect = 0;
		}
		if(nowSelect < 0) {
			nowSelect = 1;
		}
		
		if(InputManager.keyIspressed(InputManager.KEY_W) || InputManager.keyIspressed(InputManager.KEY_UP)) {
			nowSelect += 1;
		}
		
		if(InputManager.keyIspressed(InputManager.KEY_S) || InputManager.keyIspressed(InputManager.KEY_DOWN)) {
			nowSelect -= 1;
		}
		
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getNowSelect() {
		return nowSelect;
	}

	public void setNowSelect(int nowSelect) {
		this.nowSelect = nowSelect;
	}
}
