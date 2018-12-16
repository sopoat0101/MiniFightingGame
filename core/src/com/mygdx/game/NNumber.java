package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NNumber {

	private TextureAtlas NUMBER;

	private TextureRegion[] TRNum;
	private Sprite[] number;
	
	private int count;
	
	private float width;
	private float height;
	
	public NNumber() {
		
		NUMBER = new TextureAtlas("gui/number/number.pack");
		
	}

	public void setSize(float width, float height) {

//		numberFront.setSize(width / 2, height);
//		numberBack.setSize(width / 2, height);
//		
		this.width = width;
		this.height = height;

	}

	public void setAlpha(float a) {

		

	}

	public void draw(SpriteBatch batch) {

		for(Sprite item: number) {
			item.draw(batch);
		}

	}

	public void setPosition(float x, float y) {
		
		for(int i = 0; i < count;i++) {
			number[i].setPosition(x + (i*30), y);
		}
		
	}
	
	public void setNumber(int num) {
		
		String dig = Integer.toString(num);
		count = dig.length();
		TRNum = new TextureRegion[count];
		number = new Sprite[count];
		
		for(int i = 0; i < count;i++) {
			TRNum[i] = NUMBER.findRegion(Character.toString(dig.charAt(i)));
			number[i] = new Sprite(TRNum[i]);
			number[i].setRegion(TRNum[i]);
			
			number[i].setSize(30, 60);
			number[i].setPosition(0 + (i*30), 0);
		}

	}

	public float getWidth() {
		return width;
	}
	
	public float getHeigth() {
		return height;
	}
}
