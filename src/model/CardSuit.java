package model;
/**
 * Contains Card Suit names.
 * @author Artyom M. a.k.a. artiyom
 *
 */
public enum CardSuit {
	SPADES(1), HEARTS(2), DIAMONDS(3), CLUBS(4);
	private int value;
	CardSuit(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

}
