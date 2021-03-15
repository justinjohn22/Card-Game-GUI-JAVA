package game.view.input;

import javax.swing.JOptionPane;

public class PlaceBetOptionPane {

	String bet;

	public PlaceBetOptionPane() {
		bet = JOptionPane.showInputDialog("Enter bet amount");
	}

	public String getBetAmount() {
		return bet;
	}

}
