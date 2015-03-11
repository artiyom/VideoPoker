package model;

/**
 * This Class represents current 5 cards dealt to the player. 
 * It contains the card deck and an Array of 5 card.
 * It has all the main functions of the game: deal, draw, hold, unhold.
 * Also it has ability to check if these 5 cards represent any Poker Hand
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class PlayerCards {
	private CardDeck deck;
	private Card[] cards = new Card[5];
	private final int CARDS = 5;
	private Hand hand;
	private boolean dealt = false;
	
	
	public PlayerCards() {
		this.deal();
		
	}
	
	/**
	 * Obtains 5 cards from the deck
	 */
	public void deal() {
		this.dealt = true;
		this.deck = new CardDeck();
		int i = 0;
		while(i < CARDS) {
			cards[i] = deck.getCard();
			cards[i].makeHoldable();
			i++;
		}
		
		
	}
	
	/**
	 * Checks which cards have been held and substituates the rest of the cards 
	 * with new ones.
	 */
	public void draw() {
		this.dealt = false;
		int i = 0;
		while(i < CARDS) {
			cards[i].makeUnholdable();
			if(cards[i].isHeld() == false) {
				cards[i] = deck.getCard();
			}
			i++;
		}
	}
	
	/**
	 * Sets the card n to be hold
	 * @param n
	 */
	public void hold(int n) {
		cards[n-1].hold();
	}
	/**
	 * Unholds the card n
	 * @param n
	 */
	public void unhold(int n) {
		cards[n-1].unhold();
	}
	
	public Card getCard(int n) {
			return this.cards[n];
		
		
	}
	
	
	//debug
	public void printCards() {
		int i = 0;
		while(i < CARDS) {
			System.out.println(i+1 + ": " + cards[i].getCardName() + "");
			i++;
			
		}
		//System.out.println("Cards in the deck: " + deck.getNumberOfCards());
	}
	
	
	/**
	 * This method sends the 5 cards to the hand object and makes it check 
	 * if there is a poker hand.
	 * @return
	 */
	public boolean isTherePokerHand() {
		this.hand = new Hand(cards);
		//System.out.println(hand.getHandName());
		if(hand.getReturnFactor() > 0) {
			//hand.printHistagram();
			return true;
		}
		else
			return false;
	}
	
	public Hand getHand() {
		return this.hand;
	}

	public String getHandName() {
		return this.hand.getHandName();
	}
	
	/**
	 * This information is used during the game so no other buttons can be pressed while cards are dealt
	 * @return
	 */
	public boolean isDealt() {
		return this.dealt;
	}

}
