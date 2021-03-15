package mvc.game;

import javax.swing.SwingUtilities;

import game.view.elements.GameEngineCallbackGUI;
import game.view.elements.GameFrame;
import model.*;
import model.interfaces.*;

import view.interfaces.GameEngineCallback;

public class GameRunner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// create new game engine, frame and callbackgui objects.
				GameEngine engine = new GameEngineImpl();
				GameFrame gameFrame = new GameFrame(engine);
				gameFrame.populate();
				GameEngineCallback engineGUI = new GameEngineCallbackGUI(gameFrame);
				engine.addGameEngineCallback(engineGUI);
			}
		});
	}

}
