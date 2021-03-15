package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

// s3846390 

public class GameEngineImpl implements GameEngine {

	// data structures to hold player, cards & callbacks
	private LinkedList<Player> players = new LinkedList<>();
	private Deque<PlayingCard> playingCards = getShuffledHalfDeck();;
	private Collection<GameEngineCallback> callbacks = new LinkedList<>();

	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		dealCardTo(player, delay); // call helper method to deal to player
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		dealCardTo(null, delay); // null to differentiate with player

		for (Player thisPlayer : players) {
			thisPlayer.resetBet(); // settles round
		}
	}

	// Main deal method for both house and player to reduce duplication
	private void dealCardTo(Player player, int delay) throws IllegalArgumentException {

		// to check whether to deal to house or player
		boolean nullPlayer = (player == null); // if not null, deal to player

		int MIN_LIMIT = 0;
		int MAX_LIMIT = 1000;

		// for (GameEngineCallback callbackObj : callbacks) {
		int trackTotal = 0; // tracks card total
		PlayingCard currentCard = null;
		boolean checkNext = false;

		trackTotal = 0;

		// stops dealing if passed bust level
		while (trackTotal < BUST_LEVEL) {
			try {
				// slow dealing time
				if (delay < MAX_LIMIT && delay > MIN_LIMIT) { // accommodate player specifications
					Thread.sleep(delay);
				} else if (delay > MIN_LIMIT && nullPlayer) { // accommodate house specifications
					Thread.sleep(delay);
				} else {
					throw new IllegalArgumentException("Delay value not valid");
				}
			} catch (Exception e) {
				// System.out.println("Invalid delay time");
			}

			// loop through all callback
			for (GameEngineCallback callbackObj : callbacks) {
				if (playingCards.size() == 0) {
					playingCards = getShuffledHalfDeck(); // to regenerate if card runs out
				}
				currentCard = playingCards.pollFirst(); // pick and deal cards

				checkNext = (currentCard.getScore() + trackTotal) <= BUST_LEVEL;
				trackTotal += currentCard.getScore();

				// deal if receiver not busted
				if (!nullPlayer && checkNext) { // deal to player
					callbackObj.nextCard(player, currentCard, this);
				} else if (nullPlayer && checkNext) { // deal to house
					callbackObj.nextHouseCard(currentCard, this);
				}

			}
		}

		for (GameEngineCallback callbackObj : callbacks) {
			// calls bustCard methods if busted
			if ((trackTotal > BUST_LEVEL) && !nullPlayer) {
				callbackObj.bustCard(player, currentCard, this);
				trackTotal -= currentCard.getScore();
			} else if ((trackTotal > BUST_LEVEL) && nullPlayer) {
				callbackObj.houseBustCard(currentCard, this);
				trackTotal -= currentCard.getScore();
			}
		}

		if (trackTotal > BUST_LEVEL) {
			trackTotal -= currentCard.getScore();
		}

		for (GameEngineCallback callbackObj : callbacks) {
			if (!nullPlayer) { // final results

				callbackObj.result(player, trackTotal, this);
				player.setResult(trackTotal);
			} else {
				for (Player thisPlayer : players) {
					applyWinLoss(thisPlayer, trackTotal); // settles round
				}
				callbackObj.houseResult(trackTotal, this);
			}
		}
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		// to add or take winnings/losses from player
		if (player.getResult() < houseResult) { // house wins
			player.setPoints(player.getPoints() - player.getBet());
		} else if (player.getResult() > houseResult) { // player wins
			player.setPoints(player.getPoints() + player.getBet());
		}
	}

	@Override
	public void addPlayer(Player player) {
		boolean found = false;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerId().equals(player.getPlayerId())) {
				players.remove(players.get(i));
				players.add(player);
				found = true;
			}
		}
		if (found == false) {
			players.add(player); // add new player to collection
		}

	}

	@Override
	public Player getPlayer(String id) {
		// retrieve player
		Player returnPlayer = null;
		for (Player player : players) {
			if (player.getPlayerId() == id) {
				returnPlayer = player;
			}
		}
		return returnPlayer; // returns null if not found
	}

	@Override
	public boolean removePlayer(Player player) {
		boolean returnState = false;
		// remove player
		if (players.contains(player)) {
			players.remove(player);
			returnState = true;
		}
		return returnState; // returns false if not found
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet); // place the player bet
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback); // add to the callback collection

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		boolean removed = false;
		if (callbacks.contains(gameEngineCallback)) {
			callbacks.remove(gameEngineCallback);
			removed = true; // found and removed
		}
		return removed; // returns false if this did not exist
	}

	@Override // sort player by id
	public Collection<Player> getAllPlayers() {
		LinkedList<Player> playersCopy = players;
		Collections.sort(playersCopy);
		return Collections.unmodifiableCollection(playersCopy);
	}

	@Override // construct cards and return shuffled
	public Deque<PlayingCard> getShuffledHalfDeck() {
		LinkedList<PlayingCard> playingCards = new LinkedList<>();
		// generate every possible combination of card
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < Value.values().length; j++) { // construct and add
				playingCards.add(new PlayingCardImpl(Suit.values()[i], Value.values()[j]));
			}
		}
		Collections.shuffle(playingCards);
		return playingCards;
	}
}
