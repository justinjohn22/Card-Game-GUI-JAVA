package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.MenuListener;

import game.view.elements.GameFrame;
import game.view.elements.GameMenuBar;
import game.view.input.GetPanelColourPane;

@SuppressWarnings("unused")
public class GameMenuListener implements ActionListener {

	private GameFrame gameFrame;

	public GameMenuListener(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// construct and display option for user to change card panel color
		GetPanelColourPane colourPane = new GetPanelColourPane(gameFrame);
		colourPane.constructColourPane();

	}
}
