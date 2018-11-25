package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Number {

	private TextureAtlas NUMBER;

	private TextureRegion TRNumF;
	private Sprite numberFront;

	private TextureRegion TRNumB;
	private Sprite numberBack;

	private float width;
	private float height;
	
	public Number() {
		NUMBER = new TextureAtlas("gui/number/number.pack");

		TRNumF = NUMBER.findRegion("9");
		numberFront = new Sprite(TRNumF);
		numberFront.setSize(50, 100);

		TRNumB = NUMBER.findRegion("9");
		numberBack = new Sprite(TRNumB);
		numberBack.setSize(50, 100);
		
		this.width = numberFront.getWidth()+numberBack.getWidth();
		this.height = numberFront.getHeight();
		
	}

	public void setSize(float width, float height) {

		numberFront.setSize(width / 2, height);
		numberBack.setSize(width / 2, height);
		
		this.width = width;
		this.height = height;

	}

	public void setAlpha(float a) {

		numberFront.setAlpha(a);
		numberBack.setAlpha(a);

	}

	public void draw(SpriteBatch batch) {

		numberFront.draw(batch);
		numberBack.draw(batch);

	}

	public void setPosition(float x, float y) {
		
		numberFront.setPosition(x, y);
		numberBack.setPosition(x + numberFront.getWidth(), y);
		
	}
	
	public void setNumber(int num) {

		TRNumF = NUMBER.findRegion("" + (((int) num / 10)));
		numberFront.setRegion(TRNumF);

		TRNumB = NUMBER.findRegion("" + num % 10);
		numberBack.setRegion(TRNumB);

	}

	public float getWidth() {
		return width;
	}
	
	public float getHeigth() {
		return height;
	}
}
