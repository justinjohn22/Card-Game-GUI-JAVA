package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import game.view.elements.GameFrame;
import game.view.elements.GameToolBar;
import game.view.input.PlaceBetOptionPane;

@SuppressWarnings({ "static-access", "unused" })
public class PlaceBetListener implements ActionListener {

	private GameFrame gameFrame;
	private GameToolBar toolBar;
	private String bet;

	public PlaceBetListener(GameFrame gameFrame, GameToolBar toolBar) {
		this.gameFrame = gameFrame;
		this.toolBar = toolBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// create option pane to get bet amount
		PlaceBetOptionPane pane = new PlaceBetOptionPane();
		bet = pane.getBetAmount();

		int finalBet = Integer.parseInt(bet);
		
		// get currently selected player from combo box
		Player player = (Player) toolBar.getPlayerBox().getSelectedItem();
		
		// validate bet amount
		if (player.getPoints() - finalBet < 0) {
			// if bet is invalid, show error message
			JOptionPane errorbet = new JOptionPane();
			errorbet.showMessageDialog(gameFrame, "Insufficient funds.");
		} // make sure only player can make a bet with updating button usability
		else if (player != toolBar.getHOUSE()) {
			gameFrame.getEngine().placeBet(player, finalBet);
			gameFrame.getCenterPanel().getPlayerSummary().showBet(player);
			gameFrame.getBottomPanel().getGameBar().setStatusLabel1(player.getPlayerName() + " has placed a bet!");
			if (player.getBet() > 0) {
				toolBar.getDealPlayerButton().setEnabled(true);
				toolBar.getResetBetButton().setEnabled(true);
				toolBar.getPlaceBetButton().setEnabled(false);
			}
		}
	}
}
