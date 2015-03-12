package model;
//import CardViewController;


/**
 * This class represents a playing card. It has information on to what suit card belongs
 * what is its rank. It can return these information as int or String
 * Below are numerical constants that represents all possible suits and values. In other classes they
 * will be used to determine suits and values when creating a card.
 * This class is a Model class for the CardViewController class that represent this class with GUI.
 * @see CardViewController
 * @author Artyom M. a.k.a. artiyom
 * 
 */


public class CardModel {
	private String suitName;
	private String rankName;
	private CardSuit suit;
	private CardRank rank;
	private boolean held;
	private boolean holdable = false;
	

	/**
	 * This constructor is used to create a dummy "Face down" card that doesn't have a rank or suit
	 * It's used in the game at the starting screen, when the game just started but no bets were made
	 */
	public CardModel() {
		this.suitName = "DUMMYSUIT";
		this.rankName = "DUMMYRANK";
		this.held = false;
	}
	/**
	 * This is the main constructor, it gets 2 numerical values that represent the Suit and the Value
	 * of the card, to create a card. The values must be within specific range, or an exception will 
	 * be thrown.
	 * @param suit
	 * @param rank
	 */
	public CardModel(CardSuit suit, CardRank rank) {
		this.held = false;
		if (suit.getValue() >= CardSuit.SPADES.getValue() && suit.getValue() <= CardSuit.CLUBS.getValue()) {
			this.suit = suit;
		}
		else {
			throw new IllegalArgumentException(suit + " is out of range.");
				
		}
		
		if (rank.getValue() >= CardRank.TWO.getValue() && rank.getValue() <= CardRank.ACE.getValue()) {
			
			this.rank = rank;
		}
		else {
			throw new IllegalArgumentException(rank + " is out of range.");
		}
		
		CardNameInitializer();

	}
	
	/**
	 * This method sets the names (using String) of the suit and the rank
	 */
	private void CardNameInitializer() {
		switch(this.suit) {
			case SPADES: this.suitName = "SPADES"; break;
			case HEARTS: this.suitName = "HEARTS"; break;
			case DIAMONDS: this.suitName = "DIAMONDS"; break;
			case CLUBS: this.suitName = "CLUBS"; break;
		}
		
		switch(this.rank) {
			case TWO: this.rankName = "TWO"; break;
			case THREE: this.rankName = "THREE"; break;
			case FOUR: this.rankName = "FOUR"; break;
			case FIVE: this.rankName = "FIVE"; break;
			case SIX: this.rankName = "SIX"; break;
			case SEVEN: this.rankName = "SEVEN"; break;
			case EIGHT: this.rankName = "EIGHT"; break;
			case NINE: this.rankName = "NINE"; break;
			case TEN: this.rankName = "TEN"; break;
			case JACK: this.rankName = "JACK"; break;
			case QUEEN: this.rankName = "QUEEN"; break;
			case KING: this.rankName = "KING"; break;
			case ACE: this.rankName = "ACE"; break;			
		}
		
		
		
	}
	
	public CardSuit getSuit() {
		return this.suit;
	}
	
	public CardRank getRank() {
		return this.rank;
	}
	public String getSuitName() {
		return this.suitName;
	}
	public String getRankName() {
		return this.rankName;
	}
	
	
	/**
	 * 
	 * @return String(Value-Name + Suit-Name)
	 */
	public String getCardName() {
		return this.rankName + " of " + this.suitName;
	}
	
	/**
	 * Next 3 methods deal with holding and unholding the card.
	 * Holding/Unholding the cards is essential part of "Jacks or Better" game
	 * 
	 */
	public boolean isHeld() {
		return this.held;
	}
	public void hold() {
		this.held = true;
	}
	
	public void unhold() {
		this.held = false;
	}
	
	
	/**
	 * The next 3 methods allow/prohibit the card to be hold
	 * This is used in the game, so cards can't get hold/unhold until
	 * the Deal button hasn't been pressed.
	 */
	public void makeHoldable() {
		this.holdable = true;
	}
	
	public boolean isHoldable() {
		return this.holdable ;
	}

	public void makeUnholdable() {
		this.holdable = false;
		
	}

}
