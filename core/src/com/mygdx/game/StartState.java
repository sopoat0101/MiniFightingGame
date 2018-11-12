package com.mygdx.game;

public class StartState extends State{

	public StartState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		
		handle();
		
	}

	@Override
	public void handle() {
		
		if(InputManager.keyIspressed(InputManager.KEY_SPACE)) {
			
			gsm.setState(GameStateManager.MENU);
			
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
