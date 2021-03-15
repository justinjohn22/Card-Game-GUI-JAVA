package model;

import java.util.Arrays;

import model.interfaces.PlayingCard;

//s3846390 

public class PlayingCardImpl implements PlayingCard {

	private Value value;
	private Suit suit;
	private int score;

	// card constructor
	public PlayingCardImpl(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;	
	}

	@Override // gets suit of this object
	public Suit getSuit() {
		return this.suit;
	}

	@Override // gets value of this object
	public Value getValue() {
		return this.value;
	}

	@Override // gets score of this object
	public int getScore() {
		// to assign computer readable score to card
		int INDEX_OFFSET = 8; // enum value starts at 8
		int indexEnum = Arrays.asList(Value.values()).indexOf(this.value) + INDEX_OFFSET;

		// assign score based on value enun
		if (indexEnum < 10) {
			this.score = indexEnum;
		} else if (indexEnum <= 13 && indexEnum >= 10) {
			this.score = 10; // picture cards
		} else if (value == Value.ACE) {
			this.score = 11; // ace
		}
		
		return this.score;
	}

	@Override // return string of playing card object
	public String toString() {
		StringBuilder strBuild = new StringBuilder(); // build string for card
		strBuild.append(String.format("Suit: %s, ", changeCase(this.getSuit().toString())));
		strBuild.append(String.format("Value: %s, ", changeCase(this.getValue().toString())));
		strBuild.append(String.format("Score: %d \n", this.getScore()));
		return strBuild.toString();
	}

	// to change enum values from all caps
	private String changeCase(String enumText) {
		return enumText.charAt(0) + (enumText.substring(1)).toLowerCase();
	}

	@Override // checking equality with both playing card type objects
	public boolean equals(PlayingCard card) {
		// checking suit and value
		return (this.getSuit().equals(card.getSuit()) && this.getValue().equals(card.getValue()));
	}

	@Override // check equality between playing card and casted playing card object
	public boolean equals(Object card) {
		if (card instanceof PlayingCard) {
			return (this.equals(card));
		}
		return false;
	}

	@Override // generate hashCode
	public int hashCode() {
		return value.hashCode() + suit.hashCode();
	}
}
