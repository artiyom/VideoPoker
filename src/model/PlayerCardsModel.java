package model;

/**
 * This Class represents current 5 cardModels dealt to the player. 
 * It contains the card deck and an Array of 5 card.
 * It has all the main functions of the game: deal, draw, hold, unhold.
 * Also it has ability to check if these 5 cardModels represent any Poker HandModel
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class PlayerCardsModel {
	private CardDeckModel deck;
	private CardModel[] cardModels = new CardModel[5];
	private final int CARDS = 5;
	private HandModel handModel;
	private boolean dealt = false;
	
	
	public PlayerCardsModel() {
		this.deal();
		
	}
	
	/**
	 * Obtains 5 cardModels from the deck
	 */
	public void deal() {
		this.dealt = true;
		this.deck = new CardDeckModel();
		int i = 0;
		while(i < CARDS) {
			cardModels[i] = deck.getCard();
			cardModels[i].makeHoldable();
			i++;
		}
		
		
	}
	
	/**
	 * Checks which cardModels have been held and substituates the rest of the cardModels 
	 * with new ones.
	 */
	public void draw() {
		this.dealt = false;
		int i = 0;
		while(i < CARDS) {
			cardModels[i].makeUnholdable();
			if(cardModels[i].isHeld() == false) {
				cardModels[i] = deck.getCard();
			}
			i++;
		}
	}
	
	/**
	 * Sets the card n to be hold
	 * @param n
	 */
	public void hold(int n) {
		cardModels[n-1].hold();
	}
	/**
	 * Unholds the card n
	 * @param n
	 */
	public void unhold(int n) {
		cardModels[n-1].unhold();
	}
	
	public CardModel getCard(int n) {
			return this.cardModels[n];
		
		
	}
	
	
	//debug
	public void printCards() {
		int i = 0;
		while(i < CARDS) {
			System.out.println(i+1 + ": " + cardModels[i].getCardName() + "");
			i++;
			
		}
		//System.out.println("Cards in the deck: " + deck.getNumberOfCards());
	}
	
	
	/**
	 * This method sends the 5 cardModels to the handModel object and makes it check 
	 * if there is a poker handModel.
	 * @return
	 */
	public boolean isTherePokerHand() {
		this.handModel = new HandModel(cardModels);
		//System.out.println(handModel.getHandNameStr());
		if(handModel.getHandName().getReturnFactor() > HandName.NO_HAND.getReturnFactor()) {
			//handModel.printHistagram();
			return true;
		}
		else
			return false;
	}
	
	public HandModel getHand() {
		return this.handModel;
	}

	public String getHandName() {
		return this.handModel.getHandNameStr();
	}
	
	/**
	 * This information is used during the game so no other buttons can be pressed while cardModels are dealt
	 * @return
	 */
	public boolean isDealt() {
		return this.dealt;
	}

}
