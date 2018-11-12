package com.mygdx.game;

public class MenuState extends State {

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		
		System.out.println("Menu");

	}

	@Override
	public void update(float dt) {

		handle();

	}

	@Override
	public void handle() {

		if (InputManager.keyIspressed(InputManager.KEY_SPACE)) {

			gsm.setState(GameStateManager.SELECT);

		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
