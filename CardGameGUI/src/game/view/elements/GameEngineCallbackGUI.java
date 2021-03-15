package game.view.elements;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private GameSummaryPanel gameSummary;
	private BottomPanel bottomPanel;
	private CardPanel mainPanel;

	public GameEngineCallbackGUI(GameFrame gameFrame) {
		gameSummary = gameFrame.getCenterPanel().getPlayerSummary();
		bottomPanel = gameFrame.getBottomPanel();
		mainPanel = gameFrame.getCenterPanel().getCardPanel();
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// deal card to player
				mainPanel.addPlayingCard(card);
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// do GUI update on UI thread
				bottomPanel.getGameBar().setStatusLabel1(String.format("Player bust card %s", card.toString()));
				mainPanel.addPlayingCard(card);
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// do GUI update on UI thread
				// update status
				gameSummary.showPlayerResult(player);
				bottomPanel.getGameBar().setStatusLabel1(String.format("%s scored %d", player.getPlayerName(), result));
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// deal card to house
				mainPanel.addPlayingCard(card);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// update status
				bottomPanel.getGameBar().setStatusLabel1(String.format("House bust card %s", card.toString()));
				mainPanel.addPlayingCard(card);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// do GUI update on UI thread
				// update status
				bottomPanel.getGameBar().setStatusLabel1(String.format("House scored %d", result));
				for (Player player : engine.getAllPlayers()) {
					gameSummary.setNewBalance(player);
					gameSummary.showBet(player);
					if (player.getResult() > result) {
						gameSummary.showWinLoss(player, "Win");
					} else if (player.getResult() < result) {
						gameSummary.showWinLoss(player, "Loss");
					} else {
						gameSummary.showWinLoss(player, "Draw");
					}
				}
			}
		});
	}

}
