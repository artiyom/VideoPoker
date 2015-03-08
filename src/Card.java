/**
 * This class represents a playing card. It has information on to what suit card belongs
 * what is its value. It cn return these information as int or String
 * Below are numerical constants that represents all possible suits and values. In other classes they
 * will be used to determine suits and values when creating a card.
 * This class is a Model class for the CardViewController class that represent this class with GUI.
 * @see CardViewController
 * @author Artyom M. a.k.a. artiyom
 * 
 */




public class Card {
	/**
	 * Numerical constants that represent names of suits and values 
	 */
	public static final int SPADES = 1;
	public static final int HEARTS = 2;
	public static final int DIAMONDS = 3;
	public static final int CLUBS = 4; 
	public static final int DUMMYSUIT = 5;
	
	 
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;
	public static final int DUMMYNAME = 15; //dummy card; e means empty
	
	private String suitName;
	private String valueName;
	private int suit;
	private int value;
	private boolean held;
	private boolean holdable = false;
	

	/**
	 * This constructor is used to create a dummy "Face down" card that doesn't have a value or suit
	 * It's used in the game at the starting screen, when the game just started but no bets were made
	 */
	public Card() {
		this.suit = DUMMYSUIT;
		this.value = DUMMYNAME;
		this.held = false;
		CardNameInitializer();
	}
	/**
	 * This is the main constructor, it gets 2 numerical values that represent the Suit and the Value
	 * of the card, to create a card. The values must be within specific range, or an exception will 
	 * be thrown.
	 * @param suit
	 * @param value
	 */
	public Card(int suit, int value) {
		this.held = false;
		if (suit > 0 && suit < 5) {
			this.suit = suit;
		}
		else {
			throw new IllegalArgumentException("Out of range: " + suit);
				
		}
		
		if (value > 1 && value < 15) {
			
			this.value = value;
		}
		else {
			throw new IllegalArgumentException("Out of range: " + value);
		}
		
		CardNameInitializer();

	}
	
	/**
	 * This method sets the names (using String) of the suit and the value
	 */
	private void CardNameInitializer() {
		switch(this.suit) {
			case 1: this.suitName = "SPADES"; break;
			case 2: this.suitName = "HEARTS"; break;
			case 3: this.suitName = "DIAMONDS"; break;
			case 4: this.suitName = "CLUBS"; break;
			case 5: this.suitName = "DUMMYSUIT"; break;
		}
		
		switch(this.value) {
			case 2: this.valueName = "TWO"; break;
			case 3: this.valueName = "THREE"; break;
			case 4: this.valueName = "FOUR"; break;
			case 5: this.valueName = "FIVE"; break;
			case 6: this.valueName = "SIX"; break;
			case 7: this.valueName = "SEVEN"; break;
			case 8: this.valueName = "EIGHT"; break;
			case 9: this.valueName = "NINE"; break;
			case 10: this.valueName = "TEN"; break;
			case 11: this.valueName = "JACK"; break;
			case 12: this.valueName = "QUEEN"; break;
			case 13: this.valueName = "KING"; break;
			case 14: this.valueName = "ACE"; break;
			case 15: this.valueName = "DUMMYNAME"; break;
			
		}
		
		
		
	}
	
	public int getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.value;
	}
	public String getSuitName() {
		return this.suitName;
	}
	public String getValueName() {
		return this.valueName;
	}
	
	
	/**
	 * 
	 * @return String(Value-Name + Suit-Name)
	 */
	public String getCardName() {
		return this.valueName + " of " + this.suitName;
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
