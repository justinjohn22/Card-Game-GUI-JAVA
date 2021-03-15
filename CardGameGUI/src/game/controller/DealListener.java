package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.view.elements.GameFrame;
import game.view.elements.GameToolBar;
import game.view.model.PlayerHistory;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("unused")
public class DealListener implements ActionListener {
	private GameFrame gameFrame;
	private GameToolBar toolBar;
	private GameEngine engine;
	private final Player HOUSE = null;
	private PlayerHistory playerHistory;

	public DealListener(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.engine = gameFrame.getEngine();
		this.playerHistory = gameFrame.getPlayerHistory();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// get the currently selected player in combo box
		Player thisPlayer = (Player) gameFrame.getTopPanel().getToolBar().getPlayerBox().getSelectedItem();
		Player houseField = gameFrame.getTopPanel().getToolBar().getHOUSE(); // get House player object
		boolean dealHouse = thisPlayer.equals(houseField); // check whether to deal to player/house 
		
		// deal to house
		if (dealHouse) {
			new Thread() {
				@Override
				public void run() {
					gameFrame.getCenterPanel().getCardPanel().removeCards();
					gameFrame.getBottomPanel().getGameBar().setStatusLabel1("Dealing House ...");
					engine.dealHouse(400);
					playerHistory.clearDealtPlayers();

				}
			}.start();
			// enable/disable buttons
			gameFrame.getCenterPanel().getCardPanel().setCardPosition(15);
			gameFrame.getTopPanel().getToolBar().getDealPlayerButton().setEnabled(false);
		}
		
		// deal to player if eligible 
		else if (thisPlayer.getBet() > 0 && !playerHistory.isPlayerDealing(thisPlayer)
				&& !playerHistory.hasBeenDealt(thisPlayer)) {
			new Thread() {
				@Override
				public void run() {
					// clean the card panel before dealing
					gameFrame.getCenterPanel().getCardPanel().removeCards();
					gameFrame.getBottomPanel().getGameBar() // update status bar
							.setStatusLabel1(String.format("Dealing %s", thisPlayer.getPlayerName()));
					engine.dealPlayer(thisPlayer, 400);
					playerHistory.addDealtPlayer(thisPlayer);
					
				}
			}.start();
			// enable/disable buttons
			gameFrame.getTopPanel().getToolBar().getDealPlayerButton().setEnabled(false);
			gameFrame.getTopPanel().getToolBar().getResetBetButton().setEnabled(false);
			
			// update status bar
		} else if (playerHistory.isPlayerDealing(thisPlayer)) {
			gameFrame.getBottomPanel().getGameBar()
					.setStatusLabel1(String.format("%s is currently being dealt to.", thisPlayer.getPlayerName()));
		} else if (playerHistory.hasBeenDealt(thisPlayer)) {
			gameFrame.getBottomPanel().getGameBar()
					.setStatusLabel1(String.format("%s has already been dealt.", thisPlayer.getPlayerName()));
		} else {
			gameFrame.getBottomPanel().getGameBar()
					.setStatusLabel1(String.format("%s needs to place a bet.", thisPlayer.getPlayerName()));
		}
	}
}
