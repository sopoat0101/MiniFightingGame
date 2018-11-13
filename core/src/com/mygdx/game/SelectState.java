package com.mygdx.game;

public class SelectState extends State {

	private int select_P1;
	private int select_P2;

	public SelectState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {

		System.out.println("Select");

	}

	@Override
	public void update(float dt) {

		handle();

	}

	@Override
	public void handle() {

		if (InputManager.keyIspressed(InputManager.KEY_SPACE)) {

			
			gsm.setState(GameStateManager.PLAYING);

		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
