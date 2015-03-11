package model;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is made to determine if 5 given cards contain any kind of poker combination or not.
 * @author Artyom M. a.k.a. artiyom
 *
 */


public class Hand {
	private ArrayList<Card> cards;
	private String handName;
	private int returnFactor;
	private boolean repeatability = false;
	private boolean flush = false;
	private boolean straight = false;
	private int[] cardHistogram = new int[13];
	
	//these are return factors
	//will be used when calculating the Win from each hand
	public static final int LOW_PAIR = 0;
	public static final int JACKS_OR_BETTER = 1; 
	public static final int TWO_PAIR = 2;
	public static final int THREE_OF_A_KIND = 3;
	public static final int STRAIGHT = 4;
	public static final int FLUSH = 6;
	public static final int FULL_HOUSE = 9;
	public static final int FOUR_OF_A_KIND = 25;
	public static final int STRAIGHT_FLUSH = 50;
	public static final int ROYAL_FLUSH = 250;

	
	
	/**
	 * Default constructor. Does nothing but creates "Unknown" hand. Not used in the game. 
	 */
	public Hand() {
		this.handName = "Unknown!!!";
	}
	
		
	/**
	 * Main constructor. Receives an array of 5 Card elements. Sorts cards. Creates histogram.
	 * Then checks for combinations.
	 * @param cards
	 */
	public Hand(Card[] cards) {
		this.cards = new ArrayList<Card>(Arrays.asList(cards)); //saving the 5 cards to an ArrayList
		this.handName = "No Hand"; //default value
		this.sortCards();
		this.createHistogram();
		
		
		/**
		 * Checking repeatability  - if one or more cards appears more than once
		 * if there is repeatability, means there cant's be a Flush
		 * if there is repeatability, means there can't be Straight
		 */
		this.checkRepeatability();
		if(this.repeatability == true) {
			return;
		}
		
		/**
		 * If code gotten to this point, means there was no repeatability among 5 cards.
		 * Means there is only one of each card in the game. Checking for Flush and Straight
		 */
		this.checkFlush();
		this.checkStraight();
		
	}
	
	/**
	 * The Object's equal method overridden to compare equality of 2 hands 
	 */
	@Override
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(!(other instanceof Hand))
			return false;
		Hand hand = (Hand) other;
		
