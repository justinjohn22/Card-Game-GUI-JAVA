package game.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import game.view.elements.GameFrame;
import game.view.elements.GameToolBar;
import game.view.model.PlayerHistory;
import model.interfaces.Player;

public class ComboBoxListener implements ItemListener {

	private GameFrame gameFrame;
	private GameToolBar toolBar;
	private Player selectedPlayer;
	private PlayerHistory playerHistory;

	public ComboBoxListener(GameFrame gameFrame, GameToolBar toolBar) {
		this.gameFrame = gameFrame;
		this.toolBar = toolBar;
		this.playerHistory = gameFrame.getPlayerHistory();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		// get the currently selected player
		selectedPlayer = (Player) toolBar.getPlayerBox().getSelectedItem();
		
		// check which buttons should be enabled/disabled for house
		if (selectedPlayer.equals(toolBar.getHOUSE())) {
			toolBar.getDealPlayerButton().setEnabled(false);
			if (checkHouseDeal()) {
				toolBar.getDealPlayerButton().setEnabled(true);
			}
			toolBar.getRemovePlayerButton().setEnabled(false);
			toolBar.getPlaceBetButton().setEnabled(false);
			toolBar.getResetBetButton().setEnabled(false);
		} else {  // check which buttons should be enabled/disabled for players
			toolBar.getRemovePlayerButton().setEnabled(true);
			toolBar.getPlaceBetButton().setEnabled(true);
			if (selectedPlayer.getPoints() == 0) {
				toolBar.getPlaceBetButton().setEnabled(false);
			}
			if (selectedPlayer.getBet() > 0 && !playerHistory.getDealtPlayers().contains(selectedPlayer)) {
				toolBar.getDealPlayerButton().setEnabled(true);
				toolBar.getPlaceBetButton().setEnabled(false);
				toolBar.getResetBetButton().setEnabled(true);
			} else {
				toolBar.getDealPlayerButton().setEnabled(false);
			}
		}
	}

	public boolean checkHouseDeal() {
		// making sure that all the players are dealt before dealing house
		int allPlayers = 0;
		int dealtPlayers = 0;
		for (Player player : gameFrame.getEngine().getAllPlayers()) {
			if (player.getPoints() > 0) {
				allPlayers++;
			}
		}
		dealtPlayers = playerHistory.getDealtPlayers().size();
		// if all the players that have sufficient funds have been dealt, return true to deal house
		return (allPlayers == dealtPlayers && gameFrame.getEngine().getAllPlayers().size() > 0);
	}

}
