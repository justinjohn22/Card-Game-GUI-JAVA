package model;

import model.interfaces.Player;

//s3846390 

public class SimplePlayer implements Player {

	// initialize variables
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	// construct a player
	public SimplePlayer(String id, String playerName, int initialPoints) {
		if (id == null) { // id must have a value
			throw new IllegalArgumentException("null argument");
		} else {
			this.playerId = id;
			this.playerName = playerName;
			this.points = initialPoints;
		}
	}

	@Override // getter for player name
	public String getPlayerName() {
		return this.playerName;
	}

	@Override // setter for player name
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override // getter for player points
	public int getPoints() {
		return this.points;
	}

	@Override // setter for player points
	public void setPoints(int points) {
		this.points = points;

	}

	@Override // getter for player ID
	public String getPlayerId() {
		return this.playerId;
	}

	@Override // setting a bet
	public boolean setBet(int bet) {
		// make sure player has funds for bet
		if (bet > 0 && (this.getPoints() - bet >= 0)) {
			this.bet = bet;
			return true;
		} else {
			this.resetBet();
			return false;
		}
	}

	@Override // getter for bet placed
	public int getBet() {
		return this.bet;
	}

	@Override // reset after round
	public void resetBet() {
		this.bet = 0;

	}

	@Override // getter for result
	public int getResult() {
		return this.result;
	}

	@Override // setter for result
	public void setResult(int result) {
		this.result = result;

	}

	@Override // checking equality with both player type objects
	public boolean equals(Player player) {
		return (this.playerId == player.getPlayerId());
	}

	@Override // generate hashCode
	public int hashCode() {
		return playerId.hashCode() + playerName.hashCode();
	}

	@Override // check equality between player and casted player object
	public boolean equals(Object player) {
		if (player instanceof Player) { // check with casted player
			return (this.equals(((Player) player)));
		}
		return false;
	}

	@Override // greater, less or equal with player ID
	public int compareTo(Player player) {
		return this.playerId.compareTo(player.getPlayerId());
		// return Integer.parseInt(this.playerId) -
		// Integer.parseInt(player.getPlayerId());
	}

	@Override // returns string of player object
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d", this.getPlayerId(),
				this.getPlayerName(), this.getBet(), this.getPoints(), this.getResult());
	}
}
