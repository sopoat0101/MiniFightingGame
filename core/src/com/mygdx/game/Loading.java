package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Loading {
	
	private float time;
	
	private float count;
	
	private int frame = 0;
	
	private Sprite bg;
	private Sprite loading;
	private TextureAtlas TAload;
	private TextureRegion TRload;
	
	public Loading(float time) {
		
		this.time = time;
		
		bg = new Sprite(new Texture(Gdx.files.internal("gui/loading/bg.png")));
		
		TAload = new TextureAtlas(Gdx.files.internal("gui/loading/textload/load.pack"));
		TRload = TAload.findRegion("0");
		
		loading = new Sprite(TRload);
		
		loading.setPosition(0 + PlayingState.WIDTH - 300,0 + 20);
		
	}
	
	public void setPosition(float pox, float poy) {
		
		bg.setPosition(pox - bg.getWidth()/2, poy - bg.getHeight()/2);
		
		loading.setPosition(pox + PlayingState.WIDTH / 2  - 300, poy - PlayingState.HEIGHT/2);
		
	}
	
	public void setAlpha(float a) {
		
		bg.setAlpha(a);
		loading.setAlpha(a);
		
	}
	
	public void draw(SpriteBatch batch) {
		
		count += Gdx.graphics.getDeltaTime();
		
		if(count >= time) {
			count = 0;
			frame += 1;
			
			if(frame > 2) {
				frame = 0;
			}
			
			TRload = TAload.findRegion(""+frame);
			loading.setRegion(TRload);
		}
		
		bg.draw(batch);
		loading.draw(batch);
		
		
		
	}
}
