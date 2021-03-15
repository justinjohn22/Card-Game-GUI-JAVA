package game.view.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.jdi.Value;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.*;
import game.view.model.PlayerHistory;

@SuppressWarnings({ "unused", "serial" })
public class CardPanel extends JPanel {

	private GameFrame frame;
	private ImageIcon currentCard;

	private JLabel cardLabel;
	private File[] imageFile;
	private Deque<PlayingCard> currentDeck = new LinkedList<PlayingCard>();
	private LinkedList<Image> imageDeck = new LinkedList<Image>();
	private Image imageCard;
	private int space;
	private Graphics2D g2;

	private Image scaledCard;
	private final String FILE_PATH = String.format("images%scards", File.separator);

	private int cardPosition;
	private int frameHeight;
	private int frameWidth;

	public void setCardPosition(int cardPosition) {
		this.cardPosition = cardPosition;
	}

	public int getCardPosition() {
		return cardPosition;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public CardPanel(GameFrame frame) {
		this.frame = frame;
		changeBackground("Pink"); // set default colour
		imageFile = new File(FILE_PATH).listFiles();
		this.cardPosition = 0;

	}

	public void addPlayingCard(PlayingCard card) {
		// add the card to deck to be dealt
		currentDeck.add(card);
		
		// get frame dimensions 
		frameHeight = frame.getHeight();
		frameWidth = frame.getWidth();
		
		// create the path to get image
		String source = String.format("images%scards%s%s_%s.png", File.separator, File.separator, card.getSuit().name(),
				card.getValue().name());
		
		// make a new image icon with the card picture 
		currentCard = new ImageIcon(source);
		imageCard = currentCard.getImage();
		
		// resize the image based on frame
		scaledCard = imageCard.getScaledInstance((int) (frameWidth / 7), (int) (frameHeight / 3),
				java.awt.Image.SCALE_SMOOTH);
		
		// add to image linkedlist
		imageDeck.add(scaledCard);
		currentCard = new ImageIcon(scaledCard);
		cardLabel = new JLabel(currentCard);
		
		this.repaint();
	}
	
	// change the colour from option pane
	public void changeBackground(String colour) {
		if (colour.equals("Red")) {
			setBackground(Color.RED);
		} else if (colour.equals("Blue")) {
			setBackground(Color.BLUE);
		} else if (colour.equals("Pink")) {
			setBackground(Color.PINK);
		} else if (colour.equals("Yellow")) {
			setBackground(Color.YELLOW);
		}
	}

	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		super.paintComponent(g2);
		// spacing between cards
		space = frame.getWidth() / 15;
		
		// paint all the cards
		for (int i = 0; i < imageDeck.size(); i++) {
			g2.drawImage(imageDeck.get(i), space, 15, null);
			space += frame.getWidth() / 6; 
		}
	}

	public void removeCards() {
		// clean panel for new deal
		imageDeck.clear();
		repaint();
	}

}
