package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Hitbox {
	
	private Texture tex;
	private Sprite sp;
	private Rectangle rec;
	private float poX;
	private float poY;
	
	public Hitbox(String path, float poX, float poY) {
		
		tex = new Texture(path);
		sp = new Sprite(tex);
		rec = new Rectangle(poX, poY, sp.getWidth(), sp.getHeight());
		sp.setPosition(poX, poY);
		this.poX = poX;
		this.poY = poY;
		
	}
	
	public void draw(SpriteBatch batch) {
		sp.draw(batch);
	}
	
	public void flib(boolean x, boolean y) {
		sp.flip(x, y);
	}
	
	public void dispose() {
		tex.dispose();
	}
	
	public void setPosition(float poX, float poY) {
		rec.setPosition(poX, poY);
		sp.setPosition(poX, poY);
		
		this.poX = poX;
		this.poY = poY;
	}
	
	public float getPoX() {
		return poX;
	}

	public float getPoY() {
		return poY;
	}

	public float getWidth() {
		return sp.getWidth();
	}
	
	public float getHeight() {
		return sp.getHeight();
	}
	
	public Rectangle getRectangle() {
		return rec;
	}
	
	public void setAlpha(float f) {
		sp.setAlpha(f);
	}
	
	public void setSize(float width, float height) {
		sp.setSize(width, height);
		rec.setSize(width, height);
	}
	
	public boolean hitwith(Hitbox hitbox) {
		if(this.rec.overlaps(hitbox.getRectangle())) {
			return true;
		}else {
			return false;
		}
	}
}
