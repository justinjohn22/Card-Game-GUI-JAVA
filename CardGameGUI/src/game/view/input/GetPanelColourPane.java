package game.view.input;

import javax.swing.JOptionPane;
import game.view.elements.GameFrame;

public class GetPanelColourPane {

	private String selectedString;
	private GameFrame gameFrame;

	public GetPanelColourPane(GameFrame gameFrame) {
		this.gameFrame = gameFrame;

	}

	public void constructColourPane() {
		String[] values = { "Red", "Blue", "Pink", "Yellow" };

		Object selected = JOptionPane.showInputDialog(null, "Change colour to", "Colour Selection",
				JOptionPane.DEFAULT_OPTION, null, values, "0");
		if (selected != null) {// null if the user cancels.
			selectedString = selected.toString();
			gameFrame.getCenterPanel().getCardPanel().changeBackground(selectedString);
		} else {
			System.out.println("User cancelled!");
		}
	}

	public String getSelectedString() {
		return selectedString;
	}

}
