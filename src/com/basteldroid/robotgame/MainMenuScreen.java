package com.basteldroid.robotgame;

import java.util.List;
import java.util.Random;

import com.basteldroid.framework.Game;
import com.basteldroid.framework.Graphics;
import com.basteldroid.framework.Screen;
import com.basteldroid.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {
	
	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 50, 350, 250, 450)) {
					// game.setScreen(new GameScreen(game));
					Random r = new Random();
					if (r.nextInt(2) == 0) {
						System.out.println("THIS");
						Settings.currentLevel = 0;
						Settings.save(game.getFileIO());
					} else {
						System.out.println("THAT");
						Settings.currentLevel = 1;
						Settings.save(game.getFileIO());
					}
					
					game.setScreen(new GameScreen(game));
				}
			}
		}
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y +height -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
