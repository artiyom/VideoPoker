package model;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is made to determine if 5 given cardModels contain any kind of poker combination or not.
 * @author Artyom M. a.k.a. artiyom
 *
 */


public class HandModel {
	private ArrayList<CardModel> cardModels;
	private String handNameStr;
	private HandName handName;
	private boolean repeatability = false;
	private boolean flush = false;
	private boolean straight = false;
	private int[] cardHistogram = new int[13];
	
		
	/**
	 * Default constructor. Does nothing but creates "Unknown" hand. Not used in the game. 
	 */
	public HandModel() {
		this.handNameStr = "Unknown!!!";
	}
	
		
	/**
	 * Main constructor. Receives an array of 5 CardModel elements. Sorts cardModels. Creates histogram.
	 * Then checks for combinations.
	 * @param cardModels
	 */
	public HandModel(CardModel[] cards) {
		this.cardModels = new ArrayList<CardModel>(Arrays.asList(cards)); //saving the 5 cardModels to an ArrayList
		this.setHandInformatio(HandName.NO_HAND); //default value
		this.sortCards();
		this.createHistogram();
		
		
		/**
		 * Checking repeatability  - if one or more cardModels appears more than once
		 * if there is repeatability, means there cant's be a Flush
		 * if there is repeatability, means there can't be Straight
		 */
		this.checkRepeatability();
		if(this.repeatability == true) {
			return;
		}
		
		/**
		 * If code gotten to this point, means there was no repeatability among 5 cardModels.
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
		if(!(other instanceof HandModel))
			return false;
		HandModel handModel = (HandModel) other;
		
		if(handModel.getHandNameStr().equals(this.getHandNameStr()))
			return true;
		else
			return false;
		
	}
	/**
	 * This private method receives a HandName constant from local methods and sets sufficient hand name
	 * @param handName
	 */
	private void setHandInformatio(HandName handName) {
		this.handName = handName;
		switch(handName) {
			case NO_HAND:
				this.handNameStr = "No HandModel";
				break;
			case JACKS_OR_BETTER:
				this.handNameStr = "Jacks Or Better";
				break;
			case TWO_PAIR:
				this.handNameStr = "Two Pair";
				break;
			case THREE_OF_A_KIND:
				this.handNameStr = "Three of a Kind";
				break;
			case STRAIGHT:
				this.handNameStr = "Straight";
				break;
			case FLUSH:
				this.handNameStr = "Flush";
				break;
			case FULL_HOUSE:
				this.handNameStr = "Full House";
				break;
			case FOUR_OF_A_KIND:
				this.handNameStr = "Four of a Kind";
				break;
			case STRAIGHT_FLUSH:
				this.handNameStr = "Straight Flush";
				break;
			case ROYAL_FLUSH:
				this.handNameStr = "Royal Flush";
				break;
				
		}
	}

	/**
	 * Method sorts the 5 cardModels from lower rank to higher. So they are easier to operate in this class  
	 */
	private void sortCards() {
		CardModel temp;
		int i, j;
		for(i = 0; i < 5; i++) {
			for (j = 0; j < 4; j++) {
				if (cardModels.get(j).getRank().getValue() > cardModels.get(j+1).getRank().getValue()) {
					temp = cardModels.get(j);
					cardModels.set(j, cardModels.get(j+1));
					cardModels.set(j+1, temp);
				}
			}
			
		}
	}
	
	//debug
	public void printCards() {
		int i = 0;
		while(i < 5) {
			System.out.println(i+1 + ": " + cardModels.get(i).getCardName() + " ");
			i++;
		}
			
	}

	
	/**
	 * 
	 * @return HandModel's name as String.
	 */
	public String getHandNameStr() {
		return this.handNameStr;
	}
	
	/**
	 * 
	 * @return The return factor of this particular hand
	 */
	public HandName getHandName() {
		return this.handName;
	}
	
	/**
	 * Histogram is necessary to count how many times each of 5 cardModels appears in this hand
	 * It has 13 int variables each of which increases once we have a card to which it corresponds
	 */
	private void createHistogram() {
		int i = 0;
		while(i < 5) {
			switch(cardModels.get(i).getRank()) {
				case TWO: this.cardHistogram[CardRank.TWO.getValue()-2]++;
				break;
				case THREE: this.cardHistogram[CardRank.THREE.getValue()-2]++;
				break;
				case FOUR: this.cardHistogram[CardRank.FOUR.getValue()-2]++;
				break;
				case FIVE: this.cardHistogram[CardRank.FIVE.getValue()-2]++;
				break;
				case SIX: this.cardHistogram[CardRank.SIX.getValue()-2]++;
				break;
				case SEVEN: this.cardHistogram[CardRank.SEVEN.getValue()-2]++;
				break;
				case EIGHT: this.cardHistogram[CardRank.EIGHT.getValue()-2]++;
				break;
				case NINE: this.cardHistogram[CardRank.NINE.getValue()-2]++;
				break;
				case TEN: this.cardHistogram[CardRank.TEN.getValue()-2]++;
				break;
				case JACK: this.cardHistogram[CardRank.JACK.getValue()-2]++;
				break;
				case QUEEN: this.cardHistogram[CardRank.QUEEN.getValue()-2]++;
				break;
				case KING: this.cardHistogram[CardRank.KING.getValue()-2]++;
				break;
				case ACE: this.cardHistogram[CardRank.ACE.getValue()-2]++;
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
		
		if (cardModels.get(0).getSuit() == cardModels.get(1).getSuit() &&
			cardModels.get(0).getSuit() == cardModels.get(2).getSuit() &&
			cardModels.get(0).getSuit() == cardModels.get(3).getSuit() &&
			cardModels.get(0).getSuit() == cardModels.get(4).getSuit()) {
			this.setHandInformatio(HandName.FLUSH);
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
		if ((cardModels.get(4).getRank().getValue() - cardModels.get(0).getRank().getValue()) == 4) {
			this.straight = true;
		}
		else if (cardModels.get(0).getRank() == CardRank.TWO &&
				cardModels.get(1).getRank() == CardRank.THREE &&
				cardModels.get(2).getRank() == CardRank.FOUR && 
				cardModels.get(3).getRank() == CardRank.FIVE &&
				cardModels.get(4).getRank() == CardRank.ACE) {
			//after sorting, first card is two and last card is ace, and mid. cardModels are 3,4,5
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
		if (this.straight == true && this.flush == true && cardModels.get(0).getRank() == CardRank.TEN) {
			this.setHandInformatio(HandName.ROYAL_FLUSH);
		}
		//Straight flush
		else if (this.straight == true && this.flush == true) {
			this.setHandInformatio(HandName.STRAIGHT_FLUSH);
		}
		//Plain Straight
		else if (this.straight == true) {
			this.setHandInformatio(HandName.STRAIGHT);
		}
			
	}
	

	/**
	 * This method uses the HandModel Histogram to checks if any of the cardModels appears more than once.
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
				pairedCardIndex = i;//to remember the first pair card
				isTherePair = true;
				numberOfPairs++;
			}
			else if (cardHistogram[i] == 3) {
				isThereSet = true;
			}
			else if (cardHistogram[i] == 4) {
				this.setHandInformatio(HandName.FOUR_OF_A_KIND);
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
			this.setHandInformatio(HandName.FULL_HOUSE);
			this.repeatability = true;
		}
		else if(isThereSet) {
			this.setHandInformatio(HandName.THREE_OF_A_KIND);
			this.repeatability = true;
		}
		else if(isTherePair && numberOfPairs > 1) {
			this.setHandInformatio(HandName.TWO_PAIR);
			this.repeatability = true;
		}
		else if(isTherePair && pairedCardIndex+2 >= CardRank.JACK.getValue()) {
			this.setHandInformatio(HandName.JACKS_OR_BETTER);
			this.repeatability = true;
		}
		else if (isTherePair) {
			this.setHandInformatio(HandName.NO_HAND);
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
