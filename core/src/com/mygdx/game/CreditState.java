package com.mygdx.game;

public class CreditState extends State{
	private float time = 0;

	public CreditState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("        Lead Developer");
		System.out.println("       Sopoat Iamcharoen");
		System.out.println("Wassapol 'Feedplank' Pungjap");
		System.out.println();
		System.out.println("        Lead Designer");
		System.out.println("       Pipatboon Puttakun");
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handle();
		time += dt;
		System.out.println(time);
		if(time >= 8.0) {
			gsm.setState(GameStateManager.MENU);
		}
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
