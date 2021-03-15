package game.view.elements;

import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JToolBar;
import game.controller.AddPlayerListener;
import game.controller.ComboBoxListener;
import game.controller.DealListener;
import game.controller.PlaceBetListener;
import game.controller.RemovePlayerListener;
import game.controller.ResetBetListener;
import model.SimplePlayer;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class GameToolBar extends JToolBar {

	private JComboBox<Player> playerBox;
	private AbstractButton dealPlayerButton;
	private AbstractButton removePlayerButton;
	private AbstractButton placeBetButton;
	private AbstractButton resetBetButton;
	private AbstractButton addPlayerButton;
	private GameFrame gameFrame;

	public AbstractButton getResetBetButton() {
		return resetBetButton;
	}

	public AbstractButton getPlaceBetButton() {
		return placeBetButton;
	}

	public AbstractButton getRemovePlayerButton() {
		return removePlayerButton;
	}

	public AbstractButton getDealPlayerButton() {
		return dealPlayerButton;
	}

	public JComboBox<Player> getPlayerBox() {
		return playerBox;
	}

	private final Player HOUSE = new SimplePlayer("notAPlayer", "The House", 0);

	public Player getHOUSE() {
		return HOUSE;
	}
	
	// constructor for tool bar
	GameToolBar(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setBorder(BorderFactory.createTitledBorder("Game Toolbar"));
		this.setFloatable(false);
		
		// create and add the deal button
		dealPlayerButton = new JButton("Deal");
		dealPlayerButton.addActionListener(new DealListener(gameFrame));
		dealPlayerButton.setEnabled(false); 
		this.add(dealPlayerButton);
		
		// create and add the Place Bet button
		placeBetButton = new JButton("Place Bet");
		placeBetButton.addActionListener(new PlaceBetListener(gameFrame, this));
		placeBetButton.setEnabled(false);
		this.add(placeBetButton);
		
		// create and add the combo box
		playerBox = new JComboBox<Player>();
		playerBox.addItem(HOUSE);
		playerBox.addItemListener(new ComboBoxListener(gameFrame, this));
		
		// to add player object to combo box but only display player name
		playerBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (value != null)
					setText(((Player) value).getPlayerName());

				return this;
			}
		});
		add(playerBox);
		
		// create and add the Reset Bet button
		resetBetButton = new JButton("Reset Bet");
		resetBetButton.addActionListener(new ResetBetListener(gameFrame, this));
		resetBetButton.setEnabled(false);
		add(resetBetButton);
		
		// create and add the add player button
		addPlayerButton = new JButton("Add Player");
		addPlayerButton.addActionListener(new AddPlayerListener(gameFrame, this));
		addPlayerButton.setName("Add Player");
		add(addPlayerButton);
		
		// create and add the Remove Player button
		removePlayerButton = new JButton("Remove Player");
		removePlayerButton.addActionListener(new RemovePlayerListener(gameFrame, this));
		add(removePlayerButton);
		removePlayerButton.setEnabled(false);
	}
	
	// making sure to remove duplication from combo box
	public void addToComboBox(Player player) {
		int i = 0;
		for (Player currentPlayer : gameFrame.getEngine().getAllPlayers()) {
			i++;
			if (currentPlayer.getPlayerId().equals(player.getPlayerId())) {
				Player player1 = playerBox.getItemAt(i);
				playerBox.removeItem(player1); // replace if id already exists
			}
		}
		// add player to combo box and removing old duplicate 
		playerBox.addItem(player);
	}
}
