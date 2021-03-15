package game.view.elements;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {

	private GameSummaryPanel playerSummary;

	public GameSummaryPanel getPlayerSummary() {
		return playerSummary;
	}

	private GameFrame frame;
	private CardPanel cardPanel;

	public CardPanel getCardPanel() {
		return cardPanel;
	}
	
	
	public CenterPanel(GameFrame frame) {
		this.frame = frame;
	};
	
	// make  panels
	public void ConstructCenterCardPanel() {
		cardPanel = new CardPanel(frame);
		this.add(cardPanel, BorderLayout.CENTER);
	}
	
	// construct the player summary and populate it with elements
	public void ConstructCenterTable() {
		setLayout(new BorderLayout());
		playerSummary = new GameSummaryPanel(frame);
		playerSummary.populate();
		this.add(playerSummary, BorderLayout.SOUTH);
	}

}
