package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.view.elements.GameFrame;
import game.view.elements.GameToolBar;
import model.interfaces.Player;

public class ResetBetListener implements ActionListener {

	private GameFrame gameFrame;
	private GameToolBar toolBar;

	public ResetBetListener(GameFrame gameFrame, GameToolBar toolBar) {
		this.gameFrame = gameFrame;
		this.toolBar = toolBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get selected player 
		Player player = (Player) toolBar.getPlayerBox().getSelectedItem();
		player.resetBet(); // reset the players bet 
		
		// update gui components with the reset bet 
		gameFrame.getCenterPanel().getPlayerSummary().showBet(player);
		gameFrame.getBottomPanel().getGameBar().setStatusLabel1(player.getPlayerName() + " has reset bet.");
		toolBar.getDealPlayerButton().setEnabled(false);
		toolBar.getResetBetButton().setEnabled(false);
		toolBar.getPlaceBetButton().setEnabled(true);
	}

}
