package game.view.elements;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameStatusBar extends JPanel {
	private JLabel statusLabel1 = new JLabel("    Add a player to start game.", JLabel.LEFT);

	public void setStatusLabel1(String status) {
		statusLabel1.setText(status);
	}
	
	// constructor for game status bar
	public GameStatusBar() {
		setLayout(new GridLayout(1,3));
		setBorder(BorderFactory.createTitledBorder("Game Status"));
		setBackground(Color.LIGHT_GRAY);
		add(statusLabel1);		
	}

}
