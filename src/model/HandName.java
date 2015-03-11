package model;
/**
 * Contains hand names with the return factors.
 * @author Artyom M. a.k.a. artiyom
 *
 */
public enum HandName {
	NO_HAND(0), JACKS_OR_BETTER(1), TWO_PAIR(2), THREE_OF_A_KIND(3), STRAIGHT(4), FLUSH(6),
	FULL_HOUSE(9), FOUR_OF_A_KIND(25), STRAIGHT_FLUSH(50), ROYAL_FLUSH(250);
	private int value;
	HandName(int value) {
		this.value = value;
	}
	public int getReturnFactor() {
		return value;
	}

}
