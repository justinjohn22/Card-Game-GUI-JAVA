package view;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */

//s3846390 

public class GameEngineCallbackImpl implements GameEngineCallback {

	public static final Logger logger = Logger.getLogger(GameEngineCallbackImpl.class.getName());

	// utility method to set output level of logging handlers
	public static Logger setAllHandlers(Level level, Logger logger, boolean recursive) {
		// end recursion?
		if (logger != null) {
			logger.setLevel(level);
			for (Handler handler : logger.getHandlers())
				handler.setLevel(level);
			// recursion
			setAllHandlers(level, logger.getParent(), recursive);
		}
		return logger;
	}

	public GameEngineCallbackImpl() {
		// NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
		setAllHandlers(Level.FINE, logger, true);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString()).trim());

	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%s, final result=%d", player.getPlayerName(), result).trim());
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		// log the bust card for player
		logger.log(Level.INFO, String
				.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card.toString()).trim());

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// log the card received for house
		logger.log(Level.FINE, String.format("Card Dealt to House .. %s", card.toString()).trim());
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// log the bust card for house
		logger.log(Level.INFO, String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString()).trim());

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// log house result and final player results
		logger.log(Level.INFO, String.format("House, final result=%s", result).trim());
		logger.log(Level.INFO, finalResult(engine));

	}

	// add the players together
	private String finalResult(GameEngine engine) {
		StringBuilder strBuild = new StringBuilder();
		for (Player players : engine.getAllPlayers()) {
			// append every player to string builder
			strBuild.append(players.toString() + "\n");
		} // return all players
		return ("Final Player Results\n" + strBuild.toString());
	}

}
