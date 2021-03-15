package game.view.model;

import java.util.ArrayList;
import java.util.Collection;

import game.view.elements.GameFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("unused")
public class PlayerHistory {
	private GameEngine engine;
	private Player currentDealing = null;
	private Player lastDealt = null;
	private Collection<Player> dealtPlayers = new ArrayList<>();
	
	public PlayerHistory(GameFrame gameFrame) {
		engine = gameFrame.getEngine();

	}
	
	// get all players 
	public Collection<Player> getDealtPlayers() {
		return dealtPlayers;
	}
	
	// if player has been dealt
	public boolean hasBeenDealt(Player player) {
		return dealtPlayers.contains(player);
	}
	
	// add already dealt player 
	public void addDealtPlayer(Player player) {
		dealtPlayers.add(player);
	}
	
	// check dealing
	public boolean isPlayerDealing(Player player) {
		return currentDealing != null && player.equals(currentDealing);
	}
	
	// remove all dealt players 
	public void clearDealtPlayers() {
		dealtPlayers.clear();
	}

}
