package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;

import game.view.elements.GameFrame;
import game.view.elements.GameSummaryPanel;
import game.view.elements.GameToolBar;
import game.view.input.AddPlayerOptionPane;


@SuppressWarnings("unused")
public class AddPlayerListener implements ActionListener {

	private GameFrame gameFrame;
	private GameToolBar toolBar;
	
	private String newPlayerID;
	private String newPlayerName;
	private String newPlayerBalance;
	private Player newPlayer;
	

	public AddPlayerListener(GameFrame gameFrame, GameToolBar toolBar) {
		this.gameFrame = gameFrame;
		this.toolBar = toolBar;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		// create option pane and get inputs to create a new player
		AddPlayerOptionPane pane = new AddPlayerOptionPane();
		newPlayerID = pane.getIDPane();
		newPlayerName = pane.getNamePane();
		newPlayerBalance = pane.getBalancePane();
		
		// call a helper method to create the player 
		newPlayer = createPlayer(newPlayerID, newPlayerName, newPlayerBalance);
		
		// add the new player into game engine
		gameFrame.getEngine().addPlayer(newPlayer);
		
		// update combo box, player summary and status bar
		toolBar.addToComboBox(newPlayer);	
		gameFrame.getCenterPanel().getPlayerSummary().addNewPlayer(newPlayer);
		gameFrame.getBottomPanel().getGameBar().setStatusLabel1(newPlayer.getPlayerName() + " has been added!");
		
	
	}
	
	// constructs the new player with the retrieved values 
	public Player createPlayer(String id, String name, String balance) {
		Player currentPlayer = new SimplePlayer(id, name, Integer.parseInt(balance));
		return currentPlayer;
	}
}