		if(hand.getHandName().equals(this.getHandName()))
			return true;
		else
			return false;
		
	}
	/**
	 * This private method receives a int from local methods and sets sufficient hand name
	 * @param returnFactor
	 */
	private void setHandInformatio(int returnFactor) {
		this.returnFactor = returnFactor;
		switch(returnFactor) {
			case LOW_PAIR:
				this.handName = "Low pair";
				break;
			case JACKS_OR_BETTER:
				this.handName = "Jacks Or Better";
				break;
			case TWO_PAIR:
				this.handName = "Two Pair";
				break;
			case THREE_OF_A_KIND:
				this.handName = "Three of a Kind";
				break;
			case STRAIGHT:
				this.handName = "Straight";
				break;
			case FLUSH:
				this.handName = "Flush";
				break;
			case FULL_HOUSE:
				this.handName = "Full House";
				break;
			case FOUR_OF_A_KIND:
				this.handName = "Four of a Kind";
				break;
			case STRAIGHT_FLUSH:
				this.handName = "Straight Flush";
				break;
			case ROYAL_FLUSH:
				this.handName = "Royal Flush";
				break;
				
		}
	}

	/**
	 * Method sorts the 5 cards from lower value to higher. So they are easier to operate in this class  
	 */
	private void sortCards() {
		Card temp;
		int i, j;
		for(i = 0; i < 5; i++) {
			for (j = 0; j < 4; j++) {
				if (cards.get(j).getValue() > cards.get(j+1).getValue()) {
					temp = cards.get(j);
					cards.set(j, cards.get(j+1));
					cards.set(j+1, temp);
				}
			}
			
		}
	}
	
	//debug
	public void printCards() {
		int i = 0;
		while(i < 5) {
			System.out.println(i+1 + ": " + cards.get(i).getCardName() + " ");
			i++;
		}
			
	}

	
	/**
	 * 
	 * @return Hand's name as String.
	 */
	public String getHandName() {
		return this.handName;
	}
	
	/**
	 * 
	 * @return The return factor of this particular hand
	 */
	public int getReturnFactor() {
		return this.returnFactor;
	}
	
	//debug
	private void createHistogram() {
		int i = 0;
		while(i < 5) {
			switch(cards.get(i).getValue()) {
				case Card.TWO: this.cardHistogram[Card.TWO-2]++;
				break;
				case Card.THREE: this.cardHistogram[Card.THREE-2]++;
				break;
				case Card.FOUR: this.cardHistogram[Card.FOUR-2]++;
				break;
				case Card.FIVE: this.cardHistogram[Card.FIVE-2]++;
				break;
				case Card.SIX: this.cardHistogram[Card.SIX-2]++;
				break;
				case Card.SEVEN: this.cardHistogram[Card.SEVEN-2]++;
				break;
				case Card.EIGHT: this.cardHistogram[Card.EIGHT-2]++;
				break;
				case Card.NINE: this.cardHistogram[Card.NINE-2]++;
				break;
				case Card.TEN: this.cardHistogram[Card.TEN-2]++;
				break;
				case Card.JACK: this.cardHistogram[Card.JACK-2]++;
				break;
				case Card.QUEEN: this.cardHistogram[Card.QUEEN-2]++;
				break;
				case Card.KING: this.cardHistogram[Card.KING-2]++;
				break;
				case Card.ACE: this.cardHistogram[Card.ACE-2]++;
				break;
			}
			i++;
		}
	}
	
	/**
	 * This method checks if there is a Flush in this 5 card combination
	 * It compares 5 card's suit value with each other. And then if there is
	 * it uses setHandInformatio() method to set the presence of Flush
	 * 
	 */
	private void checkFlush() {
		
		if (cards.get(0).getSuit() == cards.get(1).getSuit() &&
			cards.get(0).getSuit() == cards.get(2).getSuit() &&
			cards.get(0).getSuit() == cards.get(3).getSuit() &&
			cards.get(0).getSuit() == cards.get(4).getSuit()) {
			this.setHandInformatio(Hand.FLUSH);
			this.flush = true;
		}
		else
			this.flush = false;
		
	}
	
	/**
	 * This method checks the presence of Straight 
	 */
	private void checkStraight() {
		
		//if hand's last card minus hand's first card = 4, it's a straight
		if ((cards.get(4).getValue() - cards.get(0).getValue()) == 4) {
			this.straight = true;
		}
		else if (cards.get(0).getValue() == Card.TWO &&
				cards.get(1).getValue() == Card.THREE &&
				cards.get(2).getValue() == Card.FOUR && 
				cards.get(3).getValue() == Card.FIVE &&
				cards.get(4).getValue() == Card.ACE) {
			//after sorting, first card is two and last card is ace, and mid. cards are 3,4,5
			//means there is straight with the Ace as the lowest card
			this.straight = true;
		}
		else
			this.straight = false;
		
		/**
		 * After it was checked if there is straight or no, we can check straight+flush combination
		 * and Royal Flush combination, and only then set the hand information
		 */
		//if both straight and flush are true and the lowest card is 10, then there is a Royal Flush
		if (this.straight == true && this.flush == true && cards.get(0).getValue() == Card.TEN) {
			this.setHandInformatio(Hand.ROYAL_FLUSH);
		}
		//Straight flush
		else if (this.straight == true && this.flush == true) {
			this.setHandInformatio(Hand.STRAIGHT_FLUSH);
		}
		//Plain Straight
		else if (this.straight == true) {
			this.setHandInformatio(Hand.STRAIGHT);
		}
			
	}
	

	/**
	 * This method uses the Hand Histogram to checks if any of the cards appears more than once.
	 * If so, which combination is it: pair, 3 of kind, four of a kind etc.
	 */
	private void checkRepeatability() {
		int i = 0;
		/**
		 * With these 4 variables we will keep track of how many pairs there are in the hand.
		 * if there are sets etc.
		 */
		int pairedCardIndex = 12; 
		int numberOfPairs = 0;
		boolean isTherePair = false;
		boolean isThereSet = false;
		
		/**
		 * We are going from the first card in the histogram (TWO) to the last card (ACE)
		 * and check for pairs, "Three of a kinds", and "four of a kinds" for each cardHistogram[i].
		 */
		while(i < 13) {
			if (cardHistogram[i] == 2) {
				pairedCardIndex = i;//to remember the first paire card
				isTherePair = true;
				numberOfPairs++;
			}
			else if (cardHistogram[i] == 3) {
				isThereSet = true;
			}
			else if (cardHistogram[i] == 4) {
				this.setHandInformatio(Hand.FOUR_OF_A_KIND);
				this.repeatability = true;
				return;
			}
			i++;
		}
		/**
		 * After all boolean variables have been set, and we have information about the hand
		 * we check for all possible combinations and then use setHandInformatio() method
		 * to set the correct name of the hand. 
		 */
		
		if (isTherePair && isThereSet) {
			this.setHandInformatio(Hand.FULL_HOUSE);
			this.repeatability = true;
		}
		else if(isThereSet) {
			this.setHandInformatio(Hand.THREE_OF_A_KIND);
			this.repeatability = true;
		}
		else if(isTherePair && numberOfPairs > 1) {
			this.setHandInformatio(Hand.TWO_PAIR);
			this.repeatability = true;
		}
		else if(isTherePair && pairedCardIndex+2 >= Card.JACK) {
			this.setHandInformatio(Hand.JACKS_OR_BETTER);
			this.repeatability = true;
		}
		else if (isTherePair) {
			this.setHandInformatio(Hand.LOW_PAIR);
			this.repeatability = true;
		}
		else {
			this.repeatability = false;
		}
		
	}
	
	
	
	//debug
	public void printHistagram() {
		int i;
		for (i = 0; i < 13; i++) {
			System.out.println(i + ": " + this.cardHistogram[i]);
		}
	}

	

}
