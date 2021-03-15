package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.view.elements.GameFrame;
import game.view.elements.GameToolBar;
import model.*;
import model.interfaces.*;

public class RemovePlayerListener implements ActionListener {

	Player stubPlayer = new SimplePlayer("2", "The Shark", 1000);
	GameFrame gameFrame;
	GameToolBar toolBar;

	public RemovePlayerListener(GameFrame gameFrame, GameToolBar toolBar) {
		this.gameFrame = gameFrame;
		this.toolBar = toolBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// remove selected player on combo box from game engine
		gameFrame.getEngine().removePlayer((Player) toolBar.getPlayerBox().getSelectedItem());

		Player currentPlayer = (Player) toolBar.getPlayerBox().getSelectedItem();
		
		// update gui components with the removed player
		if (currentPlayer != toolBar.getHOUSE()) {
			toolBar.getPlayerBox().removeItem(toolBar.getPlayerBox().getSelectedItem());
			gameFrame.getBottomPanel().getGameBar()
					.setStatusLabel1(currentPlayer.getPlayerName() + " has been removed!");
			gameFrame.getCenterPanel().getPlayerSummary().removePlayer(currentPlayer);
		}

	}

}
